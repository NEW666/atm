package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class admin extends JFrame {

	private JPanel contentPane;

	DAOInter adminDAO = DAOFactory.getUserDAOFactory();


	/**
	 * Create the frame.
	 */
	public admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton newAccount = new JButton("\u5F00\u6237");
		newAccount.setFont(new Font("宋体", Font.PLAIN, 20));
		newAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newUser kaihu = new newUser();
				setVisible(false);
				kaihu.setVisible(true);
			}
		});
		newAccount.setBounds(31, 112, 106, 47);
		panel.add(newAccount);

		JButton guashi = new JButton("\u6302\u5931");
		guashi.setBounds(31, 188, 106, 47);
		panel.add(guashi);

		JButton unFrozen = new JButton("\u89E3\u51BB");
		unFrozen.setFont(new Font("宋体", Font.PLAIN, 20));
		unFrozen.setBounds(31, 333, 106, 47);
		panel.add(unFrozen);

		JButton zhuxiao = new JButton("\u6CE8\u9500");
		zhuxiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deUser de = new deUser();
				setVisible(false);
				de.setVisible(true);
			}
		});
		zhuxiao.setFont(new Font("宋体", Font.PLAIN, 20));
		zhuxiao.setBounds(519, 188,106, 47);
		panel.add(zhuxiao);

		JButton exit = new JButton("\u9000\u51FA");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		exit.setFont(new Font("宋体", Font.PLAIN, 20));
		exit.setBounds(548, 400, 106, 47);
		panel.add(exit);


		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
		icon.getIconHeight(), Image.SCALE_DEFAULT));

		JButton changePawd = new JButton("\u5BC6\u7801\u4FEE\u6539");
		changePawd.setFont(new Font("宋体", Font.PLAIN, 18));
		changePawd.setBounds(519, 258, 106, 47);
		panel.add(changePawd);


		//挂失
		guashi.setFont(new Font("宋体", Font.PLAIN, 20));
		guashi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lostUser lu = new lostUser();
				setVisible(false);
				lu.setVisible(true);

			}
		});
		//解冻功能
		unFrozen.setFont(new Font("宋体", Font.PLAIN, 20));
		unFrozen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				unfrozenUser unfrozen = new unfrozenUser();
				unfrozen.setVisible(true);
				setVisible(false);


			}
		});

		JButton unGuashi = new JButton("\u89E3\u9664\u6302\u5931");
		unGuashi.setFont(new Font("宋体", Font.PLAIN, 18));
		unGuashi.setBounds(31, 258, 106, 47);
		panel.add(unGuashi);

		unGuashi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				noLost nl = new noLost();

				setVisible(false);

				nl.setVisible(true);




			}
		});

		JButton query = new JButton("\u67E5\u8BE2");
		query.setFont(new Font("宋体", Font.PLAIN, 20));
		query.setBounds(519, 112, 106, 47);
		panel.add(query);

		JButton frozen = new JButton("\u51BB\u7ED3");
		frozen.setFont(new Font("宋体", Font.PLAIN, 20));
		frozen.setBounds(519, 333, 106, 47);
		panel.add(frozen);

		frozen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Frozen frozen = new Frozen();
				frozen.setVisible(true);
				setVisible(false);



			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(250, 112, 153, 47);
		panel.add(lblNewLabel);
		JLabel jla = new JLabel();
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setBounds(0, 3, 664, 474);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		panel.add(jla);

		//修改密码
		changePawd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				chPawds ch = new chPawds();
				setVisible(false);
				ch.setVisible(true);

			}
		});

		//查询
		query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				query q = new query();
				q.setVisible(true);
			}
		});
	}
}
