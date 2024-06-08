package Client;

import javax.swing.*;
import java.net.Socket;
import java.io.IOException;

public class Start {
    static String port = "4907";

    public static void main(String[] args) {
        try {
            // Set NimbusLookAndFeel as the look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            String ip = JOptionPane.showInputDialog("Enter the Server IP Address");
            new Start().initialize(ip, Integer.parseInt(port));
        });
    }

    public void initialize(String ip, int port) {
        try {
            Socket sc = new Socket(ip, port);
            System.out.println("Connected to " + ip);
            Authentification frame1 = new Authentification(sc);
            frame1.setSize(300, 100);
            frame1.setLocation(500, 300);
            frame1.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}