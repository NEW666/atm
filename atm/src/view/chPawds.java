package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class chPawds extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private  JTextField account;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	public chPawds() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8D26\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(161, 141, 110, 42);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(161, 193, 110, 42);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u8BF7\u786E\u8BA4\u5BC6\u7801");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(161, 247, 110, 42);
		contentPane.add(label_2);

		account = new JTextField();
		account.setFont(new Font("宋体", Font.PLAIN, 16));
		account.setBounds(281, 149, 181, 30);
		contentPane.add(account);
		account.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(281, 202, 181, 30);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(281, 256, 181, 30);
		contentPane.add(passwordField_1);

		JButton sure = new JButton("\u786E\u8BA4");
		sure.setFont(new Font("宋体", Font.PLAIN, 20));
		sure.setBounds(178, 330, 93, 42);
		contentPane.add(sure);

		JButton exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 20));
		exit.setBounds(335, 330, 93, 42);
		contentPane.add(exit);
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 3, 664, 474);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		contentPane.add(jla);





		account.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void focusLost(FocusEvent e) {

				if(!account.getText().matches("^[0-9a-zA_Z]+$") && !"".trim().equals(account.getText())){
					JOptionPane.showMessageDialog(null,"只可输入数字和大小写英文");
					account.setText("");
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

		passwordField_1.addFocusListener(new FocusListener(){

			char[] charPawds_2 = passwordField_1.getPassword();
			String userPawd_2 = String.valueOf(charPawds_2);

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if(	!userPawd_2.matches("^[0-9a-zA_Z]+$")&&!"".trim().equals(userPawd_2)){
					JOptionPane.showMessageDialog(null,"只可输入数字和大小写英文");
					passwordField_1.setText("");
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
				char[] charPawds_2 = passwordField_1.getPassword();
				String userPawd_2 = String.valueOf(charPawds_2);
				User user = null;
				try {
					user = adminDAO.queryUserById(account.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}


				if( !userPawd_1.equals(userPawd_2)&& !userPawd_2.equals(null)){
                	JOptionPane.showMessageDialog(null,"密码不一致");
                }else if("".trim().equals(userPawd_2)){

                	JOptionPane.showMessageDialog(null,"账号不可为空");

                }else if("".trim().equals(account.getText())){

                	JOptionPane.showMessageDialog(null,"账号不不可为空");

                }else if("".trim().equals(userPawd_1)){

                	JOptionPane.showMessageDialog(null,"账号不不可为空");

                }else if(user == null){

                	JOptionPane.showMessageDialog(null,"账号不存在");

                }else{

                	try {
						adminDAO.changePawds(account.getText(),userPawd_1);

						account.setText(null);
						passwordField.setText(null);
						passwordField_1.setText(null);
						JOptionPane.showMessageDialog(null,"密码修改成功");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                }

			}
		});





	}
}
