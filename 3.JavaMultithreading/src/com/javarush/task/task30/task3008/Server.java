package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;

        private Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message request = new Message(MessageType.NAME_REQUEST, "Введите имя: ");
            Message response;
            String userName;
            while (true) {
                connection.send(request);
                response = connection.receive();
                userName = response.getData();
                if (response.getType() == MessageType.USER_NAME
                        && !userName.isEmpty()
                        && !userName.equals("")
                        && !connectionMap.containsKey(userName)) {
                    connectionMap.put(userName, connection);
                    break;
                }
            }
            connection.send(new Message(MessageType.NAME_ACCEPTED, "Вы добавлены в чат!"));
            return userName;
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> each: connectionMap.entrySet()) {
            Connection connection = each.getValue();
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Can't send the message to " + each.getKey());
            }
        }
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Сервер Запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
