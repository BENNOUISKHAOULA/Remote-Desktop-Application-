package Client;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class ReceivingScreen extends Thread
{
    private ObjectInputStream cObjectInputStream = null;
    private JPanel cPanel = null;
    private boolean continueLoop = true;
    InputStream oin = null;
    Image image1 = null;
    public ReceivingScreen(InputStream in, JPanel p)
    {
        oin = in;
        cPanel = p;
        start();
    }
    public  void run ()
    {
        while(true)
        {
            try
            {
                byte[] bytes = new byte[1024*1024];
                int count = 0;
                do
                {
                    count += oin.read(bytes, count, length-count);
                }while(!(count>4&&bytes[count-2] == (bytes)-1&&bytes[count-1] == (bytes)-39));
                image1 = ImageIO.read(new ByteArrayInputStream(bytes));
                image1 = image1.getScaledInstance(cPanel.getWidth(), cPanel.getHeight, Image.SCALE_FAST);
                Graphics graphics = cPanel.Graphics();
                graphics.drawImage(image1, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel );

            }catch(IOException e)
            {
                e.printStackTrace();
            }

        }
    }
}
