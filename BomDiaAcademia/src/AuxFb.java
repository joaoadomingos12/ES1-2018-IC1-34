import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuxFb extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private BufferedImage myPicture;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuxFb frame = new AuxFb();
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
	public AuxFb() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("User");
		label.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 14));
		label.setBounds(14, 81, 78, 39);
		contentPane.add(label);
		
		JLabel lblToken = new JLabel("Token");
		lblToken.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 14));
		lblToken.setBounds(14, 131, 78, 39);
		contentPane.add(lblToken);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(91, 86, 183, 30);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(91, 131, 183, 30);
		contentPane.add(textField_1);
		
		JButton button = new JButton("Cancelar");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		button.setBounds(91, 181, 85, 30);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Login");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		button_1.setBounds(186, 181, 85, 30);
		contentPane.add(button_1);
		
		JLabel label_2 = new JLabel();
		label_2.setBounds(55, 26, 200, 44);
		myPicture = ImageIO.read(new File("./Imagens/logo.jpg"));	
		
		// TODO Auto-generated catch block
	System.out.println("pucho");
	label_2.setIcon(new ImageIcon(myPicture));
		
		contentPane.add(label_2);
	}

}
