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

package com.liferay.portal.spring;

import java.lang.reflect.Field;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.aop.framework.AdvisedSupport;

/**
 * @author Shuyang Zhou
 */
public class SpringCompatibilityTest {

	@Test
	public void testAbstractAutowireCapableBeanFactory() throws Exception {
		Class<?> AbstractAutowireCapableBeanFactoryClass = null;

		AbstractAutowireCapableBeanFactoryClass = Class.forName(
			"org.springframework.beans.factory.support." +
				"AbstractAutowireCapableBeanFactory");

		Field filteredPropertyDescriptorsCacheField = null;

		filteredPropertyDescriptorsCacheField =
			AbstractAutowireCapableBeanFactoryClass.getDeclaredField(
				"filteredPropertyDescriptorsCache");

		Class<?> filteredPropertyDescriptorsCacheClass =
			filteredPropertyDescriptorsCacheField.getType();

		if (!Map.class.isAssignableFrom(
				filteredPropertyDescriptorsCacheClass)) {

			Assert.fail(
				filteredPropertyDescriptorsCacheClass.getClass().getName() +
					" is not " + Map.class.getName());
		}
	}

	@Test
	public void testAspectJExpressionPointcut() throws Exception {
		Class<?> aspectJExpressionPointcutClass = Class.forName(
			"org.springframework.aop.aspectj.AspectJExpressionPointcut");

		Field shadowMatchCacheField = null;

		shadowMatchCacheField = aspectJExpressionPointcutClass.getDeclaredField(
			"shadowMatchCache");

		Class<?> shadowMatchCacheClass = shadowMatchCacheField.getType();

		if (!Map.class.isAssignableFrom(shadowMatchCacheClass)) {
			Assert.fail(
				shadowMatchCacheClass.getClass().getName() + " is not " +
					Map.class.getName());
		}
	}

	@Test
	public void testJdkDynamicAopProxy() throws Exception {
		Class<?> jdkDynamicAopProxyClass = Class.forName(
			"org.springframework.aop.framework.JdkDynamicAopProxy");

		Field advisedField = jdkDynamicAopProxyClass.getDeclaredField(
			"advised");

		Class<?> advisedSupportClass = advisedField.getType();

		if (!advisedSupportClass.equals(AdvisedSupport.class)) {
			Assert.fail(
				advisedSupportClass.getClass().getName() + " is not " +
					AdvisedSupport.class.getName());
		}
	}

}