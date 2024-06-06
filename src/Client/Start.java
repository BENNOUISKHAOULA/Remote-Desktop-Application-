package Client;

import javax.swing.*;
import java.net.Socket;
import java.io.IOException;

public class Start {
    static String port = "4907";

    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Enter the Server IP Address");
        new Start().initialize(ip, Integer.parseInt(port));
    }

    public void initialize(String ip, int port) {
        try {
            Socket sc = new Socket(ip, port);
            System.out.println("Connected to " + ip);
            Authentification frame1 = new Authentification(sc);
            frame1.setSize(300, 80);
            frame1.setLocation(500, 300);
            frame1.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
