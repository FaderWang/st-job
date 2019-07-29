package com.faderw.stjob.exception;

import com.faderw.stjob.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author FaderW
 * 2019/7/29
 */
@ControllerAdvice
@Slf4j
public class JobExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity handlerControllerException(HttpServletRequest request, Throwable throwable) {
        HttpStatus httpStatus = getStatus(request);
        if (throwable instanceof SchedulerException) {
            log.error("schedule job execute error ", throwable);
            return new ResponseEntity(ResultVO.error(throwable.getMessage()), httpStatus);
        } else {
            log.error("server internal error ", throwable);
            return new ResponseEntity(ResultVO.error(throwable.getMessage()), httpStatus);
        }
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer status_code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status_code == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(status_code);
    }
}
