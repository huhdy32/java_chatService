import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
public class Main {

    public static void main(String[] args) {
        UserListController userListController;
        Scanner sc = new Scanner(System.in);

        System.out.print("Run The Server : (y/n) >> ");
        ServerSocket serverSocket;

        if (sc.next().equals("y")){
            try {
                userListController = new UserListController(new ArrayList<>());

                serverSocket = new ServerSocket(1999); // 서버 소켓 포트 열기
                System.out.println("System port is opened!!");

                Accepter accepter = new Accepter(serverSocket, userListController); // 유저 접속용 쓰레드 생성
                accepter.start(); // ... 실행
                System.out.println("ACCEPT CONTROLLER is running...");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println("CLOSE!");
        }

    }
}