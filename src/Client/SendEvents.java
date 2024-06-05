package Client;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendEvents implements KeyListener, MouseMotionListener, MouseListener
{
    private Socket cSocket = null;
    private JPanel cPanel = null;
    private PrintWriter write = null;
    String width =" ", height = "";
    double w;
    double h;
    public SendEvents(Socket cSocket, JPanel p, String width, String height)
    {
        cSocket = s;
        cPanel = p;
        this.width = width;
        this.height = height;
        w = Double.valueOf(width.trim()).doubleValue();
        h = Double.valueOf(height.trim()).doubleValue();
    }
    cPanel.addKeyListener(this);
    cPanel.addMouseListener(this);
    cPanel.addMouseMotionListener(this);
    try
    {
        write = new PrintWriter((cSocket.getOutputStream()));
    }catch(IOException e)
    {
        e.printStackTrace();
    }
    pubic void mouseDragged(MouseEvent e)
    {

    }
    public void mouseMoved(MouseEvent e)
    {
        double xScale =  (double)w/cPanel.getWidth();
        double yScale =  (double)h/cPanel.getHeight();
        writer.println(Commands.MOVE_Mouse.getAbbrev);
        writer.println((int)e.getX()*xScale);
        writer.println((int)e.getY()*yScale);
        writer.flush();
    }
    public void mouseClicked(MouseEvent e)
    {

    }
    public void mousePressed(MouseEvent e)
    {
        writer.println(Commands.PRESS_MOUSE.getAbbr());
        int button = e.getButton();
        int xButton = 16;
        if (button ==3)
        {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }
}
