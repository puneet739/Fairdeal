package com.fairdeal.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fairdeal.test.daotest.AgentDaoTest;
import com.fairdeal.test.daotest.ClassifiedDaoTest;
import com.fairdeal.test.daotest.UserDaoTest;
import com.fairdeal.test.misc.UtilTest;
import com.fairdeal.test.servicetest.AgentsServiceTest;
import com.fairdeal.test.servicetest.ClassifiedServiceTest;
import com.fairdeal.test.servicetest.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	ClassifiedDaoTest.class,
	UserDaoTest.class,
	AgentDaoTest.class,
	UtilTest.class,
	AgentsServiceTest.class,
	ClassifiedServiceTest.class,
	UserServiceTest.class
})
public class TestSuite {
}
