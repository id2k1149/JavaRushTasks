package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + "joined the chat");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + "left the chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();

                if (message.getType() == null) {
                    throw new IOException("Unexpected MessageType");
                }

                switch(message.getType()) {
                    case NAME_REQUEST:
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                        break;

                    case NAME_ACCEPTED:
                        notifyConnectionStatusChanged(true);
                        return;

                    default:
                        throw new IOException("Unexpected MessageType");

                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();

                if (message.getType() == null) {
                    throw new IOException("Unexpected MessageType");
                }

                switch (message.getType()) {
                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;

                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;

                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;

                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }
    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Enter Server Address");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Enter Server Port");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Enter User Name");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Can't send the message");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Socket thread is interrupted!");
            }
        }

        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено.\n" +  "Для выхода наберите команду 'exit'.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }

        // Считывай сообщения с консоли пока клиент подключен
        String message;
        while (clientConnected) {
            message = ConsoleHelper.readString();
            if (message.equals("exit")) break;
            // После каждого считывания, если метод shouldSendTextFromConsole() возвращает true,
            if (shouldSendTextFromConsole()) {
                // отправь считанный текст с помощью метода sendTextMessage()
                sendTextMessage(message);
            }
        }
    }
}
