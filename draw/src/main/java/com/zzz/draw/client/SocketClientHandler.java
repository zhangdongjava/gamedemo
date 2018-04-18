package com.zzz.draw.client;

import com.zzz.draw.server.Application;
import com.zzz.game.message.MessageHandler;
import com.zzz.game.message.ProtocolMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务端 channel.
 */
public class SocketClientHandler extends ChannelInboundHandlerAdapter { // (1)



    public SocketClientHandler(){

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接激活");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception { // (2)
        ProtocolMessage message = (ProtocolMessage) msg;
        int type = message.getType();
        MessageHandler messageHandler = Application.getBean("handler_"+type);
        messageHandler.handler(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}