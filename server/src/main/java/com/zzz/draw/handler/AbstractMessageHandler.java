package com.zzz.draw.handler;

import com.google.protobuf.MessageLite;
import com.zzz.game.message.ProtocolMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zha on 2018/4/17.
 */
public abstract class AbstractMessageHandler implements MessageHandler {

    public void handler(ChannelHandlerContext ctx, ProtocolMessage message) {
        MessageLite messageLite = null;
        try {
            messageLite = exec(ctx, message);
        } catch (Exception e) {
            System.out.println("消息处理错误:");
            e.printStackTrace();
        }
        if (messageLite != null) {
            message.setBuf(message.getBuf());
            ctx.writeAndFlush(message);
        }
    }

    protected abstract MessageLite exec(ChannelHandlerContext ctx, ProtocolMessage message) throws Exception;

}
