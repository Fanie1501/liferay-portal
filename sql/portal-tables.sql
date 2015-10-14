create table Account_ (
	companyId LONG,
	mvccVersion LONG default 0,
	accountId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentAccountId LONG,
	name VARCHAR(75) null,
	legalName VARCHAR(75) null,
	legalId VARCHAR(75) null,
	legalType VARCHAR(75) null,
	sicCode VARCHAR(75) null,
	tickerSymbol VARCHAR(75) null,
	industry VARCHAR(75) null,
	type_ VARCHAR(75) null,
	size_ VARCHAR(75) null
);

create table Address (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	addressId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	street1 VARCHAR(75) null,
	street2 VARCHAR(75) null,
	street3 VARCHAR(75) null,
	city VARCHAR(75) null,
	zip VARCHAR(75) null,
	regionId LONG,
	countryId LONG,
	typeId LONG,
	mailing BOOLEAN,
	primary_ BOOLEAN,
	lastPublishDate DATE null
);

create table AnnouncementsDelivery (
	companyId LONG,
	deliveryId LONG not null primary key,
	userId LONG,
	type_ VARCHAR(75) null,
	email BOOLEAN,
	sms BOOLEAN,
	website BOOLEAN
);

create table AnnouncementsEntry (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	entryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	title VARCHAR(75) null,
	content TEXT null,
	url STRING null,
	type_ VARCHAR(75) null,
	displayDate DATE null,
	expirationDate DATE null,
	priority INTEGER,
	alert BOOLEAN
);

create table AnnouncementsFlag (
	companyId LONG,
	flagId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	entryId LONG,
	value INTEGER
);

create table AssetCategory (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	categoryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentCategoryId LONG,
	leftCategoryId LONG,
	rightCategoryId LONG,
	name VARCHAR(75) null,
	title STRING null,
	description STRING null,
	vocabularyId LONG,
	lastPublishDate DATE null
);

create table AssetCategoryProperty (
	companyId LONG,
	categoryPropertyId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	categoryId LONG,
	key_ VARCHAR(75) null,
	value VARCHAR(75) null
);

create table AssetEntries_AssetCategories (
	companyId LONG not null,
	categoryId LONG not null,
	entryId LONG not null,
	primary key (companyId, categoryId, entryId)
);

create table AssetEntries_AssetTags (
	companyId LONG not null,
	entryId LONG not null,
	tagId LONG not null,
	primary key (companyId, entryId, tagId)
);

create table AssetEntry (
	companyId LONG,
	entryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	classUuid VARCHAR(75) null,
	classTypeId LONG,
	listable BOOLEAN,
	visible BOOLEAN,
	startDate DATE null,
	endDate DATE null,
	publishDate DATE null,
	expirationDate DATE null,
	mimeType VARCHAR(75) null,
	title STRING null,
	description TEXT null,
	summary TEXT null,
	url STRING null,
	layoutUuid VARCHAR(75) null,
	height INTEGER,
	width INTEGER,
	priority DOUBLE,
	viewCount INTEGER
);

create table AssetLink (
	companyId LONG,
	linkId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	entryId1 LONG,
	entryId2 LONG,
	type_ INTEGER,
	weight INTEGER
);

create table AssetTag (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	tagId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	assetCount INTEGER,
	lastPublishDate DATE null
);

create table AssetTagStats (
	companyId LONG,
	tagStatsId LONG not null primary key,
	tagId LONG,
	classNameId LONG,
	assetCount INTEGER
);

create table AssetVocabulary (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	vocabularyId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	title STRING null,
	description STRING null,
	settings_ STRING null,
	lastPublishDate DATE null
);

