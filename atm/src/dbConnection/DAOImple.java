package dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import view.newUser;

public class DAOImple implements DAOInter {

	// ���admin�Ĳ���

	public Admin queryAdminById(String adminNo) throws Exception {

		Admin admin = null;
		String sql = "select * from admin where adminNo = ?";
		PreparedStatement pstmt = null;
		conDatabase dbc = null;

		try {
			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, adminNo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setAdminNo(rs.getString(1));
				admin.setAdminPawd(rs.getString(2));
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			throw new Exception("��ID��ѯʧ��");
		} finally {
			dbc.close();
		}

		return admin;

	}

	// ���user��Ĳ���

	public User queryUserById(String userNo) throws Exception {

		User user = null;
		String sql = "select * from user where userNo = ?";
		PreparedStatement pstmt = null;
		conDatabase dbc = null;

		try {
			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);

			pstmt.setString(1, userNo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				user = new User();
				user.setUserNo(rs.getString(1));
				user.setUserPawd(rs.getString(2));
				user.setCusNo(rs.getString(3));
				user.setTotal(rs.getInt(4));
				user.setBe_bank(rs.getString(5));
				user.setOp_time(rs.getTimestamp(6));

				user.setLose(rs.getBoolean(7));
				user.setFrozen(rs.getBoolean(8));
				user.setDelete(rs.getBoolean(9));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("��ID��ѯʧ��");
		}

		return user;

	}

	// �����û�
	public void frozenUser(String userNo) throws Exception {

		String sql = "update user set isFrozen = true where userNo = ? ";
		PreparedStatement pstmt = null;

		conDatabase dbc = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);

