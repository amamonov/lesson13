package part1.lesson10;

import java.io.*;
import java.net.Socket;
import java.util.*;


public class Client {
    String name = "";

    public void clientStart(String host, int serverPort) {
        try {
            Socket serverSocket = new Socket(host, serverPort);

            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            String message = "";
            String newHost = "";
            int newPort = 0;
            System.out.print("Type *ClientsName (like *Alexander ) to send private message.\n" +
                             "Your name: ");

            while (true){
                message = scanner.nextLine();
                if (!(message.isEmpty())) {
                    name = message;
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    message = bufferedReader.readLine();
                    char[] arr = new char[message.length()];
                    arr = message.toCharArray();
                    String tempPort = "";

                    //                System.out.println("Server's feedback: " + message);
                    for (int i = 1; i < arr.length; i++) {
                        if ((arr[i] == '$') && (arr.length > 1)) {
                            for (int j = 1; j < i; j++) {
                                newHost += arr[j];
                            }
                            for (int j = i + 1; j < arr.length; j++) {
                                if (arr[j] != '$')
                                    tempPort += arr[j];
                            }
                            newPort = Integer.valueOf(tempPort);
                        }
                    }
                    break;
                }
                else {
                    System.out.print("You can not use empty name. Try again: ");
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
//            System.out.println("Host = " + newHost + "; port = " + newPort);

            Socket socket = new Socket(newHost, newPort);

            BufferedWriter newBufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader newBufferedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Got connection to server. Host = " + newHost + "; port = " + newPort + ". Welcome!");

            new Reader(socket, newBufferedReader);

            while (true) {
                try {
                    message = scanner.nextLine();
                    if (message != "") {
                        newBufferedWriter.write(name + ": " + message);
                        newBufferedWriter.newLine();
                        newBufferedWriter.flush();
                    }
                    if (message.equals("quit")) {
//                        socket.close();
                        System.out.println("Lost connection to server.");
                        break;
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Reader implements Runnable {
        private BufferedReader buf;
        private Thread t;
        private Socket socket;

        Reader (Socket socket, BufferedReader buf) {
            this.buf = buf;
            this.socket = socket;
            t = new Thread(this, "ClientsReader");
            t.start();
        }

        public void run() {
            try {
                String line;
                while((line = buf.readLine()) != null) {
                    System.out.println(line);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}