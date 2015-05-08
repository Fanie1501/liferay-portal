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

package com.liferay.document.library.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.metadata.RawMetadataProcessorUtil;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryTypeException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata;
import com.liferay.portlet.documentlibrary.model.DLFileEntryTypeConstants;
import com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService;
import com.liferay.portlet.documentlibrary.util.RawMetadataProcessor;
import com.liferay.portlet.dynamicdatamapping.io.DDMFormXSDDeserializer;
import com.liferay.portlet.dynamicdatamapping.model.DDMForm;
import com.liferay.portlet.dynamicdatamapping.model.DDMFormLayout;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService;
import com.liferay.portlet.dynamicdatamapping.storage.StorageType;
import com.liferay.portlet.dynamicdatamapping.util.DDM;
import com.liferay.portlet.dynamicdatamapping.util.DDMXML;
import com.liferay.util.ContentUtil;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 * @author Miguel Pastor
 * @author Roberto Díaz
 * @author Marcellus Tavares
 */
@Component(immediate = true, service={StartupAction.class, LifecycleAction.class})
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void addDDMStructures(
			long userId, long groupId, long classNameId, String fileName,
			ServiceContext serviceContext)
		throws Exception {

		Locale locale = PortalUtil.getSiteDefaultLocale(groupId);

		List<Element> structureElements = getDDMStructures(fileName, locale);

		for (Element structureElement : structureElements) {
			boolean dynamicStructure = GetterUtil.getBoolean(
				structureElement.elementText("dynamic-structure"));

			if (dynamicStructure) {
				continue;
			}

			String name = structureElement.elementText("name");

			String description = structureElement.elementText("description");

			String ddmStructureKey = name;

			DDMStructure ddmStructure =
				_ddmStructureLocalService.fetchStructure(
					groupId, classNameId, ddmStructureKey);

			if (ddmStructure != null) {
				continue;
			}

			Element structureElementRootElement = structureElement.element(
				"root");

			String definition = structureElementRootElement.asXML();

			Map<Locale, String> nameMap = new HashMap<>();
			Map<Locale, String> descriptionMap = new HashMap<>();

			for (Locale curLocale : LanguageUtil.getAvailableLocales(groupId)) {
				nameMap.put(curLocale, LanguageUtil.get(curLocale, name));
				descriptionMap.put(
					curLocale, LanguageUtil.get(curLocale, description));
			}

			Attribute defaultLocaleAttribute =
				structureElementRootElement.attribute("default-locale");

			Locale ddmStructureDefaultLocale = LocaleUtil.fromLanguageId(
				defaultLocaleAttribute.getValue());

			definition = _ddmXML.updateXMLDefaultLocale(
				definition, ddmStructureDefaultLocale, locale);

			if (name.equals(DLFileEntryTypeConstants.NAME_IG_IMAGE) &&
				!UpgradeProcessUtil.isCreateIGImageDocumentType()) {

				continue;
			}

			DDMForm ddmForm = _ddmFormXSDDeserializer.deserialize(definition);

			DDMFormLayout ddmFormLayout = _ddm.getDefaultDDMFormLayout(ddmForm);

			_ddmStructureLocalService.addStructure(
				userId, groupId,
				DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID, classNameId,
				ddmStructureKey, nameMap, descriptionMap, ddmForm,
				ddmFormLayout, StorageType.JSON.toString(),
				DDMStructureConstants.TYPE_DEFAULT, serviceContext);
		}
	}

	protected void addDLFileEntryType(
			long userId, long groupId, String languageKey,
			String dlFileEntryTypeKey, List<String> ddmStructureNames,
			ServiceContext serviceContext)
		throws Exception {

		List<Long> ddmStructureIds = new ArrayList<>();

		for (String ddmStructureName : ddmStructureNames) {
			String ddmStructureKey = ddmStructureName;

			DDMStructure ddmStructure =
				_ddmStructureLocalService.fetchStructure(
					groupId,
					PortalUtil.getClassNameId(DLFileEntryMetadata.class),
					ddmStructureKey);

			if (ddmStructure == null) {
				continue;
			}

			ddmStructureIds.add(ddmStructure.getStructureId());
		}

		Locale locale = PortalUtil.getSiteDefaultLocale(groupId);

		String definition = getDynamicDDMStructureDefinition(
			"document-library-structures.xml", languageKey, locale);

		DDMForm ddmForm = _ddmFormXSDDeserializer.deserialize(definition);

		serviceContext.setAttribute("ddmForm", ddmForm);

		try {
			_fileEntryTypeLocalService.getFileEntryType(
				groupId, dlFileEntryTypeKey);
		}
		catch (NoSuchFileEntryTypeException nsfete) {
			Map<Locale, String> localizationMap = getLocalizationMap(
				languageKey);

			_fileEntryTypeLocalService.addFileEntryType(
				userId, groupId, dlFileEntryTypeKey, localizationMap,
				localizationMap,
				ArrayUtil.toArray(
					ddmStructureIds.toArray(new Long[ddmStructureIds.size()])),
				serviceContext);
		}
	}

	protected void addDLFileEntryTypes(
			long userId, long groupId, ServiceContext serviceContext)
		throws Exception {

		List<String> ddmStructureNames = new ArrayList<>();

		addDLFileEntryType(
			userId, groupId, DLFileEntryTypeConstants.NAME_CONTRACT,
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_KEY_CONTRACT,
			ddmStructureNames, serviceContext);

		ddmStructureNames.clear();

		ddmStructureNames.add("Marketing Campaign Theme Metadata");

		addDLFileEntryType(
			userId, groupId, DLFileEntryTypeConstants.NAME_MARKETING_BANNER,
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_KEY_MARKETING_BANNER,
			ddmStructureNames, serviceContext);

		ddmStructureNames.clear();

		ddmStructureNames.add("Learning Module Metadata");

		addDLFileEntryType(
			userId, groupId, DLFileEntryTypeConstants.NAME_ONLINE_TRAINING,
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_KEY_ONLINE_TRAINING,
			ddmStructureNames, serviceContext);

		ddmStructureNames.clear();

		ddmStructureNames.add("Meeting Metadata");

		addDLFileEntryType(
			userId, groupId, DLFileEntryTypeConstants.NAME_SALES_PRESENTATION,
			DLFileEntryTypeConstants.FILE_ENTRY_TYPE_KEY_SALES_PRESENTATION,
			ddmStructureNames, serviceContext);

		if (UpgradeProcessUtil.isCreateIGImageDocumentType()) {
			addDLFileEntryType(
				userId, groupId, DLFileEntryTypeConstants.NAME_IG_IMAGE,
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_KEY_IG_IMAGE,
				ddmStructureNames, serviceContext);
		}
	}

	protected void addDLRawMetadataStructures(
			long userId, long groupId, ServiceContext serviceContext)
		throws Exception {

		Locale locale = PortalUtil.getSiteDefaultLocale(groupId);

		String xsd = buildDLRawMetadataXML(
			RawMetadataProcessorUtil.getFields(), locale);

		Document document = SAXReaderUtil.read(new StringReader(xsd));

		Element rootElement = document.getRootElement();

		List<Element> structureElements = rootElement.elements("structure");

		for (Element structureElement : structureElements) {
			String name = structureElement.elementText("name");
			String description = structureElement.elementText("description");

			Element structureElementRootElement = structureElement.element(
				"root");

			String structureElementRootXML =
				structureElementRootElement.asXML();

			DDMStructure ddmStructure =
				_ddmStructureLocalService.fetchStructure(
					groupId,
					PortalUtil.getClassNameId(RawMetadataProcessor.class),
					name);

			DDMForm ddmForm = _ddmFormXSDDeserializer.deserialize(
				structureElementRootXML);

			if (ddmStructure != null) {
				ddmStructure.setDDMForm(ddmForm);

				_ddmStructureLocalService.updateDDMStructure(ddmStructure);
			}
			else {
				Map<Locale, String> nameMap = new HashMap<>();

				nameMap.put(locale, name);

				Map<Locale, String> descriptionMap = new HashMap<>();

				descriptionMap.put(locale, description);

				DDMFormLayout ddmFormLayout = _ddm.getDefaultDDMFormLayout(
					ddmForm);

				_ddmStructureLocalService.addStructure(
					userId, groupId,
					DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID,
					PortalUtil.getClassNameId(RawMetadataProcessor.class), name,
					nameMap, descriptionMap, ddmForm, ddmFormLayout,
					StorageType.JSON.toString(),
					DDMStructureConstants.TYPE_DEFAULT, serviceContext);
			}
		}
	}

	protected String buildDLRawMetadataElementXML(Field field, Locale locale) {
		StringBundler sb = new StringBundler(15);

		sb.append("<dynamic-element dataType=\"string\" indexType=\"text\" ");
		sb.append("name=\"");

		Class<?> fieldClass = field.getDeclaringClass();

		sb.append(fieldClass.getSimpleName());
		sb.append(StringPool.UNDERLINE);
		sb.append(field.getName());
		sb.append("\" required=\"false\" showLabel=\"true\" type=\"text\">");
		sb.append("<meta-data locale=\"");
		sb.append(locale);
		sb.append("\">");
		sb.append("<entry name=\"label\"><![CDATA[metadata.");
		sb.append(fieldClass.getSimpleName());
		sb.append(StringPool.PERIOD);
		sb.append(field.getName());
		sb.append("]]></entry><entry name=\"predefinedValue\">");
		sb.append("<![CDATA[]]></entry></meta-data></dynamic-element>");

		return sb.toString();
	}

	protected String buildDLRawMetadataStructureXML(
		String name, Field[] fields, Locale locale) {

		StringBundler sb = new StringBundler(12 + fields.length);

		sb.append("<structure><name><![CDATA[");
		sb.append(name);
		sb.append("]]></name>");
		sb.append("<description><![CDATA[");
		sb.append(name);
		sb.append("]]></description>");
		sb.append("<root available-locales=\"");
		sb.append(locale);
		sb.append("\" default-locale=\"");
		sb.append(locale);
		sb.append("\">");

		for (Field field : fields) {
			sb.append(buildDLRawMetadataElementXML(field, locale));
		}

		sb.append("</root></structure>");

		return sb.toString();
	}

	protected String buildDLRawMetadataXML(
		Map<String, Field[]> fields, Locale locale) {

		StringBundler sb = new StringBundler(2 + fields.size());

		sb.append("<?xml version=\"1.0\"?><root>");

		for (String key : fields.keySet()) {
			sb.append(
				buildDLRawMetadataStructureXML(key, fields.get(key), locale));
		}

		sb.append("</root>");

		return sb.toString();
	}

	protected void doRun(long companyId) throws Exception {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGuestPermissions(true);
		serviceContext.setAddGroupPermissions(true);

		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		serviceContext.setScopeGroupId(group.getGroupId());

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		serviceContext.setUserId(defaultUserId);

		addDDMStructures(
			defaultUserId, group.getGroupId(),
			PortalUtil.getClassNameId(DLFileEntryMetadata.class),
			"document-library-structures.xml", serviceContext);
		addDLFileEntryTypes(defaultUserId, group.getGroupId(), serviceContext);
		addDLRawMetadataStructures(
			defaultUserId, group.getGroupId(), serviceContext);
	}

	protected String getContent(String fileName) {
		Class<?> clazz = getClass();

		return ContentUtil.get(
			clazz.getClassLoader(), "/META-INF/resources/" + fileName);
	}

	protected List<Element> getDDMStructures(String fileName, Locale locale)
		throws DocumentException {

		String xml = getContent(fileName);

		xml = StringUtil.replace(xml, "[$LOCALE_DEFAULT$]", locale.toString());

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		return rootElement.elements("structure");
	}

	protected String getDynamicDDMStructureDefinition(
			String fileName, String dynamicDDMStructureName, Locale locale)
		throws DocumentException {

		List<Element> structureElements = getDDMStructures(fileName, locale);

		for (Element structureElement : structureElements) {
			boolean dynamicStructure = GetterUtil.getBoolean(
				structureElement.elementText("dynamic-structure"));

			if (!dynamicStructure) {
				continue;
			}

			String name = structureElement.elementText("name");

			if (!name.equals(dynamicDDMStructureName)) {
				continue;
			}

			Element structureElementRootElement = structureElement.element(
				"root");

			return structureElementRootElement.asXML();
		}

		return null;
	}

	protected Map<Locale, String> getLocalizationMap(String content) {
		Map<Locale, String> localizationMap = new HashMap<>();

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = LanguageUtil.get(defaultLocale, content);

		for (Locale locale : LanguageUtil.getSupportedLocales()) {
			String value = LanguageUtil.get(locale, content);

			if (!locale.equals(defaultLocale) && value.equals(defaultValue)) {
				continue;
			}

			localizationMap.put(locale, value);
		}

		return localizationMap;
	}

	@Activate
	protected void run() throws ActionException {
		Long companyId = CompanyThreadLocal.getCompanyId();

		try {
			List<Company> companys = _companyLocalService.getCompanies();

			for (Company company : companys) {
				CompanyThreadLocal.setCompanyId(company.getCompanyId());

				run(new String[] {String.valueOf(company.getCompanyId())});
			}
		}
		finally {
			CompanyThreadLocal.setCompanyId(companyId);
		}
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDM(DDM ddm) {
		_ddm = ddm;
	}

	@Reference(unbind = "-")
	protected void setDDMFormXSDDeserializer(
		DDMFormXSDDeserializer ddmFormXSDDeserializer) {

		_ddmFormXSDDeserializer = ddmFormXSDDeserializer;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMXML(DDMXML ddmXML) {
		_ddmXML = ddmXML;
	}

	@Reference(unbind = "-")
	protected void setDLFileEntryTypeLocalService(
		DLFileEntryTypeLocalService fileEntryTypeLocalService) {

		_fileEntryTypeLocalService = fileEntryTypeLocalService;
	}

	@Reference(target = "(original.bean=true)", unbind = "-")
	protected void setServletContext(ServletContext servletContext) {
	}

	private CompanyLocalService _companyLocalService;
	private DDM _ddm;
	private DDMFormXSDDeserializer _ddmFormXSDDeserializer;
	private DDMStructureLocalService _ddmStructureLocalService;
	private DDMXML _ddmXML;
	private DLFileEntryTypeLocalService _fileEntryTypeLocalService;

}