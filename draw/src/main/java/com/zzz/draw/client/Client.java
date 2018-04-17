package com.zzz.draw.client;

import com.zzz.draw.client.send.LoginSendMessage;
import com.zzz.draw.util.Application;
import com.zzz.game.codec.CustomMessageDecode;
import com.zzz.game.codec.CustomMessageEncoder;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.draw.pojo.UserInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.swing.*;

public class Client {


    private UserInfo userInfo = new UserInfo();

    private Channel channel;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public Client() {
        Application.putBean(this);
    }

    public void content(String ip, int port) {
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
                    ch.pipeline().addLast(new SocketClientHandler());
                }
            });
            // 启动客户端
            ChannelFuture f = b.connect(ip, port).sync(); // (5)
            channel = f.channel();
            String name =  JOptionPane.showInputDialog("请输入你的昵称!");
            //登录
            Application.getBean(LoginSendMessage.class).sendMessage(name);
            // 关闭监听
            f.channel().closeFuture().addListener(future -> workerGroup.shutdownGracefully());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 1231;

    }

    public void sendMessage(ProtocolMessage message) {
        if (channel != null && channel.isActive()) {
            channel.writeAndFlush(message);
            System.out.println("发送消息!");
        }else{
            System.out.println("连接关闭");
        }

    }
}
