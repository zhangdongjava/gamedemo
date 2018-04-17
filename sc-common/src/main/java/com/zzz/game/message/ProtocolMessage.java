package com.zzz.game.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zha on 2018/4/14.
 */
public class ProtocolMessage {

    private int len;
    private int sessionId;
    private int type;
    private Map<String,Object> attachment = new HashMap<>();
    private byte[] buf;

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String , Object> attachment) {
        this.attachment = attachment;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getBuf() {
        return buf;
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
    }

    public ProtocolMessage clone(){
        ProtocolMessage protocolMessage = new ProtocolMessage();
        protocolMessage.setBuf(buf);
        protocolMessage.setType(type);
        protocolMessage.setSessionId(sessionId);
        protocolMessage.setAttachment(attachment);
        protocolMessage.setLen(len);
        return protocolMessage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProtocolMessage{");
        sb.append("len=").append(len);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", type=").append(type);
        sb.append(", attachment=").append(attachment);
        sb.append('}');
        return sb.toString();
    }
}
