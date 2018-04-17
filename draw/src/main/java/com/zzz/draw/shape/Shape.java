package com.zzz.draw.shape;

import com.zzz.draw.ui.DrawPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by zha on 2018/4/16.
 */
public abstract class Shape extends MouseAdapter {

    protected Point first;

    protected DrawPanel drawPanel;

    public Shape(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        first = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        first = null;
    }

    public abstract void draw(Graphics graphics);
}
