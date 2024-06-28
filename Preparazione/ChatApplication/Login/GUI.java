package Login;

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


public class GUI extends JFrame {
    public static Logger log = Logger.getLogger("GUI");

    private final JTextField ipField = new JTextField("127.0.0.1");
    private final JTextField portField = new JTextField("4400");
    private final JTextField userField = new JTextField(15);
    private final JTextField passField = new JTextField(15);
    private final JButton bLogin = new JButton("Login");
    private final JButton bNewUser = new JButton("Create new User");
    private final Login.ActionHandler act;

    public GUI() {
        super("Login Autentication");
        
        setLayout(new BorderLayout());
        setSize(300,200);

        JPanel upperPanel = new JPanel();
        upperPanel.add(new JLabel("Server Address"));
        upperPanel.add(ipField);
        upperPanel.add(new JLabel("Port"));
        upperPanel.add(portField);
        add(upperPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Username"));
        centerPanel.add(userField);
        centerPanel.add(new JLabel("Password"));
        centerPanel.add(passField);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(bLogin);
        bottomPanel.add(bNewUser);
        add(bottomPanel, BorderLayout.SOUTH);

        act = new ActionHandler(this);
        bLogin.addActionListener(act);
        bNewUser.addActionListener(act);

        passCheck();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        log.log(Level.INFO, "Set up and ready to go!");
    }

    public JTextField getUserField() { return userField; }

    public JTextField getPassField() { return passField; }

    public JTextField getIpField() { return ipField; }

    public JTextField getPortField() { return portField; }

    private void passCheck() {
        Writer wr = null;
        File check = new File ("userPass.txt");

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
