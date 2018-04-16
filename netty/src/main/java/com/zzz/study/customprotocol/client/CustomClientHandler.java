package com.zzz.study.customprotocol.client;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zzz.study.customprotocol.Protocol;
import com.zzz.study.proto.MessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

public class CustomClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i <1000; i++) {
            Protocol req = new Protocol();
            req.setSessionId(1);
            req.setType(2333);
            Map<String, Object> attac = new HashMap<>();
            attac.put("name", "zhangdong");
            attac.put("age", "18");
            attac.put("ewewqe", "1rew8");
            attac.put("arewrge", "1re8");
            attac.put("a3333ge", "34318");
            req.setAttachment(attac);
            MessageProto.PersonReq.Builder personReq = MessageProto.PersonReq.newBuilder();
            personReq.setName("张东");
            personReq.setAge(18);
            req.setBuf(personReq.build().toByteArray());
            ctx.writeAndFlush(req);
        }


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        MessageProto.PersonResp resp = (MessageProto.PersonResp) msg; // (1)
        String message = resp.getMessage();

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
        System.out.println(req2.getName() + ":" + req2.getAge());
    }
}
