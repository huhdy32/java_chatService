import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread{
    private Socket currSocket;
    private BufferedReader reader;
    public ReceiveThread (Socket socket){
        try{
            this.currSocket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try{
            while(reader.ready()) {
                System.out.println(reader.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
