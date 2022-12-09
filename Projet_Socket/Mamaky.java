package read;

import ff.Fonction;

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

public class Mamaky {
    Fonction fonction = new Fonction();;

    public Mamaky(Object o, int ll, int con, JPanel jp) {

        fonction.printStr(fonction.vakio(o));
        String[][] vavs;
        File fi = new File(o.getClass().getSimpleName() + ".txt");
        try {
            FileReader fr = new FileReader(fi);
            BufferedReader bfr = new BufferedReader(fr);
            String f = bfr.readLine();
            int indice = 0;
            vavs = new String[ll][con];
            vavs = fonction.vakio(o);
            while (f != null) {
                vavs[indice] = f.split(",");
                f = bfr.readLine();
                indice++;
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

    }

}
