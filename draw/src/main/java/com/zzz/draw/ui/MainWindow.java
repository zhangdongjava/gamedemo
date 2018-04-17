package com.zzz.draw.ui;

import com.zzz.draw.util.Application;

import javax.swing.*;

/**
 * Created by zha on 2018/4/16.
 */
public class MainWindow extends JFrame {

    private DrawPanel drawPanel;

    private JButton rect;
    private JButton line;
    private JTextArea messageBox;

    public MainWindow(){
        line = new JButton("曲线");
        rect = new JButton("矩形");
        messageBox = new JTextArea(30,30);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        drawPanel = new DrawPanel();
        drawPanel.setLocation(0,0);
        this.add(drawPanel);
        rect.setBounds(680,30,60,30);
        this.add(rect);
        line.setBounds(610,30,60,30);
        this.add(line);
        messageBox.setBounds(610,63,150,400);
        messageBox.setLineWrap(true);        //激活自动换行功能
        messageBox.setWrapStyleWord(true);            // 激活断行不断字功能
        JScrollPane scrollPane_1 = new JScrollPane(messageBox);
        scrollPane_1.setBounds(610,63,150,400);
        this.add(scrollPane_1);
      //  this.add(messageBox);
        initEvent();
        this.setVisible(true);
        Application.putBean(this);
    }

    private void initEvent(){
        rect.addActionListener(e-> drawPanel.changeRect());
        line.addActionListener(e-> drawPanel.changeLine());
    }
}
