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

package com.liferay.polls.service.persistence.test;

import com.liferay.arquillian.container.enricher.Inject;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.model.impl.PollsVoteModelImpl;
import com.liferay.polls.service.persistence.PollsVotePersistence;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.test.RandomTestUtil;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* @generated HOLA
*/
@RunWith(Arquillian.class)
@Transactional(propagation = Propagation.REQUIRED)
public class PollsVotePersistenceTest {
	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PollsVote newPollsVote = addPollsVote();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PollsVote.class,
			PollsVote.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("voteId",
			newPollsVote.getVoteId()));

		List<PollsVote> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PollsVote existingPollsVote = result.get(0);

		Assert.assertEquals(existingPollsVote, newPollsVote);
	}

	protected PollsVote addPollsVote() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PollsVote pollsVote = _persistence.create(pk);

		pollsVote.setUuid(RandomTestUtil.randomString());

		pollsVote.setGroupId(RandomTestUtil.nextLong());

		pollsVote.setCompanyId(RandomTestUtil.nextLong());

		pollsVote.setUserId(RandomTestUtil.nextLong());

		pollsVote.setUserName(RandomTestUtil.randomString());

		pollsVote.setCreateDate(RandomTestUtil.nextDate());

		pollsVote.setModifiedDate(RandomTestUtil.nextDate());

		pollsVote.setQuestionId(RandomTestUtil.nextLong());

		pollsVote.setChoiceId(RandomTestUtil.nextLong());

		pollsVote.setVoteDate(RandomTestUtil.nextDate());

		_pollsVotes.add(_persistence.update(pollsVote));

		return pollsVote;
	}

	private static Log _log = LogFactoryUtil.getLog(PollsVotePersistenceTest.class);

	@Inject
	private PollsVotePersistence _persistence;

	private List<PollsVote> _pollsVotes = new ArrayList<PollsVote>();
}