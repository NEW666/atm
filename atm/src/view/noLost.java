package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class noLost extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JPasswordField pawd;

	DAOInter adminDAO = DAOFactory.getUserDAOFactory();


	public noLost() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);

		JLabel aLabel = new JLabel("\u8D26\u53F7");
		aLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		aLabel.setBounds(200, 185, 56, 36);
		contentPane.add(aLabel);

		account = new JTextField();
		account.setBounds(274, 185, 170, 33);
		contentPane.add(account);
		account.setColumns(10);

		JLabel pLabel = new JLabel("\u5BC6\u7801");
		pLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		pLabel.setBounds(200, 245, 64, 33);
		contentPane.add(pLabel);

		pawd = new JPasswordField();
		pawd.setBounds(274, 247, 170, 33);
		contentPane.add(pawd);

		JButton sure = new JButton("\u786E\u8BA4");
		sure.setFont(new Font("宋体", Font.PLAIN, 20));
		sure.setBounds(185, 351, 93, 44);
		contentPane.add(sure);

		JButton exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 20));
		exit.setBounds(385, 351, 93, 44);
		contentPane.add(exit);
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 3, 664, 474);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		contentPane.add(jla);


		account.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (!account.getText().matches("^[0-9a-zA_Z]+$")
						&& !"".trim().equals(account.getText())) {
					JOptionPane.showMessageDialog(null, "只可输入数字和大小写英文");

				}

			}

		});


		pawd.addFocusListener(new FocusListener() {


			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				char[] charPawds = pawd.getPassword();
				String userPawd = String.valueOf(charPawds);

				User user = null;

				try {
					user = adminDAO.queryUserById(account.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (user != null) {

					if (!user.getUserPawd().equals(userPawd)) {

						pawd.setForeground(Color.red);
						JOptionPane.showMessageDialog(null, "密码错误");


					} else if (user.getUserPawd().equals(userPawd)) {

						pawd.setForeground(Color.black);
					}

				}

			}

		});


		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);
			}
		});



		sure.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				char[] charPawds = pawd.getPassword();
				String userPawd = String.valueOf(charPawds);
				User user = null;

				try {
					user = adminDAO.queryUserById(account.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				 if ("".trim().equals(account.getText())) {

					JOptionPane.showMessageDialog(null, "账号不可为空");

				} else if (user == null) {

					account.setForeground(Color.red);


					JOptionPane.showMessageDialog(null, "账号不存在");



				} else if ("".trim().equals(userPawd)) {



					JOptionPane.showMessageDialog(null, "密码不可为空");


				} else if (!user.getUserPawd().equals(userPawd)&&!"".equals(userPawd.trim())) {

					account.setForeground(Color.black);

					pawd.setForeground(Color.red);

					JOptionPane.showMessageDialog(null, "密码错误");


				} else {

					try {

						adminDAO.noLost(account.getText());

						JOptionPane.showMessageDialog(null, "已解除挂失状态");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});









	}
}
