import java.io.IOException;
import java.util.ArrayList;

public class UserListController {
    private ArrayList<UserSocket> userList;
    private ArrayList<UserSocket> tempList;
    public UserListController(ArrayList<UserSocket> list) {
        this.userList = list;
    }
    public synchronized void addUser(UserSocket user){
        tempList = new ArrayList<>(userList);
        tempList.add(user);
        userList = tempList;
    }
    public synchronized void removeUser(UserSocket user) throws IOException {
        user.reader.close();
        user.writer.close();
        user.close();
        tempList = new ArrayList<>(userList);
        tempList.remove(user);
        userList = tempList;
    }
    public ArrayList<UserSocket> getUserList(){
        return this.userList;
    }
}
