import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;


public class Main {

    public static void main(String[] args) {
        CopyOnWriteArrayList<UserSocket> userList = new CopyOnWriteArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Run The Server : (y/n) >> ");
        ServerSocket serverSocket;

        if (sc.next().equals("y")){
            try {
                serverSocket = new ServerSocket(1999); // 서버 소켓 포트 열기
                System.out.println("System port is opened!!");

                Accepter accepter = new Accepter(serverSocket, userList); // 유저 접속용 쓰레드 생성
                accepter.start(); // ... 실행
                System.out.println("ACCEPTER is running...");

                MessageController messageController = new MessageController(userList); // 유저 메세지 관리자 생성
                messageController.start(); // ... 실행
                System.out.println("MESSAGER is running...");
                ConnectionController connectionController = new ConnectionController((userList)); // 연결 관리자 생성
                connectionController.start(); // ... 살향
                System.out.println("CONNECTIONCONTROLLER is running...");


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            System.out.println("CLOSE!");
        }

    }
}