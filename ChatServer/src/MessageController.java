import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessageController extends Thread{
    private CopyOnWriteArrayList<UserSocket> userList;


    public MessageController(CopyOnWriteArrayList userList){
        this.userList=userList;
    }
    @Override
    public void run() {
        try {
            while(true){
                for (UserSocket socket : userList){
                    String message = socket.reader.readLine();
                    sendtoAll(message);
                    System.out.println("Running Sending message");
                }
                System.out.println("Message Checking ... ");
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendtoAll(String message){
        try {
            for (UserSocket user : this.userList) {
                user.writer.write(message + "\n");
                user.writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

