package ejercicio10;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * Cliente
 */
public class Client {
    Socket client;

    public static void main(String[] args) {
        Client client = new Client();
        client.setConnection();
        client.startThreads();
        client.closeSockets();
    }

    /**
     * Establece la conexion
     *
     */
    private void setConnection() {
        try {
            client = new Socket("localhost", 8000);
            System.out.println("Socket cliente iniciado");
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
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
