import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class Gui extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FacebookHandler yaya;
	public DefaultListModel <Postt> DefaultResultado = new DefaultListModel<Postt>();
	public ArrayList <Postt> res = new ArrayList<Postt>();
	public JList <Postt> list= new JList<Postt>();
	private BufferedImage myPicture;
	public JPanel panel;
	public Gui() throws IOException {

		
		setTitle("Bom Dia Academia\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 427);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 551, 388);
		getContentPane().add(panel);
		panel.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(223, 81, 200, 245);
		panel.add(textPane);

		myPicture = ImageIO.read(new File("C:\\Users\\Rafae\\git\\ES1-2018-IC1-34\\BomDiaAcademia\\Imagens\\logo.jpg"));	

			// TODO Auto-generated catch block
		System.out.println("pucho");
			
		JLabel logos =  new JLabel("New label");
		logos.setIcon(new ImageIcon(myPicture));
		logos.setBounds(223, 48, 200, 36);
		panel.add(logos);
		
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

		list.setBounds(10, 81, 200, 245);
		panel.add(list);
		
		
		/*JScrollPane scrollPane= new JScrollPane();

		scrollPane.add(list);
		panel.add(scrollPane);*/
	}

	public DefaultListModel <Postt> transform (DefaultListModel model){

		for (int i = 0; i < model.size(); i++) {
			res.add((Postt)model.get(i));
		}
		Collections.sort(res, Postt.comparador);
		model.removeAllElements();
		for (Postt s : res) {
			model.addElement(s);
		}
		return model;

	}
}
