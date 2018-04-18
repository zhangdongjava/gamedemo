package com.zzz.draw.ui;


import com.zzz.draw.shape.*;
import com.zzz.draw.shape.Shape;
import com.zzz.draw.server.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by zha on 2018/4/16.
 */
public class DrawPanel extends JPanel {

    private Image curr;
    private BufferedImage image;
    private com.zzz.draw.shape.Shape shape;

    public DrawPanel() {
        this.setSize(600, 563);
        //   Shape shape = new ShapeLine(this);
        //  changeShape(shape);
        this.setBackground(Color.white);
        Application.putBean(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (curr != null) {
            g.drawImage(curr, 0, 0, null);
        }
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
        if(shape != null){
            shape.draw(g);
        }

    }

    public void drawLine(Point point1, Point point2) {
        if (curr == null) {
            initCurr();
        }
        this.curr.getGraphics().drawLine(point1.x, point1.y, point2.x, point2.y);
        this.repaint();
    }

    public void drawQline(java.util.List<Point> points) {
        Point up = null;
        for (Point point : points) {
            if (up != null) {
                this.drawLine(up, point);
            }
            up = point;
        }

    }

    public void drawRect(Point first, Point point2) {
        if (image == null) {
            image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D gd = image.createGraphics();
        image = gd.getDeviceConfiguration().createCompatibleImage(this.getWidth(), this.getHeight(), Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        g.setColor(Color.red);
        g.drawRect(Math.min(first.x, point2.x), Math.min(first.y, point2.y), Math.abs(point2.x - first.x), Math.abs(point2.y - first.y));
        this.repaint();

    }

    public void drawEnd() {
        drawImage(image);
        this.repaint();

    }

    public void drawImage(Image draw) {
        if (curr == null) {
            initCurr();
        }
        this.curr.getGraphics().drawImage(draw, 0, 0, null);
        this.repaint();
    }

    public void changeRect() {
        Shape shape = new ShapeRect(this);
        changeShape(shape);
    }

    private void initCurr() {
        curr = createImage(this.getWidth(), this.getHeight());
        Graphics g = curr.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void notDraw() {
        if (this.shape != null) {
            this.removeMouseListener(this.shape);
            this.removeMouseMotionListener(this.shape);
            this.shape = null;
        }
    }

    public void changeLine() {
        Shape shape = new ShapeLine(this);
        changeShape(shape);
    }

    public void changeShape(com.zzz.draw.shape.Shape shape) {
        if (this.shape != null) {
            this.removeMouseListener(this.shape);
            this.removeMouseMotionListener(this.shape);
        }
        this.shape = shape;
        this.addMouseListener(shape);
        this.addMouseMotionListener(shape);
    }
}















