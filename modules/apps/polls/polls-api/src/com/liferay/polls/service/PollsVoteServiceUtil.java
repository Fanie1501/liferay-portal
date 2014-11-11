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

package com.liferay.polls.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides the remote service utility for PollsVote. This utility wraps
 * {@link com.liferay.polls.service.impl.PollsVoteServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PollsVoteService
 * @see com.liferay.polls.service.base.PollsVoteServiceBaseImpl
 * @see com.liferay.polls.service.impl.PollsVoteServiceImpl
 * @generated
 */
@ProviderType
public class PollsVoteServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.polls.service.impl.PollsVoteServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.polls.model.PollsVote addVote(long questionId,
		long choiceId, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addVote(questionId, choiceId, serviceContext);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static PollsVoteService getService() {
		return _service;
	}

	static void setService(PollsVoteService service) {
		_service = service;
	}

	private static PollsVoteService _service;
}