package pview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;

import dbConnection.DAOFactory;
import dbConnection.DAOInter;
import dbConnection.Record;
import dbConnection.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class queryRecord extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private String head[] = new String[] { "输入对象", "时间", "金额", "消费类型", "输出对象" };;
	private Object[][] data = null;
	private DefaultTableModel tableModel;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	/**
	 * Create the frame.
	 */
	public queryRecord(User user) {
		setTitle("\u67E5\u770B\u4EA4\u6613\u8BB0\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(
				icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);

		JButton halfMonth = new JButton("15\u5929\u5185");
		halfMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		halfMonth.setFont(new Font("宋体", Font.PLAIN, 18));
		halfMonth.setBounds(39, 92, 105, 52);
		contentPane.add(halfMonth);

		JButton halfYear = new JButton("\u534A\u5E74\u5185");
		halfYear.setFont(new Font("宋体", Font.PLAIN, 18));
		halfYear.setBounds(39, 180, 105, 52);
		contentPane.add(halfYear);

		JButton year = new JButton("\u4E00\u5E74\u5185");
		year.setFont(new Font("宋体", Font.PLAIN, 18));
		year.setBounds(39, 275, 105, 52);
		contentPane.add(year);

		JButton exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 14));
		exit.setBounds(579, 389, 72, 34);
		contentPane.add(exit);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(185, 92, 445, 240);
		contentPane.add(scrollPane);
		table = new JTable();

		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPane.setViewportView(table);
		scrollPane.setVisible(false);
		JLabel jla = new JLabel();
		jla.setBounds(0, 3, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		contentPane.add(jla);
		contentPane.setLayout(null);
		contentPane.add(halfMonth);
		contentPane.add(halfYear);
		contentPane.add(year);
		contentPane.add(scrollPane);
		contentPane.add(jla);

		halfMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tableModel = new DefaultTableModel(getRecordByHalfMonth(user), head) {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				table.setModel(tableModel);
				scrollPane.setVisible(true);

			}
		});

		halfYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tableModel = new DefaultTableModel(getRecordByHalfYear(user), head) {

					public boolean isCellEditable(int row, int column) {

						return false;

					}

				};

				table.setModel(tableModel);
				scrollPane.setVisible(true);
			}
		});

		year.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tableModel = new DefaultTableModel(getRecordByYear(user), head) {

					public boolean isCellEditable(int row, int column) {

						return false;

					}

				};

				table.setModel(tableModel);
				scrollPane.setVisible(true);
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				query q= new query(user);
				q.setVisible(true);

			}
		});

	}

	public Object[][] getRecordByHalfMonth(User user) {
		ArrayList<Record> records = new ArrayList<Record>();
		try {
			records = adminDAO.getRecordByHalfMonth(user.getUserNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!records.isEmpty()) {
			data = new Object[records.size()][head.length];

			for (int i = 0; i < records.size(); i++) {
				for (int j = 0; j < head.length; j++) {
					data[i][0] = records.get(i).getUserNo();
					data[i][1] = records.get(i).getTime();
					data[i][2] = records.get(i).getMoney();
					data[i][3] = records.get(i).getRe_type();
					data[i][4] = records.get(i).getTf_target();
				}
			}

		}
		return data;
	}

	public Object[][] getRecordByHalfYear(User user) {
		ArrayList<Record> records = new ArrayList<Record>();
		try {
			records = adminDAO.getRecordByHalfYear(user.getUserNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!records.isEmpty()) {
			data = new Object[records.size()][head.length];

			for (int i = 0; i < records.size(); i++) {
				for (int j = 0; j < head.length; j++) {
					data[i][0] = records.get(i).getUserNo();
					data[i][1] = records.get(i).getTime();
					data[i][2] = records.get(i).getMoney();
					data[i][3] = records.get(i).getRe_type();
					data[i][4] = records.get(i).getTf_target();
				}
			}

		}
		return data;
	}

	public Object[][] getRecordByYear(User user) {
		ArrayList<Record> records = new ArrayList<Record>();
		try {
			records = adminDAO.getRecordByYear(user.getUserNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!records.isEmpty()) {
			data = new Object[records.size()][head.length];

			for (int i = 0; i < records.size(); i++) {
				for (int j = 0; j < head.length; j++) {
					data[i][0] = records.get(i).getUserNo();
					data[i][1] = records.get(i).getTime();
					data[i][2] = records.get(i).getMoney();
					data[i][3] = records.get(i).getRe_type();
					data[i][4] = records.get(i).getTf_target();
				}
			}

		}
		return data;
	}

}
