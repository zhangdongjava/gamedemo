package com.zzz.draw.client.handler;

import com.zzz.draw.client.Client;
import com.zzz.draw.server.Application;
import com.zzz.game.message.MessageHandler;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto;

/**
 * Created by zha on 2018/4/17.
 */

public class LoginMessageHandler implements MessageHandler {

    @Override
    public void handler(ProtocolMessage message) throws Exception {
        DrawMessageProto.LoginResp resp = DrawMessageProto.LoginResp.parseFrom(message.getBuf());
        Application.getBean(Client.class).getUserInfo().setId(resp.getId());

    }
}
