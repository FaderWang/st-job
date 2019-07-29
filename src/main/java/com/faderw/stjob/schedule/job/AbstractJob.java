package com.faderw.stjob.schedule.job;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author FaderW
 * 2019/6/4
 */
@DisallowConcurrentExecution
public abstract class AbstractJob extends QuartzJobBean {

    @Getter
    @Setter
    protected String params;


    public <T> T getParams(Class<T> tClass) {
        T obj = JSONObject.parseObject(params, tClass);
        return obj;
    }
}
