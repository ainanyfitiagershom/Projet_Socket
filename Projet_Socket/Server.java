package server;

import ff.Fonction;
import read.*;

import java.awt.Color;
import javax.swing.border.*;
import javax.swing.*;
import org.w3c.dom.events.MouseEvent;
import java.net.URL.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.DataOutputStream;

public class Server implements ActionListener {
    ServerFile sf;
    JTextField t;
    JPanel all;
    JLabel label = new JLabel();
    Mamaky lect;
    Fonction fonction = new Fonction();

    static File file;
    static Box b = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dato;

    Server() {

        f.setLayout(null);

        JPanel jp = new JPanel();
        jp.setBackground(new Color(169, 169, 169));
        jp.setLayout(null);
        jp.setBounds(0, 0, 450, 70);

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

        JLabel nom = new JLabel("Server");
        nom.setBounds(110, 20, 100, 25);
        nom.setForeground(Color.WHITE);
        nom.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        jp.add(nom);

        JScrollBar scro = new JScrollBar();

        JPanel mety = new JPanel();
        mety.setLayout(null);
        mety.setBounds(3, 5, 450, 620);

        // mety.setBackground(Color.RED);
        f.add(mety);

        all = new JPanel();
        // all.setLayout(null);
        all.setBounds(0, 60, 450, 570);
        // all.setBackground(new Color(40, 79, 79));
        // all.setBackground(new Color(25, 25, 112));
        all.setBackground(Color.WHITE);

        f.add(all);
        mety.add(all);
        all.add(scro);
        f.getContentPane().add(BorderLayout.EAST, scro);

        JButton send = new JButton("File");
        send.setBounds(368, 20, 69, 25);

        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                sf = new ServerFile();
                System.out.println("Tafiditra");
                f.setFocusable(true);
                f.requestFocus();
                // JFileChooser fileChooser = new JFileChooser();
                // int option = fileChooser.showOpenDialog(f);
                // if (option == JFileChooser.APPROVE_OPTION) {
                // file = fileChooser.getSelectedFile();
                // System.out.println(file.getName());
                // JPanel pan = formatLabel(file.getName());
                // f.add(pan);
                // // label.setText("File Selected: " + file.getName());
                // } else {
                // label.setText("Open command canceled");
                // }
            }
        });
        f.add(send);
        jp.add(send);

        jp.add(label);

        t = new JTextField();
        t.setBounds(5, 650, 310, 40);
        t.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(t);

        JButton s = new JButton("send");
        s.setBounds(320, 650, 123, 40);
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        s.addActionListener(this);
        f.add(s);
        // JButton button = new JButton("send");
        // button.setBounds(320, 650, 123, 40);
        // button.setBackground(Color.BLACK);
        // button.setForeground(Color.WHITE);
        // button.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // lect = new Mamaky(o, fonction.count_liste(o), fonction.count_Field(o).length,
        // fonction.get_liste(o));
        // f.setFocusable(true);
        // f.requestFocus();
        // System.out.println("bouton ao");
        // }
        // });

        f.setSize(450, 700);
        f.setLocation(200, 25);
        f.setUndecorated(true);
        f.getContentPane().setBackground(new Color(169, 169, 169));

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {

            String s = t.getText();
            // String fi = file.getName();
            // System.out.println(s);
            // System.out.println(fi);

            JPanel pan = formatLabel(s);
            // JPanel f = formatLabel(fi);

            all.setLayout((new BorderLayout()));

            JPanel soratra = new JPanel(new BorderLayout());
            soratra.add(pan, BorderLayout.LINE_END);

            // JPanel fii = new JPanel();
            // fii.add(f, BorderLayout.LINE_END);

            b.add(soratra);
            // b.add(fii);
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
        new Server();

        try {
            ServerSocket ss = new ServerSocket(6000);
            while (true) {
                Socket s = ss.accept();
                DataInputStream data = new DataInputStream(s.getInputStream());
                dato = new DataOutputStream(s.getOutputStream());

                while (true) {
                    String mp = data.readUTF();
                    JPanel pan = formatLabel(mp);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(pan, BorderLayout.LINE_START);
                    b.add(left);
                    f.validate();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}