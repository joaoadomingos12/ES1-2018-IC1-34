import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import twitter4j.TwitterException;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;

public class Gui extends JFrame {
	public DefaultListModel <Postt> DefaultResultado = new DefaultListModel<Postt>();
	
	public JList <Postt> list= new JList<Postt>();
	private BufferedImage myPicture;
	public JPanel panel;
	public JScrollPane scrollPane;
	public TwitterHandler tt;
	private	long idRt;
	private JTextField textField;
	private FacebookHandler fb;
	private String []saco = {"15m","30m","60m","2h","24h","72h"};
	public Gui(TwitterHandler tt, FacebookHandler fb) throws IOException {
		this.tt=tt;
		this.fb=fb;
		setTitle("Bom Dia Academia\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		myPicture = ImageIO.read(new File("C:\\Users\\Rafae\\git\\ES1-2018-IC1-34\\BomDiaAcademia\\Imagens\\logo.jpg"));	

			// TODO Auto-generated catch block
		System.out.println("pucho");
			
		JLabel logos =  new JLabel("New label");
		logos.setIcon(new ImageIcon(myPicture));
		logos.setBounds(301, 48, 200, 36);
		panel.add(logos);
		JButton btnNewButton = new JButton("Retweet");
		
		btnNewButton.setBounds(506, 81, 102, 29);
		panel.add(btnNewButton);
		btnNewButton.setVisible(false);
		list.setBounds(1, 1, 266, 325);
		
		list.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setText(DefaultResultado.elementAt(list.getSelectedIndex()).getMessage());
				if (DefaultResultado.elementAt(list.getSelectedIndex()).getTipo().equals("Facebook")) {
					try {
						myPicture = ImageIO.read(new File("C:\\Users\\Rafae\\git\\ES1-2018-IC1-34\\BomDiaAcademia\\Imagens\\facebook.jpg"));
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
						myPicture = ImageIO.read(new File("C:\\Users\\Rafae\\git\\ES1-2018-IC1-34\\BomDiaAcademia\\Imagens\\Twitter.jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logos.setIcon(new ImageIcon(myPicture));
				}

			}
		});
		

		list.setModel(DefaultResultado);

//		list.setBounds(10, 81, 200, 245);
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
		
		JComboBox comboBox = new JComboBox(saco);
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
		
	}
	
	public void addFB(ArrayList <Postt> tu){
		
		for (int i=0;i<tu.size();i++) {
			DefaultResultado.addElement(tu.get(i));
		}
		list.setModel(transform(DefaultResultado));
	}
	public void addTT(ArrayList <Postt> tu){
//		for (int a =0 ; a < DefaultResultado.size();a++) {
//			if (DefaultResultado.getElementAt(a).getTipo().equals("Twitter"))
//				DefaultResultado.removeElementAt(a);
//		}
		DefaultResultado.removeAllElements();
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
}
