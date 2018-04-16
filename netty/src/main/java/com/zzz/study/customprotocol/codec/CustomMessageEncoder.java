package com.zzz.study.customprotocol.codec;

import com.zzz.study.customprotocol.Protocol;
import com.zzz.study.marshalling.MyMarshallingFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.ChannelBufferByteOutput;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.marshalling.Marshaller;

import java.util.List;
import java.util.Map;

/**
 * Created by zha on 2018/4/14.
 */
public class CustomMessageEncoder extends MessageToMessageEncoder<Protocol> {

    private MarshallingEncoder marshallingEncoder;

    public CustomMessageEncoder() {
        marshallingEncoder = MyMarshallingFactory.buildMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Protocol msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(0);//先把长度默认为0  在最后进行修改
        byteBuf.writeInt(msg.getSessionId());
        byteBuf.writeInt(msg.getType());
        byteBuf.writeInt(msg.getAttachment().size());
        String key;
        byte[] keyArray;
        for (Map.Entry<String, Object> entry : msg.getAttachment().entrySet()) {
            key = entry.getKey();
            keyArray = key.getBytes("utf-8");
            byteBuf.writeInt(keyArray.length);
            byteBuf.writeBytes(keyArray);
            MyMarshallingFactory.writeObject(byteBuf,entry.getValue());
        }
        if (msg.getBuf() != null) {
            byteBuf.writeBytes(msg.getBuf());
        }
        //设置消息长度
        byteBuf.setInt(0, byteBuf.readableBytes());
        System.out.println("message length:"+byteBuf.readableBytes());
        ctx.writeAndFlush(byteBuf);
    }


}
