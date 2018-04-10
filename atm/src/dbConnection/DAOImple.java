package dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import view.newUser;

public class DAOImple implements DAOInter {

	// 针对admin的操作

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
			throw new Exception("按ID查询失败");
		} finally {
			dbc.close();
		}

		return admin;

	}

	// 针对user表的操作

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

				user.setLose(rs.getBoolean(5));
				user.setFrozen(rs.getBoolean(6));
				user.setDelete(rs.getBoolean(7));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("按ID查询失败");
		}

		return user;

	}

	// 冻结用户
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
			throw new Exception("更新失败");
		} finally {
			dbc.close();
		}

	}

	// 解冻用户
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

	// 开户

	public void newAcount(User user, UserMsg uMsg) throws Exception {
		String sql1 = "insert into user(userNo,userpawd,be_bank,isLose,isFrozen,isDelete,cusNo) values(?,?,?,?,?,?,?)";
		String sql2 = "insert into user_msg values(?,?,?,?)";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		conDatabase dbc = null;

		try {

			dbc = new conDatabase();
			pstmt = dbc.getConnection().prepareStatement(sql1);
			pstmt2 = dbc.getConnection().prepareStatement(sql2);

			pstmt.setString(1, user.getUserNo());
			pstmt.setString(2, user.getUserPawd());
			pstmt.setString(3, user.getBe_bank());
			pstmt.setBoolean(4, user.isLose());
			pstmt.setBoolean(5, user.isFrozen());
			pstmt.setBoolean(6, user.isDelete());
			pstmt.setString(7, user.getCusNo());

			pstmt2.setString(1, uMsg.getCusNo());
			pstmt2.setString(2, uMsg.getUserName());
			pstmt2.setString(3, uMsg.getIDNo());
			pstmt2.setString(4, uMsg.getUserPhone());

			pstmt2.executeUpdate();
			pstmt.executeUpdate();

			pstmt.close();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			dbc.close();
		}

	}

	// 修改密码
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

	// 注销
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

	// 挂失

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

	// 解除挂失

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

	//查询15天内的数据
	public ArrayList<Record> queryHalfMonth(String userNo) throws Exception {

		String sql = "select re_money,re_time ,re_type from record where DATE_SUB(CURDATE() ,INTERVAL  15 DAY) <= DATE(re_time) AND userNo = ?";
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
				re.setMoney(rs.getInt(1));
				re.setTime(rs.getTimestamp(2));

				re.setRe_type(rs.getString(3));

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
	//查询半年内的数据
	public ArrayList<Record> queryHalfYear(String userNo) throws Exception {

		String sql = "select re_money,re_time ,re_type from record where DATE_SUB(CURDATE() ,INTERVAL  6 MONTH) <= DATE(re_time) AND userNo = ?";
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
				re.setMoney(rs.getInt(1));
				re.setTime(rs.getTimestamp(2));

				re.setRe_type(rs.getString(3));

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

	//查询一年内的数据
	public ArrayList<Record> queryYear(String userNo) throws Exception {

		String sql = "select re_money,re_time ,re_type from record where DATE_SUB(CURDATE() ,INTERVAL  1 YEAR) <= DATE(re_time) AND userNo = ?";
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
				re.setMoney(rs.getInt(1));
				re.setTime(rs.getTimestamp(2));

				re.setRe_type(rs.getString(3));

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

}
