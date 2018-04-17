package com.zzz.draw.client.handler;

import com.zzz.draw.ui.DrawPanel;
import com.zzz.draw.util.Application;
import com.zzz.game.message.MessageHandler;
import com.zzz.game.message.ProtocolMessage;

/**
 * Created by zha on 2018/4/17.
 */
public class DrawPlayerMessageHandler implements MessageHandler {

    @Override
    public void handler(ProtocolMessage message) throws Exception {
        Application.getBean(DrawPanel.class).changeLine();
    }
}
