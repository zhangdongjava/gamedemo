package com.zzz.study.netty.server;

import com.zzz.study.proto.MessageProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务端 channel.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter { // (1)


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        MessageProto.PersonReq req = (MessageProto.PersonReq) msg;
        String name = req.getName();
        int age = req.getAge();
        System.out.println(name + ":" + age);
        MessageProto.PersonResp resp = MessageProto.PersonResp.newBuilder().setMessage("success").build();
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
// 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}