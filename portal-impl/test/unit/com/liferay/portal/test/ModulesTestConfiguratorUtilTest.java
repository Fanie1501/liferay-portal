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

package com.liferay.portal.test;

import java.util.List;

import org.junit.Test;

import org.testng.Assert;

/**
 * @author Cristina González
 */
public class ModulesTestConfiguratorUtilTest {

	@Test
	public void testShouldHaveNoModulesAtAll() throws Exception {
		List<String> modules = ModulesTestConfiguratorUtil.getModules(
			NoModulesClass.class);

		Assert.assertEquals(0, modules.size());
	}

	@Test
	public void testShouldHaveOneModule() throws Exception {
		List<String> modules = ModulesTestConfiguratorUtil.getModules(
			OneModuleClass.class);

		Assert.assertEquals(1, modules.size());
	}

	@Test
	public void testShouldHaveOneModuleButIsWritenAsTwo() throws Exception {
		List<String> modules =
			ModulesTestConfiguratorUtil.getModules(
				OneModuleWritenAsTwoClass.class);

		Assert.assertEquals(1, modules.size());
	}

	@Test
	public void testShouldHaveTwoModules() throws Exception {
		List<String> modules = ModulesTestConfiguratorUtil.getModules(
			TwoModulesClass.class);

		Assert.assertEquals(2, modules.size());
	}

	@ModulesTestConfigurator(modules = {})
	private class NoModulesClass {
	}

	@ModulesTestConfigurator(modules = "module1")
	private class OneModuleClass {
	}

	@ModulesTestConfigurator(modules = {"module1,module2"})
	private class OneModuleWritenAsTwoClass {
	}

	@ModulesTestConfigurator(modules = {"module1","module2"})
	private class TwoModulesClass {
	}

}