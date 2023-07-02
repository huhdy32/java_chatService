import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ReceiveThread R_thread = null;
        SendingThread S_thread = null;
        try {
            UserSocket user = new UserSocket(new Socket("localhost", 1999));

            R_thread = new ReceiveThread(user);
            S_thread = new SendingThread(user);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Server is OFFLINE");
        }
        R_thread.start();
        S_thread.start();






    }
}