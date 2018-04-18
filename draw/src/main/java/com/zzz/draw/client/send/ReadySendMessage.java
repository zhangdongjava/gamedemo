package com.zzz.draw.client.send;

import com.zzz.game.proto.DrawMessageProto;
import com.zzz.game.proto.TypeMessageEnumProto;

import java.awt.*;
import java.util.List;

/**
 * Created by zha on 2018/4/17.
 */
public class ReadySendMessage extends SendMessage {


    public void sendMessage(int code) {
        DrawMessageProto.ReadyReq.Builder req = DrawMessageProto.ReadyReq.newBuilder();
        req.setCode(code);
        send(req.build());
    }

    @Override
    public int getType() {
        return TypeMessageEnumProto.MessageType.READY_VALUE;
    }
}
