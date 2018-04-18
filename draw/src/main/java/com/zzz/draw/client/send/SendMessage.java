package com.zzz.draw.client.send;

import com.google.protobuf.MessageLite;
import com.zzz.draw.client.Client;
import com.zzz.draw.server.Application;
import com.zzz.game.message.ProtocolMessage;

/**
 * Created by zha on 2018/4/17.
 */
public abstract class SendMessage {


    protected abstract int getType();

    protected void send(MessageLite messageLite) {
        Client client = Application.getBean(Client.class);

        ProtocolMessage message = new ProtocolMessage();
        message.setType(getType());
        if (messageLite != null) {
            message.setBuf(messageLite.toByteArray());
        }
        message.setSessionId(client.getUserInfo().getId());
        client.sendMessage(message);
    }
}
