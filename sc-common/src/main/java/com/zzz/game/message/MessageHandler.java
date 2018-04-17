package com.zzz.game.message;

/**
 * Created by zha on 2018/4/16.
 */
public interface MessageHandler {

     void handler(ProtocolMessage message) throws  Exception;

}
