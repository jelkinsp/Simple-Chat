package ejercicio10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Socket client;
    ServerSocket server;

    public static void main(String[] args) {

        Server server = new Server();
        server.setConnection();
        server.startThreads();
        server.closeStreamSockets();
    }

    private void setConnection() {
        try {
            server = new ServerSocket(8000);
            System.out.println("Esperando conexion entre en el puerto 8000");
            client = server.accept();
            System.out.println("Conexion establecida");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startThreads(){
        ThreadRead read = new ThreadRead(client);
        ThreadWrite write = new ThreadWrite(client);
        read.start();
        write.start();

        try {
            read.join();
            write.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void closeStreamSockets() {
        try{
            client.close();
            server.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
