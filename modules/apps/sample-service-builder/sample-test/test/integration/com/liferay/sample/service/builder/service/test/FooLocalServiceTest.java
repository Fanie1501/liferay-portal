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
package com.liferay.sample.service.builder.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.sample.service.builder.model.Foo;
import com.liferay.sample.service.builder.service.FooLocalServiceUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class FooLocalServiceTest {

	@ClassRule
	@Rule
	public static LiferayIntegrationTestRule liferayInegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddFoo() {
		long fooId = CounterLocalServiceUtil.increment();

		int initial = FooLocalServiceUtil.getFoosCount();

		Foo foo = FooLocalServiceUtil.createFoo(fooId);

		foo.setGroupId(_group.getGroupId());

		Assert.assertNotNull(foo);

		FooLocalServiceUtil.addFoo(foo);

		int actual = FooLocalServiceUtil.getFoosCount();

		Assert.assertEquals(initial + 1, actual);
	}

	@DeleteAfterTestRun
	private Group _group;

}
