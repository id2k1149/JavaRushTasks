package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
        класс Server
        Сервер должен поддерживать множество соединений с разными клиентами одновременно.
        Это можно реализовать с помощью следующего алгоритма:

        - Сервер создает серверное сокетное соединение.
        - В цикле ожидает, когда какой-то клиент подключится к сокету.
        - Создает новый поток обработчик Handler, в котором будет происходить обмен сообщениями с клиентом.
        - Ожидает следующее соединение.

 */

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

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


    private static class Handler extends Thread {
        Socket socket;

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

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry each: connectionMap.entrySet()) {
                String name = each.getKey().toString();
                if (!name.equals(userName)) {
                    Message message = new Message(MessageType.USER_ADDED, name);
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message messageReceived = connection.receive();
                String text = "";
                if (messageReceived.getType() == MessageType.TEXT) {
                    text = userName + ": " + messageReceived.getData();
                    Message messageToSend = new Message(MessageType.TEXT, text);
                    sendBroadcastMessage(messageToSend);
                }
                else ConsoleHelper.writeMessage("Неверный тип сообщения");
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("New connection with remote address: " + socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket)) {
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("произошла ошибка при обмене данными с удаленным адресом");
            }
            ConsoleHelper.writeMessage("Remote address " + socket.getRemoteSocketAddress() +  " connection is closed");
        }
    }
}
