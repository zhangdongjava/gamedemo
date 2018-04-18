package com.zzz.draw.client.send;

import com.zzz.game.proto.DrawMessageProto;
import com.zzz.game.proto.TypeMessageEnumProto;

import java.awt.*;
import java.util.List;

/**
 * Created by zha on 2018/4/17.
 */
public class MessageSendMessage extends SendMessage {


    public void sendMessage(String message) {
        DrawMessageProto.UserMessageReq.Builder req = DrawMessageProto.UserMessageReq.newBuilder();
        req.setMessage(message);
        send(req.build());
    }

    @Override
    public int getType() {
        return TypeMessageEnumProto.MessageType.USER_MESSAGE_VALUE;
    }
}
