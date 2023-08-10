import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String address = "localhost";
        int port = 1999;
        Scanner sc = new Scanner(System.in);
//        System.out.print("SET DESTINATION SERVER : ");
//        address = sc.next();
//        System.out.print("SET DESTINATION PORT   : ");
//        port = sc.nextInt();

        ReceiveThread R_thread = null;
        SendingThread S_thread = null;
        try {
            UserSocket user = new UserSocket(address, port);
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