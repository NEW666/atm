package dbConnection;

public interface DAOInter {

	//��Ա�admin�Ĳ���
	public Admin queryAdminById(String adminNo) throws Exception;


	//��Ա�user�Ĳ���

	public User queryUserById(String userNo) throws Exception;



}
