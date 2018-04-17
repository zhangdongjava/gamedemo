package com.zzz.draw.server;

import com.zzz.draw.client.Client;
import com.zzz.draw.ui.MainWindow;

/**
 * Created by zha on 2018/4/16.
 */
public class ClientMainServer {

    public static void main(String[] args) {
        Client client = new Client();
        client.content("192.168.10.76",1231);
        new MainWindow();
    }
}
