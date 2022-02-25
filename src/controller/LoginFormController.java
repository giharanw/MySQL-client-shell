package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginFormController {

    public TextField txtHost;
    public TextField txtPort;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Button btnConnect;
    public Button btnExit;
    
    public void btnConnectOnAction(ActionEvent event) {
        /* Let's validate some inputs */
        if (txtHost.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Host can't be empty").show();
            txtHost.requestFocus();
            txtHost.selectAll();
            return;
        }else if (!txtPort.getText().matches("\\d+")){
            new Alert(Alert.AlertType.ERROR, "Invalid port").show();
            txtPort.requestFocus();
            txtPort.selectAll();
            return;
        }else if (txtUsername.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Username can't be empty").show();
            txtUsername.requestFocus();
            txtUsername.selectAll();
            return;
        }

        try {

            /*
            String command = String.format("mysql -h %s -u %s -p%s --port %s -e exit",
                txtHost.getText(),
                txtUsername.getText(),
                txtPassword.getText(),
                txtPort.getText());
                */

            /* String[] command = { "mysql",
                    "-h",txtHost.getText(),
                    "-u",txtUsername.getText(),
                    "-p"+txtPassword.getText(),
                    "--port",txtPort.getText(),
                    "-e","exit"
            };
            Process mysql = Runtime.getRuntime().exec(command);
            */

            Process mysql = new ProcessBuilder("mysql",
                    "-h",txtHost.getText(),
                    "-u",txtUsername.getText(),
                    "-p",
                    "--port",txtPort.getText(),
                    "-e","exit").start();

            mysql.getOutputStream().write(txtPassword.getText().getBytes());
            mysql.getOutputStream().close();

            int exitCode = mysql.waitFor();
            if (exitCode !=0){
                new Alert(Alert.AlertType.ERROR, "Can't establish the connection, try again").show();
                txtUsername.requestFocus();
                txtUsername.selectAll();
            }else{
                System.out.println("Wade Goda!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void btnExitOnAction(ActionEvent event) {
        System.exit(0);
    }

}
