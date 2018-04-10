package dbConnection;

import java.sql.Timestamp;

public class User {

	private String userNo;
	private String userPawd;
	private String cusNo;
	private int total;
	private String be_bank;
	private Timestamp op_time;

	private boolean isLose;
	private boolean isFrozen;
	private boolean isDelete;
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String ran_account) {
		this.userNo = ran_account;
	}
	public String getUserPawd() {
		return userPawd;
	}
	public void setUserPawd(String userPawd) {
		this.userPawd = userPawd;
	}
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String i) {
		this.cusNo = i;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getBe_bank() {
		return be_bank;
	}
	public void setBe_bank(String be_bank) {
		this.be_bank = be_bank;
	}
	public Timestamp getOp_time() {
		return op_time;
	}
	public void setOp_time(Timestamp timestamp) {
		this.op_time = timestamp;
	}
	public boolean isLose() {
		return isLose;
	}
	public void setLose(boolean isLose) {
		this.isLose = isLose;
	}
	public boolean isFrozen() {
		return isFrozen;
	}
	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}





}
