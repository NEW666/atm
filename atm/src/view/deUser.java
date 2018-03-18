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
import javax.swing.JButton;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;

public class deUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	private JPasswordField passwordField;

	public deUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  674, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
		icon.getIconHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8D26\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(171, 150, 115, 37);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(296, 152, 183, 37);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton sure = new JButton("\u786E\u5B9A");
		sure.setFont(new Font("宋体", Font.PLAIN, 20));
		sure.setBounds(180, 288, 106, 47);
		contentPane.add(sure);

		JButton exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 20));
		exit.setBounds(353, 288, 106, 47);
		contentPane.add(exit);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(177, 212, 115, 37);
		contentPane.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(296, 212, 183, 37);
		contentPane.add(passwordField);
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(5, 5, 648, 430);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		contentPane.add(jla);


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


		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);
			}
		});



		sure.addActionListener(new ActionListener() {
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

                }else{

                	try {
						adminDAO.deUser(textField.getText());
						textField.setText(null);
						passwordField.setText(null);
						JOptionPane.showMessageDialog(null,"注销成功");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                }

			}
		});





	}
}
