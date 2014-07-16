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
package com.liferay.portal.test;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Cristina González
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class TestCreateGroupTestRuleTest
	extends BaseTestCreateGroupTestRuleTestCase{

	@BeforeClass
	public static void setUp() {
		_initialCount = GroupLocalServiceUtil.getGroupsCount();
	}

	@AfterClass
	public static void tearDown() {
		Assert.assertEquals(
			_initialCount, GroupLocalServiceUtil.getGroupsCount());
	}

	@Rule
	public CreateGroupTestRule _createGroupTestRule =
		new CreateGroupTestRule(this);

	@Test
	public void dummyTest() {
		Assert.assertNotNull(groupBase);
		Assert.assertNotNull(group1);
		Assert.assertNotNull(group2);
		Assert.assertNull(group3);
	}

	@CreateGroup
	public Group group1;

	@CreateGroup
	public Group group2;

	public Group group3;

	private static int _initialCount;

}
