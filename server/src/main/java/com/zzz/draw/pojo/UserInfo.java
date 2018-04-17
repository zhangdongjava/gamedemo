package com.zzz.draw.pojo;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zha on 2018/4/17.
 */
public class UserInfo {

    private ChannelHandlerContext ctx;

    private Integer id;

    private String name;

    public UserInfo(ChannelHandlerContext ctx, Integer id, String name) {
        this.ctx = ctx;
        this.id = id;
        this.name = name;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("ctx=").append(ctx);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
