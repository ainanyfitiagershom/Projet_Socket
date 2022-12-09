package client;


import server.*;

import java.awt.*;
import java.io.*;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientFile {

    public static void main(String[] args) {

        final File[] fileToSend = new File[1];

        JFrame f = new JFrame("Client");
        f.setSize(450, 450);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        JLabel jlTitle = new JLabel("send file");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jlFileName = new JLabel();
        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
        jlFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButton = new JPanel();
        jpButton.setBorder(new EmptyBorder(75, 0, 10, 0));

        JButton jbSendFile = new JButton("Send file");
        jbSendFile.setPreferredSize(new Dimension(150, 75));
        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));

        JButton jbChooseFile = new JButton("Choose file");
        jbChooseFile.setPreferredSize(new Dimension(150, 75));
        jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));

        jpButton.add(jbSendFile);
        jpButton.add(jbChooseFile);

        jbChooseFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser choos = new JFileChooser();
                choos.setDialogTitle("Choose a file to send");

                if (choos.showOpenDialog(null) == choos.APPROVE_OPTION) {
                    fileToSend[0] = choos.getSelectedFile();
                    jlFileName.setText("Name file: " + fileToSend[0].getName());

                }
            }
        });

        jbSendFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileToSend[0] == null) {
                    jlFileName.setText("Choose a file first");
                } else {
                    try {
                        FileInputStream pu = new FileInputStream(fileToSend[0].getAbsolutePath());
                        Socket so = new Socket("localhost", 8000);
                        // Socket so = new Socket("192.168.88.22", 6000);

                        DataOutputStream dataOutputStream = new DataOutputStream(so.getOutputStream());

                        String fileName = fileToSend[0].getName();
                        byte[] fileNameBytes = fileName.getBytes();

                        byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                        pu.read(fileContentBytes);

                        dataOutputStream.writeInt(fileNameBytes.length);
                        dataOutputStream.write(fileNameBytes);

                        dataOutputStream.writeInt(fileContentBytes.length);
                        dataOutputStream.write(fileContentBytes);
                    } catch (IOException error) {
                        error.printStackTrace();
                    }

                }
            }
        });

        f.add(jlTitle);
        f.add(jlFileName);
        f.add(jpButton);
        f.setVisible(true);
    }
}
