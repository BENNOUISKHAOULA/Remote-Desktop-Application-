package Client;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendEvents implements KeyListener, MouseMotionListener, MouseListener {
    private Socket cSocket = null;
    private JPanel cPanel = null;
    private PrintWriter writer = null;
    private String width = "";
    private String height = "";
    private double w;
    private double h;

    public SendEvents(Socket cSocket, JPanel p, String width, String height) {
        this.cSocket = cSocket;
        this.cPanel = p;
        this.width = width;
        this.height = height;
        w = Double.parseDouble(width.trim());
        h = Double.parseDouble(height.trim());

        cPanel.addKeyListener(this);
        cPanel.addMouseListener(this);
        cPanel.addMouseMotionListener(this);

        try {
            writer = new PrintWriter(cSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Implementation for mouseDragged
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double xScale = w / cPanel.getWidth();
        double yScale = h / cPanel.getHeight();
        writer.println(Commands.MOVE_MOUSE.getAbbrev());
        writer.println((int) (e.getX() * xScale));
        writer.println((int) (e.getY() * yScale));
        writer.flush();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Implementation for mouseClicked
    }

    @Override
    public void mousePressed(MouseEvent e) {
        writer.println(Commands.PRESS_MOUSE.getAbbrev());
        int button = e.getButton();
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implementation for mouseEntered
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implementation for mouseExited
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Implementation for mouseReleased
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implementation for keyTyped
    }

    @Override
    public void keyPressed(KeyEvent e) {
        writer.println(Commands.PRESS_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        writer.println(Commands.RELEASE_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }
}
