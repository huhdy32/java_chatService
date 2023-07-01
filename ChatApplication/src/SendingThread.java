import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SendingThread extends Thread{
    private Socket currSocket;
    Scanner sc = new Scanner(System.in);
    BufferedWriter writer;
    public SendingThread(Socket socket){
        try {
            this.currSocket = socket;
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try{
            while(true){
                System.out.print("Type a Message >> ");
                String OutputMessage = sc.nextLine();
                if (OutputMessage.equals("EXIT")){
                    sc.close();
                    System.out.println("Socket Closed!! Bye :)");
                    writer.write("Socket Closed!! Bye :)" + "\n");
                    writer.flush();
                    this.currSocket.close();
                }
                writer.write(OutputMessage + "\n");
                writer.flush();

            }
        }catch(Exception e){
            e.printStackTrace();
            sc.close();
        }
    }
}
