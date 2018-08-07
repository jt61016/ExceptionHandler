package com.jt61016.exceptionHandler.alert.service.impl;

import com.jt61016.exceptionHandler.alert.dto.AlertPoint;
import com.jt61016.exceptionHandler.alert.service.AlertFilterContext;
import com.jt61016.exceptionHandler.alert.service.AlertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午3:03.
 */
@Component
@Slf4j
public class DefaultAlertService implements AlertService {

    @Autowired
    private AlertFilterContext alertFilterContext;

    private BlockingQueue<AlertPoint> alertPointBlockingQueue;

    private Thread writeThread;

    private boolean isAlertSwitchOpen = true;

    private volatile boolean running = true;

    @PostConstruct
    private void init() {
        alertPointBlockingQueue = new LinkedBlockingQueue<AlertPoint>(10000);
        writeThread = new Thread(
            new Runnable() {
                public void run() {
                    while(running) {
                        try {
                            // 执行报警操作
                            executeAlert();
                        } catch (Throwable t) {
                            log.error("Unexpected error occur when consume alert, cause: {}.  {}", t.getMessage(), t);
                            try {
                                Thread.sleep(5000);
                            } catch (Throwable t2) {

                            }
                        }
                    }
                }
            }
        );
        writeThread.setName("Exception-Alert-Worker");
        writeThread.setDaemon(true);
        writeThread.start();
    }

    private void executeAlert() throws Exception {
        AlertPoint alertPoint = alertPointBlockingQueue.take();
        alertFilterContext.executeAlert(alertPoint);
    }


    public void collect(AlertPoint alertPoint) {
        if (isAlertSwitchOpen) {
            alertPointBlockingQueue.offer(alertPoint);
        }
    }
}
