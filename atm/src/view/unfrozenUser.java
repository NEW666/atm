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
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;

import dbConnection.Admin;
import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;
import dbConnection.UserMsg;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class unfrozenUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton button;
	private JButton btnNewButton;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	private JPanel panel_2;
	private JLabel punFro;
	private JPanel panel_1;
	private JLabel pname;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public unfrozenUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));

		panel.setLayout(null);

		JLabel jla2 = new JLabel();
		jla2.setBounds(0, 0, 680, 508);

		jla2.setBackground(Color.LIGHT_GRAY);
		jla2.setHorizontalAlignment(0);
		jla2.setIcon(icon);


		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 664, 469);
		panel.add(panel_2);
		panel_2.setLayout(null);


		pname = new JLabel("");
		pname.setFont(new Font("宋体", Font.PLAIN, 16));
		pname.setHorizontalAlignment(SwingConstants.CENTER);
		pname.setBounds(192, 120, 241, 33);
		panel_2.add(pname);

		JLabel paccount = new JLabel("");
		paccount.setHorizontalAlignment(SwingConstants.CENTER);
		paccount.setBounds(192, 176, 241, 33);
		paccount.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_2.add(paccount);

		punFro = new JLabel("");
		punFro.setHorizontalAlignment(SwingConstants.CENTER);
		punFro.setBounds(192, 230, 241, 33);
		punFro.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_2.add(punFro);

		JButton yes = new JButton("\u786E\u8BA4");
		yes.setBounds(178, 296, 77, 38);
		yes.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_2.add(yes);

		JButton no = new JButton("\u53D6\u6D88");
		no.setBounds(368, 296, 77, 38);
		no.setFont(new Font("宋体", Font.PLAIN, 14));
		panel_2.add(no);
		panel_2.add(jla2);
		panel_2.setVisible(false);




		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					adminDAO.unfrozenUser(textField.getText());
					;
					panel_2.setVisible(false);
					panel_1.setVisible(true);
					textField.setText("");


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 664, 469);
		panel.add(panel_1);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setBounds(273, 143, 160, 33);
		panel_1.add(textField);
		textField.setColumns(10);

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (!textField.getText().matches("^[0-9a-zA_Z]+$")
						&& !"".trim().equals(textField.getText())) {
					JOptionPane.showMessageDialog(null, "只可输入数字和大小写英文");
					textField.setText("");
				}

			}

		});

		lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u8D26\u53F7");
		lblNewLabel.setBounds(152, 145, 100, 24);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));

		btnNewButton = new JButton("\u9000\u51FA");
		btnNewButton.setBounds(360, 293, 84, 43);
		panel_1.add(btnNewButton);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 22));

		button = new JButton("\u786E\u8BA4");
		button.setBounds(186, 295, 84, 43);
		panel_1.add(button);
		button.setFont(new Font("宋体", Font.PLAIN, 22));

		button.setFont(new Font("宋体", Font.PLAIN, 20));
		JLabel jla = new JLabel();
		jla.setBounds(0, 0, 680, 508);

		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel_1.add(jla);

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


				User user = null;
				try {
					user = adminDAO.queryUserById(textField.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if ("".trim().equals(textField.getText())) {

					JOptionPane.showMessageDialog(null, "账号不可为空");

				} else if (user == null) {

					JOptionPane.showMessageDialog(null, "账号不存在");

				} else if (!user.isFrozen()) {

					JOptionPane.showMessageDialog(null, "账号已解冻");

				} else {

					UserMsg userMsg = new UserMsg();
					try {
						userMsg = adminDAO.queryUserMsgByCusNo(user.getCusNo());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					panel_2.setVisible(true);
					panel_1.setVisible(false);

					paccount.setText("账号：" + user.getUserNo());
					pname.setText("姓名：" + userMsg.getUserName());
					if (user.isFrozen()) {
						punFro.setText("是否已解冻：已解冻");
					}
					if (!user.isFrozen()) {
						punFro.setText("是否已解冻：已解冻");

					}

				}

			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);
			}
		});

	}
}
