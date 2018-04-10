package view;

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

public class query extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table;
	private String head[] =  new String[] { "时间","金额" ,"消费类型"};;
	private Object[][] data = null;
	private DefaultTableModel tableModel;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();

	/**
	 * Create the frame.
	 */
	public query() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon("./image/atm.png");
		icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),
				icon.getIconHeight(), Image.SCALE_DEFAULT));
		contentPane.setLayout(null);

		JButton halfMonth = new JButton("15\u5929\u5185");
		halfMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		halfMonth.setFont(new Font("宋体", Font.PLAIN, 18));
		halfMonth.setBounds(39, 181, 105, 52);
		contentPane.add(halfMonth);

		JButton halfYear = new JButton("\u534A\u5E74\u5185");
		halfYear.setFont(new Font("宋体", Font.PLAIN, 18));
		halfYear.setBounds(39, 269, 105, 52);
		contentPane.add(halfYear);

		JButton year = new JButton("\u4E00\u5E74\u5185");
		year.setFont(new Font("宋体", Font.PLAIN, 18));
		year.setBounds(39, 360, 105, 52);
		contentPane.add(year);

		JLabel aLable = new JLabel("\u8D26\u53F7");
		aLable.setFont(new Font("宋体", Font.PLAIN, 20));
		aLable.setBounds(10, 87, 57, 28);
		contentPane.add(aLable);

		textField = new JTextField();
		textField.setBounds(39, 125, 169, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 12));
		exit.setBounds(585, 430, 60, 29);
		contentPane.add(exit);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 180, 384, 240);
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
		contentPane.add(aLable);
		contentPane.add(textField);
		contentPane.add(scrollPane);
		contentPane.add(jla);

		halfMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				if(!textField.getText().trim().equals("")){

					tableModel = new DefaultTableModel(queryHalfMonth() , head) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};

					table.setModel(tableModel);
					scrollPane.setVisible(true);

				}else{

					JOptionPane.showMessageDialog(null, "账号不可为空");

				}



			}
		});

		halfYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {



				if(!textField.getText().trim().equals("")){

					tableModel = new DefaultTableModel(queryHalfYear(),head){

						public boolean isCellEditable(int row,int column){

							return false;

						}

					};

					table.setModel(tableModel);
					scrollPane.setVisible(true);

				}else{

					JOptionPane.showMessageDialog(null, "账号不可为空");

				}



			}
		});

		year.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				if(!textField.getText().trim().equals("")){
					tableModel = new DefaultTableModel(queryYear(), head) {

						public boolean isCellEditable(int row, int column) {

							return false;

						}

					};

					table.setModel(tableModel);
					scrollPane.setVisible(true);
				}else{

					JOptionPane.showMessageDialog(null, "账号不可为空");

				}



			}
		});

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (!textField.getText().matches("^[0-9a-zA_Z]+$")
						&& !"".trim().equals(textField.getText())) {
					JOptionPane.showMessageDialog(null, "只可输入数字");

				}

			}

		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				admin ad = new admin();
				ad.setVisible(true);

			}
		});

	}

	public Object[][] queryHalfMonth() {
		ArrayList<Record> records =new ArrayList<Record>();
		try {
			records = adminDAO.queryHalfMonth(textField.getText().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!records.isEmpty()) {
			data = new Object[records.size()][head.length];

			for (int i = 0; i < records.size(); i++) {
				for (int j = 0; j < head.length; j++) {
					data[i][0] = records.get(i).getTime();
					data[i][1] = records.get(i).getMoney();
					data[i][2] = records.get(i).getRe_type();

				}
			}

		}
		return data;
	}
	public Object[][] queryHalfYear(){
		ArrayList<Record> records =new ArrayList<Record>();
		try {
			records = adminDAO.queryHalfYear(textField.getText().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!records.isEmpty()) {
			data = new Object[records.size()][head.length];

			for (int i = 0; i < records.size(); i++) {
				for (int j = 0; j < head.length; j++) {
					data[i][0] = records.get(i).getTime();
					data[i][1] = records.get(i).getMoney();
					data[i][2] = records.get(i).getRe_type();

				}
			}

		}
		return data;
	}

	public Object[][] queryYear(){
		ArrayList<Record> records =new ArrayList<Record>();
		try {
			records = adminDAO.queryYear(textField.getText().trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!records.isEmpty()) {
			data = new Object[records.size()][head.length];

			for (int i = 0; i < records.size(); i++) {
				for (int j = 0; j < head.length; j++) {
					data[i][0] = records.get(i).getTime();
					data[i][1] = records.get(i).getMoney();
					data[i][2] = records.get(i).getRe_type();

				}
			}

		}
		return data;
	}
}
