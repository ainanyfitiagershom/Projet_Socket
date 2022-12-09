package client;

import java.awt.Color;
import javax.swing.border.*;
import javax.swing.*;
import org.w3c.dom.events.MouseEvent;

import java.net.Socket;
import java.net.URL.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener {
    ClientFile cf;
    JTextField t;
    JLabel label = new JLabel();
    static File file;
    static JPanel all;
    static Box b = Box.createVerticalBox();
    static DataOutputStream dato;
    static JFrame f = new JFrame();

    Client() {

        f.setLayout(null);

        JPanel jp = new JPanel();
        jp.setBackground(new Color(169, 169, 169));
        jp.setBounds(0, 0, 450, 70);
        jp.setLayout(null);
        f.add(jp);

        ImageIcon ii = new ImageIcon(ClassLoader.getSystemResource("icon/h2.PNG"));
        Image i2 = ii.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JButton jb = new JButton(i3);
        jb.setBounds(5, 20, 25, 25);
        jp.add(jb);
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton send = new JButton("File");
        send.setBounds(368, 20, 69, 25);
        jp.add(send);
        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cf = new ClientFile();
                f.setFocusable(true);
                f.requestFocus();
                // JFileChooser fileChooser = new JFileChooser();
                // int option = fileChooser.showOpenDialog(f);
                // if(option == JFileChooser.APPROVE_OPTION){
                // file = fileChooser.getSelectedFile();
                // label.setText("File Selected: " + file.getName());
                // }else{
                // label.setText("Open command canceled");
                // }
            }
        });
        jp.add(send);
        jp.add(label);
        f.getContentPane().add(jp, BorderLayout.CENTER);

        ImageIcon iii = new ImageIcon(ClassLoader.getSystemResource("icon/ou.PNG"));
        Image i4 = iii.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i5 = new ImageIcon(i4);
        JButton jj = new JButton(i5);
        jj.setBounds(40, 10, 50, 50);
        jp.add(jj);

        ImageIcon iiii = new ImageIcon(ClassLoader.getSystemResource("icon/h2.PNG"));
        Image i6 = iiii.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i7 = new ImageIcon(i6);
        JButton jjj = new JButton(i7);
        jjj.setBounds(300, 20, 30, 30);
        jp.add(jjj);

        JLabel nom = new JLabel("User2");
        nom.setBounds(110, 20, 100, 25);
        nom.setForeground(Color.WHITE);
        nom.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        jp.add(nom);

        all = new JPanel();
        all.setBounds(0, 60, 450, 570);
        f.add(all);

        t = new JTextField();
        t.setBounds(5, 650, 310, 40);
        all.setBackground(Color.WHITE);
        t.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(t);

        JButton s = new JButton("send");
        s.setBounds(320, 650, 123, 40);
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        s.addActionListener(this);
        f.add(s);

        f.setSize(450, 700);
        f.setLocation(800, 25);
        f.setUndecorated(true);
        f.getContentPane().setBackground(new Color(169, 169, 169));

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        try {
            String s = t.getText();
            // System.out.println(s);

            JPanel pan = formatLabel(s);

            all.setLayout((new BorderLayout()));

            JPanel soratra = new JPanel(new BorderLayout());
            soratra.add(pan, BorderLayout.LINE_END);
            b.add(soratra);
            b.add(Box.createVerticalStrut(15));

            all.add(b, BorderLayout.PAGE_START);

            dato.writeUTF(s);
            t.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JPanel formatLabel(String s) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel sb = new JLabel("<html><p style=\"width: 150px\"> " + s + "</p></html>");
        sb.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sb.setBackground(new Color(221, 160, 221));
        sb.setOpaque(true);
        sb.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(sb);

        Calendar ca = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel heure = new JLabel();
        heure.setText(sdf.format(ca.getTime()));

        panel.add(heure);

        return panel;
    }

    public static void main(String[] args) throws Exception {
        new Client();

        try {
            // Socket ss = new Socket("192.168.88.22", 6000);
            Socket ss = new Socket("localhost", 6000);
            DataInputStream data = new DataInputStream(ss.getInputStream());
            dato = new DataOutputStream(ss.getOutputStream());

            while (true) {
                all.setLayout(new BorderLayout());
                String mp = data.readUTF();
                JPanel pan = formatLabel(mp);

                JPanel left = new JPanel(new BorderLayout());
                left.add(pan, BorderLayout.LINE_START);
                b.add(left);

                b.add((Box.createVerticalStrut(15)));
                all.add(b, BorderLayout.PAGE_START);
                f.validate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}