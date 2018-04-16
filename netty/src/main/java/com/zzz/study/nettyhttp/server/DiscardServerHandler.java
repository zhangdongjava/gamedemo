package com.zzz.study.nettyhttp.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.File;
import java.sql.SQLOutput;

/**
 * 处理服务端 channel.
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final static String rootDir = "netty/src/main/java";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();
        File file;
        if (uri.equals("/")) {
            file = new File(rootDir);
        } else {
            file = new File(uri);
        }
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) {
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
        StringBuilder builder = new StringBuilder();
        int index = uri.lastIndexOf("/");
        String upPath = uri.substring(0, index);
        builder.append("<a href='http://localhost:1231/");
        builder.append(upPath);
        builder.append("'>");
        builder.append("返回上一级");
        builder.append("</a></br>");
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File file1 : list) {
                builder.append("<a href='http://localhost:1231/");
                builder.append(file1.getAbsolutePath());
                builder.append("'>");
                builder.append(file1.getName());
                builder.append("</a></br>");
            }
        } else {

            builder.append("文件内容");
        }
        response.content().writeBytes(builder.toString().getBytes());
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    } // (1)


    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer("fail:" + status, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


}