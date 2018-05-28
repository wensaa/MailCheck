package changgeIP;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.URLName;
import mailcheck.mailxunhuan;

public class MailTest {
	  public static void main(String[] args) throws Exception {
		 //发送邮件
		 //test.sendmail();
		 //通过代理发送邮件
		 MailTest test=new MailTest();
		test.sendMailByProxy();
		}
		  private void sendMailByProxy()throws Exception{
		  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		//      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		 //设置代理服务器
		  Properties props = System.getProperties();
		 props.setProperty("proxySet", "true");
		  props.setProperty("socksProxyHost", "195.190.124.202");
		  props.setProperty("socksProxyPort", "8080");
		  props.setProperty("mail.smtp.host", "smtp.163.com");
		 
		  //props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		  props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		  props.setProperty("mail.smtp.socketFactory.fallback", "false");
		  props.setProperty("mail.smtp.port", "465");
		  props.setProperty("mail.smtp.socketFactory.port", "465");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.debug", "true");
		  //props.put("mail.store.protocol", "pop3");
		  props.put("mail.transport.protocol", "smtp");
		  final String username = "15616190645@163.com";
		  final String password = "5201314tvxq";
		 //使用验证
		  Session session = Session.getDefaultInstance(props,
				    new Authenticator() {
		   protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		  return new javax.mail.PasswordAuthentication(username, password); }
		  });
		  URLName urlName=new URLName("smtp","smtp.163.com",465,"","15616190645@163.com","5201314tvxq");  
		  mailxunhuan mail=new mailxunhuan(session, urlName, null);
		  mail.login(urlName);

		  }
		   class SmtpAuth extends javax.mail.Authenticator {
			   private String user, password;
			   public void getuserinfo(String getuser, String getpassword) {
			   user = getuser;
			   password = getpassword;
			   }
			   protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
			   {
				  return new javax.mail.PasswordAuthentication(user, password);
				  }
			   }
}
