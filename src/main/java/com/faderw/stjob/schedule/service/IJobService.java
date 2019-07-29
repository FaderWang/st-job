package com.faderw.stjob.schedule.service;


import com.faderw.stjob.model.domain.ScheduleJob;
import org.quartz.SchedulerException;

/**
 * @author FaderW
 * 2019/6/4
 */

public interface IJobService {

    /**
     * 调度任务
     * @param scheduleJob
     */
    void schedule(ScheduleJob scheduleJob) throws SchedulerException;

    /**
     * 取消任务调度
     * @param scheduleJob
     */
    void unschedule(ScheduleJob scheduleJob) throws SchedulerException;
}
