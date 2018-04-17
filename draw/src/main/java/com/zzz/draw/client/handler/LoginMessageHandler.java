package com.zzz.draw.client.handler;

import com.zzz.draw.client.Client;
import com.zzz.draw.ui.DrawPanel;
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

public class LoginMessageHandler implements MessageHandler {

    @Override
    public void handler(ProtocolMessage message) throws Exception {
        Application.getBean(Client.class).getUserInfo().setId(message.getSessionId());
    }
}
