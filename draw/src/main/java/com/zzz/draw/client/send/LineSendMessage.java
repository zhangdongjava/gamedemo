package com.zzz.draw.client.send;

import com.zzz.game.proto.DrawMessageProto;
import com.zzz.game.proto.TypeMessageEnumProto;

import java.awt.*;
import java.util.List;

/**
 * Created by zha on 2018/4/17.
 */
public class LineSendMessage extends SendMessage {


    public void sendMessage(List<Point> points) {
        DrawMessageProto.DrawLineReq.Builder req = DrawMessageProto.DrawLineReq.newBuilder();
        for (Point point : points) {
            DrawMessageProto.DrawLineReq.point.Builder build = DrawMessageProto.DrawLineReq.point.newBuilder();
            build.setX(point.x);
            build.setY(point.y);
            req.addPoints(build);
        }
        send(req.build());
    }

    @Override
    public int getType() {
        return TypeMessageEnumProto.MessageType.DRAW_LINE_VALUE;
    }
}
