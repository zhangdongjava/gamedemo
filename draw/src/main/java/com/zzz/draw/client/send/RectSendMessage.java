package com.zzz.draw.client.send;

import com.zzz.game.proto.DrawMessageProto;
import com.zzz.game.proto.TypeMessageEnumProto;

import java.awt.*;
import java.util.List;

/**
 * Created by zha on 2018/4/17.
 */
public class RectSendMessage extends SendMessage {


    public void sendMessage(Point point,Point point2,int type) {
        DrawMessageProto.DrawRectReq.Builder req = DrawMessageProto.DrawRectReq.newBuilder();
        if(point != null && point2 != null){
            req.setX1(point.x);
            req.setY1(point.y);
            req.setX2(point2.x);
            req.setY2(point2.y);
        }
        req.setType(type);
        send(req.build());
    }

    @Override
    public int getType() {
        return TypeMessageEnumProto.MessageType.DRAW_RECT_VALUE;
    }
}
