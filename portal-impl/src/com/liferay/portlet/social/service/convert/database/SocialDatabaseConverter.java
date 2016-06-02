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

package com.liferay.portlet.social.service.convert.database;

import com.liferay.portal.convert.database.DatabaseConverter;
import com.liferay.portal.convert.util.ModelMigrator;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.model.BaseModel;

import com.liferay.portlet.social.model.impl.SocialActivityAchievementImpl;
import com.liferay.portlet.social.model.impl.SocialActivityCounterImpl;
import com.liferay.portlet.social.model.impl.SocialActivityImpl;
import com.liferay.portlet.social.model.impl.SocialActivityLimitImpl;
import com.liferay.portlet.social.model.impl.SocialActivitySetImpl;
import com.liferay.portlet.social.model.impl.SocialActivitySettingImpl;
import com.liferay.portlet.social.model.impl.SocialRelationImpl;
import com.liferay.portlet.social.model.impl.SocialRequestImpl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SocialDatabaseConverter implements DatabaseConverter {
	@Override
	public void convert(DataSource dataSource) throws Exception {
		_modelMigrator.migrate(dataSource, _getModels());
	}

	public void setModelMigrator(ModelMigrator modelMigrator) {
		_modelMigrator = modelMigrator;
	}

	private List<Class<?extends BaseModel<?>>> _getModels() {
		List<Class<?extends BaseModel<?>>> models = new ArrayList<Class<?extends BaseModel<?>>>();

		models.add(SocialActivityImpl.class);
		models.add(SocialActivityAchievementImpl.class);
		models.add(SocialActivityCounterImpl.class);
		models.add(SocialActivityLimitImpl.class);
		models.add(SocialActivitySetImpl.class);
		models.add(SocialActivitySettingImpl.class);
		models.add(SocialRelationImpl.class);
		models.add(SocialRequestImpl.class);

		return models;
	}

	@BeanReference(type = ModelMigrator.class)
	private ModelMigrator _modelMigrator;
}