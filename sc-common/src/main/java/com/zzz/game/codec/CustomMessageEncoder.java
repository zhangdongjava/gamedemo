package com.zzz.game.codec;


import com.zzz.game.message.ProtocolMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Created by zha on 2018/4/14.
 */
public class CustomMessageEncoder extends MessageToMessageEncoder<ProtocolMessage> {


    public CustomMessageEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolMessage msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(0);//先把长度默认为0  在最后进行修改
        byteBuf.writeInt(msg.getSessionId());
        byteBuf.writeInt(msg.getType());
        if (msg.getBuf() != null) {
            byteBuf.writeBytes(msg.getBuf());
        }
        //设置消息长度
        byteBuf.setInt(0, byteBuf.readableBytes());
        System.out.println("message length:"+byteBuf.readableBytes());
        ctx.writeAndFlush(byteBuf);
    }


}
