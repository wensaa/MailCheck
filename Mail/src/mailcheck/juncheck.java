package mailcheck;

import java.util.ArrayList;

import javax.swing.JLabel;

public class juncheck extends Thread {
	int zongjd;//总进度
	 ArrayList<mailthread> arrayList;
	 JLabel JL;
	public juncheck(int a, ArrayList<mailthread> arrayList,JLabel JL) {
		this.zongjd=a;
		this.arrayList=arrayList;
		this.JL=JL;
	}
public void run() {
	int m=0;
	while(m<zongjd) {
		m=0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<arrayList.size();i++) {
			 if(Netinfo.bb=true) {
			m=m+arrayList.get(i).Getm();
			 }
			 else {
				 i=i-1;
			 }
		}
		if(Netinfo.bb=true) {
		JL.setText("进度："+zongjd+"/"+m);
		}
	}
	
}
}
