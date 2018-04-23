package pview;

/**
 * ת�˹���
 *
 * 1.�Ƚ���ȡǮ����
 *
 * 2.�ٽ��д�Ǯ����
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
		label.setFont(new Font("΢���ź�", Font.PLAIN, 20));

		JLabel label_1 = new JLabel("\u8F6C\u8D26\u91D1\u989D");
		label_1.setBounds(166, 247, 93, 26);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));

		textField = new JTextField();
		textField.setBounds(276, 178, 207, 33);
		textField.setFont(new Font("΢���ź�", Font.BOLD, 16));
		textField.setColumns(15);

		textField_1 = new JTextField();
		textField_1.setBounds(276, 246, 207, 33);
		textField_1.setFont(new Font("΢���ź�", Font.BOLD, 16));
		textField_1.setColumns(15);

		JButton button = new JButton("\u63D0\u4EA4");
		button.setBounds(150, 327, 88, 39);
		button.setToolTipText("\u786E\u8BA4\u6B63\u786E\u683C\u5F0F");
		button.setBackground(UIManager.getColor("Button.light"));
		button.setSelectedIcon(
				new ImageIcon("C:\\Users\\\u8C6A\u8C6A\u8C6A\\Desktop\\ATMsystem\\atm\\atm\\image\\atm.png"));
		button.setFont(new Font("΢���ź�", Font.BOLD, 16));

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.setFont(new Font("����", Font.PLAIN, 14));
		button_1.setBounds(411, 328, 81, 39);
		button_1.setBackground(UIManager.getColor("Button.light"));
		panel.setLayout(null);

		JToggleButton tglbtnNewToggleButton = new JToggleButton("\u9000\u51FA");
		tglbtnNewToggleButton.setFont(new Font("����", Font.PLAIN, 14));
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
		String account = textField.getText(); /* ת���˺� */
		final int PRECISION = 2; // FIXME С��λ��Ϊ2�� �������
		/* float money = Float.parseFloat(textField_1.getText()); */

		/**
		 * textField_1 -- ת�˽�� textField -- ת���˺�
		 *
		 */

		/* ���ε���������ֵ��ַ� */
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				button.setEnabled(true); /* ���������ݺ��ܹ������ť�� */
				if (textField.getText().equals("")) {
					button.setEnabled(false);
				}
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // �ؼ������ε��Ƿ�����
				}
			}

		});
		/* ���ε���������ֵ��ַ� */
		textField_1.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				button.setEnabled(true); /* ���������ݺ��ܹ������ť�� */
				if (textField_1.getText().equals("")) {
					button.setEnabled(false);
				}
				String text = textField_1.getText(); // ��ǰ���������
				char ch = e.getKeyChar(); // ׼�����ӵ��������ַ�

				// ���Ʋ�����������ֺ�С����
				if (!(ch >= '0' && ch <= '9') && ch != '.') {
					e.consume(); // ���ٵ�ǰ�����ַ�

					// ���Ʋ�����С���㿪ͷ
				} else if ("".equals(text) && ch == '.') {
					e.consume();

				} else if (text.contains(".")) {

					// ���Ʋ����ظ�����С����
					if (ch == '.') {
						e.consume();

						// ����С��Ϊ2λ��
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

		// �ύ
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String account = textField.getText(); /* ת���˺� */
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
					JOptionPane.showMessageDialog(null, "�˺Ų�����");

				} else if (user1 != null) {

					if (textField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��");
						textField.setText("");
						textField_1.setText("");
					} else if (textField_1.getText().equals("")) {
						textField_1.setText("");
					}
					if (user1.equals("") || !account.equals(user1.getUserNo()) || account.equals("")) {
						JOptionPane.showMessageDialog(null, "�˺Ŵ���");
						textField.setText("");
					}
					try {
						user1 = adminDAO.queryUserById(account);
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}

					if (textField_1.getText().trim().equals("")) {

						JOptionPane.showMessageDialog(null, "�������Ϊ0");
						return;

					}
					money = Float.parseFloat(textField_1.getText()); /* ת�˽�� */

					if (user1 != null && user1.getUserNo().equals(account)
							&& !user1.getUserNo().equals(user.getUserNo())) {
						if (user1.isDelete()) {

							JOptionPane.showMessageDialog(null, "�˺��ѱ�ע�����޷�ת��");
							setVisible(false);
							customerFrame cf = new customerFrame(user);
							cf.setVisible(true);
						} else {

							if (money == 0) {

								JOptionPane.showMessageDialog(null, "�������Ϊ0������������");
								textField_1.setText("");
							} else {
								try {
									user2 = adminDAO.queryUserById(user.getUserNo());
									/*records = adminDAO.getRecordByHalfMonth(user2.getUserNo());*/
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								/* ����ת�� */
								money = Float.parseFloat(textField_1.getText()); /* ת�˽�� */
								if (!user1.getBe_bank().equals("�й�����")) {
									au_money = (float) (money * (1 + 0.0003));
									total_money = total_money + money ;
								} else {
									au_money = money;
									total_money = total_money + money;
								}
								System.out.println(total_money);
								if (total_money > 50000 || user2.getTotal() < au_money) {
									JOptionPane.showMessageDialog(null, "ת���Ѵ����ޣ��޷�����ת��");
									total_money = total_money - money ;
									textField_1.setText("");
								} else {
									try {
										/* һ���˻�ȡǮ */
										adminDAO.transfer(user2.getUserNo(), -au_money);
										adminDAO.saveRecord(user2.getUserNo(), money, "ת��", user1.getUserNo());
										// һ���˻���Ǯ
										adminDAO.transfer(user1.getUserNo(), money);
										JOptionPane.showMessageDialog(null, "ת�˳ɹ�");
										textField_1.setText(null);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
						}
					} else if (textField.getText().equals(user.getUserNo())) {

						JOptionPane.showMessageDialog(null, "����ת�˸��Լ�");

					} else {
						JOptionPane.showMessageDialog(null, "�˺������������������");
						textField.setText("");
					}

				}

				/* �������ʽ�ж� */
				/* user1�������ת���˺� */
				/*
				 * else if(!user1.getBe_bank().equals("�й�����")){ /*money =
				 * (float) (money * (1-0.0003));
				 */

				// TODO Auto-generated method stub

			}
		});

		// ����
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField_1.setText("");
				textField.setText("");
			}
		});

		// �˳�
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
