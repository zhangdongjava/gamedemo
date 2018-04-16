package com.zzz.study.marshalling.client;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zzz.study.marshalling.OneMessage;
import com.zzz.study.proto.MessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        OneMessage req = new OneMessage()
                .setName("test").setAge(18);
        ctx.writeAndFlush(req);
        System.out.println("发送了消息");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        OneMessage resp = (OneMessage) msg; // (1)
        String message = resp.getName();

        System.out.println("server message:" + message);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        MessageProto.PersonReq req = MessageProto.PersonReq.newBuilder()
                .setName("test").setAge(18).build();
        System.out.println(req);
        MessageProto.PersonReq req2 = MessageProto.PersonReq.parseFrom(req.toByteArray());
        System.out.println(req2.getName()+":"+req2.getAge());
    }
}
