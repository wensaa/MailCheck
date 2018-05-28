package mailcheck;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Session;
import javax.swing.JLabel;
public class mailLog  { 

	public  void startup(String[] str,JLabel LBjindu,Netinfo netinfo){
	    //public static void main(String[] args) {
	    	//UImail mui=new UImail();
	    	//mui.setVisible(true);
	        Properties props = new Properties();
	        //props.setProperty("mail.debug", "true");
	        final String smtpPort = "465";	         
	        props.setProperty("mail.smtp.port", smtpPort);
	        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
	       // props.setProperty("mail.smtp.socketFactory.port", smtpPort);
	        Session session = Session.getDefaultInstance(props);
	        //session.setDebug(true);
	    	InputStreamReader reader = null;	    	
	    	String[] strss = null;
			try {
				
				File fle=new File(str[0]);
				if(fle.length()>5300000){}else{
					System.out.println("++++++++++"+fle.length());
				reader = new InputStreamReader(new FileInputStream(fle));			
				char[] cbuf = new char[307200];//5300000不可以超过10m
				try {
					 ArrayList<mailthread> arrayList=new ArrayList<>() ;//进度数组
					BufferedWriter writer = new BufferedWriter(new FileWriter(str[1]+"\\输出结果.txt",true));
					int count = reader.read(cbuf);
					String strs=new String(cbuf,0,count);
					 strss=strs.split("]");
					System.out.println(strss[0]+"+++++"+strss.length);//读取总人数
					int len=strss.length;
					int m=0;//复制起点				
					while(m<len&&len<5000){						
						if(m>len-100){
							String[] stgroup=new String[len-m];							
							System.arraycopy(strss, m, stgroup, 0, len-m);
						mailthread mailtr=new mailthread( session, stgroup,writer,netinfo);
						System.out.println("+++++++++++++++++++1");
						arrayList.add(mailtr);
						mailtr.start();
						}else{
						String[]  stgroup=new String[100];
						System.arraycopy(strss, m, stgroup, 0, 100);
						mailthread mailtr=new mailthread( session, stgroup,writer,netinfo);
						System.out.println("+++++++++++++++++++1");
						arrayList.add(mailtr);
						mailtr.start();
						}m=m+100;
					}//分线程
					juncheck jindu=new juncheck(strss.length,arrayList,LBjindu);
					jindu.start();//进度检查
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}          	         	          
	    }   

}

