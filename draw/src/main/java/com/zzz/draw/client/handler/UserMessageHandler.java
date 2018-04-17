package com.zzz.draw.client.handler;

import com.zzz.draw.ui.DrawPanel;
import com.zzz.draw.ui.MainWindow;
import com.zzz.draw.util.Application;
import com.zzz.game.message.MessageHandler;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

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
