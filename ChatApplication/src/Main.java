import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Type A Name >> ");
//        Scanner sc = new Scanner(System.in);
        ReceiveThread R_thread = null;
        SendingThread S_thread = null;
        try {
            Socket socket = new Socket("localhost", 1999);
            R_thread = new ReceiveThread(socket);
            S_thread = new SendingThread(socket);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Server is OFFLINE");
        }

        R_thread.start();
        S_thread.start();






    }
}