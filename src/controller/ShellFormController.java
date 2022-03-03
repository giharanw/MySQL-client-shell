package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ShellFormController {
    public TextArea txtCommand;
    public TextArea txtOutput;
    public Button btnExecute;
    private String host;
    private String port;
    private String userName;
    private String password;

    public void btnExecuteOnAction(ActionEvent actionEvent) {
    }

    public void initdata(String host, String port, String userName, String password) {
    }
}
