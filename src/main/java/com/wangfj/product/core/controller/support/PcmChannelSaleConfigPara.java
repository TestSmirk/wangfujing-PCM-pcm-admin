package com.wangfj.product.core.controller.support;

public class PcmChannelSaleConfigPara {

    private Long sid;

    private Long shoppeProSid; /* 专柜SID */

    private String channelSid; /* 渠道编码 */

    private Integer saleStauts; /* 状态 */

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getShoppeProSid() {
        return shoppeProSid;
    }

    public void setShoppeProSid(Long shoppeProSid) {
        this.shoppeProSid = shoppeProSid;
    }

    public String getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(String channelSid) {
        this.channelSid = channelSid;
    }

    public Integer getSaleStauts() {
        return saleStauts;
    }

    public void setSaleStauts(Integer saleStauts) {
        this.saleStauts = saleStauts;
    }

    @Override
    public String toString() {
        return "PcmChannelSaleConfigPara [sid=" + sid + ", shoppeProSid=" + shoppeProSid
                + ", channelSid=" + channelSid + ", saleStauts=" + saleStauts + "]";
    }
}