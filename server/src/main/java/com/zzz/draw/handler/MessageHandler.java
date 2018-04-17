package com.zzz.draw.handler;

import com.zzz.game.message.ProtocolMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zha on 2018/4/17.
 */
public interface MessageHandler {

     void handler(ChannelHandlerContext ctx,ProtocolMessage message);

}
