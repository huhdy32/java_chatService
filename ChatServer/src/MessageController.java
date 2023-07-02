import java.io.IOException;
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
                for (UserSocket user : userList){
                    String name = user.getName();
                    String message = user.reader.readLine();
                    if ( message == null){
                        ConnectionController.removeUser(user);
                    }else if (message.equals("EXIT")){
                        user.writer.close();
                        user.reader.close();
                        user.close();
                        message = "s connection closed!! Bye :)";
                        userList.remove(user);
                    }
                    sendtoAll(name, message);
                    System.out.println(name + " : " + message);
//                    System.out.println("Running Sending message");
                }
//                System.out.println("Message Checking ... ");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendtoAll(String name, String message){
        try {
            for (UserSocket user : this.userList) {
                if (name.equals(user.getName())) continue;
                user.writer.write(name + " : " + message + "\n");
                user.writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

