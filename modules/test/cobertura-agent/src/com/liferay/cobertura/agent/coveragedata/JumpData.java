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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Cristina González
 */
public class JumpData implements BranchCoverageData, Comparable, Serializable,
		HasBeenInstrumented {

	public JumpData(int conditionNumber) {
		this.conditionNumber = conditionNumber;
		this.trueHits = 0L;
		this.falseHits = 0L;
		initLock();
	}

	public int compareTo(Object o) {
		if (!o.getClass().equals(JumpData.class)) {
			return Integer.MAX_VALUE;
		}

		return this.conditionNumber - ((JumpData)o).conditionNumber;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || !obj.getClass().equals(this.getClass())) {
			return false;
		}

		JumpData branchData = (JumpData)obj;
		getBothLocks(branchData);

		try {
			return (this.trueHits == branchData.trueHits) &&
					 (this.falseHits == branchData.falseHits) &&
					 (this.conditionNumber == branchData.conditionNumber);
		}
		finally {
			lock.unlock();
			branchData.lock.unlock();
		}
	}

	public double getBranchCoverageRate() {
		lock.lock();

		try {
			return (double)getNumberOfCoveredBranches() /
				getNumberOfValidBranches();
		}
		finally {
			lock.unlock();
		}
	}

	public int getConditionNumber() {
		return this.conditionNumber;
	}

	public long getFalseHits() {
		lock.lock();

		try {
			return this.falseHits;
		}
		finally {
			lock.unlock();
		}
	}

	public int getNumberOfCoveredBranches() {
		lock.lock();

		try {
			return ((trueHits > 0) ? 1 : 0) + ((falseHits > 0) ? 1: 0);
		}
		finally {
			lock.unlock();
		}
	}

	public int getNumberOfValidBranches() {
		return 2;
	}

	public long getTrueHits() {
		lock.lock();

		try {
			return this.trueHits;
		}
		finally {
			lock.unlock();
		}
	}

	public int hashCode() {
		return this.conditionNumber;
	}

	public void merge(BranchCoverageData coverageData) {
		JumpData jumpData = (JumpData)coverageData;
		getBothLocks(jumpData);

		try {
			this.trueHits += jumpData.trueHits;
			this.falseHits += jumpData.falseHits;
		}
		finally {
			lock.unlock();
			jumpData.lock.unlock();
		}
	}

	public void touchBranch(boolean branch, int new_hits) {
		lock.lock();

		try {
			if (branch) {
				this.trueHits+= new_hits;
			}
			else {
				this.falseHits+= new_hits;
			}
		}
		finally {
			lock.unlock();
		}
	}

	protected transient Lock lock;

	private void getBothLocks(JumpData other) {
		/*
		 * To prevent deadlock, we need to get both locks or none at all.
		 *
		 * When this method returns, the thread will have both locks.
		 * Make sure you unlock them!
		 */
		boolean myLock = false;

		boolean otherLock = false;

		while (!myLock || !otherLock) {
			try {
				myLock = lock.tryLock();
				otherLock = other.lock.tryLock();
			}
			finally {
				if (!myLock || !otherLock) {
					//could not obtain both locks - so unlock the one we got.

					if (myLock) {
						lock.unlock();
					}

					if (otherLock) {
						other.lock.unlock();
					}
					//do a yield so the other threads will get to work.
					Thread.yield();
				}
			}
		}
	}

	private void initLock() {
		lock = new ReentrantLock();
	}

	private void readObject(ObjectInputStream in)
		throws ClassNotFoundException, IOException {

		in.defaultReadObject();
		initLock();
	}

	private static final long serialVersionUID = 8;

	private int conditionNumber;
	private long falseHits;
	private long trueHits;

}