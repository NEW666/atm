package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JPasswordField;

public class newUser extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JTextField userName;
	private JTextField save;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	private JPasswordField pawds;
	private JPasswordField pawds_2;

	public newUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));

		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8D26\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(178, 99, 110, 42);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(178, 151, 110, 42);
		contentPane.add(label_1);


		pawds = new JPasswordField();
		pawds.setFont(new Font("宋体", Font.PLAIN, 16));
		pawds.setBounds(313, 160, 181, 30);
		contentPane.add(pawds);

		pawds_2 = new JPasswordField();
		pawds_2.setFont(new Font("宋体", Font.PLAIN, 16));
		pawds_2.setBounds(313, 209, 181, 30);
		contentPane.add(pawds_2);

		account = new JTextField();
		account.setFont(new Font("宋体", Font.PLAIN, 16));
		account.setBounds(313, 108, 181, 30);
		contentPane.add(account);
		account.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237\u540D");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(169, 252, 151, 42);
		contentPane.add(lblNewLabel);

		userName = new JTextField();
		userName.setFont(new Font("宋体", Font.PLAIN, 16));
		userName.setBounds(313, 261, 181, 30);
		contentPane.add(userName);
		userName.setColumns(10);

		JLabel label_2 = new JLabel(
				"\u8BF7\u8F93\u5165\u9884\u5B58\u91D1\u989D");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(157, 304, 151, 42);
		contentPane.add(label_2);

		save = new JTextField();
		save.setFont(new Font("宋体", Font.PLAIN, 16));
		save.setBounds(313, 313, 181, 30);
		contentPane.add(save);
		save.setColumns(10);
		save.setText("50");

		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(195, 370, 93, 42);
		contentPane.add(button);


		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				 int month = c.get(Calendar.MONTH);
				int date = c.get(Calendar.DATE);
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);
				String stime = year+"年"+month+"月"+date+"日"+hour+":"+minute+":"+second;
				char[] pawd = pawds.getPassword();
				String pd = String.valueOf(pawd);
				char[] userPawd_3 = pawds_2.getPassword();
				String userPawd_2 = String.valueOf(userPawd_3);

				User newuser = new User();
				newuser.setUserNo(account.getText());
				newuser.setUserPawd(pd);
				newuser.setName(userName.getText());
				newuser.setSave(save.getText());
				newuser.setStime(stime);
				newuser.setTftime(null);
				newuser.setTransfer(null);
				newuser.setWidthdraw(null);
				newuser.setWtime(null);
				newuser.setTotal(null);
				newuser.setIsDelete(false);
				newuser.setIsFrozen(false);
				newuser.setIsLose(false);



				try {

					if(!pd.equals(userPawd_2)){

						JOptionPane.showMessageDialog(null, "密码不一致");


					}else if("".trim().equals(account.getText())){

						JOptionPane.showMessageDialog(null, "账号不可为空");

					}else if("".trim().equals(userName.getText())){

						JOptionPane.showMessageDialog(null, "用户名不可为空");

					} else if("".trim().equals(pd.trim())&&"".trim().equals(userPawd_2.trim())){

						JOptionPane.showMessageDialog(null, "密码框和确认密码框不可为空");

					}else if(adminDAO.queryUserById(account.getText()) != null){

						JOptionPane.showMessageDialog(null, "账号已存在");

					}else{

										adminDAO.newAcount(newuser);
										JOptionPane.showMessageDialog(null, "创建成功");
										account.setText(null);
										pawds.setText(null);
										pawds_2.setText(null);
										userName.setText(null);
										save.setText(null);

									}



				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
		});

		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(365, 372, 93, 40);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);
			}
		});
		contentPane.add(button_1);


		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(178, 203, 110, 42);
		contentPane.add(lblNewLabel_1);


		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 0, 659, 440);
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

		pawds.addFocusListener(new FocusListener(){

			char[] userPawds = pawds.getPassword();
			String userPawd = String.valueOf(userPawds);

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if(	!userPawd.matches("^[0-9a-zA_Z]+$")&&!"".trim().equals(userPawd)){


					JOptionPane.showMessageDialog(null,"只可输入数字和大小写英文");
					pawds.setText("");

				}



			}

        });

		pawds_2.addFocusListener(new FocusListener(){

			char[] userPawds = pawds_2.getPassword();
			String userPawd = String.valueOf(userPawds);

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				 if(	!userPawd.matches("^[0-9a-zA_Z]+$")&&!"".trim().equals(userPawd)){


					JOptionPane.showMessageDialog(null,"只可输入数字和大小写英文");
					pawds_2.setText("");

				}

			}

        });

		save.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {



			 if(	!save.getText().matches("^[0-9a-zA_Z]+$")&&!"".trim().equals(save.getText())){


					JOptionPane.showMessageDialog(null,"只可输入数字和大小写英文");
					save.setText("");

				}
			}

        });





		pawds_2.addFocusListener(new FocusListener(){




				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub


				}

				@Override
				public void focusLost(FocusEvent e) {

					char[] userPawds = pawds.getPassword();
					String userPawd = String.valueOf(userPawds);

					char[] userPawd_3 = pawds_2.getPassword();
					String userPawd_2 = String.valueOf(userPawd_3);


					if( !userPawd.equals(userPawd_2)&& !userPawd_2.equals(null)){
                    	JOptionPane.showMessageDialog(null,"密码不一致");
                    }


				}

	        });


	}
}
