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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the UserIdMapper service. Represents a row in the &quot;UserIdMapper&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.UserIdMapperModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.UserIdMapperImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserIdMapper
 * @see com.liferay.portal.model.impl.UserIdMapperImpl
 * @see com.liferay.portal.model.impl.UserIdMapperModelImpl
 * @generated
 */
@ProviderType
public interface UserIdMapperModel extends BaseModel<UserIdMapper>, MVCCModel,
	PartitionableModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user ID mapper model instance should use the {@link UserIdMapper} interface instead.
	 */

	/**
	 * Returns the primary key of this user ID mapper.
	 *
	 * @return the primary key of this user ID mapper
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this user ID mapper.
	 *
	 * @param primaryKey the primary key of this user ID mapper
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this user ID mapper.
	 *
	 * @return the mvcc version of this user ID mapper
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this user ID mapper.
	 *
	 * @param mvccVersion the mvcc version of this user ID mapper
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the user ID mapper ID of this user ID mapper.
	 *
	 * @return the user ID mapper ID of this user ID mapper
	 */
	public long getUserIdMapperId();

	/**
	 * Sets the user ID mapper ID of this user ID mapper.
	 *
	 * @param userIdMapperId the user ID mapper ID of this user ID mapper
	 */
	public void setUserIdMapperId(long userIdMapperId);

	/**
	 * Returns the user ID of this user ID mapper.
	 *
	 * @return the user ID of this user ID mapper
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this user ID mapper.
	 *
	 * @param userId the user ID of this user ID mapper
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this user ID mapper.
	 *
	 * @return the user uuid of this user ID mapper
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this user ID mapper.
	 *
	 * @param userUuid the user uuid of this user ID mapper
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the type of this user ID mapper.
	 *
	 * @return the type of this user ID mapper
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this user ID mapper.
	 *
	 * @param type the type of this user ID mapper
	 */
	public void setType(String type);

	/**
	 * Returns the description of this user ID mapper.
	 *
	 * @return the description of this user ID mapper
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this user ID mapper.
	 *
	 * @param description the description of this user ID mapper
	 */
	public void setDescription(String description);

	/**
	 * Returns the external user ID of this user ID mapper.
	 *
	 * @return the external user ID of this user ID mapper
	 */
	@AutoEscape
	public String getExternalUserId();

	/**
	 * Sets the external user ID of this user ID mapper.
	 *
	 * @param externalUserId the external user ID of this user ID mapper
	 */
	public void setExternalUserId(String externalUserId);

	/**
	 * Returns the company ID of this user ID mapper.
	 *
	 * @return the company ID of this user ID mapper
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this user ID mapper.
	 *
	 * @param companyId the company ID of this user ID mapper
	 */
	@Override
	public void setCompanyId(long companyId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(com.liferay.portal.model.UserIdMapper userIdMapper);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.UserIdMapper> toCacheModel();

	@Override
	public com.liferay.portal.model.UserIdMapper toEscapedModel();

	@Override
	public com.liferay.portal.model.UserIdMapper toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}