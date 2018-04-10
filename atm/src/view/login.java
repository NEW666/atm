package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import dbConnection.Admin;
import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private  int count = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DAOInter adminDAO = DAOFactory.getUserDAOFactory();

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel("\u8D26\u53F7");
		label.setFont(new Font("����", Font.PLAIN, 22));
		label.setBounds(196, 147, 54, 25);
		panel.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setFont(new Font("����", Font.PLAIN, 22));
		label_1.setBounds(196, 197, 54, 24);
		panel.add(label_1);

		textField = new JTextField();
		textField.setText("100");
		textField.setBounds(279, 147, 167, 32);
		panel.add(textField);
		textField.setColumns(10);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u7528\u6237");
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton.setFont(new Font("����", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBounds(194, 254, 93, 26);
		panel.add(rdbtnNewRadioButton);

		JRadioButton radioButton = new JRadioButton("\u7BA1\u7406\u5458");
		radioButton.setSelected(true);
		radioButton.setBackground(Color.LIGHT_GRAY);
		radioButton.setFont(new Font("����", Font.PLAIN, 18));
		radioButton.setBounds(353, 254, 93, 26);
		panel.add(radioButton);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(radioButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(279, 197, 167, 32);
		passwordField.setText("123");
		panel.add(passwordField);



		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 24));
		btnNewButton.setBounds(254, 294, 106, 47);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// �û���¼
				if (rdbtnNewRadioButton.isSelected()) {

					String userNo = textField.getText();
					char[] userPawds = passwordField.getPassword();
					String userPawd = String.valueOf(userPawds);
					User user;
					try {
						user = adminDAO.queryUserById(userNo);
						if (user == null) {
							JOptionPane.showMessageDialog(null, "�˺Ų�����");

						}else	if (userNo.equals(user.getUserNo())
								&& userPawd.equals(user.getUserPawd())) {
							frame.setVisible(false);
						} else if(!userPawd.equals(user.getUserPawd())){

							count++;

							JOptionPane.showMessageDialog(null, "�������");

							if(count>1){

								adminDAO.frozenUser(userNo);

							}else if(user.isFrozen() &&userPawd.equals(user.getUserPawd())){

							JOptionPane.showMessageDialog(null, "�˺��Ѷ��ᣬ�뵽Ӫҵ������ⶳ");

						}else if(user.isDelete()&&userPawd.equals(user.getUserPawd())){

							JOptionPane.showMessageDialog(null, "���˺���ע��");

						}else if(user.isLose()&&userPawd.equals(user.getUserPawd())){

							JOptionPane.showMessageDialog(null, "���˺��ѹ�ʧ���뵽Ӫҵ������ⶳ");

						}

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					// ����Ա��¼
					String adminNo = textField.getText();
					char[] adminPawds = passwordField.getPassword();
					String adminPawd = String.valueOf(adminPawds);
					try {
						Admin admin = adminDAO.queryAdminById(adminNo);
						if (admin == null) {

							JOptionPane.showMessageDialog(null, "�˺Ų�����");

						}else {

							if (adminNo.equals(admin.getAdminNo())
									&& adminPawd.equals(admin.getAdminPawd())) {
								frame.setVisible(false);
								admin  adminFrame = new admin();
								adminFrame.setVisible(true);
							} else {

								JOptionPane.showMessageDialog(null, "�˺Ŵ���");


							}

						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		panel.add(btnNewButton);


		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
		icon.getIconHeight(), Image.SCALE_DEFAULT));
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 3, 664, 474);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);

	}


}
