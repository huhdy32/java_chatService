import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SendingThread extends Thread{
    private UserSocket currSocket;
    Scanner sc = new Scanner(System.in);
    BufferedWriter writer;
    public SendingThread(UserSocket user){
        this.currSocket = user;
        writer = user.writer;
    }
    @Override
    public void run() {
        try{
            while(true){
                String OutputMessage = sc.nextLine();
                writer.write(OutputMessage + "\n");
                writer.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
            sc.close();
        }
    }
}
