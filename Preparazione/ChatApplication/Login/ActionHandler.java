package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ActionHandler implements ActionListener {
    private static Logger log = Logger.getLogger("Action Handler");
    private final GUI frame;
    private static Socket cs;

    public static Socket getSocket() {
        return cs;
    }

    ActionHandler(GUI frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().getClass().equals(JButton.class)) {
            switch (((JButton) ae.getSource()).getText()) {
                case "Login":
                    login();
                    break;
                case "Create new User":
                    newUser();
                    break;
                default:
                    log.log(Level.SEVERE, "Unrecognized button press");
                    break;
            }
        }
    }

    @SuppressWarnings("unused")
    private void newUser() {
        NewUser.GUI newUserGUI = new NewUser.GUI();
        frame.dispose();
    }

    private void login() {
        Boolean isUserValid = validateUser();
        if (!isUserValid) log.log(Level.WARNING, "Inserted credentials are not valid");
        else {
            try {
                cs = new Socket(frame.getIpField().getText(), Integer.parseInt(frame.getPortField().getText()));
                log.log(Level.INFO, "Connection established");
            } catch (IOException e) {
                log.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    @SuppressWarnings("unused")
    private Boolean validateUser() {
        Boolean isUserValid = null;
        String userTxt = " ";
        String passTxt = " ";
        String txUserField = frame.getUserField().getText();
        String txPassField = frame.getPassField().getText();
        File file = new File("userPass.txt");

            try (Scanner scan = new Scanner(file); FileWriter fw = new FileWriter(file, true)) {
                while (scan.hasNext()) {
                    userTxt = scan.nextLine();
                    passTxt = scan.nextLine();

                    if (txUserField.equals(userTxt) && txPassField.equals(passTxt)) {
                        Chat.GUI chatGUI = new Chat.GUI();
                        frame.dispose();
                        isUserValid = true;
                    } else if (txUserField.equals("") && txPassField.equals("")) 
                        JOptionPane.showMessageDialog(frame, "Please insert Username and Password");
                    else {
                        frame.getUserField().setText("");
                        frame.getPassField().setText("");
                        frame.getUserField().requestFocus();
                        isUserValid = false;
                    }
                }
            } catch (IOException e) {
                log.log(Level.SEVERE, e.getMessage());
            }
            return isUserValid;
    }
}
