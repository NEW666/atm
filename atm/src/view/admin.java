package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class admin extends JFrame {

	private JPanel contentPane;

	DAOInter adminDAO = DAOFactory.getUserDAOFactory();


	/**
	 * Create the frame.
	 */
	public admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("\u5F00\u6237");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newUser kaihu = new newUser();
				setVisible(false);
				kaihu.setVisible(true);
			}
		});
		btnNewButton.setBounds(31, 151, 106, 47);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u6302\u5931");
		btnNewButton_1.setBounds(31, 244, 106, 47);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u89E3\u51BB");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(31, 344, 106, 47);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u6CE8\u9500");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deUser de = new deUser();
				setVisible(false);
				de.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_3.setBounds(518, 151,106, 47);
		panel.add(btnNewButton_3);

		JButton btnNewButton_5 = new JButton("\u9000\u51FA");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_5.setBounds(518, 344, 106, 47);
		panel.add(btnNewButton_5);


		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
		icon.getIconHeight(), Image.SCALE_DEFAULT));

		JButton button = new JButton("\u5BC6\u7801\u4FEE\u6539");
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBounds(518, 244, 106, 47);
		panel.add(button);


		//挂失
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lostUser lu = new lostUser();
				setVisible(false);
				lu.setVisible(true);

			}
		});
		//解冻功能
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				unfrozenUser unfrozen = new unfrozenUser();
				unfrozen.setVisible(true);
				setVisible(false);


			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(250, 112, 153, 47);
		panel.add(lblNewLabel);
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 0, 664, 474);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);

		//修改密码
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chPawds ch = new chPawds();
				setVisible(false);
				ch.setVisible(true);

			}
		});
	}


}
