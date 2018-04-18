package com.zzz.draw.client.handler;

import com.zzz.draw.ui.MainWindow;
import com.zzz.draw.server.Application;
import com.zzz.game.message.MessageHandler;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto;

/**
 * Created by zha on 2018/4/17.
 */

public class UserMessageHandler implements MessageHandler {

    @Override
    public void handler(ProtocolMessage message) throws Exception {
        byte[] bytes = message.getBuf();
        DrawMessageProto.UserMessageResp resp = DrawMessageProto.UserMessageResp.parseFrom(bytes);
        Application.getBean(MainWindow.class).appendText(resp.getName(),resp.getMessage());
    }
}
