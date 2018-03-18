package dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				user.setIsLose(rs.getBoolean(11));
				user.setIsFrozen(rs.getBoolean(12));
				user.setIsDelete(rs.getBoolean(13));

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("按ID查询失败");
		}

		return user;

	}


	//冻结用户
		public void frozenUser(String userNo) throws Exception{

	       String sql = "update user set isFrozen = true where userNo = ? ";
	       PreparedStatement  pstmt = null;
	       ResultSet rs = null;
	       conDatabase dbc = null;

	       try {


	    	   dbc = new conDatabase();
	    	   pstmt = dbc.getConnection().prepareStatement(sql);
	    	   pstmt.setString(1, userNo);
	    	   pstmt.executeUpdate();
	    	   pstmt.close();
	    	   rs.close();




		} catch (Exception e) {
			throw new Exception("更新失败");
		}finally{
			dbc.close();
		}

		}

    //解冻用户
		public void unfrozenUser(String userNo) throws Exception{



			String  sql = "update user set isFrozen = false where userNo = ?";
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
			}finally{
				dbc.close();
			}




		}

     //开户

		public void newAcount(User user)throws Exception{
			String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = null;
		    conDatabase dbc = null;

		    try {

		    	dbc = new conDatabase();

		    	pstmt = dbc.getConnection().prepareStatement(sql);

				pstmt.setString(1, user.getUserNo());
				pstmt.setString(2, user.getUserPawd());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getSave());
				pstmt.setString(5, user.getStime());
				pstmt.setString(6, user.getWidthdraw());
				pstmt.setString(7, user.getWtime());
				pstmt.setString(8, user.getTransfer());
				pstmt.setString(9, user.getTftime());
				pstmt.setString(10, user.getTotal());

				pstmt.setBoolean(11, user.getIsLose());
				pstmt.setBoolean(12, user.getIsFrozen());
				pstmt.setBoolean(13, user.getIsDelete());
				pstmt.executeUpdate();

				pstmt.close();



			} catch (SQLException e) {
				System.out.println(e);
			}finally{
				dbc.close();
			}




		}

		//修改密码
		public void changePawds(String userNo,String pawds)throws Exception{

			String sql = "update user set userpawd = ? where userNo=?";
			conDatabase dbc = null;
			PreparedStatement pstmt =null;

			try {

				dbc = new  conDatabase();
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setString(1, pawds);
				pstmt.setString(2, userNo);

				pstmt.executeUpdate();

				pstmt.close();


			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				dbc.close();
			}

		}

        //注销
		public void deUser(String userNo)throws Exception{

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
		}finally{

			dbc.close();

		}

		}

		//挂失

		public void lostUser(String userNo)throws Exception{

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
		}finally{

			dbc.close();

		}

		}


}
