package pview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.Record;
import dbConnection.User;

import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class savemoney extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	float total_save = 0;
	Record record = null;
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { savemoney frame = new
	 * savemoney(User user); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public savemoney(User user) {
		setTitle("\u5B58\u6B3E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		textField = new JTextField();
		textField.setBounds(285, 166, 188, 40);
		textField.setFont(new Font("����", Font.BOLD, 14));
		textField.setColumns(20);

		JLabel label = new JLabel("\u5B58\u5165\u91D1\u989D");
		label.setBounds(162, 166, 100, 37);
		label.setFont(new Font("����", Font.PLAIN, 20));

		JButton button = new JButton("\u786E\u5B9A");
		button.setBounds(180, 250, 82, 40);

		JButton btnNewButton = new JButton("\u91CD\u7F6E");
		btnNewButton.setBounds(367, 250, 86, 40);

		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.setBounds(579, 400, 74, 40);
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		panel.setLayout(null);
		panel.add(button);
		panel.add(btnNewButton);
		panel.add(label);
		panel.add(textField);
		panel.add(button_1);
		button.setEnabled(false);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(
				icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
		JLabel jla = new JLabel();
		jla.setBounds(0, 3, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);
		/**
		 * textField ������
		 *  button ȷ��
		 *   btnNewButton ����
		 */

		/*���ε���������ֵ��ַ�*/
		textField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				button.setEnabled(true);	/*���������ݺ��ܹ������ť��*/
				if(textField.getText().equals("")){
					button.setEnabled(false);
				}
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //�ؼ������ε��Ƿ�����
                }
            }


		});

		/*û�������������޷����ȷ�ϰ�ť*/
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(textField.getText().equals("")){
					button.setEnabled(false);
				}
			}
		});

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User user1 = null;
				String text = textField.getText();
				float money = Float.parseFloat(text);
				if (!text.matches("^[1-9][0-9]\\d*{3,5}$")) {
					JOptionPane.showMessageDialog(null, "�������ʽ����");
					textField.setText("");
				} else if (money > 10000 || money < 0) {
					JOptionPane.showMessageDialog(null, "�������������´��");
					textField.setText("");
				} else if (money % 100 != 0) {
					JOptionPane.showMessageDialog(null, "�����Ϊ����,�����´��");
					textField.setText("");
				} else {
					/*���д����������*/
					if(user.getBe_bank() != "�й�����"){
						money = (float) (money * (1-0.003));
					}
					try {
						user1 = adminDAO.queryUserById(user.getUserNo());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					total_save = total_save + money;
					if (user1.getTotal() > 50000 || total_save > 50000) {
						JOptionPane.showMessageDialog(null, "�������Ѵ�����,�޷��������");
						customerFrame cf = new customerFrame(user);
						cf.setVisible(true);
						setVisible(false);
					}else{
						try {
							// ���
							adminDAO.saveMoney(user1.getUserNo(), money);
							// �������¼
							adminDAO.saveRecord(user.getUserNo(), money, "���", user.getUserNo());
							JOptionPane.showMessageDialog(null, "���ɹ�");
							textField.setText("");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

			}
		});
		// ����
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("");
			}
		});
		//�˳�
		button_1.addActionListener(new ActionListener() {

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
