package mailcheck;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class UIinfo {
	
	JTextField daorutxt;
	JTextField shuchutxt;
	JLabel jindu;
	JTextField adslname;
	JTextField username;
	JTextField password;
	JTextField wait;
	public UIinfo(	JTextField daorutxt,
	JTextField shuchutxt,
	JLabel jindu,
	JTextField adslname,
	JTextField username,
	JTextField password,
	JTextField wait) {
		this.daorutxt=daorutxt;
		this.shuchutxt=shuchutxt;
		this.adslname=adslname;
		this.jindu=jindu;
		this.username=username;
		this.password=password;
		this.wait=wait;
	}
	public JTextField getDaorutxt() {
		return daorutxt;
	}
	public JTextField getShuchutxt() {
		return shuchutxt;
	}
	public JLabel getJindu() {
		return jindu;
	}
	public JTextField getAdslname() {
		return adslname;
	}
	public JTextField getUsername() {
		return username;
	}
	public JTextField getPassword() {
		return password;
	}
	public JTextField getWait() {
		return wait;
	}


}
