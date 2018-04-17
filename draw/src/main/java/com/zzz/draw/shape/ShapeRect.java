package com.zzz.draw.shape;

import com.zzz.draw.client.Client;
import com.zzz.draw.client.send.RectSendMessage;
import com.zzz.draw.ui.DrawPanel;
import com.zzz.draw.util.Application;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by zha on 2018/4/16.
 */
public class ShapeRect extends Shape implements MouseMotionListener {

    private BufferedImage image;

    private Client client;

    public ShapeRect(DrawPanel drawPanel) {
        super(drawPanel);
    }

    @Override
    public void draw(Graphics graphics) {


    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Point point2 = e.getPoint();
        drawPanel.drawRect(first,point2);
        Application.getBean(RectSendMessage.class).sendMessage(first,point2,0);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        first = null;
        drawPanel.drawEnd();
        Application.getBean(RectSendMessage.class).sendMessage(null,null,1);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
