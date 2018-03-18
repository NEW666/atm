package dbConnection;

public interface DAOInter {

	//��Ա�admin�Ĳ���
	public Admin queryAdminById(String adminNo) throws Exception;


	//��̨��Ա�user�Ĳ���

	public User queryUserById(String userNo) throws Exception;

	public void frozenUser(String userNo) throws Exception;//�����û�

	public void unfrozenUser(String userNo) throws Exception;//�ⶳ�û�

	public void newAcount(User user)throws Exception;//����

	public void changePawds(String userNo,String pawds)throws Exception;//�޸�����

	public void deUser(String userNo)throws Exception;//ɾ���û�

	public void lostUser(String userNo)throws Exception;//��ʧ�û�

	public void noLost(String userNo)throws Exception;//�����ʧ


}
