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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dbConnection.Admin;
import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

public class unfrozenUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton button;
	private JButton btnNewButton;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public unfrozenUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(215, 195, 182, 35);
		panel.add(textField);
		textField.setColumns(10);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
		icon.getIconHeight(), Image.SCALE_DEFAULT));

		lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u89E3\u51BB\u7528\u6237\u7684\u8D26\u53F7");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(92, 136, 182, 35);
		panel.add(lblNewLabel);

		button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("宋体", Font.PLAIN, 22));
		button.setBounds(177, 283, 96, 36);
		panel.add(button);

		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton.setBounds(342, 283, 96, 36);
		panel.add(btnNewButton);
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 0, 659, 440);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);

		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userNo = textField.getText();
				try {
					adminDAO.unfrozenUser(userNo);
					User user = adminDAO.queryUserById(userNo);
					if(user == null){
						JOptionPane.showMessageDialog(null, "账号不存在");
					}

					if(!user.getIsFrozen()){

						JOptionPane.showMessageDialog(null,"解冻成功");

					}




				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				setVisible(false);
				admin  ad = new admin();
				ad.setVisible(true);


			}
		});


	}
}
