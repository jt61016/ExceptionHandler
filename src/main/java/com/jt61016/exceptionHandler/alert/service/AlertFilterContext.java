package com.jt61016.exceptionHandler.alert.service;

import com.jt61016.exceptionHandler.alert.constant.AlertTypeEnum;
import com.jt61016.exceptionHandler.alert.dto.AlertPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午4:06.
 */
@Component
@Slf4j
public class AlertFilterContext implements InitializingBean, ApplicationContextAware {
    ApplicationContext applicationContext;

    private Map<AlertTypeEnum, AlertFilter> ALERT_FILTER_MAP = new ConcurrentHashMap<>(16);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<AlertFilter> alertFilters = applicationContext.getBeansOfType(AlertFilter.class).values();
        alertFilters.forEach(service -> ALERT_FILTER_MAP.put(service.supportAlertType(), service));
    }

    public void executeAlert(AlertPoint alertPoint) {
        AlertTypeEnum alertTypeEnum = alertPoint.getAlertTypeEnum();
        AlertFilter alertFilter = ALERT_FILTER_MAP.get(alertTypeEnum);
        if (null == alertFilter) {
            log.error("No support alert type ", alertTypeEnum);
            return;
        }
        alertFilter.executeAlert(alertPoint);
    }
}
