/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package androidserver;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author TempL
 */
public class Arayuz extends JFrame implements Runnable {
      private JTextField metin=new JTextField();
    private JTextArea area =new JTextArea();
    private JLabel label=new JLabel("İP:");
    Socket socket;
    DataInputStream is;
    DataOutputStream os;
    String data;
    SeriHaberlesme arduino;
    public Arayuz() {
         JPanel jpanel=new JPanel(new BorderLayout());
         jpanel.add(label,BorderLayout.NORTH);
        jpanel.add(new JLabel("Mesajı Giriniz"),BorderLayout.WEST);
        jpanel.add(metin,BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(jpanel,BorderLayout.NORTH);
        add(new JScrollPane(area),BorderLayout.CENTER);
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            arduino=new SeriHaberlesme();
            arduino.initialize();
            ServerSocket serversocket=new ServerSocket(7777);
            Socket socket=serversocket.accept();
            label.setText(label.getText()+" "+socket.getInetAddress().toString());
            os=new DataOutputStream(socket.getOutputStream());
            is=new DataInputStream(socket.getInputStream());
            metin.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        os.writeUTF(metin.getText());
                        area.append("Ben: " + metin.getText()+"\n");
                    } catch (IOException ex) {
                        
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void run() {
        while(true){
            try {
                data=is.readUTF();
                arduino.sendData(data);
                area.append("istemci: "+data +"\n");
            } catch (IOException ex) {
                Logger.getLogger(Arayuz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
