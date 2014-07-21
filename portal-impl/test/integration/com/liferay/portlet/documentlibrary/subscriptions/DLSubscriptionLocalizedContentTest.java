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

package com.liferay.portlet.documentlibrary.subscriptions;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousMailExecutionTestListener;
import com.liferay.portal.test.listeners.MainServletExecutionTestListener;
import com.liferay.portal.test.rule.DeleteAfterTestRunRule;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.subscriptions.BaseSubscriptionLocalizedContentTestCase;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLConstants;
import com.liferay.portlet.documentlibrary.util.test.DLAppTestUtil;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousMailExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class DLSubscriptionLocalizedContentTest
	extends BaseSubscriptionLocalizedContentTestCase {

	@Rule
	public DeleteAfterTestRunRule deleteAfterTestRunRule =
		new DeleteAfterTestRunRule(this);

	@Override
	protected long addBaseModel(long containerModelId) throws Exception {
		FileEntry fileEntry = DLAppTestUtil.addFileEntryWithWorkflow(
			group.getGroupId(), group.getGroupId(), containerModelId, true);

		return fileEntry.getFileEntryId();
	}

	@Override
	protected void addSubscriptionContainerModel(long containerModelId)
		throws Exception {

		DLAppLocalServiceUtil.subscribeFolder(
			TestPropsValues.getUserId(), group.getGroupId(), containerModelId);
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.DOCUMENT_LIBRARY;
	}

	@Override
	protected String getSubscriptionBodyPreferenceName() throws Exception {
		return "emailFileEntryAddedBody";
	}

	@Override
	protected void setAddBaseModelSubscriptionBodyPreferences()
		throws Exception {

		Settings settings = SettingsFactoryUtil.getGroupServiceSettings(
			group.getGroupId(), DLConstants.SERVICE_NAME);

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		String germanSubscriptionBodyPreferencesKey =
			LocalizationUtil.getPreferencesKey(
				getSubscriptionBodyPreferenceName(),
				LocaleUtil.toLanguageId(LocaleUtil.GERMANY));

		modifiableSettings.setValue(
			germanSubscriptionBodyPreferencesKey, GERMAN_BODY);

		String spanishSubscriptionBodyPreferencesKey =
			LocalizationUtil.getPreferencesKey(
				getSubscriptionBodyPreferenceName(),
				LocaleUtil.toLanguageId(LocaleUtil.SPAIN));

		modifiableSettings.setValue(
			spanishSubscriptionBodyPreferencesKey, SPANISH_BODY);

		modifiableSettings.store();
	}

}