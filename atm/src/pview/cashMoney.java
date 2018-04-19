package pview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.Record;
import dbConnection.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class cashMoney extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	DAOInter adminDAO  = DAOFactory.getUserDAOFactory();
	float total_money= 0;
	Record record = null;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cashMoney frame = new cashMoney();
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
	public cashMoney(User user) {
		setTitle("\u53D6\u6B3E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JButton button = new JButton("100");
		button.setFont(new Font("宋体", Font.PLAIN, 22));
		button.setBounds(27, 104, 80, 44);

		JButton btnNewButton = new JButton("1000");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton.setBounds(543, 104, 80, 44);

		JButton btnNewButton_1 = new JButton("200");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton_1.setBounds(27, 188, 80, 49);

		JButton btnNewButton_2 = new JButton("1500");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(543, 188, 80, 49);

		JButton btnNewButton_3 = new JButton("500");
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton_3.setBounds(27, 285, 80, 49);

		JButton btnNewButton_4 = new JButton("2000");
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 22));
		btnNewButton_4.setBounds(543, 287, 80, 44);

		JButton button_1 = new JButton("\u786E\u8BA4");
		button_1.setBounds(184, 359, 72, 37);
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));

		JButton btnNewButton_5 = new JButton("\u91CD\u7F6E");
		btnNewButton_5.setBounds(385, 359, 72, 37);
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label = new JLabel("\u53D6\u6B3E\u91D1\u989D");
		label.setBounds(196, 199, 73, 33);
		label.setFont(new Font("宋体", Font.PLAIN, 16));

		textField = new JTextField();
		textField.setBounds(279, 201, 159, 31);
		textField.setFont(new Font("宋体", Font.BOLD, 14));
		textField.setColumns(15);

		JButton button_2 = new JButton("\u9000\u51FA");
		button_2.setBounds(578, 409, 73, 37);
		button_2.setFont(new Font("宋体", Font.PLAIN, 14));
		panel.setLayout(null);
		panel.add(btnNewButton_3);
		panel.add(btnNewButton_1);
		panel.add(button);
		panel.add(label);
		panel.add(textField);
		panel.add(btnNewButton_2);
		panel.add(btnNewButton_4);
		panel.add(btnNewButton);
		panel.add(button_1);
		panel.add(btnNewButton_5);
		panel.add(button_2);
		ImageIcon icon1 = new ImageIcon("./image/atm.png");
		icon1.setImage(
				icon1.getImage().getScaledInstance(icon1.getIconWidth(), icon1.getIconHeight(), Image.SCALE_DEFAULT));
		JLabel jla = new JLabel();
		jla.setBounds(-12, 0, 673, 468);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon1);
		panel.add(jla);
		button_1.setEnabled(false);
		/*100*/
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("100");
				button_1.setEnabled(true);
			}
		});
		/*200*/
		btnNewButton_1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						textField.setText("200");
						button_1.setEnabled(true);
					}
		});
		/*500*/
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("500");
				button_1.setEnabled(true);
			}
		});
		/*1000*/
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("1000");
				button_1.setEnabled(true);
			}
		});
		/*1500*/
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("1500");
				button_1.setEnabled(true);
			}
		});
		/*2000*/
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("2000");
				button_1.setEnabled(true);
			}
		});

		/*屏蔽掉输入非数字的字符*/
		textField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				button_1.setEnabled(true);/*输入了内容后能够点击按钮了*/
				if(textField.getText().equals("")){
					button_1.setEnabled(false);
				}
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //关键，屏蔽掉非法输入
                }
            }
		});

		/*没有输入数字则无法点击确认按钮*/
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(textField.getText().equals("")){
					button_1.setEnabled(false);
				}else{
					button_1.setEnabled(true);
				}
			}
		});

		/*确认*/
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User user1 = null;
				String text = textField.getText();
				float money = Float.parseFloat(text);
				// TODO Auto-generated method stub
				if(!text.matches("^[1-9][0-9]\\d*{3,5}$")){
					JOptionPane.showMessageDialog(null, "输入金额格式错误");
					textField.setText("");
				}else if(money > 2000){
					JOptionPane.showMessageDialog(null, "取款金额过大，请重新取款");
					textField.setText("");
				}else if(money % 100 != 0){
					JOptionPane.showMessageDialog(null, "取出金额不为整数,请重新取款");
					textField.setText("");
				}else{
					/*跨行取款收手续费*/
					if(user.getBe_bank() != "中国银行"){
						money = (float) (money * (1+0.003));
					}
					try {
						user1 = adminDAO.queryUserById(user.getUserNo());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					/*测试语句System.out.println(user.getBe_bank().toString().trim());*/
					if(money > user1.getTotal()){
						JOptionPane.showMessageDialog(null, "余额不足，请重新输入");
						System.out.println(money);
						System.out.println(user.getTotal());
						textField.setText("");

					}else {
						total_money = total_money + money;
						System.out.println(total_money);
						if(total_money > 10000){
							JOptionPane.showMessageDialog(null, "取出金额已达上限,无法继续取款");
							textField.setText("");
							/*customerFrame cf = new customerFrame(user);
							cf.setVisible(true);
							setVisible(false);*/
						}else{
							/*取款*/
							try {
								//存款
								adminDAO.saveMoney(user1.getUserNo(),-money);
								adminDAO.saveRecord(user1.getUserNo(),money,"取款",user.getUserNo());
								JOptionPane.showMessageDialog(null, "取款成功");
								textField.setText("");
								button_1.setEnabled(false);
								return;
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}


				}
			}
		});
		//重置
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setText("");
				button_1.setEnabled(false);
			}
		});
		//退出
		button_2.addActionListener(new ActionListener() {

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
