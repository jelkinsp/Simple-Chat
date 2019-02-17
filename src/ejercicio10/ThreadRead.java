package ejercicio10;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadRead extends Thread{
    Socket socket;
    public ThreadRead(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream inputStream = new DataInputStream(socket.getInputStream())){
            while (true) {
                System.out.println("\rOtro: "+inputStream.readUTF());
                System.out.print("Tu: ");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
