package com.faderw.stjob.cli;

import com.faderw.stjob.mapper.ScheduleJobMapper;
import com.faderw.stjob.model.domain.ScheduleJob;
import com.faderw.stjob.model.vo.ResultVO;
import com.faderw.stjob.schedule.service.IJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author FaderW
 * 2019/7/27
 */
@RestController
@RequestMapping("/cli")
public class SchedulerController {

    @Autowired
    ScheduleJobMapper scheduleJobMapper;

    @Autowired
    IJobService jobService;

    @RequestMapping("/schedule/{id}")
    public Object schedule(@PathVariable("id") long id) throws SchedulerException {
        ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(id);
        jobService.schedule(job);

        return ResultVO.success();
    }

    @RequestMapping("/unSchedule/{id}")
    public Object unSchedule(@PathVariable("id") long id) throws SchedulerException {
        ScheduleJob job = scheduleJobMapper.selectByPrimaryKey(id);
        jobService.unschedule(job);

        return ResultVO.success();
    }

    @PostMapping(value = "/job", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object createJob(@RequestBody ScheduleJob scheduleJob) {
        scheduleJobMapper.insert(scheduleJob);

        return ResultVO.success(scheduleJob);
    }

    @GetMapping(value = "/job/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getJob(@PathVariable("id") long id) {
        return scheduleJobMapper.selectByPrimaryKey(id);
    }
}
