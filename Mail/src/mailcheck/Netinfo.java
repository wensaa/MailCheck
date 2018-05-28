package mailcheck;

import javax.swing.JLabel;

public class Netinfo {
	static boolean bb = true;
	String adslname;
	String username;
	String password;
	int wait;
	JLabel state;
	JLabel nowip;
	public String getAdslname() {
		return adslname;
	}
	public void setAdslname(String adslname) {
		this.adslname = adslname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getWait() {
		return wait;
	}
	public void setWait(int wait) {
		this.wait = wait;
	}
	public JLabel getState() {
		return state;
	}
	public void setState(JLabel state) {
		this.state = state;
	}
	public JLabel getNowip() {
		return nowip;
	}
	public void setNowip(JLabel nowip) {
		this.nowip = nowip;
	}
}
