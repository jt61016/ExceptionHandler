package com.jt61016.exceptionHandler.alert.service;

import com.jt61016.exceptionHandler.alert.constant.AlertTypeEnum;
import com.jt61016.exceptionHandler.alert.dto.AlertPoint;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午4:07.
 */
public interface AlertFilter {
    AlertTypeEnum supportAlertType();

    void executeAlert(AlertPoint alertPoint);
}
