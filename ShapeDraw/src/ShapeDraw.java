/*
 Copyright (C) 2018 Eric Pogue. All rights reserved.

 This file is part of the ShapeDraw project.

 This file can not be copied and/or distributed without
 the express permission of Eric Pogue

 This application uses the open source component ShapesLibrary.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;

class OvalDraw extends Oval {
    private int R,G,B;
    public void setColor(int rIn, int gIn, int bIn){
        R = rIn;
        G = gIn;
        B = bIn;
    }

    OvalDraw() {
        super();
        setColor(0,0,0);
    }

    OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
        setColor(0,0,0);
    }

    OvalDraw(int panelWidthIn, int panelHeightIn) {
        // Create random and appropriate dimensions given the inputs of panel width and height.
        final int widthMin = 10;
        int width = GetNumberBetween(widthMin, panelWidthIn);

        final int heightMin = 10;
        int height = GetNumberBetween(heightMin, panelHeightIn);

        final int xMin = 0;
        int xMax = panelWidthIn - width;
        int x = GetNumberBetween(xMin, xMax);

        final int yMin = 0;
        int yMax = panelHeightIn - height;
        int y = GetNumberBetween(yMin, yMax);

        setPositionX(x);
        setPositionY(y);
        setWidth(width);
        setHeight(height);
    }

    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min);
    }

    public void paintComponent(Graphics g) {
        Color myColor = new Color(R,G,B);
        g.setColor(myColor);

        g.drawOval(getPositionX(),getPositionY(),getWidth(),getHeight());
        System.out.println("Attributes: "+this);
        System.out.format("Area=%.2f\n", CalcArea());
        System.out.format("Perimiter=%.2f\n", CalcPerimeter());
    }
}

class ShapeDrawPanel extends JPanel {
    public void setOvalDrawList(ArrayList<OvalDraw> OvalDrawListIn) { OvalDrawList = OvalDrawListIn; }
    private ArrayList<OvalDraw> OvalDrawList;

    ShapeDrawPanel() {
        super();
        assert false:"Unexpected call to ShapeDrawPanel default constructor.";
        OvalDrawList = null;
    }

    ShapeDrawPanel(ArrayList<OvalDraw> OvalDrawListIn) {
        setOvalDrawList(OvalDrawListIn);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (OvalDraw oD : OvalDrawList) {
            oD.paintComponent(g);
        }
    }

    public void addRandomOval() {
        // To-do: Have this method call the overridden versions below.
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        OvalDrawList.add(new OvalDraw(panelWidth, panelHeight));
        repaint();
    }
    public void addRandomOval(int rIn, int gIn, int bIn) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        OvalDraw myOval = new OvalDraw(panelWidth, panelHeight);
        myOval.setColor(rIn, gIn, bIn);
        OvalDrawList.add(myOval);
        repaint();

    }

    public void clearOvals() {
        OvalDrawList.clear();
        repaint();
    }
}

class ShapeDrawFrame extends JFrame implements ActionListener, KeyListener {
    JTextField colorRTextField;
    JTextField colorGTextField;
    JTextField colorBTextField;
    private ShapeDrawPanel myShapeDrawPanel;

    public ShapeDrawFrame() {
        assert false:"Unexpected call to ShapeDrawFrame default constructor.";
    }

    public ShapeDrawFrame(ArrayList<OvalDraw> OvalDrawListIn) {
        setBounds(100,100,600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel myButtonPanel = new JPanel();
        myButtonPanel.setLayout(new FlowLayout());

        JButton newOvalButton = new JButton("Add Random Oval");
        newOvalButton.addActionListener(this);
        myButtonPanel.add(newOvalButton);

        contentPane.add(myButtonPanel, BorderLayout.SOUTH);

        myShapeDrawPanel = new ShapeDrawPanel(OvalDrawListIn);
        contentPane.add(myShapeDrawPanel, BorderLayout.CENTER);

        // Create menu bars and menus.
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        fileMenu.add(openMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(editMenu);

        JMenuItem clearMenuItem = new JMenuItem("Clear", KeyEvent.VK_C);
        clearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myShapeDrawPanel.clearOvals();

            }
        });
        editMenu.add(clearMenuItem);


        add(menuBar, BorderLayout.NORTH);

        // Add label and text field.
        JLabel colorRLabel = new JLabel("Color R:");
        myButtonPanel.add(colorRLabel);

        colorRTextField = new JTextField(3);
        colorRTextField.addKeyListener(this);
        myButtonPanel.add(colorRTextField);

        JLabel colorGLabel = new JLabel("Color G:");
        myButtonPanel.add(colorGLabel);

        colorGTextField = new JTextField(3);
        colorGTextField.addKeyListener(this);
        myButtonPanel.add(colorGTextField);

        JLabel colorBLabel = new JLabel("Color B:");
        myButtonPanel.add(colorBLabel);

        colorBTextField = new JTextField(3);
        colorBTextField.addKeyListener(this);
        myButtonPanel.add(colorBTextField);


    }

    public void actionPerformed(ActionEvent e) {
        // myShapeDrawPanel.addRandomOval();

        String R = colorRTextField.getText();
        String G = colorGTextField.getText();
        String B = colorBTextField.getText();
        myShapeDrawPanel.addRandomOval(TextToRGB(R), TextToRGB(G), TextToRGB(B));
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() ==KeyEvent.VK_ENTER) {
            String R = colorRTextField.getText();
            String G = colorGTextField.getText();
            String B = colorBTextField.getText();
            myShapeDrawPanel.addRandomOval(TextToRGB(R), TextToRGB(G), TextToRGB(B));
        }
    }

    private static int TextToRGB(String valueRGB) {
        int returnValue;
        try {
            returnValue = Integer.parseInt(valueRGB);
            if (returnValue < 0) {
                returnValue = 0;
            } else if (returnValue > 255) {
                returnValue = 255;
            }
        }
        catch (Exception e){
            returnValue = 0;
        }
        return returnValue;
    }



    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }
}

public class ShapeDraw {
    public static void main(String[] args) {
        System.out.println("ShapeDraw Starting!");

        ArrayList<OvalDraw> myOvalDrawList = new ArrayList<OvalDraw>();
        ShapeDrawFrame myShapeDrawFrame = new ShapeDrawFrame(myOvalDrawList);
        myShapeDrawFrame.setVisible(true);
    }
}