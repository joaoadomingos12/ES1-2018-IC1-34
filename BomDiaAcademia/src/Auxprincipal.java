import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Auxprincipal extends JFrame {

	private JPanel contentPane;
	private BufferedImage myPicture;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Auxprincipal frame = new Auxprincipal();
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
	public Auxprincipal() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Facebook");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		btnNewButton.setBounds(10, 74, 99, 31);
		contentPane.add(btnNewButton);
		
		JButton btnEmail = new JButton("Email");
		btnEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnEmail.setBounds(113, 74, 99, 31);
		contentPane.add(btnEmail);
		
		JButton btnTwitter = new JButton("Twitter");
		btnTwitter.setBounds(220, 74, 99, 31);
		contentPane.add(btnTwitter);
		
		JLabel lblNewLabel = new JLabel("");
		myPicture = ImageIO.read(new File("./Imagens/logo.jpg"));	
		lblNewLabel.setIcon(new ImageIcon(myPicture));
		lblNewLabel.setBounds(66, 11, 207, 36);
		contentPane.add(lblNewLabel);
	}

}