create table BlogsEntry (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	entryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(150) null,
	subtitle STRING null,
	urlTitle VARCHAR(150) null,
	description STRING null,
	content TEXT null,
	displayDate DATE null,
	allowPingbacks BOOLEAN,
	allowTrackbacks BOOLEAN,
	trackbacks TEXT null,
	coverImageCaption STRING null,
	coverImageFileEntryId LONG,
	coverImageURL STRING null,
	smallImage BOOLEAN,
	smallImageFileEntryId LONG,
	smallImageId LONG,
	smallImageURL STRING null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table BlogsStatsUser (
	companyId LONG,
	statsUserId LONG not null primary key,
	groupId LONG,
	userId LONG,
	entryCount INTEGER,
	lastPostDate DATE null,
	ratingsTotalEntries INTEGER,
	ratingsTotalScore DOUBLE,
	ratingsAverageScore DOUBLE
);

create table BrowserTracker (
	companyId LONG,
	mvccVersion LONG default 0,
	browserTrackerId LONG not null primary key,
	userId LONG,
	browserKey LONG
);

create table CalEvent (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	eventId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	description TEXT null,
	location STRING null,
	startDate DATE null,
	endDate DATE null,
	durationHour INTEGER,
	durationMinute INTEGER,
	allDay BOOLEAN,
	timeZoneSensitive BOOLEAN,
	type_ VARCHAR(75) null,
	repeating BOOLEAN,
	recurrence TEXT null,
	remindBy INTEGER,
	firstReminder INTEGER,
	secondReminder INTEGER
);

create table ClassName_ (
	mvccVersion LONG default 0,
	classNameId LONG not null primary key,
	value VARCHAR(200) null
);

create table ClusterGroup (
	mvccVersion LONG default 0,
	clusterGroupId LONG not null primary key,
	name VARCHAR(75) null,
	clusterNodeIds VARCHAR(75) null,
	wholeCluster BOOLEAN
);

create table Company (
	mvccVersion LONG default 0,
	companyId LONG not null primary key,
	accountId LONG,
	webId VARCHAR(75) null,
	key_ TEXT null,
	mx VARCHAR(75) null,
	homeURL STRING null,
	logoId LONG,
	system BOOLEAN,
	maxUsers INTEGER,
	active_ BOOLEAN
);

create table Contact_ (
	companyId LONG,
	mvccVersion LONG default 0,
	contactId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	accountId LONG,
	parentContactId LONG,
	emailAddress VARCHAR(75) null,
	firstName VARCHAR(75) null,
	middleName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	prefixId LONG,
	suffixId LONG,
	male BOOLEAN,
	birthday DATE null,
	smsSn VARCHAR(75) null,
	aimSn VARCHAR(75) null,
	facebookSn VARCHAR(75) null,
	icqSn VARCHAR(75) null,
	jabberSn VARCHAR(75) null,
	msnSn VARCHAR(75) null,
	mySpaceSn VARCHAR(75) null,
	skypeSn VARCHAR(75) null,
	twitterSn VARCHAR(75) null,
	ymSn VARCHAR(75) null,
	employeeStatusId VARCHAR(75) null,
	employeeNumber VARCHAR(75) null,
	jobTitle VARCHAR(100) null,
	jobClass VARCHAR(75) null,
	hoursOfOperation VARCHAR(75) null
);

create table Counter (
	name VARCHAR(75) not null primary key,
	currentId LONG
);

create table Country (
	mvccVersion LONG default 0,
	countryId LONG not null primary key,
	name VARCHAR(75) null,
	a2 VARCHAR(75) null,
	a3 VARCHAR(75) null,
	number_ VARCHAR(75) null,
	idd_ VARCHAR(75) null,
	zipRequired BOOLEAN,
	active_ BOOLEAN
);

create table CyrusUser (
	userId VARCHAR(75) not null primary key,
	password_ VARCHAR(75) not null
);

create table CyrusVirtual (
	emailAddress VARCHAR(75) not null primary key,
	userId VARCHAR(75) not null
);

create table DLContent (
	companyId LONG,
	contentId LONG not null primary key,
	groupId LONG,
	repositoryId LONG,
	path_ VARCHAR(255) null,
	version VARCHAR(75) null,
	data_ BLOB,
	size_ LONG
);

create table DLFileEntry (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	fileEntryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	repositoryId LONG,
	folderId LONG,
	treePath STRING null,
	name VARCHAR(255) null,
	fileName VARCHAR(255) null,
	extension VARCHAR(75) null,
	mimeType VARCHAR(75) null,
	title VARCHAR(255) null,
	description STRING null,
	extraSettings TEXT null,
	fileEntryTypeId LONG,
	version VARCHAR(75) null,
	size_ LONG,
	readCount INTEGER,
	smallImageId LONG,
	largeImageId LONG,
	custom1ImageId LONG,
	custom2ImageId LONG,
	manualCheckInRequired BOOLEAN,
	lastPublishDate DATE null
);

create table DLFileEntryMetadata (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	fileEntryMetadataId LONG not null primary key,
	DDMStorageId LONG,
	DDMStructureId LONG,
	fileEntryId LONG,
	fileVersionId LONG
);

create table DLFileEntryType (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	fileEntryTypeId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fileEntryTypeKey VARCHAR(75) null,
	name STRING null,
	description STRING null,
	lastPublishDate DATE null
);

create table DLFileEntryTypes_DLFolders (
	companyId LONG not null,
	fileEntryTypeId LONG not null,
	folderId LONG not null,
	primary key (companyId, fileEntryTypeId, folderId)
);

create table DLFileRank (
	companyId LONG,
	fileRankId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	fileEntryId LONG,
	active_ BOOLEAN
);

create table DLFileShortcut (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	fileShortcutId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	repositoryId LONG,
	folderId LONG,
	toFileEntryId LONG,
	treePath STRING null,
	active_ BOOLEAN,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table DLFileVersion (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	fileVersionId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	repositoryId LONG,
	folderId LONG,
	fileEntryId LONG,
	treePath STRING null,
	fileName VARCHAR(255) null,
	extension VARCHAR(75) null,
	mimeType VARCHAR(75) null,
	title VARCHAR(255) null,
	description STRING null,
	changeLog VARCHAR(75) null,
	extraSettings TEXT null,
	fileEntryTypeId LONG,
	version VARCHAR(75) null,
	size_ LONG,
	checksum VARCHAR(75) null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table DLFolder (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	folderId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	repositoryId LONG,
	mountPoint BOOLEAN,
	parentFolderId LONG,
	treePath STRING null,
	name VARCHAR(255) null,
	description STRING null,
	lastPostDate DATE null,
	defaultFileEntryTypeId LONG,
	hidden_ BOOLEAN,
	restrictionType INTEGER,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table DLSyncEvent (
	companyId LONG,
	syncEventId LONG not null primary key,
	modifiedTime LONG,
	event VARCHAR(75) null,
	type_ VARCHAR(75) null,
	typePK LONG
);

create table EmailAddress (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	emailAddressId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	address VARCHAR(75) null,
	typeId LONG,
	primary_ BOOLEAN,
	lastPublishDate DATE null
);

create table ExpandoColumn (
	companyId LONG,
	columnId LONG not null primary key,
	tableId LONG,
	name VARCHAR(75) null,
	type_ INTEGER,
	defaultData TEXT null,
	typeSettings TEXT null
);

create table ExpandoRow (
	companyId LONG,
	rowId_ LONG not null primary key,
	modifiedDate DATE null,
	tableId LONG,
	classPK LONG
);

create table ExpandoTable (
	companyId LONG,
	tableId LONG not null primary key,
	classNameId LONG,
	name VARCHAR(75) null
);

create table ExpandoValue (
	companyId LONG,
	valueId LONG not null primary key,
	tableId LONG,
	columnId LONG,
	rowId_ LONG,
	classNameId LONG,
	classPK LONG,
	data_ TEXT null
);

create table ExportImportConfiguration (
	companyId LONG,
	mvccVersion LONG default 0,
	exportImportConfigurationId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(200) null,
	description STRING null,
	type_ INTEGER,
	settings_ TEXT null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table Group_ (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	groupId LONG not null primary key,
	creatorUserId LONG,
	classNameId LONG,
	classPK LONG,
	parentGroupId LONG,
	liveGroupId LONG,
	treePath STRING null,
	groupKey VARCHAR(150) null,
	name STRING null,
	description STRING null,
	type_ INTEGER,
	typeSettings TEXT null,
	manualMembership BOOLEAN,
	membershipRestriction INTEGER,
	friendlyURL VARCHAR(255) null,
	site BOOLEAN,
	remoteStagingGroupCount INTEGER,
	inheritContent BOOLEAN,
	active_ BOOLEAN
);

create table Groups_Orgs (
	companyId LONG not null,
	groupId LONG not null,
	organizationId LONG not null,
	primary key (companyId, groupId, organizationId)
);

create table Groups_Roles (
	companyId LONG not null,
	groupId LONG not null,
	roleId LONG not null,
	primary key (companyId, groupId, roleId)
);

create table Groups_UserGroups (
	companyId LONG not null,
	groupId LONG not null,
	userGroupId LONG not null,
	primary key (companyId, groupId, userGroupId)
);

create table Image (
	companyId LONG,
	mvccVersion LONG default 0,
	imageId LONG not null primary key,
	modifiedDate DATE null,
	type_ VARCHAR(75) null,
	height INTEGER,
	width INTEGER,
	size_ INTEGER
);

create table Layout (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	plid LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	privateLayout BOOLEAN,
	layoutId LONG,
	parentLayoutId LONG,
	name STRING null,
	title STRING null,
	description STRING null,
	keywords STRING null,
	robots STRING null,
	type_ VARCHAR(75) null,
	typeSettings TEXT null,
	hidden_ BOOLEAN,
	friendlyURL VARCHAR(255) null,
	iconImageId LONG,
	themeId VARCHAR(75) null,
	colorSchemeId VARCHAR(75) null,
	wapThemeId VARCHAR(75) null,
	wapColorSchemeId VARCHAR(75) null,
	css TEXT null,
	priority INTEGER,
	layoutPrototypeUuid VARCHAR(75) null,
	layoutPrototypeLinkEnabled BOOLEAN,
	sourcePrototypeLayoutUuid VARCHAR(75) null,
	lastPublishDate DATE null
);

create table LayoutBranch (
	companyId LONG,
	mvccVersion LONG default 0,
	layoutBranchId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	layoutSetBranchId LONG,
	plid LONG,
	name VARCHAR(75) null,
	description STRING null,
	master BOOLEAN
);

create table LayoutFriendlyURL (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	layoutFriendlyURLId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	plid LONG,
	privateLayout BOOLEAN,
	friendlyURL VARCHAR(255) null,
	languageId VARCHAR(75) null,
	lastPublishDate DATE null
);

create table LayoutPrototype (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	layoutPrototypeId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	settings_ STRING null,
	active_ BOOLEAN,
	lastPublishDate DATE null
);

create table LayoutRevision (
	companyId LONG,
	mvccVersion LONG default 0,
	layoutRevisionId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	layoutSetBranchId LONG,
	layoutBranchId LONG,
	parentLayoutRevisionId LONG,
	head BOOLEAN,
	major BOOLEAN,
	plid LONG,
	privateLayout BOOLEAN,
	name STRING null,
	title STRING null,
	description STRING null,
	keywords STRING null,
	robots STRING null,
	typeSettings TEXT null,
	iconImageId LONG,
	themeId VARCHAR(75) null,
	colorSchemeId VARCHAR(75) null,
	wapThemeId VARCHAR(75) null,
	wapColorSchemeId VARCHAR(75) null,
	css TEXT null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table LayoutSet (
	companyId LONG,
	mvccVersion LONG default 0,
	layoutSetId LONG not null primary key,
	groupId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	privateLayout BOOLEAN,
	logoId LONG,
	themeId VARCHAR(75) null,
	colorSchemeId VARCHAR(75) null,
	wapThemeId VARCHAR(75) null,
	wapColorSchemeId VARCHAR(75) null,
	css TEXT null,
	pageCount INTEGER,
	settings_ TEXT null,
	layoutSetPrototypeUuid VARCHAR(75) null,
	layoutSetPrototypeLinkEnabled BOOLEAN
);

create table LayoutSetBranch (
	companyId LONG,
	mvccVersion LONG default 0,
	layoutSetBranchId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	privateLayout BOOLEAN,
	name VARCHAR(75) null,
	description STRING null,
	master BOOLEAN,
	logoId LONG,
	themeId VARCHAR(75) null,
	colorSchemeId VARCHAR(75) null,
	wapThemeId VARCHAR(75) null,
	wapColorSchemeId VARCHAR(75) null,
	css TEXT null,
	settings_ TEXT null,
	layoutSetPrototypeUuid VARCHAR(75) null,
	layoutSetPrototypeLinkEnabled BOOLEAN
);

create table LayoutSetPrototype (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	layoutSetPrototypeId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	settings_ STRING null,
	active_ BOOLEAN,
	lastPublishDate DATE null
);

create table ListType (
	mvccVersion LONG default 0,
	listTypeId LONG not null primary key,
	name VARCHAR(75) null,
	type_ VARCHAR(75) null
);

create table MBBan (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	banId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	banUserId LONG,
	lastPublishDate DATE null
);

create table MBCategory (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	categoryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentCategoryId LONG,
	name VARCHAR(75) null,
	description STRING null,
	displayStyle VARCHAR(75) null,
	threadCount INTEGER,
	messageCount INTEGER,
	lastPostDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table MBDiscussion (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	discussionId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	threadId LONG,
	lastPublishDate DATE null
);

create table MBMailingList (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	mailingListId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	categoryId LONG,
	emailAddress VARCHAR(75) null,
	inProtocol VARCHAR(75) null,
	inServerName VARCHAR(75) null,
	inServerPort INTEGER,
	inUseSSL BOOLEAN,
	inUserName VARCHAR(75) null,
	inPassword VARCHAR(75) null,
	inReadInterval INTEGER,
	outEmailAddress VARCHAR(75) null,
	outCustom BOOLEAN,
	outServerName VARCHAR(75) null,
	outServerPort INTEGER,
	outUseSSL BOOLEAN,
	outUserName VARCHAR(75) null,
	outPassword VARCHAR(75) null,
	allowAnonymous BOOLEAN,
	active_ BOOLEAN
);

create table MBMessage (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	messageId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	categoryId LONG,
	threadId LONG,
	rootMessageId LONG,
	parentMessageId LONG,
	subject VARCHAR(75) null,
	body TEXT null,
	format VARCHAR(75) null,
	anonymous BOOLEAN,
	priority DOUBLE,
	allowPingbacks BOOLEAN,
	answer BOOLEAN,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table MBStatsUser (
	companyId LONG,
	statsUserId LONG not null primary key,
	groupId LONG,
	userId LONG,
	messageCount INTEGER,
	lastPostDate DATE null
);

create table MBThread (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	threadId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	categoryId LONG,
	rootMessageId LONG,
	rootMessageUserId LONG,
	messageCount INTEGER,
	viewCount INTEGER,
	lastPostByUserId LONG,
	lastPostDate DATE null,
	priority DOUBLE,
	question BOOLEAN,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table MBThreadFlag (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	threadFlagId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	threadId LONG,
	lastPublishDate DATE null
);

create table MembershipRequest (
	companyId LONG,
	mvccVersion LONG default 0,
	membershipRequestId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	comments STRING null,
	replyComments STRING null,
	replyDate DATE null,
	replierUserId LONG,
	statusId LONG
);

create table Organization_ (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	organizationId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentOrganizationId LONG,
	treePath STRING null,
	name VARCHAR(100) null,
	type_ VARCHAR(75) null,
	recursable BOOLEAN,
	regionId LONG,
	countryId LONG,
	statusId LONG,
	comments STRING null,
	logoId LONG,
	lastPublishDate DATE null
);

create table OrgGroupRole (
	companyId LONG,
	mvccVersion LONG default 0,
	organizationId LONG not null,
	groupId LONG not null,
	roleId LONG not null,
	primary key (organizationId, groupId, roleId)
);

create table OrgLabor (
	companyId LONG,
	mvccVersion LONG default 0,
	orgLaborId LONG not null primary key,
	organizationId LONG,
	typeId LONG,
	sunOpen INTEGER,
	sunClose INTEGER,
	monOpen INTEGER,
	monClose INTEGER,
	tueOpen INTEGER,
	tueClose INTEGER,
	wedOpen INTEGER,
	wedClose INTEGER,
	thuOpen INTEGER,
	thuClose INTEGER,
	friOpen INTEGER,
	friClose INTEGER,
	satOpen INTEGER,
	satClose INTEGER
);

create table PasswordPolicy (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	passwordPolicyId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	defaultPolicy BOOLEAN,
	name VARCHAR(75) null,
	description STRING null,
	changeable BOOLEAN,
	changeRequired BOOLEAN,
	minAge LONG,
	checkSyntax BOOLEAN,
	allowDictionaryWords BOOLEAN,
	minAlphanumeric INTEGER,
	minLength INTEGER,
	minLowerCase INTEGER,
	minNumbers INTEGER,
	minSymbols INTEGER,
	minUpperCase INTEGER,
	regex VARCHAR(75) null,
	history BOOLEAN,
	historyCount INTEGER,
	expireable BOOLEAN,
	maxAge LONG,
	warningTime LONG,
	graceLimit INTEGER,
	lockout BOOLEAN,
	maxFailure INTEGER,
	lockoutDuration LONG,
	requireUnlock BOOLEAN,
	resetFailureCount LONG,
	resetTicketMaxAge LONG,
	lastPublishDate DATE null
);

create table PasswordPolicyRel (
	companyId LONG,
	mvccVersion LONG default 0,
	passwordPolicyRelId LONG not null primary key,
	passwordPolicyId LONG,
	classNameId LONG,
	classPK LONG
);

create table PasswordTracker (
	companyId LONG,
	mvccVersion LONG default 0,
	passwordTrackerId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	password_ VARCHAR(75) null
);

create table Phone (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	phoneId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	number_ VARCHAR(75) null,
	extension VARCHAR(75) null,
	typeId LONG,
	primary_ BOOLEAN,
	lastPublishDate DATE null
);

create table PluginSetting (
	companyId LONG,
	mvccVersion LONG default 0,
	pluginSettingId LONG not null primary key,
	pluginId VARCHAR(75) null,
	pluginType VARCHAR(75) null,
	roles STRING null,
	active_ BOOLEAN
);

create table PortalPreferences (
	mvccVersion LONG default 0,
	portalPreferencesId LONG not null primary key,
	ownerId LONG,
	ownerType INTEGER,
	preferences TEXT null
);

create table Portlet (
	companyId LONG,
	mvccVersion LONG default 0,
	id_ LONG not null primary key,
	portletId VARCHAR(200) null,
	roles STRING null,
	active_ BOOLEAN
);

create table PortletItem (
	companyId LONG,
	mvccVersion LONG default 0,
	portletItemId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	portletId VARCHAR(200) null,
	classNameId LONG
);

create table PortletPreferences (
	companyId LONG,
	mvccVersion LONG default 0,
	portletPreferencesId LONG not null primary key,
	ownerId LONG,
	ownerType INTEGER,
	plid LONG,
	portletId VARCHAR(200) null,
	preferences TEXT null
);

create table RatingsEntry (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	entryId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	score DOUBLE,
	lastPublishDate DATE null
);

create table RatingsStats (
	companyId LONG,
	statsId LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	totalEntries INTEGER,
	totalScore DOUBLE,
	averageScore DOUBLE
);

create table Region (
	mvccVersion LONG default 0,
	regionId LONG not null primary key,
	countryId LONG,
	regionCode VARCHAR(75) null,
	name VARCHAR(75) null,
	active_ BOOLEAN
);

create table Release_ (
	mvccVersion LONG default 0,
	releaseId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	servletContextName VARCHAR(75) null,
	schemaVersion VARCHAR(75) null,
	buildNumber INTEGER,
	buildDate DATE null,
	verified BOOLEAN,
	state_ INTEGER,
	testString VARCHAR(1024) null
);

create table Repository (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	repositoryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	name VARCHAR(75) null,
	description STRING null,
	portletId VARCHAR(200) null,
	typeSettings TEXT null,
	dlFolderId LONG,
	lastPublishDate DATE null
);

create table RepositoryEntry (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	repositoryEntryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	repositoryId LONG,
	mappedId VARCHAR(255) null,
	manualCheckInRequired BOOLEAN,
	lastPublishDate DATE null
);

create table ResourceAction (
	mvccVersion LONG default 0,
	resourceActionId LONG not null primary key,
	name VARCHAR(255) null,
	actionId VARCHAR(75) null,
	bitwiseValue LONG
);

create table ResourceBlock (
	companyId LONG,
	mvccVersion LONG default 0,
	resourceBlockId LONG not null primary key,
	groupId LONG,
	name VARCHAR(75) null,
	permissionsHash VARCHAR(75) null,
	referenceCount LONG
);

create table ResourceBlockPermission (
	companyId LONG,
	mvccVersion LONG default 0,
	resourceBlockPermissionId LONG not null primary key,
	resourceBlockId LONG,
	roleId LONG,
	actionIds LONG
);

create table ResourcePermission (
	companyId LONG,
	mvccVersion LONG default 0,
	resourcePermissionId LONG not null primary key,
	name VARCHAR(255) null,
	scope INTEGER,
	primKey VARCHAR(255) null,
	primKeyId LONG,
	roleId LONG,
	ownerId LONG,
	actionIds LONG,
	viewActionId BOOLEAN
);

create table ResourceTypePermission (
	companyId LONG,
	mvccVersion LONG default 0,
	resourceTypePermissionId LONG not null primary key,
	groupId LONG,
	name VARCHAR(75) null,
	roleId LONG,
	actionIds LONG
);

create table Role_ (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	roleId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	name VARCHAR(75) null,
	title STRING null,
	description STRING null,
	type_ INTEGER,
	subtype VARCHAR(75) null,
	lastPublishDate DATE null
);

create table SCFrameworkVersi_SCProductVers (
	companyId LONG not null,
	frameworkVersionId LONG not null,
	productVersionId LONG not null,
	primary key (companyId, frameworkVersionId, productVersionId)
);

create table SCFrameworkVersion (
	companyId LONG,
	frameworkVersionId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null,
	active_ BOOLEAN,
	priority INTEGER
);

create table SCLicense (
	companyId LONG,
	licenseId LONG not null primary key,
	name VARCHAR(75) null,
	url STRING null,
	openSource BOOLEAN,
	active_ BOOLEAN,
	recommended BOOLEAN
);

create table SCLicenses_SCProductEntries (
	companyId LONG not null,
	licenseId LONG not null,
	productEntryId LONG not null,
	primary key (companyId, licenseId, productEntryId)
);

create table SCProductEntry (
	companyId LONG,
	productEntryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	type_ VARCHAR(75) null,
	tags VARCHAR(255) null,
	shortDescription STRING null,
	longDescription STRING null,
	pageURL STRING null,
	author VARCHAR(75) null,
	repoGroupId VARCHAR(75) null,
	repoArtifactId VARCHAR(75) null
);

create table SCProductScreenshot (
	companyId LONG,
	productScreenshotId LONG not null primary key,
	groupId LONG,
	productEntryId LONG,
	thumbnailId LONG,
	fullImageId LONG,
	priority INTEGER
);

create table SCProductVersion (
	companyId LONG,
	productVersionId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	productEntryId LONG,
	version VARCHAR(75) null,
	changeLog STRING null,
	downloadPageURL STRING null,
	directDownloadURL VARCHAR(2000) null,
	repoStoreArtifact BOOLEAN
);

create table ServiceComponent (
	companyId LONG,
	mvccVersion LONG default 0,
	serviceComponentId LONG not null primary key,
	buildNamespace VARCHAR(75) null,
	buildNumber LONG,
	buildDate LONG,
	data_ TEXT null
);

create table SocialActivity (
	companyId LONG,
	activityId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate LONG,
	activitySetId LONG,
	mirrorActivityId LONG,
	classNameId LONG,
	classPK LONG,
	parentClassNameId LONG,
	parentClassPK LONG,
	type_ INTEGER,
	extraData STRING null,
	receiverUserId LONG
);

create table SocialActivityAchievement (
	companyId LONG,
	activityAchievementId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate LONG,
	name VARCHAR(75) null,
	firstInGroup BOOLEAN
);

create table SocialActivityCounter (
	companyId LONG,
	activityCounterId LONG not null primary key,
	groupId LONG,
	classNameId LONG,
	classPK LONG,
	name VARCHAR(75) null,
	ownerType INTEGER,
	currentValue INTEGER,
	totalValue INTEGER,
	graceValue INTEGER,
	startPeriod INTEGER,
	endPeriod INTEGER,
	active_ BOOLEAN
);

create table SocialActivityLimit (
	companyId LONG,
	activityLimitId LONG not null primary key,
	groupId LONG,
	userId LONG,
	classNameId LONG,
	classPK LONG,
	activityType INTEGER,
	activityCounterName VARCHAR(75) null,
	value VARCHAR(75) null
);

create table SocialActivitySet (
	companyId LONG,
	activitySetId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate LONG,
	modifiedDate LONG,
	classNameId LONG,
	classPK LONG,
	type_ INTEGER,
	extraData STRING null,
	activityCount INTEGER
);

create table SocialActivitySetting (
	companyId LONG,
	activitySettingId LONG not null primary key,
	groupId LONG,
	classNameId LONG,
	activityType INTEGER,
	name VARCHAR(75) null,
	value VARCHAR(1024) null
);

create table SocialRelation (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	relationId LONG not null primary key,
	createDate LONG,
	userId1 LONG,
	userId2 LONG,
	type_ INTEGER
);

create table SocialRequest (
	companyId LONG,
	uuid_ VARCHAR(75) null,
	requestId LONG not null primary key,
	groupId LONG,
	userId LONG,
	createDate LONG,
	modifiedDate LONG,
	classNameId LONG,
	classPK LONG,
	type_ INTEGER,
	extraData STRING null,
	receiverUserId LONG,
	status INTEGER
);

create table Subscription (
	companyId LONG,
	mvccVersion LONG default 0,
	subscriptionId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	frequency VARCHAR(75) null
);

create table SystemEvent (
	companyId LONG,
	mvccVersion LONG default 0,
	systemEventId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	classUuid VARCHAR(75) null,
	referrerClassNameId LONG,
	parentSystemEventId LONG,
	systemEventSetKey LONG,
	type_ INTEGER,
	extraData TEXT null
);

create table Team (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	teamId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	groupId LONG,
	name VARCHAR(75) null,
	description STRING null,
	lastPublishDate DATE null
);

create table Ticket (
	companyId LONG,
	mvccVersion LONG default 0,
	ticketId LONG not null primary key,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	key_ VARCHAR(75) null,
	type_ INTEGER,
	extraInfo TEXT null,
	expirationDate DATE null
);

create table TrashEntry (
	companyId LONG,
	entryId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	classNameId LONG,
	classPK LONG,
	systemEventSetKey LONG,
	typeSettings TEXT null,
	status INTEGER
);

create table TrashVersion (
	companyId LONG,
	versionId LONG not null primary key,
	entryId LONG,
	classNameId LONG,
	classPK LONG,
	typeSettings TEXT null,
	status INTEGER
);

create table UserNotificationDelivery (
	companyId LONG,
	mvccVersion LONG default 0,
	userNotificationDeliveryId LONG not null primary key,
	userId LONG,
	portletId VARCHAR(200) null,
	classNameId LONG,
	notificationType INTEGER,
	deliveryType INTEGER,
	deliver BOOLEAN
);

create table User_ (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	userId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	defaultUser BOOLEAN,
	contactId LONG,
	password_ VARCHAR(75) null,
	passwordEncrypted BOOLEAN,
	passwordReset BOOLEAN,
	passwordModifiedDate DATE null,
	digest VARCHAR(255) null,
	reminderQueryQuestion VARCHAR(75) null,
	reminderQueryAnswer VARCHAR(75) null,
	graceLoginCount INTEGER,
	screenName VARCHAR(75) null,
	emailAddress VARCHAR(75) null,
	facebookId LONG,
	ldapServerId LONG,
	openId VARCHAR(1024) null,
	portraitId LONG,
	languageId VARCHAR(75) null,
	timeZoneId VARCHAR(75) null,
	greeting VARCHAR(255) null,
	comments STRING null,
	firstName VARCHAR(75) null,
	middleName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	jobTitle VARCHAR(100) null,
	loginDate DATE null,
	loginIP VARCHAR(75) null,
	lastLoginDate DATE null,
	lastLoginIP VARCHAR(75) null,
	lastFailedLoginDate DATE null,
	failedLoginAttempts INTEGER,
	lockout BOOLEAN,
	lockoutDate DATE null,
	agreedToTermsOfUse BOOLEAN,
	emailAddressVerified BOOLEAN,
	lastPublishDate DATE null,
	status INTEGER
);

create table UserGroup (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	userGroupId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentUserGroupId LONG,
	name VARCHAR(75) null,
	description STRING null,
	addedByLDAPImport BOOLEAN,
	lastPublishDate DATE null
);

create table UserGroupGroupRole (
	companyId LONG,
	mvccVersion LONG default 0,
	userGroupId LONG not null,
	groupId LONG not null,
	roleId LONG not null,
	primary key (userGroupId, groupId, roleId)
);

create table UserGroupRole (
	companyId LONG,
	mvccVersion LONG default 0,
	userId LONG not null,
	groupId LONG not null,
	roleId LONG not null,
	primary key (userId, groupId, roleId)
);

create table UserGroups_Teams (
	companyId LONG not null,
	teamId LONG not null,
	userGroupId LONG not null,
	primary key (companyId, teamId, userGroupId)
);

create table UserIdMapper (
	companyId LONG,
	mvccVersion LONG default 0,
	userIdMapperId LONG not null primary key,
	userId LONG,
	type_ VARCHAR(75) null,
	description VARCHAR(75) null,
	externalUserId VARCHAR(75) null
);

create table UserNotificationEvent (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	userNotificationEventId LONG not null primary key,
	userId LONG,
	type_ VARCHAR(75) null,
	timestamp LONG,
	deliveryType INTEGER,
	deliverBy LONG,
	delivered BOOLEAN,
	payload TEXT null,
	actionRequired BOOLEAN,
	archived BOOLEAN
);

create table Users_Groups (
	companyId LONG not null,
	groupId LONG not null,
	userId LONG not null,
	primary key (companyId, groupId, userId)
);

create table Users_Orgs (
	companyId LONG not null,
	organizationId LONG not null,
	userId LONG not null,
	primary key (companyId, organizationId, userId)
);

create table Users_Roles (
	companyId LONG not null,
	roleId LONG not null,
	userId LONG not null,
	primary key (companyId, roleId, userId)
);

create table Users_Teams (
	companyId LONG not null,
	teamId LONG not null,
	userId LONG not null,
	primary key (companyId, teamId, userId)
);

create table Users_UserGroups (
	companyId LONG not null,
	userId LONG not null,
	userGroupId LONG not null,
	primary key (companyId, userId, userGroupId)
);

create table UserTracker (
	companyId LONG,
	mvccVersion LONG default 0,
	userTrackerId LONG not null primary key,
	userId LONG,
	modifiedDate DATE null,
	sessionId VARCHAR(200) null,
	remoteAddr VARCHAR(75) null,
	remoteHost VARCHAR(75) null,
	userAgent VARCHAR(200) null
);

create table UserTrackerPath (
	companyId LONG,
	mvccVersion LONG default 0,
	userTrackerPathId LONG not null primary key,
	userTrackerId LONG,
	path_ STRING null,
	pathDate DATE null
);

create table VirtualHost (
	mvccVersion LONG default 0,
	virtualHostId LONG not null primary key,
	companyId LONG,
	layoutSetId LONG,
	hostname VARCHAR(75) null
);

create table WebDAVProps (
	companyId LONG,
	mvccVersion LONG default 0,
	webDavPropsId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	props TEXT null
);

create table Website (
	companyId LONG,
	mvccVersion LONG default 0,
	uuid_ VARCHAR(75) null,
	websiteId LONG not null primary key,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	url STRING null,
	typeId LONG,
	primary_ BOOLEAN,
	lastPublishDate DATE null
);

create table WorkflowDefinitionLink (
	companyId LONG,
	mvccVersion LONG default 0,
	workflowDefinitionLinkId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	typePK LONG,
	workflowDefinitionName VARCHAR(75) null,
	workflowDefinitionVersion INTEGER
);

create table WorkflowInstanceLink (
	companyId LONG,
	mvccVersion LONG default 0,
	workflowInstanceLinkId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	workflowInstanceId LONG
);