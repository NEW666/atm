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
import dbConnection.UserMsg;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Customizer;
import java.util.Calendar;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class newUser extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	private JPasswordField pawds;
	private JPasswordField pawds_2;
	private JTextField IDNo;
	private JTextField phoneNo;
	String ran_account = String.valueOf(Calendar.getInstance().getTime()
			.getTime());
	String ran_cus = String.valueOf(Calendar.getInstance().getTime().getTime());

	public newUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));

		JLabel account = new JLabel("\u8D26\u53F7");
		account.setFont(new Font("宋体", Font.PLAIN, 20));
		account.setBounds(193, 99, 80, 42);
		contentPane.add(account);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(163, 275, 110, 42);
		contentPane.add(label_1);

		JLabel accountNo = new JLabel("");
		accountNo.setBounds(298, 111, 181, 30);
		contentPane.add(accountNo);
		accountNo.setText(ran_account);

		pawds = new JPasswordField();
		pawds.setFont(new Font("宋体", Font.PLAIN, 16));
		pawds.setBounds(298, 283, 181, 30);
		contentPane.add(pawds);

		pawds_2 = new JPasswordField();
		pawds_2.setFont(new Font("宋体", Font.PLAIN, 16));
		pawds_2.setBounds(298, 323, 181, 30);
		contentPane.add(pawds_2);

		JLabel name = new JLabel("\u8BF7\u8F93\u5165\u59D3\u540D");
		name.setFont(new Font("宋体", Font.PLAIN, 20));
		name.setBounds(163, 143, 151, 42);
		contentPane.add(name);

		userName = new JTextField();
		userName.setFont(new Font("宋体", Font.PLAIN, 16));
		userName.setBounds(298, 151, 181, 30);
		contentPane.add(userName);
		userName.setColumns(10);

		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(180, 373, 93, 42);
		contentPane.add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pawd = pawds.getPassword();
				String pd = String.valueOf(pawd);
				char[] userPawd_3 = pawds_2.getPassword();
				String userPawd_2 = String.valueOf(userPawd_3);
				String ran_cus = String.valueOf(Calendar.getInstance().getTime().getTime());
				UserMsg um1 = new UserMsg();
				try {
					um1 = adminDAO.queryUserMsgByCusID(IDNo.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				User newuser = new User();

				UserMsg uMsg = new UserMsg();

				try {

				   if(pd.length()!=6||userPawd_2.length()!=6||!pd.matches("^[0-9]{1,6}$")||!userPawd_2.matches("^[0-9]{1,6}$")){

						JOptionPane.showMessageDialog(null, "密码只可为6位数字");

					}else if (!pd.equals(userPawd_2)) {

						JOptionPane.showMessageDialog(null, "密码不一致");

					} else if ("".trim().equals(userName.getText())) {

						JOptionPane.showMessageDialog(null, "用户名不可为空");

					} else if ("".trim().equals(pd.trim())
							&& "".trim().equals(userPawd_2.trim())) {

						JOptionPane.showMessageDialog(null, "密码框和确认密码框不可为空");

					} else if (!IDNo
							.getText()
							.matches(
									"^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$")
							&& !checkCard(IDNo.getText())) {

						JOptionPane.showMessageDialog(null, "输入的身份证号有误");

					} else if(phoneNo.getText().matches("^[0-9]+$")&& phoneNo.getText().length() != 11){

						JOptionPane.showMessageDialog(null, "电话号码错误");

					}else if(um1 != null && !um1.getUserName().equals(userName.getText())){
						JOptionPane.showMessageDialog(null, "名字有误");
					}else  if(um1 != null && !um1.getUserPhone().equals(phoneNo.getText())){
						JOptionPane.showMessageDialog(null, "电话有误");
					}else{

						  if(um1 == null){
								System.out.println("A");
								uMsg.setCusNo(ran_cus);
								uMsg.setIDNo(IDNo.getText());
								uMsg.setUserName(userName.getText());
								uMsg.setUserPhone(phoneNo.getText());
								adminDAO.newAccountCusNo(uMsg);
								newuser.setUserNo(ran_account);
								newuser.setUserPawd(pd);
								newuser.setBe_bank("中国银行");
								newuser.setCusNo(uMsg.getCusNo());
								newuser.setDelete(false);
								newuser.setFrozen(false);
								newuser.setDelete(false);
								adminDAO.newAcount(newuser);

//								ran_account = String.valueOf(Calendar.getInstance().getTime()
//										.getTime());
//								accountNo.setText(ran_account);

							}else if(um1 != null){

								newuser.setUserNo(ran_account);
								newuser.setUserPawd(pd);
								newuser.setBe_bank("中国银行");
								newuser.setCusNo(um1.getCusNo());
								newuser.setDelete(false);
								newuser.setFrozen(false);
								newuser.setDelete(false);
								adminDAO.newAcount(newuser);
//								ran_account = String.valueOf(Calendar.getInstance().getTime()
//										.getTime());
//								accountNo.setText(ran_account);

							}

							JOptionPane.showMessageDialog(null, "创建成功");
							accountNo.setText("");
							userName.setText("");
							IDNo.setText("");
							phoneNo.setText("");
							pawds.setText("");

							pawds_2.setText("");

							ran_account = String.valueOf(Calendar.getInstance().getTime()
									.getTime());
							accountNo.setText(ran_account);









					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(374, 374, 93, 40);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);
			}
		});
		contentPane.add(button_1);

		JLabel lable_2 = new JLabel("\u8BF7\u786E\u8BA4\u5BC6\u7801");
		lable_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lable_2.setBounds(163, 321, 110, 42);
		contentPane.add(lable_2);

		JLabel ID = new JLabel("\u8BF7\u8F93\u5165\u8EAB\u4EFD\u8BC1\u53F7");
		ID.setFont(new Font("宋体", Font.PLAIN, 20));
		ID.setBounds(144, 192, 151, 30);
		contentPane.add(ID);

		IDNo = new JTextField();
		IDNo.setBounds(298, 195, 181, 30);
		contentPane.add(IDNo);
		IDNo.setColumns(10);

		JLabel phone = new JLabel("\u8BF7\u8F93\u5165\u624B\u673A\u53F7\u7801");
		phone.setFont(new Font("宋体", Font.PLAIN, 20));
		phone.setHorizontalAlignment(SwingConstants.TRAILING);
		phone.setBounds(144, 235, 141, 30);
		contentPane.add(phone);

		phoneNo = new JTextField();
		phoneNo.setBounds(298, 235, 181, 30);
		contentPane.add(phoneNo);
		phoneNo.setColumns(10);

		JLabel jla = new JLabel();
		jla.setFont(new Font("宋体", Font.PLAIN, 18));
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 3, 664, 474);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		contentPane.add(jla);

		pawds.addFocusListener(new FocusListener() {

			char[] userPawds = pawds.getPassword();
			String userPawd = String.valueOf(userPawds);

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (userPawd.matches("^[0-9]{1,6}$")
						&& !"".trim().equals(userPawd)) {

					JOptionPane.showMessageDialog(null, "请输入6位数的密码");
					pawds.setText("");

				}

			}

		});

		pawds_2.addFocusListener(new FocusListener() {

			char[] userPawds = pawds_2.getPassword();
			String userPawd = String.valueOf(userPawds);

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (userPawd.matches("^[0-9]{1,6}$")
						&& !"".trim().equals(userPawd)) {
					JOptionPane.showMessageDialog(null, "请输入6位数的密码");
			}
		}

		});

		pawds_2.addFocusListener(new FocusListener() {

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

				if (!userPawd.equals(userPawd_2) && !userPawd_2.equals(null)) {
					JOptionPane.showMessageDialog(null, "密码不一致");
				}

			}

		});

		IDNo.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (!IDNo
						.getText()
						.matches(
								"^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$")&&!IDNo.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "输入的身份证号有误");

				} else if (!checkCard(IDNo.getText())&&!IDNo.getText().trim().equals("")) {

								JOptionPane.showMessageDialog(null, "输入的身份证号有误");

							}

			}

		});


		phoneNo.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {


                 if(!phoneNo.getText().matches("^[0-9]+$")&&!phoneNo.getText().equals("")){
                	 JOptionPane.showMessageDialog(null, "只能输入数字");
                	 phoneNo.setText("");
                 }else if(phoneNo.getText().length() !=11 && !phoneNo.getText().equals("")){
                	 JOptionPane.showMessageDialog(null, "号码有误");
                 }


			}

		});



	}

	public boolean checkCard(String no) {

		int i = 0;
		String r = "error";
		String lastnumber = "";

	   if(no.length() == 18){

		i += Integer.parseInt(IDNo.getText().substring(0, 1)) * 7;
		i += Integer.parseInt(IDNo.getText().substring(1, 2)) * 9;
		i += Integer.parseInt(IDNo.getText().substring(2, 3)) * 10;
		i += Integer.parseInt(IDNo.getText().substring(3, 4)) * 5;
		i += Integer.parseInt(IDNo.getText().substring(4, 5)) * 8;
		i += Integer.parseInt(IDNo.getText().substring(5, 6)) * 4;
		i += Integer.parseInt(IDNo.getText().substring(6, 7)) * 2;
		i += Integer.parseInt(IDNo.getText().substring(7, 8)) * 1;
		i += Integer.parseInt(IDNo.getText().substring(8, 9)) * 6;
		i += Integer.parseInt(IDNo.getText().substring(9, 10)) * 3;
		i += Integer.parseInt(IDNo.getText().substring(10, 11)) * 7;
		i += Integer.parseInt(IDNo.getText().substring(11, 12)) * 9;
		i += Integer.parseInt(IDNo.getText().substring(12, 13)) * 10;
		i += Integer.parseInt(IDNo.getText().substring(13, 14)) * 5;
		i += Integer.parseInt(IDNo.getText().substring(14, 15)) * 8;
		i += Integer.parseInt(IDNo.getText().substring(15, 16)) * 4;
		i += Integer.parseInt(IDNo.getText().substring(16, 17)) * 2;
		i = i % 11;
		lastnumber = IDNo.getText().substring(17, 18);

	   }else{
		   return false;
	   }
		if (i == 0) {
			r = "1";
		}
		if (i == 1) {
			r = "0";
		}
		if (i == 2) {
			r = "x";
		}
		if (i == 3) {
			r = "9";
		}
		if (i == 4) {
			r = "8";
		}
		if (i == 5) {
			r = "7";
		}
		if (i == 6) {
			r = "6";
		}
		if (i == 7) {
			r = "5";
		}
		if (i == 8) {
			r = "4";
		}
		if (i == 9) {
			r = "3";
		}
		if (i == 10) {
			r = "2";
		}
		if (r.equals(lastnumber.toLowerCase())) {
			return true;
		}
		return false;
	}

}
