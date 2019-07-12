package com.lili.common;

/**
 * 支付类型的枚举
 */
public enum OrderStatusEnum {
    ORDER_CANCELED(0, "已取消"),
    ORDER_NON_PAYMENT(10, "未付款"),
    ORDER_HAS_PAYMENT(20, "已付款"),
    ORDER_HAS_DELIVER(40, "已发货"),
    ORDER_BUSINESS_SUCCESS(50, "交易成功"),
    ORDER_BUSINESS_CLOSE(60, "交易关闭")
    ;
    private int status;
    private String desc;
    OrderStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 遍历枚举
     * @param status
     * @return
     */

    public static OrderStatusEnum codeOf(Integer status) {
        for (OrderStatusEnum orderStatusEnum : values()) {
            if (orderStatusEnum.getStatus() == status) {
                return orderStatusEnum;
            }
        }
        return null;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
