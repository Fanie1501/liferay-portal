create table BackgroundTask (
	companyId LONG,
	mvccVersion LONG default 0,
	backgroundTaskId LONG not null primary key,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(255) null,
	servletContextNames VARCHAR(255) null,
	taskExecutorClassName VARCHAR(200) null,
	taskContextMap TEXT null,
	completed BOOLEAN,
	completionDate DATE null,
	status INTEGER,
	statusMessage TEXT null
);