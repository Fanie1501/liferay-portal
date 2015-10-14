<#assign ddlRecordSetCounts = dataFactory.getSequence(dataFactory.maxDDLRecordSetCount)>

<#list ddlRecordSetCounts as ddlRecordSetCount>
	<#if (ddlRecordSetCount = 1)>
		<#assign ddmStructureModel = dataFactory.newDDLDDMStructureModel(groupId)>

		insert into DDMStructure values (${ddmStructureModel.companyId}, '${ddmStructureModel.uuid}', ${ddmStructureModel.structureId}, ${ddmStructureModel.groupId}, ${ddmStructureModel.userId}, '${ddmStructureModel.userName}', ${ddmStructureModel.versionUserId}, '${ddmStructureModel.versionUserName}', '${dataFactory.getDateString(ddmStructureModel.createDate)}', '${dataFactory.getDateString(ddmStructureModel.modifiedDate)}', ${ddmStructureModel.parentStructureId}, ${ddmStructureModel.classNameId}, '${ddmStructureModel.structureKey}', '${ddmStructureModel.version}', '${ddmStructureModel.name}', '${ddmStructureModel.description}', '${ddmStructureModel.definition}', '${ddmStructureModel.storageType}', ${ddmStructureModel.type}, '${dataFactory.getDateString(ddmStructureModel.lastPublishDate)}');

		<@insertResourcePermissions
			_entry = ddmStructureModel
		/>
	</#if>

	<#assign layoutName = "dynamic_data_list_display_" + ddlRecordSetCount>
	<#assign portletId = "169_INSTANCE_TEST" + ddlRecordSetCount>

	<#assign layoutModel = dataFactory.newLayoutModel(groupId, layoutName, "", portletId)>

	<@insertLayout
		_layoutModel = layoutModel
	/>

	<#assign ddlRecordSetModel = dataFactory.newDDLRecordSetModel(ddmStructureModel, ddlRecordSetCount)>

	insert into DDLRecordSet values (${ddlRecordSetModel.companyId}, '${ddlRecordSetModel.uuid}', ${ddlRecordSetModel.recordSetId}, ${ddlRecordSetModel.groupId}, ${ddlRecordSetModel.userId}, '${ddlRecordSetModel.userName}', '${dataFactory.getDateString(ddlRecordSetModel.createDate)}', '${dataFactory.getDateString(ddlRecordSetModel.modifiedDate)}', ${ddlRecordSetModel.DDMStructureId}, '${ddlRecordSetModel.recordSetKey}', '${ddlRecordSetModel.name}', '${ddlRecordSetModel.description}', ${ddlRecordSetModel.minDisplayRows}, ${ddlRecordSetModel.scope}, '${dataFactory.getDateString(ddlRecordSetModel.lastPublishDate)}');

	<@insertDDMStructureLink
		_entry = ddlRecordSetModel
	/>

	<@insertResourcePermissions
		_entry = ddlRecordSetModel
	/>

	<#assign ddlRecordCounts = dataFactory.getSequence(dataFactory.maxDDLRecordCount)>

	<#list ddlRecordCounts as ddlRecordCount>
		<#assign ddlRecordModel = dataFactory.newDDLRecordModel(ddlRecordSetModel)>

		insert into DDLRecord values (${ddlRecordModel.companyId}, '${ddlRecordModel.uuid}', ${ddlRecordModel.recordId}, ${ddlRecordModel.groupId}, ${ddlRecordModel.userId}, '${ddlRecordModel.userName}', ${ddlRecordModel.versionUserId}, '${ddlRecordModel.versionUserName}', '${dataFactory.getDateString(ddlRecordModel.createDate)}', '${dataFactory.getDateString(ddlRecordModel.modifiedDate)}', ${ddlRecordModel.DDMStorageId}, ${ddlRecordModel.recordSetId}, '${ddlRecordModel.version}', ${ddlRecordModel.displayIndex}, '${dataFactory.getDateString(ddlRecordModel.lastPublishDate)}');

		<#assign ddlRecordVersionModel = dataFactory.newDDLRecordVersionModel(ddlRecordModel)>

		insert into DDLRecordVersion values (${ddlRecordVersionModel.companyId}, ${ddlRecordVersionModel.recordVersionId}, ${ddlRecordVersionModel.groupId}, ${ddlRecordVersionModel.userId}, '${ddlRecordVersionModel.userName}', '${dataFactory.getDateString(ddlRecordVersionModel.createDate)}', ${ddlRecordVersionModel.DDMStorageId}, ${ddlRecordVersionModel.recordSetId}, ${ddlRecordVersionModel.recordId}, '${ddlRecordVersionModel.version}', ${ddlRecordVersionModel.displayIndex}, ${ddlRecordVersionModel.status}, ${ddlRecordVersionModel.statusByUserId}, '${ddlRecordVersionModel.statusByUserName}', '${dataFactory.getDateString(ddlRecordVersionModel.statusDate)}');

		<@insertDDMContent
			_currentIndex = ddlRecordCount
			_ddmStorageLinkId = dataFactory.getCounterNext()
			_ddmStructureId = ddmStructureModel.structureId
			_entry = ddlRecordModel
		/>

		${dynamicDataListCSVWriter.write(ddlRecordModel.groupId + "," + layoutName + "," + portletId + "," + ddlRecordSetModel.recordSetId + "," + ddlRecordModel.recordId + "\n")}
	</#list>

	<#assign portletPreferencesModel = dataFactory.newPortletPreferencesModel(layoutModel.plid, portletId, ddlRecordSetModel)>

	<@insertPortletPreferences
		_portletPreferencesModel = portletPreferencesModel
	/>

	<#assign portletPreferencesModels = dataFactory.newDDLPortletPreferencesModels(layoutModel.plid)>

	<#list portletPreferencesModels as portletPreferencesModel>
		<@insertPortletPreferences
			_portletPreferencesModel = portletPreferencesModel
		/>
	</#list>
</#list>