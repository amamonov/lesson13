package part1.lesson10;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Реализация сервера. Ждем появления клиента, после чего создаем для него отдельное соединение,
 * отправляем ему координаты этого соединения, обслуживаем его команды и сообщения.
 */
public class Server {
    HashMap<String, BufferedWriter> clients = new HashMap<>();                              /* для привата */

    public void serverStart(String host, int port) {
        Serve server = new Serve(host, port);
    }

    /**
     * Получение контакта клиента от всегда открытого порта сервера.
     */
    private class Serve implements Runnable {
        private Thread t;
        private String host;
        private int port;
        private String message;

        Serve(String host, int port) {
            this.host = host;
            this.port = port;

            t = new Thread(this, "Server");
            t.start();
            System.out.println("Server has started successfully. Host = " + host + "; port = " + port);
        }

        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName(host));
                Socket socket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String newHost = host;
                int newPort = port;

                while (true) {
                    message = reader.readLine();
//                    System.out.println(message);
                    if (message != null) {
                        newServe newServer = new newServe(newHost, ++newPort, message);
                        Thread.sleep(100);                                                  /* дать отработать */
                        writer.write("$" + newHost + "$" + newPort);
                        writer.newLine();
                        writer.flush();
                        System.out.println("New client's connection. Host = " + newHost
                                + "; port = " + newPort);
                    }
                    socket = serverSocket.accept();
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Новое соединение с клиентом. Обработка приватных сообщений и команды "quit".
     */
    private class newServe implements Runnable {
        private Thread t;
        private String host;
        private int port;
        private String message;
        private String name;

        newServe(String host, int port, String name) {
            this.host = host;
            this.port = port;
            this.name = name;

            t = new Thread(this, "newServerConnection");
            t.start();
//            System.out.println("New connection established. Host = " + host + "; port = " + port);
        }

        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName(host));
                Socket socket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                clients.put(name, writer);

                while (true) {
                    message = reader.readLine();
//                    System.out.println(message);
                    if (message != "") {
                        name = "";
                        if (message.contains("*")) {                                        /* *Alexander */
                            char[] arr = new char[message.length()];
                            arr = message.toCharArray();
                            int start = 0;
                            int finish = 0;

                            for (int i = 0; i < arr.length; i++) {
                                if ((arr[i] == '*') && (i < (arr.length-1))) {
                                    start = i+1;
                                }
                                else if ((arr[i] == ' ') && (start > 0)) {
                                    finish = i;
                                    break;
                                }
                            }
                            name = message.substring(start, finish);
                        }
//                        System.out.println(name);
                        if (clients.containsKey(name)) {                                    /* приват */
                            BufferedWriter bw = clients.get(name);
                            bw.write(message);
                            bw.newLine();
                            bw.flush();
                        }
                        else {
                            for (BufferedWriter bw : clients.values()) {                    /* всем */
                                if (bw != writer) {
                                    bw.write(message);
                                    bw.newLine();
                                    bw.flush();
                                }
                            }
                        }
                    }
                    else if ((message.isEmpty()) || (message.contains("quit"))) {
                        clients.remove(writer);
                        socket.close();
                        serverSocket.close();
                        break;
                    }
                }
                System.out.println("Disconnected client host = " + host + "; port = " + port);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}