package com.Shubh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main{
    static Image red = null;
    static Image yellow = null;
    static JButton btnStart ;
    static JButton btnPause ;
    static JLabel chTime ;
    static JLabel disTime ;
    static JLabel status ;
    static boolean stopped = false;
    static long startTime = 0 ;
    static long stopTime = 0;
    static boolean prevCharging = true;

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //set button colors

                    for(int i=0;i<1200;i++);
                    if (!ConnChecker.isChargerConnected() && !stopped) {
                        discharging();
                    } else if(ConnChecker.isChargerConnected() && !stopped){
                        charging();
                    }else if(stopped){
                        stopping();
                    }
                }
            }
        });

        JFrame frame = new JFrame("Charger Status");
        frame.setSize(300,180);
         btnStart = new JButton("Start");
         btnPause = new JButton("Pause");
        JLabel connectionTime = new JLabel("Charging time: ");
        chTime = new JLabel("");
        JLabel dischargeTime = new JLabel("Discharging time: ");
        disTime = new JLabel("");
        JLabel s = new JLabel("Status:");
        status = new JLabel();

        btnStart.setBounds(20,120,100,25);
        btnPause.setBounds(150,120,100,25);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                charging();
                stopped = false;
            }
        });

        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discharging();
                stopped = true;
            }
        });
        connectionTime.setBounds(20,10,150,20);
        chTime.setBounds(20,25,150,20);
        dischargeTime.setBounds(20,45,150,20);
        disTime.setBounds(20,60,150,20);
        s.setBounds(180,10,150,25);
        status.setBounds(180,25,150,25);

        frame.add(btnPause);    frame.add(btnStart);    frame.add(connectionTime);
        frame.add(dischargeTime); frame.add(chTime);    frame.add(disTime);     frame.add(s);   frame.add(status);// frame.add(red);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        thread1.start();

    }


    public static void charging(){
        status.setText("Charging...");
        //System.out.println("charging");
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
        stopTime = System.currentTimeMillis();
        double time = (stopTime - startTime)/1000;
        chTime.setText(String.valueOf(time)+" sec");
        if(!prevCharging){
            startTime = System.currentTimeMillis();
            disTime.setText("");
            prevCharging = true;
        }
    }

    public static void discharging(){
        status.setText("Discharging...");
        //System.out.println("discharging");
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
        stopTime = System.currentTimeMillis();
        double time = (stopTime - startTime)/1000;
        disTime.setText(String.valueOf(time)+" sec");
        if(prevCharging){
            startTime = System.currentTimeMillis();
            chTime.setText("");
            prevCharging = false;
        }
    }

    public static void stopping(){
        status.setText("Stopped.");
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
        //chTime.setText();
    }


}
