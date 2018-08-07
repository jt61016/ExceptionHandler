package com.jt61016.exceptionHandler.alert.constant;

/**
 * @Author jiangtao
 * @Date 2018/8/7 下午4:08.
 */
public enum AlertTypeEnum {
    GENEAL_EXCEPTION("general_exception", "普通程序异常");

    String name;
    String desc;

    AlertTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
