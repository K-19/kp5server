package kursach;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainController {

    private static StringBuffer logs = new StringBuffer(1000000);

    @FXML
    private TextArea logArea;
    private static TextArea logArea_;

    @FXML
    private Button stopButton;

    @FXML
    private TextField alreadyField;

    @FXML
    private TextField portField;

    @FXML
    private TextField clientsField;
    private static TextField clientsField_;
    private static Integer clients = 0;

    @FXML
    private TextField dbField;
    private static TextField dbField_;

    public static Stage stage;



    private TimeThread timeThread = new TimeThread();

    @FXML
    void initialize() {
        portField.setText(Integer.toString(Const.PORT));
        clientsField.setText("0");
        timeThread.start();
        logArea_ = logArea;
        clientsField_ = clientsField;
        dbField_ = dbField;

        stopButton.setOnAction(actionEvent -> {
            try {
                timeThread.interrupt();
                MultiThreadServer.stoped = true;
                stage.close();
                MultiThreadServer.server.close();
            } catch (Exception e) {}
        });
        //MultiThreadServer.fxmlIsLoading = false;
    }
    public static synchronized void logMessage(String text) {
        if(logs.length() > 1000000) logs.delete(0, logs.indexOf("\n"));

        SimpleDateFormat formatter = new SimpleDateFormat("[dd-MM-yy HH:mm:ss] ");
        String currentTime = formatter.format(new Date());

        logs.append(currentTime).append(text).append("\n");

        try {
            File file = new File(Const.LOG_FILE);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(currentTime + text + "\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {}

        logArea_.setText(logs.toString());
    }
    public static synchronized void addClient() {
        clients++;
        clientsField_.setText(Integer.toString(clients));
    }
    public static synchronized void exitClient() {
        clients--;
        clientsField_.setText(Integer.toString(clients));
    }
    public static synchronized void dbConnecting(String text) {
        dbField_.setText(text);
    }

    class TimeThread extends Thread {
        public void run() {
            long seconds = 0;
            while(true) {
                try {
                    Thread.sleep(1000);
                    seconds++;
                    alreadyField.setText(strTime(seconds));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private String strTime(long sec) {
            if (sec < 60) return sec + " сек.";
            if (sec < 3600) return sec/60 + " мин. " + sec%60 + " сек.";
            if (sec < 86400) return sec/3600 + " ч. " + sec%3600/60 + " мин. " + sec%60 + " сек.";
            if (sec > 86400) return sec/86400 + " дней | " + sec%86400/3600 + " ч. " + sec%3600/60 + " мин. " + sec%60 + " сек.";
            else return "Ошибка!";
        }
    }
}
