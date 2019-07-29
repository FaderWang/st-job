package com.faderw.stjob.model.domain;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author FaderW
 * 2019/6/4
 */
@Table(name = "schedule_job")
@NameStyle(value = Style.normal)
@Getter
@Setter
public class ScheduleJob implements Serializable{

    private static final long serialVersionUID = -5216643996253135760L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String jobClass;

    private String jobName;

    private String param;

    private String cronExpression;

    private String description;
}
