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

package test;

import com.liferay.polls.exception.NoSuchVoteException;
import com.liferay.polls.model.PollsVote;
import com.liferay.polls.model.impl.PollsVoteModelImpl;
import com.liferay.polls.service.PollsVoteLocalServiceUtil;
import com.liferay.polls.service.persistence.PollsVotePersistence;
import com.liferay.polls.service.persistence.PollsVoteUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.test.RandomTestUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.liferay.arquillian.container.enricher.Inject;

/**
 * @generated
 */
@RunWith(Arquillian.class)
@Transactional(propagation = Propagation.REQUIRED)
public class PollsVotePersistenceTest {
	@After
	public void tearDown() throws Exception {
		Iterator<PollsVote> iterator = _pollsVotes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PollsVote pollsVote = _persistence.create(pk);

		Assert.assertNotNull(pollsVote);

		Assert.assertEquals(pollsVote.getPrimaryKey(), pk);
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
	private List<PollsVote> _pollsVotes = new ArrayList<PollsVote>();
	@Inject
	private PollsVotePersistence _persistence;
}