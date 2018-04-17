package com.zzz.draw.handler.impl;

import com.google.protobuf.MessageLite;
import com.zzz.draw.handler.AbstractMessageHandler;
import com.zzz.draw.pojo.UserInfo;
import com.zzz.draw.server.ServerApplication;
import com.zzz.game.message.ProtocolMessage;
import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by zha on 2018/4/17.
 */

public class LineMessageHandler extends AbstractMessageHandler {

    @Override
    protected MessageLite exec(ChannelHandlerContext ctx, ProtocolMessage message) throws Exception {
        Collection<UserInfo> list = ServerApplication.getUserList();
        list.stream().filter(userInfo -> !userInfo.getId().equals(message.getSessionId())).forEach(userInfo -> {
            ProtocolMessage clone = message.clone();
            clone.setSessionId(userInfo.getId());
            userInfo.getCtx().writeAndFlush(clone);
            System.out.println("给用户发送:"+userInfo);
        });
        return null;
    }
}
