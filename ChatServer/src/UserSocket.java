import java.io.*;
import java.net.Socket;

public class UserSocket extends Socket {
    private String name;
    private Socket socket;
    BufferedReader reader;
    BufferedWriter writer;
    public UserSocket(Socket socket){
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() { return this.name;}

}
