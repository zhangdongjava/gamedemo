package com.zzz.study.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.UnpooledHeapByteBuf;

import java.util.Arrays;

/**
 * Created by zha on 2018/4/18.
 */
public class Test {

    public static void main(String[] args) {
        UnpooledByteBufAllocator allo = UnpooledByteBufAllocator.DEFAULT;
        ByteBuf buf = new UnpooledHeapByteBuf(allo,8,10000000);
        buf.writeInt(1);
        buf.writeInt(2);
        buf.capacity(3);
        System.out.println(Arrays.toString(buf.array()));
    }
}
