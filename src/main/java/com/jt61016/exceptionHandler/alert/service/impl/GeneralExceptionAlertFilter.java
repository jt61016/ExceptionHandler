package com.jt61016.exceptionHandler.alert.service.impl;

import com.jt61016.exceptionHandler.alert.constant.AlertTypeEnum;
import com.jt61016.exceptionHandler.alert.dto.AlertPoint;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午5:08.
 */
@Component
public class GeneralExceptionAlertFilter extends AbstractAlertFilter {
    @Override
    boolean allowAlert(AlertPoint alertPoint) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (8 > hour) {
            return false;
        }
        return true;
    }

    @Override
    public AlertTypeEnum supportAlertType() {
        return AlertTypeEnum.GENEAL_EXCEPTION;
    }
}
