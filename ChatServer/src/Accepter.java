import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Accepter extends Thread{

    private ServerSocket listener;
    private CopyOnWriteArrayList userList;
    public Accepter(ServerSocket listener, CopyOnWriteArrayList userlist) {
        this.listener=listener;
        this.userList=userlist;
    }
    @Override
    public void run() {
        while(true){
            try {
                UserSocket user = new UserSocket(listener.accept());
                System.out.println("New User Connected!!");
                user.writer.write("Set your name >> " + "\n");
                user.writer.flush();
                user.setName(user.reader.readLine());


                userList.add(user);
                System.out.println("USER COUNT : " + userList.size());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}
