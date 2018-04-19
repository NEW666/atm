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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
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

public class Frozen extends JFrame {

	private JPanel contentPane;
	private JTextField account;

	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	public Frozen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 664, 477);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 5, 664, 472);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel paccount = new JLabel("\u8D26\u53F7");
		paccount.setHorizontalAlignment(SwingConstants.CENTER);
		paccount.setFont(new Font("宋体", Font.PLAIN, 16));
		paccount.setBounds(204, 126, 232, 35);
		panel_2.add(paccount);

		JLabel pname = new JLabel("New label");
		pname.setHorizontalAlignment(SwingConstants.CENTER);
		pname.setFont(new Font("宋体", Font.PLAIN, 16));
		pname.setBounds(204, 183, 232, 35);
		panel_2.add(pname);

		JLabel prfo = new JLabel("New label");
		prfo.setHorizontalAlignment(SwingConstants.CENTER);
		prfo.setBounds(204, 244, 232, 35);
		panel_2.add(prfo);

		JButton yes = new JButton("\u786E\u5B9A");
		yes.setFont(new Font("宋体", Font.PLAIN, 14));
		yes.setBounds(185, 339, 67, 35);
		panel_2.add(yes);

		JButton no = new JButton("\u53D6\u6D88");
		no.setFont(new Font("宋体", Font.PLAIN, 14));
		no.setBounds(369, 338, 67, 36);
		panel_2.add(no);

		JLabel jla2 = new JLabel();
		jla2.setBounds(0, 0, 664, 474);
		panel_2.add(jla2);
		jla2.setBackground(Color.LIGHT_GRAY);
		jla2.setHorizontalAlignment(0);
		jla2.setIcon(icon);
		panel_2.setVisible(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 5, 664, 472);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel aLabel = new JLabel("\u8D26\u53F7");
		aLabel.setBounds(203, 138, 56, 36);
		panel_1.add(aLabel);
		aLabel.setFont(new Font("宋体", Font.PLAIN, 20));

		account = new JTextField();
		account.setBounds(266, 140, 170, 33);
		panel_1.add(account);
		account.setColumns(10);

		JButton sure = new JButton("\u786E\u8BA4");
		sure.setBounds(167, 288, 93, 44);
		panel_1.add(sure);
		sure.setFont(new Font("宋体", Font.PLAIN, 20));

		JButton exit = new JButton("\u9000\u51FA");
		exit.setBounds(372, 285, 93, 44);
		panel_1.add(exit);
		exit.setFont(new Font("宋体", Font.PLAIN, 20));
		JLabel jla = new JLabel();
		jla.setBounds(0, 0, 664, 474);
		panel_1.add(jla);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);
			}
		});

		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					adminDAO.frozenUser(account.getText());

					panel_2.setVisible(false);
					panel_1.setVisible(true);
					account.setText("");

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

		sure.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				User user = null;

				try {
					user = adminDAO.queryUserById(account.getText());
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if ("".trim().equals(account.getText())) {

					JOptionPane.showMessageDialog(null, "账号不可为空");

				} else if (user == null && !"".equals(account.getText())) {

					JOptionPane.showMessageDialog(null, "账号不存在");

				} else if(user.isFrozen()){

					JOptionPane.showMessageDialog(null, "该账号已冻结");


				}else{

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
						prfo.setText("是否已冻结：已冻结");
					}
					if (!user.isFrozen()) {
						prfo.setText("是否已冻结：已冻结");

					}

				}

			}
		});

		account.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (!account.getText().matches("^[0-9a-zA_Z]+$")
						&& !"".trim().equals(account.getText())) {
					JOptionPane.showMessageDialog(null, "只可输入数字和大小写英文");

				}

			}

		});

	}
}
