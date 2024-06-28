package NewUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ActionHandler implements ActionListener {
    private static Logger log = Logger.getLogger("Action Handler");
    private static Socket cs;
    
    public static Socket getSocket() {
        return cs;
    }

    private final GUI frame;

    public ActionHandler(GUI frame) {
        this.frame = frame;
    }

    @SuppressWarnings("unused")
    @Override
    public void actionPerformed(ActionEvent ae) {
        String userTxt = " ";
        String passTxt = " ";
        String txUserField = frame.getUserField().getText();
        String txPassField = frame.getPassField().getText();
        File file = new File("userPass.txt");

        try (Scanner scan = new Scanner(file); FileWriter fw = new FileWriter(file, true)) {
            while (scan.hasNext()) {
                userTxt = scan.nextLine();
                passTxt = scan.nextLine();

                if (txUserField.equals(userTxt) || txPassField.equals(passTxt)) { 
                    JOptionPane.showMessageDialog(frame, "Username or password is already in use");
                    frame.getUserField().setText("");
                    frame.getPassField().setText("");
                    frame.getUserField().requestFocus();        
                } else if (txUserField.equals("") && txPassField.equals(""))
                    JOptionPane.showMessageDialog(frame, "Please insert Username and Password");
                else {
                    fw.write(txUserField + "\r\n" + txUserField);
                    JOptionPane.showMessageDialog(frame, "Account has been created");
                    frame.dispose();
                    Login.GUI loginGUI = new Login.GUI();
                }

            }
        }  catch (IOException e) {
                log.log(Level.SEVERE, e.getMessage());
        }
    }

}
