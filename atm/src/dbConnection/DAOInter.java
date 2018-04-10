package dbConnection;

import java.util.ArrayList;

public interface DAOInter {

	//��Ա�admin�Ĳ���
	public Admin queryAdminById(String adminNo) throws Exception;


	//��̨��Ա�user�Ĳ���

	public User queryUserById(String userNo) throws Exception;

	public void frozenUser(String userNo) throws Exception;//�����û�

	public void unfrozenUser(String userNo) throws Exception;//�ⶳ�û�

	public void newAcount(User user, UserMsg uMsg)throws Exception;//����

	public void changePawds(String userNo,String pawds)throws Exception;//�޸�����

	public void deUser(String userNo)throws Exception;//ɾ���û�

	public void lostUser(String userNo)throws Exception;//��ʧ�û�

	public boolean noLost(String userNo)throws Exception;//�����ʧ


	//��̨���UserMsg��Ĳ���


	//��̨���record��Ĳ���


	public ArrayList<Record> queryHalfMonth(String userNo) throws Exception; //��ѯ15���ڵ�����

	public ArrayList<Record> queryHalfYear(String userNo) throws Exception; //��ѯ�����ڵ�����

	public ArrayList<Record> queryYear(String userNo) throws Exception;//��ѯһ���ڵ�����




}
