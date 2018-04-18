package com.zzz.draw.client.handler;

import com.zzz.draw.ui.DrawPanel;
import com.zzz.draw.server.Application;
import com.zzz.game.message.MessageHandler;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto;

import java.awt.*;

/**
 * Created by zha on 2018/4/17.
 */

public class RectMessageHandler implements MessageHandler {

    @Override
    public void handler(ProtocolMessage message) throws Exception {
        byte[] bytes = message.getBuf();
        System.out.println("收到画举行!");
        DrawMessageProto.DrawRectReq req = DrawMessageProto.DrawRectReq.parseFrom(bytes);
        DrawPanel drawPanel = Application.getBean(DrawPanel.class);
        if(req.getType() == 0){
            drawPanel.drawRect(new Point(req.getX1(), req.getY1()),new Point(req.getX2(), req.getY2()));
        }else{
            drawPanel.drawEnd();
        }

    }
}
