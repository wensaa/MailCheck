package mailcheck;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.mail.Session;
import javax.mail.URLName;

public class mailthread extends Thread  {
	int m=0;//分进度
	Session session;
	String[] strmail;
	BufferedWriter writer; 
	Netinfo netinfo;
	public mailthread(Session session,String[] strmail,BufferedWriter writer,Netinfo netinfo){
		this.strmail=strmail;
		this.session=session;
		this.writer=writer;
		this.netinfo=netinfo;
	};
public void run(){
	mailxunhuan login;		
	 URLName urlName=null;
	for(int i=0;i<strmail.length;i++){
		//检查ip
	//System.out.println(i+"+++++++"+	connectIP.getV4IP());
		String[] strp=strmail[i].split("\\[");//分割账户名和密码
		//System.out.println("+++++++++"+strp[0]+"+++++"+strp[1]);
		urlName=new URLName("smtp","smtp.163.com",465,"",strp[0],strp[1]);  
       //Session session=Session.getInstance(new Properties());  
       login=new mailxunhuan(session,urlName, writer,netinfo);
       if(Netinfo.bb=true) {
       try {
    	   login.login(urlName);
		//System.out.println("登陆:"+i+"++++"++"+++++"+strp[0]+"+++++"+strp[1]);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       m=i;
       }
		else {
			i=i-1;
		}
	}	
}
public int Getm() {
	return m;
}
}
