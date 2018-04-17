package com.zzz.draw.shape;

import com.zzz.draw.client.send.LineSendMessage;
import com.zzz.draw.ui.DrawPanel;
import com.zzz.draw.util.Application;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

/**
 * Created by zha on 2018/4/16.
 */
public class ShapeLine extends Shape implements MouseMotionListener {

    private java.util.LinkedList<Point> points = new LinkedList<>();

    public ShapeLine(DrawPanel drawPanel) {
        super(drawPanel);
    }

    @Override
    public void draw(Graphics graphics) {
        //if (image != null) {
        //  graphics.drawImage(image, 0, 0, null);
        //}

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Point point2 = e.getPoint();
        points.add(point2);

        if (points.size() > 2) {
            drawPanel.drawQline(points);
            sendToServer();
            Point last = points.getLast();
            points.clear();
            points.add(last);

        }
    }

    /**
     * 发送数据到服务器
     */
    private void sendToServer(){
        Application.getBean(LineSendMessage.class).sendMessage(points);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        points.clear();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
