package com.task.quartz;

import spring.framework.base.BaseJUnit4SpringContextTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DataWorkContext Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 25, 2016</pre>
 */
public class QuartzServiceTest extends BaseJUnit4SpringContextTests {

    @Autowired
    private QuartzService quartzService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addJob(ScheduleJob scheduleJob)
     */
    @Test
    public void testAddJob() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: getAllJob()
     */
    @Test
    public void testGetAllJob() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: run()
     */
    @Test
    public void testInit() throws Exception {
        quartzService.init();
    }
}
