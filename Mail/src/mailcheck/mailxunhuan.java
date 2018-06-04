package MailCheck;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.URLName;

import com.sun.mail.smtp.SMTPTransport;

public class mailxunhuan extends SMTPTransport {
	private static String key="key";
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
	            //System.out.println("��½:"+login);
		        if(login){		    		
		        	this.WriteResult("�ɹ�");
		        }
	            this.close();	            	            
	        } catch (MessagingException e) {
	        	//�л�ip�������½������
	        	//��ȡ������ݱ������
	        	
	        	//String[] strip=ChangeIP.Getips().split(":");
	        	//ChangeIP.changeip(strip);

	        	System.out.println("��¼����");
	        	this.WriteResult("����");
	        	if(Netinfo.bb) {
	        		 adsl=new ADSLip(netinfo.getAdslname(), netinfo.getUsername(), netinfo.getPassword(), netinfo.getWait(), netinfo.getState(), netinfo.getNowip());
	        		 adsl.run();
	        	}
	        	
	            login=false;  
	        }  	  
	        return login;  
	    }  	 
	  public void WriteResult(String result){
		  synchronized (key) {
			  try {
				writer.write("{"+url.getUsername()+"|"+url.getPassword()+"|"+result+"}");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}		  
	  }
}
