package com.qu.lele.dto;

/**
 * @author: 屈光乐
 * @create: 2022-03-13 22-24
 */
public enum MsgStatus {
    Deliverying(0,"投递中"),
    DeliverySuccess(1,"投递成功"),
    DeliveryFail(2,"投递失败"),
    Consumption(3,"已消费");

    private int status;
    private String description;

    MsgStatus(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
