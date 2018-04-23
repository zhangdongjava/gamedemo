package com.zzz.draw.handler.impl;

import com.google.protobuf.MessageLite;
import com.zzz.draw.handler.AbstractMessageHandler;
import com.zzz.draw.pojo.UserInfo;
import com.zzz.draw.server.ServerApplication;
import com.zzz.draw.util.PlayerUtil;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto.ReadyReq;
import com.zzz.game.proto.DrawMessageProto.ReadyResp;
import com.zzz.game.proto.DrawMessageProto.TimeLimitResp;
import com.zzz.game.proto.DrawMessageProto.UserMessageReq;
import com.zzz.game.proto.DrawMessageProto.UserMessageResp;

import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by zha on 2018/4/17.
 */

public class ReadyMessageMessageHandler extends AbstractMessageHandler {

    @Override
    protected MessageLite exec(ChannelHandlerContext ctx, ProtocolMessage message) throws Exception {
    	ReadyReq req = ReadyReq.parseFrom(message.getBuf());
       	int  code =   req.getCode();
        UserInfo userInfo = ServerApplication.getUserInfo(message.getSessionId());
        userInfo.setReadyCode(code);
       	ReadyResp.Builder builder = ReadyResp.newBuilder();
       	builder.setCode(1);
       	Collection<UserInfo> list = ServerApplication.getUserList();
       	for (UserInfo userInfo2 : list) {
       		if(userInfo2.getReadyCode().intValue()!=1){
       			return builder.build();
       		}
		}
       	Integer id=ServerApplication.context.getBean(PlayerUtil.class).luckyPlayer();
		if(id == null){
			return builder.build();
		}
       	TimeLimitResp.Builder timeBuilder = TimeLimitResp.newBuilder();
       	timeBuilder.setTime(100);
        byte[] buf = timeBuilder.build().toByteArray();
        
       	list.forEach(userInfou -> {
            ProtocolMessage clone = message.clone();
            clone.setBuf(buf);
            clone.setSessionId(userInfou.getId());
            userInfou.getCtx().writeAndFlush(clone);
        });
       	return null;
    }
}
