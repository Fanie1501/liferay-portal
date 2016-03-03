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

package com.liferay.portal.upgrade.v5_2_7_to_6_0_0;

/**
 * @author Cristina González
 */
public class UpgradeMVCCVersion
	extends com.liferay.portal.upgrade.util.UpgradeMVCCVersion {

	@Override
	protected String[] getExcludedTableNames() {
		return new String[] {
			"BackgroundTask", "ClusterGroup", "ExportImportConfiguration",
			"LayoutBranch", "LayoutFriendlyURL", "LayoutRevision",
			"LayoutSetBranch", "PortalPreferences", "RecentLayoutBranch",
			"RecentLayoutRevision", "RecentLayoutSetBranch", "Repository",
			"RepositoryEntry", "ResourceBlock", "ResourceBlockPermission",
			"ResourceTypePermission", "SystemEvent", "Team", "Ticket",
			"UserNotificationDelivery", "UserNotificationEvent", "VirtualHost"
		};
	}

}