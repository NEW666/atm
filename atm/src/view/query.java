package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.SwingConstants;

public class query extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JScrollPane scrollPane;
	private JTable table;
	private String head[] =  new String[] { "账号","时间","金额" ,"消费类型","消费对象账号"};;
	private Object[][] data = null;
	private DefaultTableModel tableModel;
	DAOInter adminDAO = DAOFactory.getUserDAOFactory();
	private JButton query;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	final JXDatePicker datepick = new JXDatePicker();
	final JXDatePicker datepick2 = new JXDatePicker();
	private Timestamp timestamp = new Timestamp(new Date().getTime()); ;
	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Create the frame.
	 */

	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				query frame = new query();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
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

		JLabel aLable = new JLabel("\u8D26\u53F7");
		aLable.setFont(new Font("宋体", Font.PLAIN, 20));
		aLable.setBounds(73, 91, 57, 28);
		contentPane.add(aLable);

		account = new JTextField();
		account.setBounds(127, 92, 169, 28);
		contentPane.add(account);
		account.setColumns(10);

		JButton exit = new JButton("\u9000\u51FA");
		exit.setFont(new Font("宋体", Font.PLAIN, 12));
		exit.setBounds(585, 430, 60, 29);
		contentPane.add(exit);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 219, 421, 240);
		contentPane.add(scrollPane);
		table = new JTable();

		table.setBorder(new LineBorder(new Color(0, 0, 0)));


		scrollPane.setViewportView(table);
		scrollPane.setVisible(false);
		contentPane.setLayout(null);
		contentPane.add(aLable);
		contentPane.add(account);
		contentPane.add(scrollPane);

		query = new JButton("\u67E5\u8BE2");
		query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		query.setFont(new Font("宋体", Font.PLAIN, 13));
		query.setBounds(382, 160, 69, 33);
		contentPane.add(query);

		lblNewLabel = new JLabel("\u8D77\u59CB\u65F6\u95F4");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(73, 129, 77, 27);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("\u7ED3\u675F\u65F6\u95F4");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(234, 131, 77, 25);
		contentPane.add(lblNewLabel_1);
		datepick.getEditor().setFont(new Font("宋体", Font.PLAIN, 14));
		datepick.getEditor().setHorizontalAlignment(SwingConstants.CENTER);
		dateFormater.format(timestamp);
		datepick.setFormats(dateFormater);
		datepick.setBounds(73, 163, 120, 28);
		contentPane.add(datepick);

		datepick2.getEditor().setFont(new Font("宋体", Font.PLAIN, 14));
		datepick2.getEditor().setHorizontalAlignment(SwingConstants.CENTER);
		dateFormater.format(timestamp);
		datepick2.setFormats(dateFormater);
		datepick2.setBounds(234, 163, 120, 28);
		contentPane.add(datepick2);


				 // 设置 date日期
		JLabel jla = new JLabel();
		jla.setFont(new Font("宋体", Font.PLAIN, 18));
		jla.setBounds(0, 3, 664, 474);
		jla.setBackground(Color.LIGHT_GRAY);
		jla.setHorizontalAlignment(0);
		jla.setIcon(icon);
		contentPane.add(jla);

		tableModel = new DefaultTableModel(null , head) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);
		scrollPane.setVisible(true);

		query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ArrayList<Record> arrayList = new ArrayList<Record>();
				try {
					arrayList = adminDAO.queryUnclearMethodByUserNo(account.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				if(account.getText().trim().equals("")){

					JOptionPane.showMessageDialog(null, "账号不可为空");
					tableModel = new DefaultTableModel(null , head) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					table.setModel(tableModel);
					scrollPane.setVisible(true);


				}else if(arrayList.size() == 0){


					JOptionPane.showMessageDialog(null, "没有匹配的账号");
					tableModel = new DefaultTableModel(null , head) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					table.setModel(tableModel);
					scrollPane.setVisible(true);

				}else if(!account.getText().trim().equals("")&&datepick.getDate()==null&&datepick2.getDate()==null){

					if(queryUnClear() == null){

						tableModel = new DefaultTableModel(null , head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						table.setModel(tableModel);
						scrollPane.setVisible(true);
					}else {
						tableModel = new DefaultTableModel(queryUnClear() , head) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};

					table.setModel(tableModel);
					scrollPane.setVisible(true);
					}



				}else if(!account.getText().trim().equals("") && datepick.getDate() != null&&datepick2.getDate()==null){


					if(queryUnClearAndStime() == null){

						tableModel = new DefaultTableModel(null , head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						table.setModel(tableModel);
						scrollPane.setVisible(true);



					}else if(queryUnClearAndStime() != null){

						tableModel = new DefaultTableModel(queryUnClearAndStime(), head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						table.setModel(tableModel);
						scrollPane.setVisible(true);

					}



				}else if(!account.getText().trim().equals("")  && datepick2.getDate() !=null && datepick.getDate() == null){

					System.out.println("a");

					if(queryUnClearAndEtime() == null){

						tableModel = new DefaultTableModel(null , head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						table.setModel(tableModel);
						scrollPane.setVisible(true);



					}else if(queryUnClearAndEtime() != null){

						tableModel = new DefaultTableModel(queryUnClearAndEtime(), head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						table.setModel(tableModel);
						scrollPane.setVisible(true);

					}



				}else if(datepick.getDate()!=null&&datepick2.getDate()!=null&& datepick.getDate().getTime() > datepick2.getDate().getTime() ){

					JOptionPane.showMessageDialog(null, "起始时间不可大于结束时间");
					tableModel = new DefaultTableModel(null, head) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};

					table.setModel(tableModel);
					scrollPane.setVisible(true);

				}else if(!account.getText().trim().equals("") && datepick.getDate() != null&&datepick2.getDate() !=null &&datepick.getDate().compareTo(datepick2.getDate()) !=0){


					if(queryUnClearAndSEtime()== null){

						tableModel = new DefaultTableModel(null , head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						table.setModel(tableModel);
						scrollPane.setVisible(true);

					}else{

						tableModel = new DefaultTableModel(queryUnClearAndSEtime() , head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						table.setModel(tableModel);
						scrollPane.setVisible(true);
					}
				}else if(!account.getText().trim().equals("") && datepick.getDate() != null&&datepick2.getDate() !=null &&datepick.getDate().compareTo(datepick2.getDate()) ==0 ){


					System.out.println(queryUnClearAndOtime()== null);
					System.out.println(queryUnClearAndOtime()!= null);

					if(queryUnClearAndOtime()== null){

						tableModel = new DefaultTableModel(null , head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						table.setModel(tableModel);
						scrollPane.setVisible(true);

					}else{
						tableModel = new DefaultTableModel(queryUnClearAndOtime() , head) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						table.setModel(tableModel);
						scrollPane.setVisible(true);
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

				if (!account.getText().matches("^[0-9]{1,13}$")
						&& !"".trim().equals(account.getText())) {
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



	public Object[][] queryUnClear(){
		ArrayList<Record> records =new ArrayList<Record>();
		try {

			records = adminDAO.queryUnclearMethodByUserNo(account.getText().trim());

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
					data[i][4] = records.get(i).getTf_tarString();

				}
			}

			return data;
		}

		return null;

	}



	public Object[][] queryUnClearAndStime(){
		ArrayList<Record> records =new ArrayList<Record>();

		final Timestamp t = new Timestamp(datepick.getDate().getTime());

		try {
			records = adminDAO.queryUnclearMethodByUserNoAndStime(account.getText(),t );
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
					data[i][4] = records.get(i).getTf_tarString();

				}
			}

			return data;

		}

		return null;

	}




	public Object[][] queryUnClearAndSEtime(){
		ArrayList<Record> records =new ArrayList<Record>();
		final Timestamp t = new Timestamp(datepick.getDate().getTime());
		final Timestamp t2 = new Timestamp(datepick2.getDate().getTime());

		try {
			records = adminDAO.queryUnclearMethodByUserNoAndSEtime(account.getText(),t ,t2);
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
					data[i][4] = records.get(i).getTf_tarString();

				}
			}

			return data;
		}

		return null;



	}


	public Object[][] queryUnClearAndOtime(){
		ArrayList<Record> records =new ArrayList<Record>();
		final Timestamp t = new Timestamp(datepick.getDate().getTime());

		try {
			records = adminDAO.queryUnclearMethodByUserNoAndOtime(account.getText(),t);
			System.out.println(records);
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
					data[i][4] = records.get(i).getTf_tarString();

				}
			}

			return data;
		}

	  return null;
	}



	public Object[][] queryUnClearAndEtime(){
		ArrayList<Record> records =new ArrayList<Record>();
		final Timestamp t = new Timestamp(datepick2.getDate().getTime());

		try {
			records = adminDAO.queryUnclearMethodByUserNoAndEtime(account.getText(),t);
			System.out.println(records);
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
					data[i][4] = records.get(i).getTf_tarString();

				}
			}

			return data;
		}

	  return null;
	}



}
