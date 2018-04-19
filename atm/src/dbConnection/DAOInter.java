package dbConnection;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface DAOInter {

	//针对表admin的操作
	public Admin queryAdminById(String adminNo) throws Exception;


	//后台针对表user的操作

	public User queryUserById(String userNo) throws Exception;

	public void frozenUser(String userNo) throws Exception;//冻结用户

	public void unfrozenUser(String userNo) throws Exception;//解冻用户

	public void newAcount(User user)throws Exception;//开户

	public void changePawds(String userNo,String pawds)throws Exception;//修改密码

	public void deUser(String userNo)throws Exception;//删除用户

	public void lostUser(String userNo)throws Exception;//挂失用户

	public boolean noLost(String userNo)throws Exception;//解除挂失

	//后台针对UserMsg表的操作

	public UserMsg queryUserMsgByCusNo(String cusNo) throws Exception;

	public void newAccountCusNo(UserMsg uMsg) throws Exception;

	public UserMsg queryUserMsgByCusID(String id) throws Exception;


	//后台针对record表的操作


	public ArrayList<Record> queryHalfMonth(String userNo) throws Exception; //查询15天内的数据

	public ArrayList<Record> queryHalfYear(String userNo) throws Exception; //查询半年内的数据

	public ArrayList<Record> queryYear(String userNo) throws Exception;//查询一年内的数据

	public ArrayList<Record> queryUnclearMethodByUserNo(String userNo) throws Exception;//模糊查询

	public ArrayList<Record> queryUnclearMethodByUserNoAndStime(String userNo,Timestamp timestamp) throws Exception;//模糊查询+起始时间

	public ArrayList<Record> queryUnclearMethodByUserNoAndSEtime(String userNo,Timestamp timestamp1,Timestamp timestamp2) throws Exception;//模糊查询+起始时间





   public void saveMoney(String userNo,float money) throws Exception;	//存款

	public ArrayList<Record> getRecordByHalfMonth(String userNo) throws Exception;		//查询交易记录 -15天

	public ArrayList<Record> getRecordByHalfYear(String userNo) throws Exception;		//查询交易记录 -半年

	public ArrayList<Record> getRecordByYear(String userNo) throws Exception;			//查询交易记录 -一年

	public void saveRecord(String userNo,float money,String type,String target) throws Exception;	//插入存款记录

	/*public void updateSaveRecord(String userNo,float money, long time) throws Exception;		//更新存款记录
*/
	public void transfer(String userNo,float money)throws Exception;	//转账


	public boolean isDelete(String userNo) throws Exception;	 //查看账号是否挂失



}
