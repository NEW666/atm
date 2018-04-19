package pview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.User;

import javax.swing.JButton;
import java.awt.Font;

public class query extends JFrame {

	private JPanel contentPane;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	/**
	 * Create the frame.
	 */
	public query(User user) {
		setTitle("\u67E5\u8BE2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("\u67E5\u8BE2\u4F59\u989D");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.setBounds(70, 183, 120, 55);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u67E5\u8BE2\u4EA4\u6613\u8BB0\u5F55");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 13));
		btnNewButton_1.setBounds(251, 183, 120, 55);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u9000\u51FA");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_2.setBounds(458, 186, 109, 49);
		panel.add(btnNewButton_2);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(
				icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
		JLabel jla = new JLabel();
		jla.setBounds(0, 3, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);
		/*查询余额*/
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User user1 = null;
				try {
					user1 = adminDAO.queryUserById(user.getUserNo());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "您当前余额是:"+user1.getTotal());
			}
		});
		/*查询交易记录*/
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				queryRecord qr = new queryRecord(user);
				qr.setVisible(true);
				setVisible(false);
			}
		});

		//退出
		btnNewButton_2.addActionListener(new ActionListener() {

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
