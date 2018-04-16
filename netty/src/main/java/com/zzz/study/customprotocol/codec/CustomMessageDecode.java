package com.zzz.study.customprotocol.codec;

import com.zzz.study.customprotocol.Protocol;
import com.zzz.study.marshalling.MyMarshallingFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zha on 2018/4/14.
 */
public class CustomMessageDecode extends ByteToMessageDecoder {

    private ThreadLocal<ByteBuf> threadLocal = new ThreadLocal<>();

    public CustomMessageDecode() {
        super();
    }


    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        in.markReaderIndex();
        int readLength = in.readableBytes();
        int messageLength = in.readInt();
        if (readLength < messageLength) {
            in.resetReaderIndex();
            return null;
        }
        Protocol protocol = new Protocol();
        protocol.setLen(messageLength);
        protocol.setSessionId(in.readInt());
        protocol.setType(in.readInt());
        int mapSize = in.readInt();
        if (mapSize > 0) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < mapSize; i++) {
                int keyLen = in.readInt();
                byte[] keyArray = new byte[keyLen];
                in.readBytes(keyArray);
                String key = new String(keyArray, "utf-8");
                Object value = MyMarshallingFactory.readObject(in);
                map.put(key, value);
            }
            protocol.setAttachment(map);
        }
        //还剩余有长度可读  就是buf的长度
        if (in.readerIndex() < messageLength) {
            int bufLen = messageLength - in.readerIndex();
            byte[] buf = new byte[bufLen];
            in.readBytes(buf);
            protocol.setBuf(buf);
        }else{
            System.out.println("没有buf长度!!!");
        }
       //释放读取了的空间
        in.discardReadBytes();
        System.out.println(in);
        return protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object message = decode(ctx, in);
        if(message != null){
            out.add(message);
        }
    }
}
























