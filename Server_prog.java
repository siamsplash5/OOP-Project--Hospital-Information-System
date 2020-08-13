package serverclienthospitalinfo;

import java.io.DataInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Server_prog extends Thread {

	Socket s1;
	
	JTextArea textArea;
	int myid;
	public Server_prog(Socket S, JTextArea t,int id){
		s1=S;
		textArea = t;
		myid=id;
	}

	public void run(){
		try{

			JFrame frame = new JFrame();
			DataInputStream din=new DataInputStream(s1.getInputStream());
			String code = JOptionPane.showInputDialog(
					frame,
					"Enter your name:",
					"Client name",
					JOptionPane.WARNING_MESSAGE
			);
			while(true){
				String p=	din.readUTF();
				String q=  textArea.getText();
				
				
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date dateobj = new Date();
				String scc=df.format(dateobj);
				
				textArea.setText(q+"\n"+"-"+code+"\n"+p);
			
			
			}	
		}catch(Exception e){
		}

	}
}
