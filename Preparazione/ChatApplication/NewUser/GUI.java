package NewUser;

import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
    public static Logger log = Logger.getLogger("GUI");
    private final JButton bCreate = new JButton("Create");
    private final JTextField userField = new JTextField(15);
    private final JTextField passField = new JTextField(15);
    private final NewUser.ActionHandler act;

    public GUI() {
        super("User registration");
        
        setLayout(new BorderLayout());
        setSize(300, 200);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Username"));
        centerPanel.add(userField);
        centerPanel.add(new JLabel("Password"));
        centerPanel.add(passField);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(bCreate);
        add(bottomPanel, BorderLayout.SOUTH);

        act = new ActionHandler(this);
        bCreate.addActionListener(act);

        userExists();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        log.log(Level.INFO, "Set up and ready to go!");
    }

    public JTextField getUserField() { return userField; }

    public JTextField getPassField() { return passField; }

    private void userExists() {
        Writer wr = null;
        File check = new File("userPass.txt");

        if (check.exists()) {}
        else {
            try {
                File texting = new File("userPass.txt");
                wr = new BufferedWriter(new FileWriter(texting));
                wr.write("message");
            } catch (IOException e) {
                log.log(Level.SEVERE, e.getMessage());
            }
        }
    }

}
