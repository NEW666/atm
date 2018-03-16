package dbConnection;

public interface DAOInter {

	//针对表admin的操作
	public Admin queryAdminById(String adminNo) throws Exception;


	//针对表user的操作

	public User queryUserById(String userNo) throws Exception;



}
