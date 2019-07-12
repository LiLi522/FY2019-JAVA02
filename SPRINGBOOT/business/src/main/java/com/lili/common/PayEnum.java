package com.lili.common;

public enum PayEnum {
    ONLINE(1, "在线支付")
    ;
    private int pay;
    private String desc;
    PayEnum(int pay, String desc) {
        this.pay = pay;
        this.desc = desc;
    }
    /**
     * 遍历枚举
     * @param status
     * @return
             */

    public static PayEnum codeOf(Integer status) {
        for (PayEnum payEnum : values()) {
            if (payEnum.getPay() == status) {
                return payEnum;
            }
        }
        return null;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
