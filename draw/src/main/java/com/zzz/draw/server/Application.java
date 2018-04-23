package com.zzz.draw.server;

import com.zzz.draw.client.Client;
import com.zzz.draw.client.handler.*;
import com.zzz.draw.ui.MainWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zha on 2018/4/17.
 */
public class Application {

    private static Map<String, Object> nameMap = new HashMap<>();

    private static Map<Class<?>, Object> beanMap = new HashMap<>();

    static {
        putBean("handler_2", new LineMessageHandler());
        putBean("handler_3", new RectMessageHandler());
        putBean("handler_1", new LoginMessageHandler());
        putBean("handler_4", new UserMessageHandler());
        putBean("handler_5", new DrawPlayerMessageHandler());
        putBean("handler_7", new ReadyMessageHandler());
    }

    public static void putBean(Object bean) {
        beanMap.put(bean.getClass(), bean);
    }

    public static void putBean(String name, Object bean) {
        nameMap.put(name, bean);
    }

    public static <T> T getBean(String name) {
        return (T) nameMap.get(name);
    }

    public static <T> T getBean(Class<T> aClass) {
        Object sendMessage = beanMap.get(aClass);
        if (sendMessage != null) {
            return (T) sendMessage;
        }
        sendMessage = getByNameMap(aClass);
        if (sendMessage != null) {
            return (T) sendMessage;
        }
        try {
            sendMessage = aClass.newInstance();
            beanMap.put(aClass, sendMessage);
        } catch (Exception e) {
            throw new RuntimeException(aClass + "没有默认构造器!");
        }
        return (T) sendMessage;
    }

    private static <T> T getByNameMap(Class<T> aClass) {
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            if (entry.getValue().getClass().equals(aClass)) {
                return (T) entry.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.content("192.168.10.59",1231);
        new MainWindow();
    }
}
