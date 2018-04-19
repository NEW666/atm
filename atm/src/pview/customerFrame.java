package pview;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.Color;

/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;*/


import dbConnection.User;
import view.login;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class customerFrame extends JFrame {

	/**
	 * Launch the application.
	 */


	// }

	/**
	 * Create the frame.
	 */
	public customerFrame(User user) {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setTitle("\u7528\u6237\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 504);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("\u5B58\u6B3E");
		btnNewButton.setBounds(51, 145, 112, 55);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u53D6\u6B3E");
		btnNewButton_1.setBounds(51, 245, 112, 55);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u67E5\u8BE2");
		btnNewButton_2.setBounds(51, 356, 112, 55);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u8F6C\u8D26");
		btnNewButton_3.setBounds(479, 145, 112, 55);
		panel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		btnNewButton_4.setBounds(479, 245, 112, 55);
		panel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("\u9000\u51FA");
		btnNewButton_5.setBounds(477, 356, 114, 55);
		panel.add(btnNewButton_5);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(
				icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
		JLabel jla = new JLabel();
		jla.setBounds(0, 3, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);
		/*存款*/
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				savemoney sm = new savemoney(user);
				sm.setVisible(true);
				setVisible(false);
			}
		});
		/*取款*/
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cashMoney cm = new cashMoney(user);
				cm.setVisible(true);
				setVisible(false);
			}
		});
		/*查询*/
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				query q = new query(user);
				q.setVisible(true);
				setVisible(false);
			}
		});
		/*转账*/
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				transfermoney tm = new transfermoney(user);
				tm.setVisible(true);
				setVisible(false);
			}
		});
		/*修改密码*/
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modifyPwd mp = new modifyPwd(user);
				mp.setVisible(true);
				setVisible(false);
			}
		});
		/*退出*/
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login l = new login();
				String[] args = null;
				l.main(args);
				setVisible(false);
			}
		});
		}
}
