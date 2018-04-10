package dbConnection;

import java.util.ArrayList;

public interface DAOInter {

	//针对表admin的操作
	public Admin queryAdminById(String adminNo) throws Exception;


	//后台针对表user的操作

	public User queryUserById(String userNo) throws Exception;

	public void frozenUser(String userNo) throws Exception;//冻结用户

	public void unfrozenUser(String userNo) throws Exception;//解冻用户

	public void newAcount(User user, UserMsg uMsg)throws Exception;//开户

	public void changePawds(String userNo,String pawds)throws Exception;//修改密码

	public void deUser(String userNo)throws Exception;//删除用户

	public void lostUser(String userNo)throws Exception;//挂失用户

	public boolean noLost(String userNo)throws Exception;//解除挂失


	//后台针对UserMsg表的操作


	//后台针对record表的操作


	public ArrayList<Record> queryHalfMonth(String userNo) throws Exception; //查询15天内的数据

	public ArrayList<Record> queryHalfYear(String userNo) throws Exception; //查询半年内的数据

	public ArrayList<Record> queryYear(String userNo) throws Exception;//查询一年内的数据




}
