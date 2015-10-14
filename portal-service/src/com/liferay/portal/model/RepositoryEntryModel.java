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

import java.util.Date;

/**
 * The base model interface for the RepositoryEntry service. Represents a row in the &quot;RepositoryEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.RepositoryEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.RepositoryEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryEntry
 * @see com.liferay.portal.model.impl.RepositoryEntryImpl
 * @see com.liferay.portal.model.impl.RepositoryEntryModelImpl
 * @generated
 */
@ProviderType
public interface RepositoryEntryModel extends BaseModel<RepositoryEntry>,
	MVCCModel, PartitionableModel, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a repository entry model instance should use the {@link RepositoryEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this repository entry.
	 *
	 * @return the primary key of this repository entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this repository entry.
	 *
	 * @param primaryKey the primary key of this repository entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the company ID of this repository entry.
	 *
	 * @return the company ID of this repository entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this repository entry.
	 *
	 * @param companyId the company ID of this repository entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the mvcc version of this repository entry.
	 *
	 * @return the mvcc version of this repository entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this repository entry.
	 *
	 * @param mvccVersion the mvcc version of this repository entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this repository entry.
	 *
	 * @return the uuid of this repository entry
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this repository entry.
	 *
	 * @param uuid the uuid of this repository entry
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the repository entry ID of this repository entry.
	 *
	 * @return the repository entry ID of this repository entry
	 */
	public long getRepositoryEntryId();

	/**
	 * Sets the repository entry ID of this repository entry.
	 *
	 * @param repositoryEntryId the repository entry ID of this repository entry
	 */
	public void setRepositoryEntryId(long repositoryEntryId);

	/**
	 * Returns the group ID of this repository entry.
	 *
	 * @return the group ID of this repository entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this repository entry.
	 *
	 * @param groupId the group ID of this repository entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the user ID of this repository entry.
	 *
	 * @return the user ID of this repository entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this repository entry.
	 *
	 * @param userId the user ID of this repository entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this repository entry.
	 *
	 * @return the user uuid of this repository entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this repository entry.
	 *
	 * @param userUuid the user uuid of this repository entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this repository entry.
	 *
	 * @return the user name of this repository entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this repository entry.
	 *
	 * @param userName the user name of this repository entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this repository entry.
	 *
	 * @return the create date of this repository entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this repository entry.
	 *
	 * @param createDate the create date of this repository entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this repository entry.
	 *
	 * @return the modified date of this repository entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this repository entry.
	 *
	 * @param modifiedDate the modified date of this repository entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the repository ID of this repository entry.
	 *
	 * @return the repository ID of this repository entry
	 */
	public long getRepositoryId();

	/**
	 * Sets the repository ID of this repository entry.
	 *
	 * @param repositoryId the repository ID of this repository entry
	 */
	public void setRepositoryId(long repositoryId);

	/**
	 * Returns the mapped ID of this repository entry.
	 *
	 * @return the mapped ID of this repository entry
	 */
	@AutoEscape
	public String getMappedId();

	/**
	 * Sets the mapped ID of this repository entry.
	 *
	 * @param mappedId the mapped ID of this repository entry
	 */
	public void setMappedId(String mappedId);

	/**
	 * Returns the manual check in required of this repository entry.
	 *
	 * @return the manual check in required of this repository entry
	 */
	public boolean getManualCheckInRequired();

	/**
	 * Returns <code>true</code> if this repository entry is manual check in required.
	 *
	 * @return <code>true</code> if this repository entry is manual check in required; <code>false</code> otherwise
	 */
	public boolean isManualCheckInRequired();

	/**
	 * Sets whether this repository entry is manual check in required.
	 *
	 * @param manualCheckInRequired the manual check in required of this repository entry
	 */
	public void setManualCheckInRequired(boolean manualCheckInRequired);

	/**
	 * Returns the last publish date of this repository entry.
	 *
	 * @return the last publish date of this repository entry
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this repository entry.
	 *
	 * @param lastPublishDate the last publish date of this repository entry
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

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
	public int compareTo(
		com.liferay.portal.model.RepositoryEntry repositoryEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.RepositoryEntry> toCacheModel();

	@Override
	public com.liferay.portal.model.RepositoryEntry toEscapedModel();

	@Override
	public com.liferay.portal.model.RepositoryEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}