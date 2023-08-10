import java.io.IOException;
import java.util.ArrayList;

public class ReceivingThread extends Thread{
    private UserSocket user;
    private static UserListController userListController = new UserListController(new ArrayList<>());
    public ReceivingThread(UserSocket socket, UserListController userListController) {
        this.user = socket;
        userListController.addUser(socket);
    }
    @Override
    public void run(){
        try {
            user.writer.write("Set your name >> " + "\n");
            user.writer.flush();
            String username = user.reader.readLine();
            System.out.println("========== ["+username+" 입장"+"]");
            user.writer.write("[성공적으로 접속되었습니다]" + "\n");
            user.writer.flush();
            user.setName(username);
            String message = user.reader.readLine();
            userListController.addUser(user);
            System.out.println(userListController.getUserList().size());
            while(message != null){
                sendAll(message);
                message = user.reader.readLine();
                System.out.println(user.getName() + " : " + message);
            }
            userListController.removeUser(user);
            System.out.println("현재남은 인원은 : [" + userListController.getUserList().size() + "명] 입니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendAll(String message) throws IOException {
        for (UserSocket user : userListController.getUserList()) {
            if (user.getName().equals(this.user.getName())) continue;
            user.writer.write(this.user.getName() + " : " + message + "\n");
            user.writer.flush();
        }
    }
}
