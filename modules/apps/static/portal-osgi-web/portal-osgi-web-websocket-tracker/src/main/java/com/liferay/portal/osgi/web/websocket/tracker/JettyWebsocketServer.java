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

package com.liferay.portal.osgi.web.websocket.tracker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.websocket.server.ServerContainer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Cristina González
 */
@Component(immediate = true, service = JettyWebsocketServer.class)
public class JettyWebsocketServer {

	public ServerContainer getServerContainer() {
		return _serverContainer;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		Runnable runJetty = new Runnable() {

			@Override
			public void run() {
				try {
					_server = new Server(_PORT);

					ServletContextHandler context = new ServletContextHandler(
						ServletContextHandler.SESSIONS);

					context.setContextPath("/");

					_server.setHandler(context);

					_serverContainer =
						WebSocketServerContainerInitializer.configureContext(
							context);

					_server.start();
					_server.join();

					if (_log.isInfoEnabled()) {
						_log.info(
							"The Websocket Jetty Server is running in the " +
								"port " + _PORT);
					}
				}
				catch (Exception e) {
					_log.error(
						"The Jetty Server can't be started in the _PORT" +
							_PORT,
						e);
				}
			}

		};

		new Thread(runJetty).start();
	}

	@Deactivate
	protected void deactivate() throws Exception {
		_server.stop();
		_server.join();
	}

	private static final int _PORT = 8081;

	private static final Log _log = LogFactoryUtil.getLog(
		JettyWebsocketServer.class);

	private Server _server;
	private ServerContainer _serverContainer;

}