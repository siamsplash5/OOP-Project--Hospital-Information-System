package serverclienthospitalinfo;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;

class MyCanvas extends JFrame{

	public ImageIcon image;
	public JLabel label;
	MyCanvas(){
		setLayout(new FlowLayout());
		image=new ImageIcon(getClass().getResource("6464646.jpg"));
		label=new JLabel(image);
		label.setSize(image.getIconWidth(), image.getIconHeight());
		add(label);
	}

}

public class Client_part extends JFrame {

	private JFrame frmClient;
	private static Socket cs;
	private final static JTextArea ta2 = new JTextArea();
	private static JTextField tf2;
	private static String date;
	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;
	private JButton bt6;
	private JButton bt8;
	private JButton bt5;
	private JButton bt7;
	private JTextArea txtrWelcomeToClient;


	/**
	 * Launch the application.
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_part window = new Client_part();
					window.frmClient.setVisible(true);
					window.frmClient.setSize(800, 500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


		cs = new Socket("127.0.0.1", 12000);
		InetAddress address = InetAddress.getByName(INET_ADDR);

		// Create a buffer of bytes, which will be used to store
		// the incoming bytes containing the information from the server.
		// Since the message is small here, 256 bytes should be enough.
		byte[] buf = new byte[256];

		// Create a new Multicast socket (that will allow other sockets/programs
		// to join it as well.
		try (MulticastSocket clientSocket = new MulticastSocket(PORT)) {
			//Joint the Multicast group.
			clientSocket.joinGroup(address);


			while (true) {


				// Receive the information and print it.
				DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
				clientSocket.receive(msgPacket);

				String msg = new String(buf, 0, buf.length);


				String hbd = ta2.getText();

				ta2.setText(msg + "\n" + hbd);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Create the application.
	 */
	public Client_part() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */


	private void initialize() {

		MyCanvas frmClient = new MyCanvas();
		frmClient.setVisible(true);
		frmClient.getContentPane().setBackground(new Color(10, 130, 182));
		frmClient.setTitle("Hospital Information System");
		frmClient.setSize(800, 500);
		frmClient.getContentPane().setLayout(null);


		ta2.setFont(new Font("Time New Roman", Font.BOLD | Font.PLAIN, 14));
		ta2.setBackground(new Color(255, 255, 255));
		ta2.setBounds(10, 50, 564, 179);
		frmClient.getContentPane().add(ta2);


		tf2 = new JTextField();
		tf2.setFont(new Font("Arial", Font.BOLD | Font.PLAIN, 14));
		tf2.setBackground(new Color(253, 253, 253));
		tf2.setBounds(10, 299, 346, 151);
		frmClient.getContentPane().add(tf2);
		tf2.setColumns(10);

		JButton b2 = new JButton("Send");
		b2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 18));
		b2.setBackground(new Color(255, 255, 0));
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					DataOutputStream dout = new DataOutputStream(cs.getOutputStream());
					String f = tf2.getText();
					String keyword = "fuck";

					Boolean found = Arrays.asList(f.split(" ")).contains(keyword);
					if (found) {
						JOptionPane.showMessageDialog(null, "Sorry!you can't send this.Some restricted keyword matched");
						//System.out.println("some restricted key word matched.you can not send this");
						f.split(null);
					}
					tf2.setText(null);
					dout.writeUTF(f);


				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		b2.setBounds(377, 391, 173, 59);
		frmClient.getContentPane().add(b2);

		bt6 = new JButton("COVID-19");
		bt6.setBackground(new Color(241, 232, 232, 255));
		bt6.setForeground(new Color(51, 70, 231));
		bt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf2.setText("COVID-19 Test Information");

			}
		});
		bt6.setBounds(377, 299, 89, 33);
		frmClient.getContentPane().add(bt6);

		bt8 = new JButton("ICU");
		bt8.setBackground(new Color(250, 249, 249, 255));
		bt8.setForeground(new Color(51, 70, 231));
		bt8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf2.setText("ICU information");
			}
		});
		bt8.setBounds(377, 343, 89, 37);
		frmClient.getContentPane().add(bt8);

		bt5 = new JButton("Doctor");
		bt5.setBackground(new Color(250, 249, 249, 255));
		bt5.setForeground(new Color(51, 70, 231));
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf2.setText("Is doctor available now?");

			}
		});
		bt5.setBounds(476, 343, 99, 37);
		frmClient.getContentPane().add(bt5);

		bt7 = new JButton("Emergency");
		bt7.setBackground(new Color(241, 232, 232, 255));
		bt7.setForeground(new Color(51, 70, 231));
		bt7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf2.setText("Emergency Hotline Number");
			}
		});
		bt7.setBounds(476, 299, 99, 33);
		frmClient.getContentPane().add(bt7);

		txtrWelcomeToClient = new JTextArea();
		txtrWelcomeToClient.setText(" Welcome to Hospital Information System");
		txtrWelcomeToClient.setBounds(10, 11, 290, 20);
		txtrWelcomeToClient.setFont(new Font("Time New Roman", Font.BOLD | Font.PLAIN, 14));
		txtrWelcomeToClient.setBackground(new Color(255, 255, 255));
		txtrWelcomeToClient.setForeground(new Color(51, 70, 231));
		frmClient.getContentPane().add(txtrWelcomeToClient);


	}
}
