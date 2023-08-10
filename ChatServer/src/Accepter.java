import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Accepter extends Thread{

    private ServerSocket listener;
    private UserListController userListController;
    public Accepter(ServerSocket listener, UserListController userListController) {
        this.listener=listener;
        this.userListController = userListController;
    }
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("========== [서버 연결 대기중]");
                UserSocket user = new UserSocket(listener.accept());

                ReceivingThread RT = new ReceivingThread(user, userListController);
                RT.start();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
