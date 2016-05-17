package com.wangfj.product.core.controller.support;

import javax.validation.constraints.NotNull;

/**
 * 销售渠道Para
 *
 * @Class Name PcmChannelPara
 * @Author wangxuan
 * @Create In 2015-8-20
 */
public class PcmChannelPara {

    private String sid;

    @NotNull(message = "{PcmChannelPara.channelName.isNotNull}")
    private String channelName;// 销售渠道名称

    @NotNull(message = "{PcmChannelPara.channelCode.isNotNull}")
    private String channelCode;// 销售渠道编码

    @NotNull(message = "{PcmChannelPara.status.isNotNull}")
    private String status;// 状态

    private String optUser;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser == null ? null : optUser.trim();
    }

}
