package sample.Klient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextField msgField;

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    private boolean connection = true;

    final String SERVER_IP = "localhost";
    final int SERVER_PORT = 8119;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String s = in.readUTF();
                        textArea.appendText(s+"\n" );

                        if (s.equals("/end")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            if (msgField.getText().equals("/end") && connection) {
                out.writeUTF(msgField.getText());
                textArea.appendText("\n Close server connection");
                connection = false;
            } else if (connection) {
                out.writeUTF(msgField.getText());
                msgField.clear();
                msgField.requestFocus();
            } else textArea.appendText("\n Close server connection");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}