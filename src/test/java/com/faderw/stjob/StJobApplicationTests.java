package com.faderw.stjob;

import com.alibaba.fastjson.JSON;
import com.faderw.stjob.mapper.ScheduleJobMapper;
import com.faderw.stjob.model.domain.ScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StJobApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	ScheduleJobMapper scheduleJobMapper;

	@Test
	public void test() {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobClass("com.faderw.stjob.schedule.job.HelloJob");
		scheduleJob.setCronExpression("0/10 * * * * ? *");
		scheduleJob.setJobName("Hello Quartz");
		scheduleJob.setParam("{}");

		scheduleJobMapper.insert(scheduleJob);
		System.out.println(scheduleJob.getId());
		System.out.println(JSON.toJSONString(scheduleJob));
	}

}
