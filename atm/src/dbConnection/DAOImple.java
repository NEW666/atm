package dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			pstmt= dbc.getConnection ().prepareStatement(sql);

			pstmt.setString(1, userNo);

			ResultSet rs = pstmt.executeQuery();


			if (rs.next()) {



				user = new User();
				user.setUserNo(rs.getString(1));
				user.setUserPawd(rs.getString(2));
				user.setName(rs.getString(3));
				user.setSave(rs.getString(4));
				user.setStime(rs.getString(5));

				user.setWidthdraw(rs.getString(6));
				user.setWtime(rs.getString(7));
				user.setTransfer(rs.getString(8));
				user.setTftime(rs.getString(9));

				user.setTotal(rs.getString(10));
				user.setIsLose(rs.getString(11));
				user.setIsFrozen(rs.getString(12));
				user.setIsDelete(rs.getString(13));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("��ID��ѯʧ��");
		}

		return user;

	}

}
