package com.zzz.study.customprotocol.client;

import com.zzz.study.customprotocol.codec.CustomMessageDecode;
import com.zzz.study.customprotocol.codec.CustomMessageEncoder;
import com.zzz.study.proto.MessageProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class CustomClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 1231;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new CustomMessageDecode());
                    ch.pipeline().addLast(new CustomMessageEncoder());
                    ch.pipeline().addLast(new CustomClientHandler());
                }
            });
            // 启动客户端
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            // 等待连接关闭
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
