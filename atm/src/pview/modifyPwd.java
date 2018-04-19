package pview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbConnection.DAOFactory;
import dbConnection.DAOImple;
import dbConnection.DAOInter;
import dbConnection.User;
import view.login;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

/**
 * �޸����빦��
 * @author yzh
 *
 */
public class modifyPwd extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	int cishu=0;//�����������
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyPwd frame = new modifyPwd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public modifyPwd(User user) {
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u539F\u59CB\u5BC6\u7801:");
		label_1.setBounds(164, 103, 146, 34);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));

		JLabel label_2 = new JLabel("\u8BF7\u8F93\u5165\u65B0\u7684\u5BC6\u7801:");
		label_2.setBounds(164, 175, 147, 27);
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));

		JLabel label_3 = new JLabel("\u8BF7\u786E\u8BA4\u65B0\u7684\u5BC6\u7801:");
		label_3.setBounds(164, 244, 130, 34);
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		JButton button = new JButton("\u9000\u51FA");
		button.setBounds(567, 396, 85, 40);
		button.setBackground(UIManager.getColor("Button.darkShadow"));
		button.setFont(new Font("΢���ź�", Font.BOLD, 14));

		JButton button_1 = new JButton("\u63D0\u4EA4");
		button_1.setBounds(198, 320, 85, 40);
		button_1.setBackground(UIManager.getColor("Button.shadow"));
		button_1.setFont(new Font("΢���ź�", Font.BOLD, 14));

		JButton btnNewButton = new JButton("\u91CD\u7F6E");
		btnNewButton.setBounds(394, 321, 81, 40);
		btnNewButton.setBackground(UIManager.getColor("Button.shadow"));

		passwordField = new JPasswordField();
		passwordField.setBounds(304, 105, 216, 34);
		passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		passwordField.setColumns(15);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(304, 173, 216, 34);
		passwordField_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		passwordField_1.setColumns(15);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(304, 246, 216, 34);
		passwordField_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		passwordField_2.setColumns(15);

		panel.add(label_3);
		panel.add(passwordField_1);
		panel.add(label_2);
		panel.add(passwordField);
		panel.add(label_1);
		panel.add(passwordField_2);
		panel.add(button);
		panel.add(button_1);
		panel.add(btnNewButton);
		ImageIcon icon1 = new ImageIcon("./image/atm.png");
		icon1.setImage(
				icon1.getImage().getScaledInstance(icon1.getIconWidth(), icon1.getIconHeight(), Image.SCALE_DEFAULT));
		JLabel jla = new JLabel();
		jla.setBounds(0, 3, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon1);
		panel.add(jla);
		/**
		 * textField --�����������˺�
		 * passwordField	--����������ԭʼ����
		 * passwordField_1	--�������µ�����
		 * passwordField_2	--��ȷ���µ�����
		 * button_1		--�ύ
		 * btnNewButton --����
		 */
		/*button_1.setEnabled(false);*/
		panel.setLayout(null);

		/*
		char[] Pawds = passwordField.getPassword();
		String password = String.valueOf(Pawds);	//����ԭʼ����
		char[] Pawds_1 = passwordField_1.getPassword();
		String password_1 = String.valueOf(Pawds_1);	//����������
		char[] Pawds_2 = passwordField_2.getPassword();
		String password_2 = String.valueOf(Pawds_2);	//ȷ��������
		*/

		String password=passwordField.getText();//����ԭʼ����
		String password_1=passwordField_1.getText();//����������
		String password_2=passwordField_2.getText();



		//�˺�
		/*���ε���������ֵ��ַ�*/
		/*textField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				button_1.setEnabled(true);	���������ݺ��ܹ������ť��
				if(textField.getText().equals("")){
					button_1.setEnabled(false);
				}
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //�ؼ������ε��Ƿ�����
                }
            }
		});*/
		passwordField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //�ؼ������ε��Ƿ�����
                }
            }
		});
		passwordField_1.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //�ؼ������ε��Ƿ�����
                }
            }
		});
		passwordField_2.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //�ؼ������ε��Ƿ�����
                }
            }
		});
		/*textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

				if(user.equals("") || !textField.getText().equals(user.getUserNo()) || !textField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "�����˺���������������");
					textField.setText("");

					passwordField.setText("");
					passwordField_1.setText("");
					passwordField_2.setText("");
					button_1.setEnabled(false);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(textField.getText().equals("")){
					passwordField.setText("");
					passwordField_1.setText("");
					passwordField_2.setText("");
				}
			}
		});*/

	/*	passwordField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				String password=passwordField.getText();//����ԭʼ����
				if(!password.equals("")){
					if(!password.equals(user.getUserPawd())){
						System.out.println("b");

					}
				}else{
					JOptionPane.showMessageDialog(null, "����������");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				System.out.println(password);
				if(password.equals("")){
					button_1.setEnabled(false);
					passwordField_1.setText("");
					passwordField_2.setText("");
				}
			}
		});

		passwordField_1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String password=passwordField.getText();//����ԭʼ����
				String password_1=passwordField_1.getText();//����������
				String password_2=passwordField_2.getText();//ȷ��������
				if(!password.equals("")){
					if(password_1.equals("")){
						JOptionPane.showMessageDialog(null, "�����벻��Ϊ��");
					}else if(password_1.equals(password)){
						JOptionPane.showMessageDialog(null, "�����벻�ܺ;�������ͬ");
						passwordField_1.setText("");
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub


			}
		});
		passwordField_2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String password=passwordField.getText();//����ԭʼ����
				String password_1=passwordField_1.getText();//����������
				String password_2=passwordField_2.getText();//ȷ��������
				if(!password.equals("") && !password_1.equals("")){
					if(password_2.equals("")){
						JOptionPane.showMessageDialog(null, "ȷ�����벻��Ϊ��");
					}else if(!password_2.equals(password_1)){
						JOptionPane.showMessageDialog(null, "������ȷ�ϴ���");
						passwordField_1.setText("");
						passwordField_2.setText("");
					}

				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				String password=passwordField.getText();//����ԭʼ����
				String password_1=passwordField_1.getText();//����������
				String password_2=passwordField_2.getText();//ȷ��������
				if(!password.equals("") && !password_1.equals("") && !password_1.equals("")){
					button_1.setEnabled(true);
				}else{
					button_1.setEnabled(false);
				}
			}
		});
		*/



		//�ύ
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password=passwordField.getText();//����ԭʼ����
				String password_1=passwordField_1.getText();//����������
				String password_2=passwordField_2.getText();//ȷ��������

				if(!passwordField.getText().equals("") && !passwordField_1.getText().equals("")  &&  !user.getUserPawd().equals("")&& password.toString().equals(user.getUserPawd()) && !password_1.equals(password) && password_2.equals(password_1) && !password_2.equals(password)){
					try{
						adminDAO.changePawds(user.getUserNo(), password_1);
					}catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "��������ɹ������ص�¼����");
					login l = new login();
					String[] args = null;
					l.main(args);
					setVisible(false);
				}else{
					if (password.equals("") || !user.getUserPawd().equals(password)) {
						++cishu;
						JOptionPane.showMessageDialog(null, "����������������ʣ"+(3-cishu)+"�λ���,����������");
						passwordField.setText("");
					/*	button_1.setEnabled(false);*/
						System.out.println(cishu);
						if(cishu>=3){
							JOptionPane.showMessageDialog(null, "�������볬�����Σ����˺��Ѷ���");
							setVisible(false);
							login l = new login();
							String[] args = null;
							l.main(args);
							try {
								adminDAO.frozenUser(user.getUserNo());
							} catch (Exception e1) {
								// TODO Auto-generated catc                      h block
								e1.printStackTrace();
							}
							return;
						}
							passwordField.setText("");
							passwordField_1.setText("");
							passwordField_2.setText("");
					}else{
						if(password_1.equals(password )|| password_1.equals("")){
							JOptionPane.showMessageDialog(null, "������������������������");
							passwordField_1.setText("");
							passwordField_2.setText("");
						}else {
							if (password_2.equals("")) {
								JOptionPane.showMessageDialog(null, "ȷ�������벻��Ϊ��");
								passwordField_2.setText("");

							}else if( password_2.equals(password)  ||  !password_2 .equals(password_1)){
								JOptionPane.showMessageDialog(null, "ȷ�����������");
								passwordField_1.setText("");
							}else {
								JOptionPane.showMessageDialog(null, "ȷ�����������");
								passwordField_1.setText("");
								passwordField_2.setText("");
							}
						}
					}

				}
			}
		});


		//����
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*textField.setText("");*/
				passwordField_1.setText("");
				passwordField.setText("");
				passwordField_2.setText("");
				button_1.setEnabled(false);
			}
		});

		//�˳��޸��������
		button.addActionListener(new ActionListener() {



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
