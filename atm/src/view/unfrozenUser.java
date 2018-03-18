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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;

import dbConnection.Admin;
import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

import javax.swing.JPasswordField;

public class unfrozenUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton button;
	private JButton btnNewButton;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public unfrozenUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(296, 156, 182, 32);
		panel.add(textField);
		textField.setColumns(10);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
		icon.getIconHeight(), Image.SCALE_DEFAULT));

		lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u8D26\u53F7");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(177, 156, 106, 35);
		panel.add(lblNewLabel);

		button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("宋体", Font.PLAIN, 22));
		button.setBounds(187, 283, 106, 47);
		panel.add(button);

		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton.setBounds(357, 282, 106, 47);
		panel.add(btnNewButton);

		button.setFont(new Font("宋体", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(177, 201, 125, 44);
		panel.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(296, 210, 182, 32);
		panel.add(passwordField);
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 3, 664, 474);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);

		textField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void focusLost(FocusEvent e) {

				if(!textField.getText().matches("^[0-9a-zA_Z]+$") && !"".trim().equals(textField.getText())){
					JOptionPane.showMessageDialog(null,"只可输入数字和大小写英文");
					textField.setText("");
				}

			}

        });

		passwordField.addFocusListener(new FocusListener(){
			char[] charPawds_1 = passwordField.getPassword();
			String userPawd_1 = String.valueOf(charPawds_1);

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if(	!userPawd_1.matches("^[0-9a-zA_Z]+$")&&!"".trim().equals(userPawd_1)){
					JOptionPane.showMessageDialog(null,"只可输入数字和大小写英文");
					passwordField.setText("");
				}
			}

        });


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);
			}
		});



		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] charPawds_1 = passwordField.getPassword();
				String userPawd_1 = String.valueOf(charPawds_1);

				User user = null;
				try {
					user = adminDAO.queryUserById(textField.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}


				if("".trim().equals(textField.getText())){

                	JOptionPane.showMessageDialog(null,"账号不可为空");

                }else if("".trim().equals(userPawd_1)){

                	JOptionPane.showMessageDialog(null,"密码不不可为空");

                }else if(user == null){

                	JOptionPane.showMessageDialog(null,"账号不存在");

                }else if(!user.getIsFrozen()) {

                	JOptionPane.showMessageDialog(null,"账号已解冻");

                }else if(user.getIsLose()){

                	try {
						adminDAO.noLost(textField.getText());
						textField.setText(null);
						passwordField.setText(null);
						JOptionPane.showMessageDialog(null,"解冻成功");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}



                }else{

                	try {
						adminDAO.unfrozenUser(textField.getText());
						textField.setText(null);
						passwordField.setText(null);
						JOptionPane.showMessageDialog(null,"解冻成功");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                }

			}
		});


	}
}
