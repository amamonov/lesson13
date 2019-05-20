package part1.lesson10;

/**
 * Запускаем клиент в новом окне
 */
public class StartClient3 {
    public static void main(String[] args) {
        String host = "localhost";
        int serverPort = 23032;

        new Client().clientStart(host, serverPort);
    }
}
