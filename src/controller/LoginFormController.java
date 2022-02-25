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

        String command = String.format("mysql -h %s -u %s -p%s --port %s",
                txtHost.getText(),
                txtUsername.getText(),
                txtPassword.getText(),
                txtPort.getText());
        try {
            Process mysql = Runtime.getRuntime().exec(command);
            System.out.println(mysql.waitFor());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void btnExitOnAction(ActionEvent event) {
    }

}
