package com.qu.lele.dto;

import java.io.Serializable;

/**
 * @author: 屈光乐
 * @create: 2022-03-10 15-03
 */

public class MessageDto implements Serializable {
    private static final long serialVersionUID = -6636409181859841093L;
    private String msg;
    public MessageDto() {
    }
    public MessageDto(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
