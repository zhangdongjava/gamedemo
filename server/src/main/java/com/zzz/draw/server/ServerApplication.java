package com.zzz.draw.server;

import com.zzz.draw.pojo.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zha on 2018/4/17.
 */
public class ServerApplication {

    public static ApplicationContext context;

    private static Map<Integer, UserInfo> infoMap = new ConcurrentHashMap<>();

    private static AtomicInteger atomUserId = new AtomicInteger(0);
    
    private static List<UserInfo> userInfos = new ArrayList<>();
    
    public static int currPlayIndex = 0;

    public static List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public static Collection<UserInfo> getUserList(){
        return infoMap.values();
    }

    public static UserInfo getUserInfo(int userId){
    	return infoMap.get(userId);
    }
    
    public static int getNewUserId() {
        return atomUserId.incrementAndGet();
    }

    public static int addUserInfo(ChannelHandlerContext context, String name) {
        int userId = getNewUserId();
        UserInfo userInfo = new UserInfo(context, userId, name);
        infoMap.put(userId, userInfo);
        userInfos.add(userInfo);
        return userId;
    }

    public static void removeUser(Integer userId) {
        infoMap.remove(userId);
    }


    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("application.xml");
        int port = 1231;
        new DrawServer(port).run();
    }


}










