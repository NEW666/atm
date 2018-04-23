package pview;

/**
 * 转账功能
 *
 * 1.先进行取钱操作
 *
 * 2.再进行存钱操作
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import dbConnection.DAOFactory;
import dbConnection.DAOImple;
import dbConnection.DAOInter;
import dbConnection.Record;
import dbConnection.User;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.UIManager;
import javax.swing.JToggleButton;

public class transfermoney extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	User user1 = null;
	float total_money = 0;
	float au_money = 0;
	ArrayList<Record> records = null;
	Record record = null;
	int i = 0;
	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() {
	 *
	 *
	 * public void run() { try { // transfermoney frame = new
	 * transfermoney(user); // frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public transfermoney(User user) {
		setTitle("\u8F6C\u8D26\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.PINK, 1, true));
		panel.setToolTipText("\u8F93\u5165\u6B63\u786E\u683C\u5F0F");
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel label = new JLabel("\u8F6C\u8D26\u8D26\u53F7");
		label.setBounds(166, 179, 93, 26);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		JLabel label_1 = new JLabel("\u8F6C\u8D26\u91D1\u989D");
		label_1.setBounds(166, 247, 93, 26);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		textField = new JTextField();
		textField.setBounds(276, 178, 207, 33);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textField.setColumns(15);

		textField_1 = new JTextField();
		textField_1.setBounds(276, 246, 207, 33);
		textField_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textField_1.setColumns(15);

		JButton button = new JButton("\u63D0\u4EA4");
		button.setBounds(150, 327, 88, 39);
		button.setToolTipText("\u786E\u8BA4\u6B63\u786E\u683C\u5F0F");
		button.setBackground(UIManager.getColor("Button.light"));
		button.setSelectedIcon(
				new ImageIcon("C:\\Users\\\u8C6A\u8C6A\u8C6A\\Desktop\\ATMsystem\\atm\\atm\\image\\atm.png"));
		button.setFont(new Font("微软雅黑", Font.BOLD, 16));

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		button_1.setBounds(411, 328, 81, 39);
		button_1.setBackground(UIManager.getColor("Button.light"));
		panel.setLayout(null);

		JToggleButton tglbtnNewToggleButton = new JToggleButton("\u9000\u51FA");
		tglbtnNewToggleButton.setFont(new Font("宋体", Font.PLAIN, 14));
		tglbtnNewToggleButton.setBounds(587, 416, 65, 39);

		ImageIcon icon = new ImageIcon("../atm/atm/image/atm.png");
		icon.setImage(
				icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));

		panel.add(tglbtnNewToggleButton);
		panel.add(label_1);
		panel.add(textField_1);
		panel.add(label);
		panel.add(button_1);
		panel.add(textField);
		panel.add(button);
		ImageIcon icon1 = new ImageIcon("./image/atm.png");
		icon1.setImage(
				icon1.getImage().getScaledInstance(icon1.getIconWidth(), icon1.getIconHeight(), Image.SCALE_DEFAULT));
		JLabel jla = new JLabel();
		jla.setBounds(0, 3, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon1);
		panel.add(jla);
		button.setEnabled(false);
		String account = textField.getText(); /* 转账账号 */
		final int PRECISION = 2; // FIXME 小数位数为2， 建议可配
		/* float money = Float.parseFloat(textField_1.getText()); */

		/**
		 * textField_1 -- 转账金额 textField -- 转账账号
		 *
		 */

		/* 屏蔽掉输入非数字的字符 */
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				button.setEnabled(true); /* 输入了内容后能够点击按钮了 */
				if (textField.getText().equals("")) {
					button.setEnabled(false);
				}
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}

		});
		/* 屏蔽掉输入非数字的字符 */
		textField_1.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				button.setEnabled(true); /* 输入了内容后能够点击按钮了 */
				if (textField_1.getText().equals("")) {
					button.setEnabled(false);
				}
				String text = textField_1.getText(); // 当前输入框内容
				char ch = e.getKeyChar(); // 准备附加到输入框的字符

				// 限制不能输入非数字和小数点
				if (!(ch >= '0' && ch <= '9') && ch != '.') {
					e.consume(); // 销毁当前输入字符

					// 限制不能是小数点开头
				} else if ("".equals(text) && ch == '.') {
					e.consume();

				} else if (text.contains(".")) {

					// 限制不能重复输入小数点
					if (ch == '.') {
						e.consume();

						// 限制小数为2位数
					} else {
						int idx = text.indexOf('.');
						String tmp = text.substring(idx + 1);
						if (tmp.length() >= PRECISION) {
							e.consume();
						}
					}
				}

			}

		});

		// 提交
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String account = textField.getText(); /* 转账账号 */
				User user2 = null;
				float money;
				/*
				 * System.out.println(account+"1");
				 * System.out.println(user1.getUserNo()+"/");
				 */
				try {
					user1 = adminDAO.queryUserById(account);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if (user1 == null) {
					JOptionPane.showMessageDialog(null, "账号不存在");

				} else if (user1 != null) {

					if (textField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "账号不能为空");
						textField.setText("");
						textField_1.setText("");
					} else if (textField_1.getText().equals("")) {
						textField_1.setText("");
					}
					if (user1.equals("") || !account.equals(user1.getUserNo()) || account.equals("")) {
						JOptionPane.showMessageDialog(null, "账号错误");
						textField.setText("");
					}
					try {
						user1 = adminDAO.queryUserById(account);
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}

					if (textField_1.getText().trim().equals("")) {

						JOptionPane.showMessageDialog(null, "输入金额不可为0");
						return;

					}
					money = Float.parseFloat(textField_1.getText()); /* 转账金额 */

					if (user1 != null && user1.getUserNo().equals(account)
							&& !user1.getUserNo().equals(user.getUserNo())) {
						if (user1.isDelete()) {

							JOptionPane.showMessageDialog(null, "账号已被注销，无法转账");
							setVisible(false);
							customerFrame cf = new customerFrame(user);
							cf.setVisible(true);
						} else {

							if (money == 0) {

								JOptionPane.showMessageDialog(null, "输入金额不可为0，请重新输入");
								textField_1.setText("");
							} else {
								try {
									user2 = adminDAO.queryUserById(user.getUserNo());
									/*records = adminDAO.getRecordByHalfMonth(user2.getUserNo());*/
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								/* 跨行转账 */
								money = Float.parseFloat(textField_1.getText()); /* 转账金额 */
								if (!user1.getBe_bank().equals("中国银行")) {
									au_money = (float) (money * (1 + 0.0003));
									total_money = total_money + money ;
								} else {
									au_money = money;
									total_money = total_money + money;
								}
								System.out.println(total_money);
								if (total_money > 50000 || user2.getTotal() < au_money) {
									JOptionPane.showMessageDialog(null, "转账已达上限，无法继续转账");
									total_money = total_money - money ;
									textField_1.setText("");
								} else {
									try {
										/* 一个账户取钱 */
										adminDAO.transfer(user2.getUserNo(), -au_money);
										adminDAO.saveRecord(user2.getUserNo(), money, "转账", user1.getUserNo());
										// 一个账户存钱
										adminDAO.transfer(user1.getUserNo(), money);
										JOptionPane.showMessageDialog(null, "转账成功");
										textField_1.setText(null);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
						}
					} else if (textField.getText().equals(user.getUserNo())) {

						JOptionPane.showMessageDialog(null, "不可转账给自己");

					} else {
						JOptionPane.showMessageDialog(null, "账号输入错误，请重新输入");
						textField.setText("");
					}

				}

				/* 输入金额格式判断 */
				/* user1代表的是转账账号 */
				/*
				 * else if(!user1.getBe_bank().equals("中国银行")){ /*money =
				 * (float) (money * (1-0.0003));
				 */

				// TODO Auto-generated method stub

			}
		});

		// 重置
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField_1.setText("");
				textField.setText("");
			}
		});

		// 退出
		tglbtnNewToggleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				customerFrame cf = new customerFrame(user);
				cf.setVisible(true);
				setVisible(false);
			}
		});
	}
}
