package com.faderw.stjob.schedule.service;

import com.alibaba.fastjson.JSON;
import com.faderw.stjob.model.domain.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author FaderW
 * 2019/6/4
 */
@Service
@Slf4j
public class JobService implements IJobService {

    @Autowired
    Scheduler quartzScheduler;

    @Override
    public void schedule(ScheduleJob scheduleJob) throws SchedulerException {
        log.info("[schedule job] job body {}", JSON.toJSONString(scheduleJob));
        if (Objects.isNull(scheduleJob)) {
            throw new SchedulerException("invalid id for scheduleJob");
        }
        Class classz = null;
        try {
            classz = Class.forName(scheduleJob.getJobClass());
        } catch (ClassNotFoundException e) {
            log.error("[schedule job]can not load class {}", scheduleJob.getJobClass());
        }
        String name = scheduleJob.getJobName();
        JobDetail jobDetail = newJob(classz)
                .withIdentity(name)
                .usingJobData("params", scheduleJob.getParam())
                .build();

        Trigger trigger = newTrigger().forJob(name)
                .withSchedule(cronSchedule(scheduleJob.getCronExpression()))
                .withIdentity("trigger_" + name)
                .startNow()
                .build();

        try {
            quartzScheduler.deleteJob(jobKey(name));
        } catch (SchedulerException e) {
            log.error("[schedule job]delete job error", e);
        }
        quartzScheduler.scheduleJob(jobDetail, trigger);
        log.info("[schedule job]succeed jobId {}, jobName {}", scheduleJob.getId(), name);
    }

    @Override
    public void unschedule(ScheduleJob scheduleJob) throws SchedulerException {
        quartzScheduler.deleteJob(jobKey(scheduleJob.getJobName()));
        log.info("[unSchedule] job]success jobId {}, jobName {}", scheduleJob.getId(), scheduleJob.getJobName());
    }
}
