package com.zzz.draw.server;

import com.zzz.draw.handler.MessageHandler;
import com.zzz.game.message.ProtocolMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

/**
 * 处理服务端 channel.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter { // (1)


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ProtocolMessage message = (ProtocolMessage) msg;
        int type = message.getType();
        MessageHandler messageHandler = (MessageHandler) ServerApplication.context.getBean("handler_" + type);
        messageHandler.handler(ctx, message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        AttributeKey<Integer> attributeKey = AttributeKey.valueOf("userId");
        Integer userId = ctx.channel().attr(attributeKey).get();
        ServerApplication.removeUser(userId);
        cause.printStackTrace();
        ctx.close();
    }
}