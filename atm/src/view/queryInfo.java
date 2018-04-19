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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;
import dbConnection.UserMsg;

public class queryInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					queryInfo frame = new queryInfo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public queryInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);
		JLabel jla = new JLabel();
		jla.setBounds(0, 0, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 664, 477);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel pLabel = new JLabel("\u8BF7\u8F93\u5165\u8D26\u53F7");
		pLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		pLabel.setBounds(123, 122, 98, 35);
		panel.add(pLabel);

		textField = new JTextField();
		textField.setBounds(216, 123, 173, 35);
		panel.add(textField);
		textField.setColumns(10);

		JLabel account = new JLabel("");
		account.setFont(new Font("宋体", Font.BOLD, 16));
		account.setBounds(123, 221, 173, 27);
		panel.add(account);

		JLabel name = new JLabel("");
		name.setFont(new Font("宋体", Font.BOLD, 16));
		name.setBounds(337, 221, 160, 27);
		panel.add(name);

		JLabel total = new JLabel("");
		total.setFont(new Font("宋体", Font.BOLD, 16));
		total.setBounds(123, 286, 160, 27);
		panel.add(total);

		JLabel isFro = new JLabel("");
		isFro.setFont(new Font("宋体", Font.BOLD, 16));
		isFro.setBounds(337, 282, 160, 27);
		panel.add(isFro);

		JLabel isLost = new JLabel("");
		isLost.setFont(new Font("宋体", Font.BOLD, 16));
		isLost.setBounds(123, 363, 160, 27);
		panel.add(isLost);

		JLabel isDelete = new JLabel("");
		isDelete.setFont(new Font("宋体", Font.BOLD, 16));
		isDelete.setBounds(337, 363, 153, 27);
		panel.add(isDelete);

		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.setBounds(386, 123, 61, 37);
		panel.add(btnNewButton);

		JButton exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 13));
		exit.setBounds(575, 419, 67, 35);
		panel.add(exit);
		panel.add(jla);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				User  user= new User();
				UserMsg userMsg = new UserMsg();

				try {
					user = adminDAO.queryUserById(textField.getText());
					if(user!=null){
						userMsg = adminDAO.queryUserMsgByCusNo(user.getCusNo());
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				if (!textField.getText().matches("^[0-9]+$")
						&& !"".trim().equals(textField.getText())) {
					JOptionPane.showMessageDialog(null, "只可输入数字");
					textField.setText("");
				}else if(textField.getText().trim().equals("")){

					JOptionPane.showMessageDialog(null, "账号不可为空");

				}else if(user == null){

					JOptionPane.showMessageDialog(null, "账号不存在");

				}else if(user != null){

					account.setText("账号："+user.getUserNo());
					name.setText("姓名："+userMsg.getUserName());
					total.setText("余额："+user.getTotal());
				    if(user.isFrozen()){
				    	isFro.setText("是否已冻结：已冻结");
				    }

				    if(!user.isFrozen()){
				    	isFro.setText("是否已冻结：未冻结");
				    }

				    if(user.isDelete()){
				    	isDelete.setText("是否已注销：已注销");
				    }

				    if(!user.isDelete()){

				    	isDelete.setText("是否已注销：未注销");

				    }

				    if(user.isLose()){

				    	isLost.setText("是否已挂失：已挂失");

				    }

				    if(!user.isLose()){
				    	isLost.setText("是否已挂失：未挂失");
				    }

				}

			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				admin a  = new admin();
				a.setVisible(true);
				setVisible(false);
			}
		});
	}
}
