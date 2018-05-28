package mailcheck;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.URLName;

import com.sun.mail.smtp.SMTPTransport;

public class mailxunhuan extends SMTPTransport {
	BufferedWriter writer; 
	Netinfo netinfo;
	ADSLip adsl;
	public mailxunhuan(Session session, URLName urlname,BufferedWriter writer,Netinfo netinfo) {
		super(session, urlname);
		this.writer=writer;
		this.netinfo=netinfo;
		// TODO Auto-generated constructor stub
	}
	  public boolean login(URLName url) throws IOException{  
	        boolean login=false;  
	        try { 
	        	//System.out.println("+++++++++++++++"); 
	            login=  this.protocolConnect(url.getHost(), url.getPort(), url.getUsername(),url.getPassword());
	            //System.out.println("登陆:"+login);
		        if(login){		    		
		    		try {		    			
		    			writer.write("{"+url.getUsername()+"|"+url.getPassword()+"|成功}");
		    			writer.flush();
		    		} catch (IOException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		        }
	            this.close();	            	            
	        } catch (MessagingException e) {
	        	//切换ip且输出登陆出错结果
	        	//获取这个数据必须加锁
	        	
	        	//String[] strip=ChangeIP.Getips().split(":");
	        	//ChangeIP.changeip(strip);

	        	System.out.println("登录出错");
	        	try {
					writer.write("{"+url.getUsername()+"|"+url.getPassword()+"|出错}");
					writer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	if(Netinfo.bb) {
	        		 adsl=new ADSLip(netinfo.getAdslname(), netinfo.getUsername(), netinfo.getPassword(), netinfo.getWait(), netinfo.getState(), netinfo.getNowip());
	        		 adsl.run();
	        	}
	        	
	            login=false;  
	        }  	  
	        return login;  
	    }  	      	      
}
