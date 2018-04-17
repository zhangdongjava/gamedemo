package com.zzz.game.codec;


import com.zzz.game.message.ProtocolMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by zha on 2018/4/14.
 */
public class CustomMessageDecode extends ByteToMessageDecoder {

    public CustomMessageDecode() {
        super();
    }


    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        in.markReaderIndex();
        int readLength = in.readableBytes();
        int startReadIndex = in.readerIndex();
        int messageLength = in.readInt();
        if (readLength < messageLength) {
            in.resetReaderIndex();
            return null;
        }
        ProtocolMessage protocol = new ProtocolMessage();
        protocol.setLen(messageLength);
        protocol.setSessionId(in.readInt());
        protocol.setType(in.readInt());

        //还剩余有长度可读  就是buf的长度
        if (in.readerIndex() - startReadIndex < messageLength) {
            int bufLen = messageLength - (in.readerIndex() - startReadIndex);
            byte[] buf = new byte[bufLen];
            in.readBytes(buf);
            protocol.setBuf(buf);
        }
        //释放读取了的空间
        if (in.readerIndex() > 10240) {
            in.discardReadBytes();
        }
        return protocol;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object message = decode(ctx, in);
        if (message != null) {
            out.add(message);
        }
    }
}
























