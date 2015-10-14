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

package com.liferay.portal.workflow.kaleo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskModel;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the KaleoTask service. Represents a row in the &quot;KaleoTask&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link KaleoTaskModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoTaskImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskImpl
 * @see KaleoTask
 * @see KaleoTaskModel
 * @generated
 */
@ProviderType
public class KaleoTaskModelImpl extends BaseModelImpl<KaleoTask>
	implements KaleoTaskModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo task model instance should use the {@link KaleoTask} interface instead.
	 */
	public static final String TABLE_NAME = "KaleoTask";
	public static final Object[][] TABLE_COLUMNS = {
			{ "companyId", Types.BIGINT },
			{ "kaleoTaskId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "kaleoDefinitionId", Types.BIGINT },
			{ "kaleoNodeId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoTaskId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNodeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table KaleoTask (companyId LONG,kaleoTaskId LONG not null primary key,groupId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoDefinitionId LONG,kaleoNodeId LONG,name VARCHAR(200) null,description STRING null)";
	public static final String TABLE_SQL_DROP = "drop table KaleoTask";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoTask.kaleoTaskId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY KaleoTask.kaleoTaskId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTask"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTask"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTask"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long KALEODEFINITIONID_COLUMN_BITMASK = 2L;
	public static final long KALEONODEID_COLUMN_BITMASK = 4L;
	public static final long KALEOTASKID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoTask"));

	public KaleoTaskModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoTaskId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoTaskId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoTaskId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTask.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTask.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("companyId", getCompanyId());
		attributes.put("kaleoTaskId", getKaleoTaskId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long kaleoTaskId = (Long)attributes.get("kaleoTaskId");

		if (kaleoTaskId != null) {
			setKaleoTaskId(kaleoTaskId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNodeId = (Long)attributes.get("kaleoNodeId");

		if (kaleoNodeId != null) {
			setKaleoNodeId(kaleoNodeId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	@Override
	public void setKaleoTaskId(long kaleoTaskId) {
		_columnBitmask = -1L;

		_kaleoTaskId = kaleoTaskId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_columnBitmask |= KALEODEFINITIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionId) {
			_setOriginalKaleoDefinitionId = true;

			_originalKaleoDefinitionId = _kaleoDefinitionId;
		}

		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public long getOriginalKaleoDefinitionId() {
		return _originalKaleoDefinitionId;
	}

	@Override
	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		_columnBitmask |= KALEONODEID_COLUMN_BITMASK;

		if (!_setOriginalKaleoNodeId) {
			_setOriginalKaleoNodeId = true;

			_originalKaleoNodeId = _kaleoNodeId;
		}

		_kaleoNodeId = kaleoNodeId;
	}

	public long getOriginalKaleoNodeId() {
		return _originalKaleoNodeId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			KaleoTask.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoTask toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (KaleoTask)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoTaskImpl kaleoTaskImpl = new KaleoTaskImpl();

		kaleoTaskImpl.setCompanyId(getCompanyId());
		kaleoTaskImpl.setKaleoTaskId(getKaleoTaskId());
		kaleoTaskImpl.setGroupId(getGroupId());
		kaleoTaskImpl.setUserId(getUserId());
		kaleoTaskImpl.setUserName(getUserName());
		kaleoTaskImpl.setCreateDate(getCreateDate());
		kaleoTaskImpl.setModifiedDate(getModifiedDate());
		kaleoTaskImpl.setKaleoDefinitionId(getKaleoDefinitionId());
		kaleoTaskImpl.setKaleoNodeId(getKaleoNodeId());
		kaleoTaskImpl.setName(getName());
		kaleoTaskImpl.setDescription(getDescription());

		kaleoTaskImpl.resetOriginalValues();

		return kaleoTaskImpl;
	}

	@Override
	public int compareTo(KaleoTask kaleoTask) {
		int value = 0;

		if (getKaleoTaskId() < kaleoTask.getKaleoTaskId()) {
			value = -1;
		}
		else if (getKaleoTaskId() > kaleoTask.getKaleoTaskId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTask)) {
			return false;
		}

		KaleoTask kaleoTask = (KaleoTask)obj;

		long primaryKey = kaleoTask.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		KaleoTaskModelImpl kaleoTaskModelImpl = this;

		kaleoTaskModelImpl._originalCompanyId = kaleoTaskModelImpl._companyId;

		kaleoTaskModelImpl._setOriginalCompanyId = false;

		kaleoTaskModelImpl._setModifiedDate = false;

		kaleoTaskModelImpl._originalKaleoDefinitionId = kaleoTaskModelImpl._kaleoDefinitionId;

		kaleoTaskModelImpl._setOriginalKaleoDefinitionId = false;

		kaleoTaskModelImpl._originalKaleoNodeId = kaleoTaskModelImpl._kaleoNodeId;

		kaleoTaskModelImpl._setOriginalKaleoNodeId = false;

		kaleoTaskModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoTask> toCacheModel() {
		KaleoTaskCacheModel kaleoTaskCacheModel = new KaleoTaskCacheModel();

		kaleoTaskCacheModel.companyId = getCompanyId();

		kaleoTaskCacheModel.kaleoTaskId = getKaleoTaskId();

		kaleoTaskCacheModel.groupId = getGroupId();

		kaleoTaskCacheModel.userId = getUserId();

		kaleoTaskCacheModel.userName = getUserName();

		String userName = kaleoTaskCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoTaskCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoTaskCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoTaskCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoTaskCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoTaskCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoTaskCacheModel.kaleoDefinitionId = getKaleoDefinitionId();

		kaleoTaskCacheModel.kaleoNodeId = getKaleoNodeId();

		kaleoTaskCacheModel.name = getName();

		String name = kaleoTaskCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			kaleoTaskCacheModel.name = null;
		}

		kaleoTaskCacheModel.description = getDescription();

		String description = kaleoTaskCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			kaleoTaskCacheModel.description = null;
		}

		return kaleoTaskCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{companyId=");
		sb.append(getCompanyId());
		sb.append(", kaleoTaskId=");
		sb.append(getKaleoTaskId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoNodeId=");
		sb.append(getKaleoNodeId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoTask");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = KaleoTask.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			KaleoTask.class
		};
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _kaleoTaskId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _kaleoDefinitionId;
	private long _originalKaleoDefinitionId;
	private boolean _setOriginalKaleoDefinitionId;
	private long _kaleoNodeId;
	private long _originalKaleoNodeId;
	private boolean _setOriginalKaleoNodeId;
	private String _name;
	private String _description;
	private long _columnBitmask;
	private KaleoTask _escapedModel;
}