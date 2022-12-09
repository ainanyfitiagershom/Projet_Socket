package ff;

import server.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import javax.swing.*;

public class Fonction {

    public void printStr(String[][] str) {
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length; j++) {
                // System.out.println(str[i][j]);

            }
        }

    }

    public int count_liste(Object o) {
        int k = 0;
        try {
            File fi = new File(o.getClass().getSimpleName() + ".txt");
            FileReader r = new FileReader(fi);
            BufferedReader br = new BufferedReader(r);
            String rdl = br.readLine();
            while (rdl != null) {
                rdl = br.readLine();
                k++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;

    }

    public Field[] count_Field(Object o) {
        int a = o.getClass().getDeclaredFields().length;
        Field[] fd = new Field[a];
        for (int i = a; i < a; i++) {
            fd[i] = o.getClass().getDeclaredFields()[i];
        }
        return fd;
    }

    public String[][] vakio(Object o) {
        int lign = count_liste(o);
        int col = count_Field(o).length;
        Field[] fi = o.getClass().getDeclaredFields();
        String[][] rep = new String[lign][col];
        File fesh = new File(o.getClass().getSimpleName() + ".txt");
        try {
            FileReader r = new FileReader(fesh);
            BufferedReader br = new BufferedReader(r);
            String f = br.readLine();
            int k = 0;
            while (f != null) {
                rep[k] = f.split(",");
                f = br.readLine();
                k++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;

    }

    public JPanel get_liste(Object o) {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();

        jf.add(jp);
        jf.setSize(350, 350);
        jf.setVisible(true);
        return jp;

    }
}
