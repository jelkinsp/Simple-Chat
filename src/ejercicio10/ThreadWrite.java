package ejercicio10;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadWrite extends Thread {
    Socket socket;

    public ThreadWrite(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String line;
        try (DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
            while (true) {

                System.out.print("Tu: ");
                line = new Scanner(System.in).nextLine();
                outputStream.writeUTF(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
