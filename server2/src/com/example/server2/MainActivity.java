package com.example.server2;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btn;
	EditText text;
	Socket socket;
	String data = "";
	TextView yazisma;
	// TextView txt=(TextView) findViewById(R.id.text1);
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn1);
		text = (EditText) findViewById(R.id.text1);
		yazisma=(TextView) findViewById(R.id.yazisma);

		// final SocketLib socket=new SocketLib("192.168.2.120", 8888);
		// socket.start();

		int i = 0;
		final DataReader dt = new DataReader(this);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					yazisma.setText(yazisma.getText()+"\n Ben:"+text.getText());
					dt.SendData(text.getText().toString());
			}
		});

		Thread thread = new Thread(dt);
		thread.start();

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	class DataReader implements Runnable {
		
		Socket socket;
		String data;
		DataInputStream io;
		DataOutputStream os;
		
		MainActivity activty;
		public DataReader(MainActivity activty) {
			this.activty=activty;
			try {
				socket = new Socket("192.168.250.198", 7777);
				io = new DataInputStream(socket.getInputStream());
				os=new DataOutputStream(socket.getOutputStream());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {
			// TODO Auto-generated method stub
			
				
				
			while(true){
			
			try {
			final String	temp = io.readUTF();
				runOnUiThread(new Runnable() {
						
						public void run() {
						
							
						
							
								activty.yazisma.setText(activty.yazisma.getText()+"\n"+" Server: "+temp.toString());
							
							
							
							// TODO Auto-generated method stub
						}
						
					});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
			
				}
		
			
		
		public void SendData(String data){
			try {
				os.writeUTF(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setData(String temp) {
		// TODO Auto-generated method stub
		yazisma.setText(yazisma.getText()+"\n"+"Ben:"+temp);
	}
	

}
