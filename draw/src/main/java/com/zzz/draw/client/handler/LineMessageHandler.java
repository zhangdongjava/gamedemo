package com.zzz.draw.client.handler;

import com.zzz.draw.ui.DrawPanel;
import com.zzz.draw.server.Application;
import com.zzz.game.message.MessageHandler;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.DrawMessageProto;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zha on 2018/4/17.
 */

public class LineMessageHandler implements MessageHandler {

    @Override
    public void handler(ProtocolMessage message) throws Exception {
        byte[] bytes = message.getBuf();
        DrawMessageProto.DrawLineReq req = DrawMessageProto.DrawLineReq.parseFrom(bytes);
        List<DrawMessageProto.DrawLineReq.point> list = req.getPointsList();
        List<Point> points = list.stream().map(point -> new Point(point.getX(), point.getY())).collect(Collectors.toList());
        Application.getBean(DrawPanel.class).drawQline(points);
    }
}
