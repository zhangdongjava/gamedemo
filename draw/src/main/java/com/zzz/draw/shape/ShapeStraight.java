package com.zzz.draw.shape;

import com.zzz.draw.ui.DrawPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by zha on 2018/4/16.
 */
public class ShapeStraight extends Shape implements MouseMotionListener {

    private BufferedImage image;

    public ShapeStraight(DrawPanel drawPanel) {
        super(drawPanel);
    }

    @Override
    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, 0, 0, null);
        }

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        Point point2 = e.getPoint();
        if (image == null) {
            image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D gd = image.createGraphics();
        image = gd.getDeviceConfiguration().createCompatibleImage(drawPanel.getWidth(), drawPanel.getHeight(), Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        g.setColor(Color.red);
        g.drawLine(first.x, first.y, point2.x, point2.y);
        g.dispose();
        drawPanel.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        first = null;
        drawPanel.drawImage(image);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
