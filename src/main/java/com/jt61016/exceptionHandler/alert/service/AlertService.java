package com.jt61016.exceptionHandler.alert.service;

import com.jt61016.exceptionHandler.alert.dto.AlertPoint;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午3:24.
 */
public interface AlertService {
    void collect(AlertPoint alertPoint);
}
