package ejercicio10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor
 *
 */
public class Server {
    Socket client;
    ServerSocket server;

    public static void main(String[] args) {
        Server server = new Server();
        server.setConnection();
        server.startThreads();
        server.closeSockets();
    }

    /**
     * Establece la conexion
     *
     */
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

    /**
     * Inicializa los hilos
     *
     */
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

    /**
     * Cerrar hilos
     */
    private void closeSockets() {
        try{
            client.close();
            server.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
