package com.jt61016.exceptionHandler;

import com.jt61016.exceptionHandler.alert.service.AlertService;
import com.jt61016.exceptionHandler.test.service.ErrorService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author jiangtao
 * @Date 2018/8/26 下午3:41.
 */
@Component
public class Application {
    public static void main(String[] args) throws InterruptedException {
        //ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
        context.start();

        Thread.sleep(1000);
        ErrorService errorService = (ErrorService)context.getBean(ErrorService.class);
        errorService.throwException();
        Thread.sleep(5000);
        context.stop();
    }
}
