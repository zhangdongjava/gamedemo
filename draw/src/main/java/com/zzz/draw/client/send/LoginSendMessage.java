package com.zzz.draw.client.send;

import com.zzz.game.proto.DrawMessageProto;

/**
 * Created by zha on 2018/4/17.
 */
public class LoginSendMessage extends SendMessage {


    public void sendMessage(String name) {
        DrawMessageProto.LoginReq req = DrawMessageProto.LoginReq.newBuilder()
                .setName(name)
                .build();
        send(req);
    }

    @Override
    public int getType() {
        return 1;
    }
}
