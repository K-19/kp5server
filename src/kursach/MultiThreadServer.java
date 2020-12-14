package kursach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {

    static ExecutorService executeIt = Executors.newFixedThreadPool(20);

    public static ServerSocket server;
    public static boolean stoped = false;

    public static void main() {
        try {
            Thread.sleep(1000);
            MainController.logMessage("Запуск сервера...");
            new DatabaseHandler(new Socket()).initialDB();

            server = new ServerSocket(Const.PORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                DatabaseHandler dbHandler = new DatabaseHandler(new Socket());
                dbHandler.getDbConnection();
                dbHandler.dbConnection.close();
                MainController.dbConnecting("Подключено");
                MainController.logMessage("База данных успешно подключена");
            } catch (Exception e) {
                MainController.logMessage("ОШИБКА БАЗЫ ДАННЫХ!!!");
            }
            MainController.logMessage("Сервер готов к работе");
            while (!server.isClosed()) {

                if(stoped) { break; }
                Socket client = server.accept();
                executeIt.execute(new MonoThreadClientHandler(client));
                MainController.addClient();
                MainController.logMessage("Подключён новый клиент. Адрес: " + client.getInetAddress().getCanonicalHostName() + ":" + client.getPort());
            }
            executeIt.shutdown();
        } catch (SocketException e) {
            try {
                if (server != null) server.close();
            } catch (IOException e1) {}
            MainController.logMessage("Сервер остановлен");
        } catch (Exception e) {}
    }
}