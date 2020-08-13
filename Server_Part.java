package serverclienthospitalinfo;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

public class Server_Part {

	private JFrame frmServer;
	 private JScrollPane scroll;
	private static JTextArea textArea;
	private JTextField textField;
	private static ServerSocket ss;
	private static Socket s1;
	final String INET_ADDR = "224.0.0.3";
    final int PORT = 8888;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_Part window = new Server_Part();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//mycode
		

		 byte[] buf = new byte[ 1 ];;
		    AudioFormat af = new AudioFormat( (float )44100, 8, 1, true, false );
		    SourceDataLine sdl = null;
			try {
				sdl = AudioSystem.getSourceDataLine( af );
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				sdl.open();
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    sdl.start();
		    for( int i = 0; i < 1000 * (float )44100 / 1000; i++ ) {
		        double angle = i / ( (float )44100 / 440 ) * 2.0 * Math.PI;
		        buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
		        sdl.write( buf, 0, 1 );
		    }
		    sdl.drain();
		    sdl.stop();
		
		try{
			ss=new ServerSocket(12000);
			
		int id=1;
		while(true){
			Socket s=ss.accept();
			Server_prog st=new Server_prog(s,textArea,id++);
			st.start();
	
	}
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
		}
	/**
	 * Create the application.
	 */
	
	public Server_Part() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setType(Type.UTILITY);
		frmServer.getContentPane().setBackground(new Color(0, 139, 139));
		frmServer.setTitle("Server Site ");
		frmServer.setSize(800, 500);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);

		JFrame frame = new JFrame();
		JPanel middlePanel = new JPanel();
		frame.add(middlePanel);
		final JTextArea display = new JTextArea(16, 58);

		
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(10, 44, 564, 170);
		frmServer.getContentPane().add(textArea);
		textArea.setFont(new Font("Time New Roman", Font.BOLD | Font.PLAIN, 18));
		textArea.setText(null);

		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(10, 268, 318, 165);
		textArea.setFont(new Font("Time New Roman", Font.BOLD | Font.PLAIN, 14));
		frmServer.getContentPane().add(textField);

		textField.setColumns(10);
	
		JButton b1 = new JButton("Send");
		b1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		b1.setBackground(new Color(255, 255, 0));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			        InetAddress addr=null;
					try {
						addr = InetAddress.getByName(INET_ADDR);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			     
			        // Open a new DatagramSocket, which will be used to send the data.
			       
					
					
					  try{
				        	
				        	DatagramSocket serverSocket = new DatagramSocket();
				        	
				        	  
				         
				        	   String msg= textField.getText();
				        	 
				                // Create a packet that will contain the data
				                // (in the form of bytes) and send it.
				                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),
				                        msg.getBytes().length, addr, PORT);
				                serverSocket.send(msgPacket);
				     
				                
				            }
					
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
		b1.setBounds(356, 374, 218, 59);
		frmServer.getContentPane().add(b1);

		JButton bt6 = new JButton("Emergency");
		bt6.setBackground(SystemColor.textHighlightText);
		bt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField.setText("Kuwait Bangladesh Friendship Government Hospital: 01999-956290\n");
			}
		});
		bt6.setBounds(463, 268, 111, 33);
		frmServer.getContentPane().add(bt6);

		JButton bt8 = new JButton("Doctor");
		bt8.setBackground(Color.WHITE);
		bt8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("Doctor available now! Call 01710101001 for appointment!\n");
			}
		});
		bt8.setBounds(463, 312, 111, 37);
		frmServer.getContentPane().add(bt8);

		JButton bt5 = new JButton("COVID-19");
		bt5.setBackground(SystemColor.textHighlightText);
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("COVID-19 available! test cost is 500 BDT only. For more, Call 62639");
			}
		});
		bt5.setBounds(356, 269, 90, 30);
		frmServer.getContentPane().add(bt5);

		JButton bt7 = new JButton("ICU");
		bt7.setBackground(SystemColor.textHighlightText);
		bt7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("12 ICU seat available! Call 09872626 for more!");
			}
		});
		bt7.setBounds(354, 314, 90, 33);
		frmServer.getContentPane().add(bt7);
	}
}