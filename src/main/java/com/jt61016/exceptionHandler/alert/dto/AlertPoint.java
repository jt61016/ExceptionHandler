package com.jt61016.exceptionHandler.alert.dto;

import com.jt61016.exceptionHandler.alert.constant.AlertTypeEnum;
import lombok.Data;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午3:27.
 */
@Data
public class AlertPoint {
    /**
     * 告警类型
     */
    private AlertTypeEnum alertTypeEnum;

    /**
     * 发生异常的类名
     */
    private String occurredClass;

    /**
     * 发生异常的方法名
     */
    private String occurredMethod;

    /**
     * 异常类型
     */
    private String exceptionType;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 业务参数,如方法入参 MQ消息体
     */
    private String bussinessParam;

    /**
     * 业务类型
     */
    private String bussinessType;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AlertPoint: [");
        sb.append(this.occurredClass);
        sb.append(" - ");
        sb.append(this.occurredMethod);
        sb.append("发生 ");
        sb.append(this.exceptionType);
        sb.append(" 类型的异常 ");
        sb.append(this.exceptionMessage);
        sb.append(". 业务参数:");
        sb.append(this.bussinessParam);
        return sb.toString();
    }
}
