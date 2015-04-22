/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.cobertura.agent.coveragedata;

import java.io.File;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sourceforge.cobertura.util.FileLocker;

/**
 * @author Cristina González
 */
public class ProjectData
	extends CoverageDataContainer implements HasBeenInstrumented {

	public static void initialize() {

		// Hack for Tomcat - by saving project data right now we force loading
		// of classes involved in this process (like ObjectOutputStream)
		// so that it won't be necessary to load them on JVM shutdown

		if (System.getProperty("catalina.home") != null) {
			saveGlobalProjectData();

			// Force the class loader to load some classes that are
			// required by our JVM shutdown hook.
			// TODO: Use ClassLoader.loadClass("whatever"); instead

			ClassData.class.toString();
			CoverageData.class.toString();
			CoverageDataContainer.class.toString();
			FileLocker.class.toString();
			HasBeenInstrumented.class.toString();
			LineData.class.toString();
			PackageData.class.toString();
			SourceFileData.class.toString();
		}

		// Add a hook to save the data when the JVM exits

		Runtime.getRuntime().addShutdownHook(new Thread(new SaveTimer()));

		// Possibly also save the coverage data every x seconds?

		//Timer timer = new Timer(true);
		//timer.schedule(saveTimer, 100);
	}

	public static void saveGlobalProjectData() {
		ProjectData projectDataToSave = new ProjectData();

		TouchCollector.applyTouchesOnProjectData(projectDataToSave);

		// Get a file lock

		File dataFile = CoverageDataFileHandler.getDefaultDataFile();

		/*
		 * A note about the next synchronized block:  Cobertura uses static
		 * fields to hold the data.   When there are multiple classloaders,
		 * each classloader will keep track of the line counts for the classes
		 * that it loads.
		 *
		 * The static initializers for the Cobertura classes are also called for
		 * each classloader.   So, there is one shutdown hook for each
		 * classloader. So, when the JVM exits, each shutdown hook will try to
		 * write the data it has kept to the datafile. They will do this at
		 * the same time.   Before Java 6, this seemed to work fine, but with
		 * Java 6, there seems to have been a change with how file locks are
		 * implemented.   So, care has to be taken to make sure only one thread
		 * locks a file at a time.
		 *
		 * So, we will synchronize on the string that represents the path to the
		 * dataFile.  Apparently, there will be only one of these in the JVM
		 * even if there are multiple classloaders.  I assume that is because
		 * the String class is loaded by the JVM's root classloader.
		 */
		synchronized (dataFile.getPath().intern() ) {
			FileLocker fileLocker = new FileLocker(dataFile);

			try {

				// Read the old data, merge our current data into it, then
				// write a new ser file.

				if (fileLocker.lock()) {
					ProjectData datafileProjectData =
						loadCoverageDataFromDatafile(dataFile);

					if (datafileProjectData == null) {
						datafileProjectData = projectDataToSave;
					}
					else {
						datafileProjectData.merge(projectDataToSave);
					}

					CoverageDataFileHandler.saveCoverageData(
						datafileProjectData, dataFile);
				}
			}

			finally {

				// Release the file lock

				fileLocker.release();
			}
		}
	}

	public void addClassData(ClassData classData) {
		lock.lock();

		try {
			String packageName = classData.getPackageName();
			PackageData packageData = (PackageData)children.get(packageName);

			if (packageData == null) {
				packageData = new PackageData(packageName);

				// Each key is a package name, stored as an String object.
				// Each value is information about the package, stored as a
				// PackageData object.

				this.children.put(packageName, packageData);
			}

			packageData.addClassData(classData);
			this.classes.put(classData.getName(), classData);
		}
		finally {
			lock.unlock();
		}
	}

	public ClassData getClassData(String name) {
		lock.lock();

		try {
			return (ClassData)this.classes.get(name);
		}
		finally {
			lock.unlock();
		}
	}

	public Collection getClasses() {
		lock.lock();

		try {
			return this.classes.values();
		}
		finally {
			lock.unlock();
		}
	}

	public int getNumberOfClasses() {
		lock.lock();

		try {
			return this.classes.size();
		}
		finally {
			lock.unlock();
		}
	}

	public int getNumberOfSourceFiles() {
		return getSourceFiles().size();
	}

	/**
	 * This is called by instrumented bytecode.
	 */
	public ClassData getOrCreateClassData(String name) {
		lock.lock();

		try {
			ClassData classData = (ClassData)this.classes.get(name);

			if (classData == null) {
				classData = new ClassData(name);
				addClassData(classData);
			}

			return classData;
		}
		finally {
			lock.unlock();
		}
	}

	public SortedSet getPackages() {
		lock.lock();

		try {
			return new TreeSet(this.children.values());
		}
		finally {
			lock.unlock();
		}
	}

	public Collection getSourceFiles() {
		SortedSet sourceFileDatas = new TreeSet();
		lock.lock();

		try {
			Iterator iter = this.children.values().iterator();

			while (iter.hasNext()) {
				PackageData packageData = (PackageData)iter.next();
				sourceFileDatas.addAll(packageData.getSourceFiles());
			}
		}
		finally {
			lock.unlock();
		}

		return sourceFileDatas;
	}

	public SortedSet getSubPackages(String packageName) {
		SortedSet subPackages = new TreeSet();
		lock.lock();

		try {
			Iterator iter = this.children.values().iterator();

			while (iter.hasNext()) {
				PackageData packageData = (PackageData)iter.next();

				if (packageData.getName().startsWith(packageName + ".") ||
				 packageData.getName().equals(packageName) ||
				 packageName.equals(""))subPackages.add(packageData);
			}
		}
		finally {
			lock.unlock();
		}

		return subPackages;
	}

	public void merge(CoverageData coverageData) {
		if (coverageData == null) {
			return;
		}

		ProjectData projectData = (ProjectData)coverageData;
		getBothLocks(projectData);

		try {
			super.merge(coverageData);

			for (Iterator iter =
				projectData.classes.keySet().iterator(); iter.hasNext();) {

				Object key = iter.next();

				if (!this.classes.containsKey(key)) {
					this.classes.put(key, projectData.classes.get(key));
				}
			}
		}
		finally {
			lock.unlock();
			projectData.lock.unlock();
		}
	}

	public void setClasses(Map classes) {
		this.classes = classes;
	}

	private static ProjectData loadCoverageDataFromDatafile(File dataFile) {
		ProjectData projectData = null;

		// Read projectData from the serialized file.

		if (dataFile.isFile()) {
			projectData = CoverageDataFileHandler.loadCoverageData(dataFile);
		}

		if (projectData == null) {

			// We could not read from the serialized file, so use a new object.

			System.out.println(
				"Cobertura: Coverage data file " + dataFile.getAbsolutePath() +
					" either does not exist or is not readable." +
					" Creating a new data file.");
		}

		return projectData;
	}

	private static final long serialVersionUID = 6;

	/** This collection is used for quicker access to the list of classes. */
	private Map classes = new HashMap();

}