package com.jt61016.exceptionHandler.alert.service.impl;

import com.jt61016.exceptionHandler.alert.dto.AlertPoint;
import com.jt61016.exceptionHandler.alert.service.AlertFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午4:33.
 */
@Component
@Slf4j
public abstract class AbstractAlertFilter implements AlertFilter {

    @Override
    public void executeAlert(AlertPoint alertPoint) {
        if (allowAlert(alertPoint)) {
            //TODO: 这里是实现报警的地方
            System.out.println(alertPoint);
        } else {
            log.warn("Alert switch closed - {}", alertPoint);
        }
    }

    abstract boolean allowAlert(AlertPoint alertPoint);

}
