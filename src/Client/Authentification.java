package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Authentification extends JFrame implements ActionListener {
    
    // Déclaration des variables pour la connexion, les flux de données, et l'interface utilisateur
    private Socket cSocket = null;
    DataOutputStream passchk = null;
    DataInputStream verification = null;
    String verify = "";
    JButton submit;
    JPanel panel;
    JLabel label, label1;
    String width = "", height = "";
    JTextField text1;

    // Constructeur qui initialise l'interface d'authentification
    public Authentification(Socket cSocket) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Initialisation des composants de l'interface utilisateur
        label1 = new JLabel();
        label1.setText("  Enter Password");
        text1 = new JTextField(15);
        this.cSocket = cSocket;
        label = new JLabel();
        label.setText("");
        this.setLayout(new BorderLayout());
        submit = new JButton("Submit");
        panel = new JPanel(new GridLayout(2, 1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label);
        panel.add(submit);
        add(panel, BorderLayout.CENTER);
        submit.addActionListener(this);
        setTitle("Authentification");
        setSize(400, 150);

    }
    
    // Méthode qui est appelée lorsqu'une action est effectuée (soumission du formulaire)
    public void actionPerformed(ActionEvent ae) {

        // Récupérer le mot de passe entré par l'utilisateur
        String value1 = text1.getText();
        try {

            // Initialiser les flux de données
            passchk = new DataOutputStream(cSocket.getOutputStream());
            verification = new DataInputStream(cSocket.getInputStream());

            // Envoyer le mot de passe au serveur pour vérification
            passchk.writeUTF(value1);
            passchk.flush();

            // Lire la réponse du serveur
            verify = verification.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vérifier la réponse du serveur
        if (verify.equals("valid")) {
            try {

                // Lire les dimensions de la fenêtre depuis le serveur
                width = verification.readUTF();
                height = verification.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Créer et afficher la fenêtre principale de l'application
            CreateFrame abc = new CreateFrame(cSocket, width, height);
            dispose();
        } else {

            // Si le mot de passe est incorrect, afficher un message d'erreur
            System.out.print("Please enter valid password");
            JOptionPane.showMessageDialog(this, "The Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);

            // Fermer la fenêtre d'authentification
            dispose();
        }
    }
}
