package com.wxq.parser.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Temp
{
JScrollPane scrollPane;
public Temp()
{
JFrame f = new JFrame("JScrollpane1");
Container contentPane = f.getContentPane();
JLabel label = new JLabel("Label");
JButton btn = new JButton("Button");
JPanel panel = new JPanel();
panel.setLayout(new BorderLayout());
panel.add(label, BorderLayout.CENTER);
panel.add(btn, BorderLayout.SOUTH);
scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
contentPane.add(scrollPane, BorderLayout.CENTER);
f.setSize(new Dimension(350, 220));
f.pack();
f.setVisible(true);
}
public static void main(String[] args)
{
new Temp();
}
}
