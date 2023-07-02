import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveThread extends Thread{
    private UserSocket currSocket;
    private BufferedReader reader;
    public ReceiveThread (UserSocket user){
        this.currSocket = user;
        reader = user.reader;
    }


    @Override
    public void run() {
        try{
            while(true) {
                String message = reader.readLine();
                if (message == null) {
                    currSocket.writer.close();
                    currSocket.reader.close();
                    this.currSocket.close();
                    System.out.println();
                    System.out.println("-------The Connection is closed!-------");
                    break;
                }
                System.out.println(message);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
