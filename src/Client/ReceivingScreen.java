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
                byte[] bytes = new byte[1024 * 1024];
                int count = 0;
                int length = bytes.length;
                while (count < length) {
                    int read = oin.read(bytes, count, length - count);
                    if (read == -1) {
                        throw new IOException("End of stream reached");
                    }
                    count += read;
                }

                // Assuming the JPEG format with markers -1 and -39
                if (count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes, 0, count);
                    image1 = ImageIO.read(byteArrayInputStream);
                    image1 = image1.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_FAST);
                    Graphics graphics = cPanel.getGraphics();
                    graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
                }

            } catch (IOException e) {
                e.printStackTrace();
                continueLoop = false; // Exit the loop on IOException
            }
        }
    }
}
