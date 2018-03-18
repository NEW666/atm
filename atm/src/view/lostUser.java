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
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class lostUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton sure;
	private JButton exit;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	public lostUser() {
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
		label.setBounds(171, 166, 112, 32);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(293, 169, 168, 32);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(171, 228, 112, 32);
		contentPane.add(label_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(293, 231, 168, 32);
		contentPane.add(passwordField);

		sure = new JButton("\u786E\u8BA4");
		sure.setFont(new Font("宋体", Font.PLAIN, 20));
		sure.setBounds(190, 299, 93, 42);
		contentPane.add(sure);
		exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 20));
		exit.setBounds(345, 299, 93, 42);
		contentPane.add(exit);
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
						adminDAO.lostUser(textField.getText());
						textField.setText(null);
						passwordField.setText(null);
						JOptionPane.showMessageDialog(null,"挂失成功");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                }

			}
		});



	}

}
