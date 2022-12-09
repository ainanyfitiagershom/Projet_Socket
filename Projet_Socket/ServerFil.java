package server;

import client.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import javax.swing.border.EmptyBorder;

public class ServerFil {
    static ArrayList<Filee> myFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int fileid = 0;

        JFrame f = new JFrame("Server");
        f.setSize((400), 400);
        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        JScrollPane scro = new JScrollPane(jp);
        scro.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel jlTitle = new JLabel("send file");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        f.add(jlTitle);
        f.add(scro);
        f.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            try {
                Socket socket = serverSocket.accept();

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                int fileNameL = dataInputStream.readInt();

                if (fileNameL > 0) {
                    byte[] fileNameBytes = new byte[fileNameL];
                    dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
                    String fileName = new String(fileNameBytes);

                    int fileContentL = dataInputStream.readInt();

                    if (fileContentL > 0) {
                        byte[] fileContentBytes = new byte[fileContentL];
                        dataInputStream.readFully(fileContentBytes, 0, fileContentL);

                        JPanel jpFR = new JPanel();
                        jpFR.setLayout(new BoxLayout(jpFR, BoxLayout.Y_AXIS));

                        JLabel jlFileName = new JLabel(fileName);
                        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
                        jlFileName.setBorder(new EmptyBorder(10, 0, 10, 0));

                        if (getFileExtension(fileName).equalsIgnoreCase("txt")) {
                            jpFR.setName(String.valueOf(fileid));
                            jpFR.addMouseListener(getMyMouseListener());
                            System.out.println(jlFileName);
                            jpFR.add(jlFileName);
                            jp.add(jpFR);
                            f.validate();

                        } else {
                            jpFR.setName(String.valueOf(fileid));
                            jpFR.addMouseListener(getMyMouseListener());

                            jpFR.add(jlFileName);
                            jp.add(jpFR);
                            f.validate();

                        }

                        myFiles.add(new Filee(fileid, fileName, fileContentBytes, getFileExtension(fileName)));

                    }
                }
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    public static MouseListener getMyMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JPanel j = (JPanel) e.getSource();

                int fileid = Integer.parseInt(j.getName());

                for (Filee filee : myFiles) {
                    if (filee.getId() == fileid) {
                        JFrame jfP = createFrame(filee.getName(), filee.getData(), filee.getFileExtension());
                        jfP.setVisible(true);
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        };
    }

    public static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
        JFrame jframe = new JFrame("dOWNLOAD");
        jframe.setSize(400, 400);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JLabel jlTitle = new JLabel("dOWNLOAD");
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel jlPrompt = new JLabel("Are you sure to download : " + fileName);
        jlPrompt.setFont(new Font("Arial", Font.BOLD, 20));
        jlPrompt.setBorder(new EmptyBorder(20, 0, 10, 0));
        jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbYes = new JButton("Yah");
        jbYes.setPreferredSize(new Dimension(70, 30));
        jbYes.setFont(new Font("Arial", Font.BOLD, 10));

        JButton jbNo = new JButton("No");
        jbNo.setPreferredSize(new Dimension(70, 30));
        jbNo.setFont(new Font("Arial", Font.BOLD, 10));

        JLabel jlFileContent = new JLabel();
        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButton = new JPanel();
        jpButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        jpButton.add(jbYes);
        jpButton.add(jbNo);

        if (fileExtension.equalsIgnoreCase("txt")) {
            jlFileContent.setText("<html>" + new String(fileData) + "<html>");

        } else {
            jlFileContent.setIcon(new ImageIcon(fileData));
        }

        jbYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fileDown = new File(fileName);

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileDown);

                    fileOutputStream.write(fileData);
                    fileOutputStream.close();

                    jframe.dispose();

                } catch (IOException error) {
                    error.printStackTrace();
                }
            }
        });

        jbNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.dispose();
            }
        });

        jPanel.add(jlTitle);
        jPanel.add(jpButton);

        jPanel.add(jlPrompt);
        jPanel.add(jlFileContent);

        jframe.add(jPanel);

        return jframe;
    }

    public static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');

        if (i > 0) {
            return fileName.substring(i + 1);
        } else {
            return "No extension found.";
        }
    }

}
