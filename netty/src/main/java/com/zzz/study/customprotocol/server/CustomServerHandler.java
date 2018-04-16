package com.zzz.study.customprotocol.server;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zzz.study.customprotocol.Protocol;
import com.zzz.study.proto.MessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务端 channel.
 */
public class CustomServerHandler extends ChannelInboundHandlerAdapter { // (1)


    private int num = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        final ByteBuf time = ctx.alloc().buffer(4); // (2)
//        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
//        f.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) {
//                assert f == future;
//                ctx.close();
//            }
//        }); // (4)
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws InvalidProtocolBufferException { // (2)
        Protocol protocol =   (Protocol) msg;
        MessageProto.PersonReq req = MessageProto.PersonReq.parseFrom(protocol.getBuf());
        String name = req.getName();
        int age = req.getAge();
        System.out.println((++num)+":"+name + ":" + age +"---->"+msg);
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