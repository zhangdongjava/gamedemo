package com.zzz.draw.util;

import java.util.List;

import com.zzz.draw.pojo.UserInfo;
import com.zzz.draw.server.ServerApplication;
import com.zzz.game.message.ProtocolMessage;
import com.zzz.game.proto.TypeMessageEnumProto.MessageType;

public class PlayerUtil {
	
	public Integer luckyPlayer(){
		 List<UserInfo> userInfos = ServerApplication.getUserInfos();
	       if(userInfos.size()>1){
	    	   UserInfo userInfo = userInfos.get((ServerApplication.currPlayIndex++)%userInfos.size());
	    	   ProtocolMessage message = new ProtocolMessage();
	    	   message.setType(MessageType.DRAW_PLAYER_VALUE);
	    	   message.setSessionId(userInfo.getId());
	    	   message.setBuf(null);
	    	   userInfo.getCtx().writeAndFlush(message);
	    	   return userInfo.getId();
	       }
	       return null; 
	}

}
