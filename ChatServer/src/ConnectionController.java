import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionController extends Thread{
    private static CopyOnWriteArrayList<UserSocket> userList;
    public ConnectionController(CopyOnWriteArrayList userList){
        this.userList=userList;
    }
    @Override
    public void run() {
        while(true) {
            for (UserSocket user : userList){
                try {
                    if (!user.isConnected() && user.isClosed()) {
                        removeUser(user);
                        System.out.println(user.getName() + " is Lefted ");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void removeUser(UserSocket user) throws IOException {
        user.reader.close();
        user.writer.close();
        user.close();
        userList.remove(user);
        System.out.println("USER DELETED");
    }
}
