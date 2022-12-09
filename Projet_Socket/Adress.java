package adress;

import java.net.InetAddress;

public class Adress {
    public static void main(String[] args) throws Exception {
        InetAddress ad = InetAddress.getLocalHost();
        System.out.println(ad);

    }
}
