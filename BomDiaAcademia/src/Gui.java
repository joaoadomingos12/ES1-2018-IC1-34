
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBException;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;

public class Gui extends JFrame {
	public DefaultListModel <Postt> DefaultResultado = new DefaultListModel<Postt>();
	private Config cfg; 
	JList <Postt> list= new JList<Postt>();
	private BufferedImage myPicture;
	public JPanel panel;
	public JScrollPane scrollPane;
	public TwitterHandler tt;
	private	long idRt;
	private JTextField textField;
	private FacebookHandler fb;
	private String []timeStamps = {"15m","30m","60m","2h","24h","72h"};
	private BdaAcademia bda;

	private EmailHandler em;
		public Gui(BdaAcademia bda, TwitterHandler tt, FacebookHandler fb, EmailHandler em, Config cfg) throws IOException {
//	public Gui(EmailHandler em, Config cfg) throws IOException {
			this.bda=bda;
		this.tt=tt;
		this.fb=fb;
		this.em=em;
		this.cfg = cfg;
		setTitle("Bom Dia Academia\r\n");
		JFrame jf=this;
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		        if (JOptionPane.showConfirmDialog(jf, 
		            "Quer gravar a lista para aceder mais tarde?", "Sair",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        		bda.saveOnFile(DefaultResultado);
		        }else {
		        	 System.exit(0);
		        }
		    }
		  
		});
		setBounds(100, 100, 634, 518);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 618, 450);
		getContentPane().add(panel);
		panel.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(301, 81, 200, 314);
		panel.add(textPane);

		myPicture = ImageIO.read(new File("./Imagens/logo.jpg"));	

		// TODO Auto-generated catch block
		System.out.println("pucho");

		JLabel logos =  new JLabel("New label");
		logos.setIcon(new ImageIcon(myPicture));
		logos.setBounds(301, 48, 200, 36);
		panel.add(logos);
		JButton btnNewButton = new JButton("Retweet");

		btnNewButton.setBounds(511, 81, 102, 29);
		panel.add(btnNewButton);
		btnNewButton.setVisible(false);
		list.setBounds(1, 1, 266, 325);
		JButton btnReply = new JButton("Reply");
		btnReply.setVisible(false);

		btnReply.setBounds(511, 122, 102, 23);
		panel.add(btnReply);

		list.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setText(DefaultResultado.elementAt(list.getSelectedIndex()).getMessage());
				if (DefaultResultado.elementAt(list.getSelectedIndex()).getTipo().equals("Facebook")) {
					try {
						myPicture = ImageIO.read(new File("./Imagens/facebook.jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logos.setIcon(new ImageIcon(myPicture));

				} else if (DefaultResultado.elementAt(list.getSelectedIndex()).getTipo().equals("Twitter")) {
					idRt = DefaultResultado.elementAt(list.getSelectedIndex()).getIDtwitter();
					btnNewButton.setVisible(true);

					btnNewButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							System.out.println(idRt);
							tt.retweet(idRt);
						}
					});

					try {
						myPicture = ImageIO.read(new File("./Imagens/Twitter.jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logos.setIcon(new ImageIcon(myPicture));
				}else if (DefaultResultado.elementAt(list.getSelectedIndex()).getTipo().equals("Email")) {
					btnReply.setVisible(true);
					btnReply.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {

							JFrame internalFrame = new JFrame("New JInternalFrame");
							internalFrame.setVisible(true);
							internalFrame.setBounds(349, 148, 305, 194);
							internalFrame.getContentPane().setLayout(null);
							internalFrame.toFront();
							JTextField textField_1 = new JTextField();
							textField_1.setBounds(10, 24, 269, 104);
							internalFrame.getContentPane().add(textField_1);
							textField_1.setColumns(10);

							JButton btnEnviar = new JButton("Enviar");
							btnEnviar.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									int emailPos = DefaultResultado.elementAt(list.getSelectedIndex()).getPosition();
									em.reply(emailPos, textField_1.getText());
									internalFrame.dispose();
								}
							});
							btnEnviar.setBounds(178, 130, 89, 23);
							internalFrame.getContentPane().add(btnEnviar);

							JButton btnCancelar = new JButton("Cancelar");
							btnCancelar.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									internalFrame.dispose();
								}
							});
							btnCancelar.setBounds(79, 130, 89, 23);
							internalFrame.getContentPane().add(btnCancelar);
							internalFrame.setVisible(true);

						}
					});
				}

			}
		});


		list.setModel(DefaultResultado);

		panel.add(list);


		scrollPane= new JScrollPane(list);
		scrollPane.setBounds(10, 81, 281, 316);
		panel.add(scrollPane);

		textField = new JTextField();
		textField.setBounds(10, 48, 119, 20);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addFB(fb.listPosts(textField.getText()));
			}
		});
		btnSearch.setBounds(202, 48, 89, 23);
		panel.add(btnSearch);

		JComboBox comboBox = new JComboBox(timeStamps);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux = comboBox.getSelectedItem().toString();
				int value = Integer.parseInt(aux.replaceAll("[^0-9]", ""));
				if (value == 2) 
					value =120;
				if (value == 24) 
					value = 1440;
				if (value == 72) 
					value = 4320;

				tt.tempo = value * 60 * 1000;
				tt.abcd.clear();
				addTT(tt.listPosts());
			}
		});

		comboBox.setBounds(132, 48, 60, 20);
		panel.add(comboBox);

		JButton btnTokens = new JButton("Tokens");
		btnTokens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					AuxPrincipal();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnTokens.setBounds(450, 14, 112, 23);
		panel.add(btnTokens);

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(349, 148, 305, 194);
		panel.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		JTextField textField_1 = new JTextField();
		textField_1.setBounds(10, 24, 269, 104);
		internalFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);


		




	}

	public void addFB(ArrayList <Postt> tu){

		for (int i=0;i<tu.size();i++) {
			DefaultResultado.addElement(tu.get(i));
		}
		list.setModel(transform(DefaultResultado));
	}
	public void addTT(ArrayList <Postt> tu){
		for (int a =0 ; a < DefaultResultado.size();a++) {
			if (DefaultResultado.getElementAt(a).getTipo().equals("Twitter"))
				//DefaultResultado.removeElementAt(a);
				DefaultResultado.remove(a);
		}


		//DefaultResultado.removeAllElements();
		for (int i=0;i<tu.size();i++) {
			DefaultResultado.addElement(tu.get(i));
		}
		list.setModel(transform(DefaultResultado));
	}
	public DefaultListModel <Postt> transform (DefaultListModel model){
		ArrayList <Postt> res = new ArrayList<Postt>();
		System.out.println("inicio" + model.size());
		for (int i = 0; i < model.size(); i++) {
			res.add((Postt)model.get(i));
		}
		Collections.sort(res, Postt.comparador);
		model.removeAllElements();

		for (Postt s : res) {
			model.addElement(s);
		}
		System.out.println("Fim"+model.size());
		return model;

	}

	public void AuxPrincipal() throws IOException {
		 JPanel contentPane;
		 BufferedImage myPicture;
		 
		 JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBounds(100, 100, 343, 171);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	frame.setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JButton btnNewButton = new JButton("Facebook");
	btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						auxFb();
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	btnNewButton.setBounds(10, 74, 99, 31);
	contentPane.add(btnNewButton);
	
	JButton btnEmail = new JButton("Email");
	btnEmail.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				auxEmail();
				frame.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	frame.setVisible(true);
}
	
	
	public void auxFb() throws IOException {

		
		JPanel contentPane;
		JTextField textField;
		JTextField textField_1;
		BufferedImage myPicture;

		JFrame frame2 = new JFrame();	
		
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setBounds(100, 100, 309, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame2.setContentPane(contentPane);
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

		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(91, 131, 183, 30);
		contentPane.add(textField_1);

		JButton button = new JButton("Cancelar");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame2.dispose();
			}
		});
		button.setBounds(91, 181, 85, 30);
		contentPane.add(button);

		JButton button_1 = new JButton("Login");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cfg.facebookUser = textField.getText();
				cfg.facebookToken = textField_1.getText();
				try {
					cfg.toXML();
					dispose();
					BdaAcademia eu = new BdaAcademia();
					eu.start();
					frame2.dispose();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		frame2.setVisible(true);

	}
	
	


	public void auxEmail() throws IOException  {
		 JPanel contentPane;
		 BufferedImage myPicture;
		 JTextField textField;
		 JTextField textField_1;
		 
		 JFrame frame = new JFrame ();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 326, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 14));
		lblUser.setBounds(24, 87, 78, 39);
		contentPane.add(lblUser);
		
		JLabel lblNewLabel = new JLabel();
		myPicture = ImageIO.read(new File("./Imagens/logo.jpg"));	
		lblNewLabel.setBounds(38, 25, 262, 44);
		
		// TODO Auto-generated catch block

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
		
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 137, 183, 30);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		btnNewButton.setBounds(101, 187, 85, 30);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Login");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cfg.email = textField.getText();
				cfg.password= textField_1.getText();
				try {
					cfg.toXML();
					dispose();
					BdaAcademia eu = new BdaAcademia();
					eu.start();
					frame.dispose();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		button.setBounds(196, 187, 85, 30);
		contentPane.add(button);
		
		
	
		
		frame.setVisible(true);
		
	
	}
}

