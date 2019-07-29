package com.faderw.stjob.schedule.job;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

/**
 * @author FaderW
 * 2019/6/4
 */
@Slf4j
public class HelloJob extends AbstractJob {

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Params implements Serializable{
        private static final long serialVersionUID = -1024903891521202664L;
        String startStr;
        String endStr;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Params params = getParams(Params.class);
        log.info("[execute job] params {}", JSON.toJSONString(params));
        System.out.println("execute hello world");
    }
}
