package dbConnection;

import java.sql.Timestamp;

public class Record {

	private float money;
	private Timestamp time;
	private String re_type;
	private String userNo;
	private String tf_tarString;
	private String transNo;
	private String tf_target;	//交易对象

	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getTf_tarString() {
		return tf_tarString;
	}
	public void setTf_tarString(String tf_tarString) {
		this.tf_tarString = tf_tarString;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getRe_type() {
		return re_type;
	}
	public void setRe_type(String re_type) {
		this.re_type = re_type;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getTf_target() {
		return tf_target;
	}
	public void setTf_target(String tf_target) {
		this.tf_target = tf_target;
	}




}
