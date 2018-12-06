import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuxEmail extends JFrame {

	private JPanel contentPane;
	private BufferedImage myPicture;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuxEmail frame = new AuxEmail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public AuxEmail() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 14));
		lblUser.setBounds(24, 87, 78, 39);
		contentPane.add(lblUser);
		
		JLabel lblNewLabel = new JLabel();
		myPicture = ImageIO.read(new File("./Imagens/logo.jpg"));	
		lblNewLabel.setBounds(38, 25, 262, 44);
		
		// TODO Auto-generated catch block
	System.out.println("pucho");
	lblNewLabel.setIcon(new ImageIcon(myPicture));
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(101, 92, 183, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 14));
		lblPassword.setBounds(24, 137, 78, 39);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 137, 183, 30);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		btnNewButton.setBounds(101, 187, 85, 30);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Login");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button.setBounds(196, 187, 85, 30);
		contentPane.add(button);
		
	
	}
}
