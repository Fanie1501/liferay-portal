/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.arquillian.transactional.extension.enricher;

import com.liferay.arquillian.transactional.extension.container.TransactionalRemoteExtension;
import com.liferay.arquillian.transactional.extension.observer.TransactionalObserver;
import com.liferay.arquillian.transactional.extension.util.TransactionalUtil;
import org.jboss.arquillian.container.test.spi.RemoteLoadableExtension;
import org.jboss.arquillian.container.test.spi.client.deployment.AuxiliaryArchiveAppender;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author Carlos Sierra Andrés
 */
public class TransactionalLiferayAuxiliaryAppender implements AuxiliaryArchiveAppender {

	@Override
	public Archive<?> createAuxiliaryArchive() {

		JavaArchive archive = ShrinkWrap.create(
			JavaArchive.class, "arquillian-liferay-transactional.jar");

		File file = new File("/tmp/MANIFEST.MF");

		try {
			file.createNewFile();

			FileOutputStream fop = new FileOutputStream(file);

			String manifest_text = "Manifest-Version: 1.0\n" +
				"Bnd-LastModified: " + new Date().getTime() + "\n" +
				"Bundle-ManifestVersion: 2\n" +
				"Bundle-Name: Liferay Arquillian Transactional Extension\n" +
				"Bundle-SymbolicName: com.liferay.arquillian.transactional.extension\n" +
				"Bundle-Vendor: Liferay, Inc.\n" +
				"Bundle-Version: 1.0.0\n" +
				"Import-Package: com.liferay.portal.kernel.util,com.liferay.portal.test.j\n" +
				" dbc,com.liferay.portal.util,org.jboss.arquillian.core.api,org.jboss.arq\n" +
				" uillian.core.api.annotation,org.jboss.arquillian.core.spi,org.jboss.arq\n" +
				" uillian.core.spi.event,org.jboss.arquillian.test.spi,org.jboss.arquilli\n" +
				" an.test.spi.event.suite\n" +
				"Include-Resource: classes\n";

			fop.write(manifest_text.getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		}

		archive.addAsManifestResource(file);

		archive.addAsServiceProvider(
			RemoteLoadableExtension.class,
			TransactionalRemoteExtension.class);

		archive.addPackages(true, TransactionalRemoteExtension.class.getPackage(), TransactionalObserver.class.getPackage(), TransactionalUtil.class.getPackage());

		return archive;
	}
}