			pstmt.setString(1, userNo);
			pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			throw new Exception("����ʧ��");
		} finally {
			dbc.close();
		}

	}

	// �ⶳ�û�
	public void unfrozenUser(String userNo) throws Exception {

		String sql = "update user set isFrozen = false where userNo = ?";
		PreparedStatement pstmt = null;
		conDatabase dbc = null;
		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userNo);
			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbc.close();
		}

	}

	// ����

	public void newAcount(User user) throws Exception {

		String sql1 = "insert into user(userNo,userpawd,be_bank,isLose,isFrozen,isDelete,cusNo) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;

		conDatabase dbc = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql1);

			pstmt.setString(1, user.getUserNo());
			pstmt.setString(2, user.getUserPawd());
			pstmt.setString(3, user.getBe_bank());
			pstmt.setBoolean(4, user.isLose());
			pstmt.setBoolean(5, user.isFrozen());
			pstmt.setBoolean(6, user.isDelete());
			pstmt.setString(7, user.getCusNo());

			pstmt.executeUpdate();

			pstmt.close();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbc.close();
		}

	}

	//�����û���Ϣ
	public void newAccountCusNo(UserMsg uMsg) throws Exception {

		String sql = "insert into user_msg values(?,?,?,?)";

		PreparedStatement pstmt = null;

		conDatabase dbc = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);

			pstmt.setString(1,uMsg.getCusNo() );
			pstmt.setString(2, uMsg.getUserName());
			pstmt.setString(3, uMsg.getIDNo());
			pstmt.setString(4, uMsg.getUserPhone());

			pstmt.executeUpdate();

			pstmt.close();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbc.close();
		}

	}

	// �޸�����
	public void changePawds(String userNo, String pawds) throws Exception {

		String sql = "update user set userpawd = ? where userNo=?";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, pawds);
			pstmt.setString(2, userNo);

			pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbc.close();
		}

	}

	// ע��
	public void deUser(String userNo) throws Exception {

		String sql = "update user set isDelete = true where userNo = ? ";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userNo);

			pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			dbc.close();

		}

	}

	// ��ʧ

	public void lostUser(String userNo) throws Exception {

		String sql = "update user set isLose = true where userNo = ? ";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userNo);

			pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			dbc.close();

		}

	}

	// �����ʧ

	public boolean noLost(String userNo) throws Exception {

		String sql = "update user set isLose = false where userNo = ? ";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);

			pstmt.setString(1, userNo);

			pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			dbc.close();

		}

		return true;

	}

	// ��ѯ15���ڵ�����
	public ArrayList<Record> queryHalfMonth(String userNo) throws Exception {

		String sql = "select * from record where DATE_SUB(CURDATE() ,INTERVAL  15 DAY) <= DATE(re_time) AND userNo = ?";
		ArrayList<Record> records = new ArrayList<Record>();
		conDatabase dbc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Record re = new Record();
				re.setTransNo(rs.getString(1));
				re.setUserNo(rs.getString(2));
				re.setMoney(rs.getInt(3));
				re.setTime(rs.getTimestamp(4));
				re.setRe_type(rs.getString(5));
				re.setTf_tarString(rs.getString(6));

				records.add(re);

			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			dbc.close();

		}

		return records;

	}

	// ��ѯ�����ڵ�����
	public ArrayList<Record> queryHalfYear(String userNo) throws Exception {

		String sql = "select * from record where DATE_SUB(CURDATE() ,INTERVAL  6 MONTH) <= DATE(re_time) AND userNo = ?";
		conDatabase dbc = null;
		ArrayList<Record> records = new ArrayList<Record>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Record re = new Record();
				re.setTransNo(rs.getString(1));
				re.setUserNo(rs.getString(2));
				re.setMoney(rs.getInt(3));

				re.setTime(rs.getTimestamp(4));

				re.setRe_type(rs.getString(5));
				re.setTf_tarString(rs.getString(6));

				records.add(re);

			}

			rs.close();
			pstmt.close();
			dbc.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return records;

	}

	// ��ѯһ���ڵ�����
	public ArrayList<Record> queryYear(String userNo) throws Exception {

		String sql = "select * from record  where DATE_SUB(CURDATE() ,INTERVAL  1 YEAR) <= DATE(re_time) AND userNo = ?";

		ArrayList<Record> records = new ArrayList<Record>();
		conDatabase dbc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				Record re = new Record();

				re.setTransNo(rs.getString(1));
				re.setUserNo(rs.getString(2));
				re.setMoney(rs.getInt(3));
				re.setTime(rs.getTimestamp(4));
				re.setRe_type(rs.getString(5));
				re.setTf_tarString(rs.getString(6));

				records.add(re);

			}

			rs.close();
			pstmt.close();
			dbc.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return records;

	}

	// ��̨���UserMsg�Ĳ���

	public UserMsg queryUserMsgByCusNo(String cusNo) throws Exception {

		UserMsg userMsg = new UserMsg();
		String sql = "select * from user_msg where cusNo = ?";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, cusNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userMsg.setCusNo(rs.getString(1));
				userMsg.setUserName(rs.getString(2));
				userMsg.setIDNo(rs.getString(3));
				userMsg.setUserPhone(rs.getString(4));

			}

			rs.close();
			pstmt.close();
			dbc.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return userMsg;

	}

	//��ѯ�û���Ϣ
	public UserMsg queryUserMsgByCusID(String id) throws Exception{

		UserMsg userMsg = new UserMsg();
		String sql = "select * from user_msg where IDNo = ?";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userMsg.setCusNo(rs.getString(1));
				userMsg.setUserName(rs.getString(2));
				userMsg.setIDNo(rs.getString(3));
				userMsg.setUserPhone(rs.getString(4));

			}else{
				return null;
			}

			rs.close();
			pstmt.close();
			dbc.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return userMsg;

	}


	//ģ����ѯ

	public ArrayList<Record> queryUnclearMethodByUserNo(String userNo) throws Exception{

		String sql = "select * from  record where userNo like  ?";
		String sqlp ="%"+userNo + "%";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Record> records = new ArrayList<Record>();

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
     		pstmt.setString(1, sqlp);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Record re = new Record();
				re.setTransNo(rs.getString(1));
				re.setUserNo(rs.getString(2));
				re.setMoney(rs.getInt(3));
				re.setTime(rs.getTimestamp(4));
				re.setRe_type(rs.getString(5));
				re.setTf_tarString(rs.getString(6));

				records.add(re);

			}

			rs.close();
			pstmt.close();
			dbc.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return records;

	}

	//ģ����ѯ+��ʼʱ��
	public ArrayList<Record> queryUnclearMethodByUserNoAndStime(String userNo,Timestamp timestamp) throws Exception{

		String sql = "select * from record  where   re_time >= ?AND userNo like  ? ";
		String sqlp ="%"+userNo + "%";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Record> records = new ArrayList<Record>();

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);

		    pstmt.setString(1, timestamp.toString());
     		pstmt.setString(2, sqlp);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Record re = new Record();
				re.setTransNo(rs.getString(1));
				re.setUserNo(rs.getString(2));
				re.setMoney(rs.getInt(3));
				re.setTime(rs.getTimestamp(4));
				re.setRe_type(rs.getString(5));
				re.setTf_tarString(rs.getString(6));

				records.add(re);

			}

			rs.close();
			pstmt.close();
			dbc.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return records;

	}

	//ģ����ѯ+��ʼʱ��+����ʱ��

	public ArrayList<Record> queryUnclearMethodByUserNoAndSEtime(String userNo,Timestamp timestamp1,Timestamp timestamp2) throws Exception{


		String sql = "select * from record where   re_time >= ?  AND  re_time <= ? AND userNo like  ? ";
		String sqlp ="%"+userNo + "%";
		conDatabase dbc = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Record> records = new ArrayList<Record>();

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, timestamp1.toString());
			pstmt.setString(2, timestamp2.toString());
     		pstmt.setString(3, sqlp);
			rs = pstmt.executeQuery();
			while(rs.next()) {

				Record re = new Record();
				re.setTransNo(rs.getString(1));
				re.setUserNo(rs.getString(2));
				re.setMoney(rs.getInt(3));
				re.setTime(rs.getTimestamp(4));
				re.setRe_type(rs.getString(5));
				re.setTf_tarString(rs.getString(6));

				records.add(re);

			}

			rs.close();
			pstmt.close();
			dbc.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return records;




	}


	/*�û����ֵĲ���*/
	// ת��
		@Override
		public void transfer(String userNo, float money) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null;
			User user = null;
			conDatabase dbc = null;
			String sql = "update user set total=total+? where userNo=?";
			try {
				dbc = new conDatabase();
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setFloat(1, money);
				pstmt.setString(2, userNo);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbc.close();
			}
		}

		// ���ȡ��
		public void saveMoney(String userNo, float money) {
			PreparedStatement pstmt = null;
			User user = null;
			conDatabase dbc = null;
			String sql = "update user set total=total+? where userNo=?";
			try {
				dbc = new conDatabase();
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setFloat(1, money);
				pstmt.setString(2, userNo);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbc.close();
			}
		}

		// �����(ȡ)���¼
		public void saveRecord(String userNo, float money, String type,String target) {
			PreparedStatement pstmt = null;
			conDatabase dbc = null;
			String sql = "insert into  record(userNo,re_money,re_type,tf_target) values(?,?,?,?)";
			try {
				dbc = new conDatabase();
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setString(1, userNo);
				pstmt.setFloat(2, money);
				/*pstmt.setTimestamp(3, new java.sql.Timestamp(time));*/
				pstmt.setString(3, type);
				pstmt.setString(4, target);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbc.close();
			}
		}
		// ��ѯ���׼�¼	--15��
		public ArrayList<Record> getRecordByHalfMonth(String userNo) throws Exception{
			String sql = "select userNo,re_money,re_time ,re_type,tf_target from record where DATE_SUB(CURDATE() ,INTERVAL  15 DAY) <= DATE(re_time) AND userNo = ?";
			ArrayList<Record> records = new ArrayList<Record>();
			conDatabase dbc = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				dbc = new conDatabase();
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setString(1, userNo);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					Record re = new Record();
					re.setUserNo(rs.getString(1));
					re.setMoney(rs.getFloat(2));
					re.setTime(rs.getTimestamp(3));

					re.setRe_type(rs.getString(4));
					re.setTf_target(rs.getString(5));
					records.add(re);

				}

				pstmt.close();
				rs.close();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {

				dbc.close();
			}
			return records;
		}

		// ��ѯ���׼�¼	--����
		public ArrayList<Record> getRecordByHalfYear(String userNo) throws Exception{
			String sql = "select userNo,re_money,re_time ,re_type,tf_target from record where DATE_SUB(CURDATE() ,INTERVAL  6 MONTH) <= DATE(re_time) AND userNo = ?";
			ArrayList<Record> records = new ArrayList<Record>();
			conDatabase dbc = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				dbc = new conDatabase();
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setString(1, userNo);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					Record re = new Record();
					re.setUserNo(rs.getString(1));
					re.setMoney(rs.getFloat(2));
					re.setTime(rs.getTimestamp(3));

					re.setRe_type(rs.getString(4));
					re.setTf_target(rs.getString(5));
					records.add(re);

				}

				pstmt.close();
				rs.close();

			} catch (Exception e) {
				// TODO: handle exception
			} finally {

				dbc.close();
			}
			return records;
		}

		// ��ѯ���׼�¼	--һ��
		public ArrayList<Record> getRecordByYear(String userNo) throws Exception{
				String sql = "select userNo,re_money,re_time ,re_type,tf_target from record where DATE_SUB(CURDATE() ,INTERVAL  1 YEAR) <= DATE(re_time) AND userNo = ?";
				ArrayList<Record> records = new ArrayList<Record>();
				conDatabase dbc = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					dbc = new conDatabase();
					pstmt = dbc.getConnection().prepareStatement(sql);
					pstmt.setString(1, userNo);
					rs = pstmt.executeQuery();

					while (rs.next()) {

						Record re = new Record();
						re.setUserNo(rs.getString(1));
						re.setMoney(rs.getFloat(2));
						re.setTime(rs.getTimestamp(3));

						re.setRe_type(rs.getString(4));
						re.setTf_target(rs.getString(5));
						records.add(re);

					}

					pstmt.close();
					rs.close();

				} catch (Exception e) {
					// TODO: handle exception
				} finally {

					dbc.close();
				}
				return records;
			}

	   //�鿴�˺��Ƿ��ʧ
		public boolean isDelete(String userNo) throws Exception{
			PreparedStatement pstmt = null;
			conDatabase dbc = null;
			ResultSet rs = null;
			User user = null;
			String sql = "select isFrozen from user where userNo=?";
			try {
				dbc = new conDatabase();
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setString(1, userNo);
				rs = pstmt.executeQuery();
				while(rs.next()){
					user = new User();
					user.setDelete(rs.getBoolean(1));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pstmt.close();
				rs.close();
				dbc.close();
			}
			return user.isDelete();
		}
}
