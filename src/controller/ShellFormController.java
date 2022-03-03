package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ShellFormController {
    public TextArea txtCommand;
    public TextArea txtOutput;
    public Button btnExecute;
    private Process mysql;

    public void btnExecuteOnAction(ActionEvent actionEvent) {
    }

    public void initdata(String host, String port, String userName, String password) {
        try{
            mysql = new ProcessBuilder("mysql","-h",host,"--port",port,"-u",userName,"-p").start();
            mysql.getOutputStream().write(password.getBytes());
            mysql.getOutputStream().flush();

            txtCommand.getScene().getWindow().setOnCloseRequest(event -> {
                if (mysql.isAlive()){
                    mysql.destroy();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to establish the connection for some reason").show();
            if (mysql.isAlive()){
                mysql.destroyForcibly();
            }
        }
    }
}
