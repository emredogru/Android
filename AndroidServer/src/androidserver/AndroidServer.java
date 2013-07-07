/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package androidserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Runnable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TempL
 */
public class AndroidServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            // TODO code application logic here
//            ServerSocket socket=new ServerSocket(7777);
//           Socket sunucu=socket.accept();
//            System.out.println(sunucu.getLocalAddress());
//           final DataOutputStream os=new DataOutputStream(sunucu.getOutputStream()) ;
//           
//           
//           while(true){
//           os.writeUTF("asd");
//           }
//        } catch (IOException ex) {
//            System.out.println("hata 3");
//        }
//    }
        Arayuz arayuz=new Arayuz();
        Thread thread=new Thread(arayuz);
        thread.start();
        arayuz.setVisible(true);
    }
}
