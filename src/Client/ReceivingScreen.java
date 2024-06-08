package Client;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ReceivingScreen extends Thread {
    private InputStream oin;
    private JPanel cPanel;
    private boolean continueLoop = true;
    private Image image1 = null;

    public ReceivingScreen(InputStream in, JPanel p) {
        this.oin = in;
        this.cPanel = p;
        start();
    }

    public void run() {
        while (continueLoop) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int read = -1;
                while ((read = oin.read(buffer)) != -1) {
                    baos.write(buffer, 0, read);
                    if (buffer[read - 2] == (byte) -1 && buffer[read - 1] == (byte) -39) {
                        break;
                    }
                }
                byte[] imageBytes = baos.toByteArray();
                image1 = ImageIO.read(new ByteArrayInputStream(imageBytes));
                Graphics2D graphics = (Graphics2D) cPanel.getGraphics();
                graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), null);
            } catch (IOException e) {
                e.printStackTrace();
                continueLoop = false;
            }
        }
    }
}
