package dbConnection;

import java.sql.Timestamp;

public class Record {

	private int money;
	private Timestamp time;
	private String re_type;

	public String getRe_type() {
		return re_type;
	}
	public void setRe_type(String re_type) {
		this.re_type = re_type;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}




}
