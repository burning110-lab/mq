package com.qu.lele.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * msg_log
 * @author 
 */
@Data
public class MsgLog implements Serializable {
    /**
     * 消息唯一标识
     */
    private String id;

    /**
     * 消息体, json格式化
     */
    private String msg;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由键
     */
    private String routingkey;

    /**
     * 状态: 0投递中 1投递成功 2投递失败 3已消费
     */
    private Integer status;

    /**
     * 重试次数
     */
    private Integer trycount;

    /**
     * 下一次重试时间
     */
    private Long nexttrytime;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 更新时间
     */
    private String updatetime;

    private String alertid;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MsgLog other = (MsgLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
            && (this.getExchange() == null ? other.getExchange() == null : this.getExchange().equals(other.getExchange()))
            && (this.getRoutingkey() == null ? other.getRoutingkey() == null : this.getRoutingkey().equals(other.getRoutingkey()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTrycount() == null ? other.getTrycount() == null : this.getTrycount().equals(other.getTrycount()))
            && (this.getNexttrytime() == null ? other.getNexttrytime() == null : this.getNexttrytime().equals(other.getNexttrytime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getAlertid() == null ? other.getAlertid() == null : this.getAlertid().equals(other.getAlertid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getExchange() == null) ? 0 : getExchange().hashCode());
        result = prime * result + ((getRoutingkey() == null) ? 0 : getRoutingkey().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTrycount() == null) ? 0 : getTrycount().hashCode());
        result = prime * result + ((getNexttrytime() == null) ? 0 : getNexttrytime().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getAlertid() == null) ? 0 : getAlertid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", msg=").append(msg);
        sb.append(", exchange=").append(exchange);
        sb.append(", routingkey=").append(routingkey);
        sb.append(", status=").append(status);
        sb.append(", trycount=").append(trycount);
        sb.append(", nexttrytime=").append(nexttrytime);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", alertid=").append(alertid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}