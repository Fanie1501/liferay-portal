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
package activator;

import org.osgi.framework.BundleContext;
import prueba.Prueba;

/**
 * @author Cristina González
 */
public class BundleActivator  implements org.osgi.framework.BundleActivator {
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Staring");

		new Prueba().estoy();

		try{
			getClass().getClassLoader().loadClass("org.jboss.arquillian.core.spi.event.Event");

			System.out.println("OKOKOK");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stoping");
	}
}
