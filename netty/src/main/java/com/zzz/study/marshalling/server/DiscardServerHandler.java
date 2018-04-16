package com.zzz.study.marshalling.server;

import com.zzz.study.marshalling.OneMessage;
import com.zzz.study.proto.MessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务端 channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("收到连接!!!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        OneMessage req = (OneMessage) msg;
        String name = req.getName();
        int age = req.getAge();
        System.out.println(name + ":" + age);

        ctx.writeAndFlush(req);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
// 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}