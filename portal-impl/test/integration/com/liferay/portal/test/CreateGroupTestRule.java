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

import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.test.GroupTestUtil;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristina González
 */
public class CreateGroupTestRule implements TestRule {

	public CreateGroupTestRule(Object instance) {
		super();

		_instance = instance;
	}

	private void before(Description description) throws Exception {
		fieldBag = new ArrayList<Field>();

		Class<?> testClass = description.getTestClass();

		for (Field field : testClass.getFields()) {
			if(field.isAnnotationPresent(CreateGroup.class)){
				field.set(_instance, GroupTestUtil.addGroup());

				fieldBag.add(field);
			}
		}
	}

	private void after() throws Exception {
		for (Field field : fieldBag) {
			GroupLocalServiceUtil.deleteGroup((Group) field.get(_instance));
		}
	}

	@Override
	public Statement apply(
		final Statement statement, final Description description) {

		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				before(description);
				try {
					statement.evaluate();
				}
				finally {
					after();
				}
			}
		};
	}

	private Object _instance;

	private List<Field> fieldBag;
}
