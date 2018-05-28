package changgeIP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;
import javax.swing.JLabel;

public class ADSLip {
	/**
	 * ִ��CMD����,������String�ַ���
	 */
	public static String executeCmd(String strCmd) throws Exception {
		Process p = Runtime.getRuntime().exec("cmd /c " + strCmd);
		StringBuilder sbCmd = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "GB2312"));
		String line;
		while ((line = br.readLine()) != null) {
			sbCmd.append(line + "\n");
		}
		return sbCmd.toString();

	}

	/**
	 * ����ADSL
	 */
	public static String connAdsl(String adslTitle, String adslName, String adslPass) throws Exception {
		System.out.println("���ڽ�������.");
		String adslCmd = "rasdial " + adslTitle + " " + adslName + " " + adslPass;
		String tempCmd = executeCmd(adslCmd);
		// �ж��Ƿ����ӳɹ�
		if (tempCmd.indexOf("������") > 0) {
			System.out.println("�ѳɹ���������.");
			return "������";
		} else {
			System.err.println(tempCmd);
			System.err.println("��������ʧ��");
			return "��������ʧ��";
		}
	}

	/**
	 * �Ͽ�ADSL
	 */
	public static String cutAdsl(String adslTitle) throws Exception {
		String cutAdsl = "rasdial " + adslTitle + " /disconnect";
		String result = executeCmd(cutAdsl);

		if (result.indexOf("û������") != -1) {
			System.err.println(adslTitle + "���Ӳ�����!");
			return "���Ӳ�����";
		} else {
			System.out.println("�����ѶϿ�");
			return "�����ѶϿ�";
		}
	}
	/**
   * ���������Ƿ�����
   */
	
	 public static String isConnect(){
	        String connect = "�Ͽ�";
	        Runtime runtime = Runtime.getRuntime();
	        Process process;
	        try {
	            process = runtime.exec("ping " + "www.baidu.com");
	            InputStream is = process.getInputStream(); 
	            InputStreamReader isr = new InputStreamReader(is); 
	            BufferedReader br = new BufferedReader(isr); 
	            String line = null; 
	            StringBuffer sb = new StringBuffer(); 
	            while ((line = br.readLine()) != null) { 
	                sb.append(line); 
	            } 
	            System.out.println("����ֵΪ:"+sb);  
	            is.close(); 
	            isr.close(); 
	            br.close(); 
	 
	            if (null != sb && !sb.toString().equals("")) { 
	                if (sb.toString().indexOf("TTL") > 0) { 
	                    // ���糩ͨ  
	                    connect = "����";
	                } else { 
	                    // ���粻��ͨ  
	                    connect = "�Ͽ�";
	                } 
	            } 
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        return connect;
	    }
	 public void changeIp(String username,String password,int wait,JLabel state,JLabel nowip) throws Exception{
//		    Scanner sc = new Scanner(System.in);
//		    System.out.println("�����������:"); // ���������ӵ�����
//		    String name = sc.next();
//		    System.out.println("����˻�:");
//		    String username = sc.next();
//		    System.out.println("�������:");
//		    String password = sc.next();
//		    System.out.println("����ʱ��(��λ����1�����1000����):");
//		    int wait = sc.nextInt();
		 	String strstate;
		    while(true){		    
		      strstate=connAdsl("�������", username, password);
		      state.setText(strstate);
		      Thread.sleep(wait);
		      strstate=cutAdsl("�������");
		      state.setText(strstate);
		      Thread.sleep(wait);
		      //����������һ���µ�IP
		      strstate=connAdsl("�������", username, password);
		      state.setText(strstate);
		      nowip.setText(getV4IP());
		    }
		    }
	 //���Դ���
//	public static void main(String[] args) throws InterruptedException,
//	  Exception { Scanner sc = new Scanner(System.in);
//	  System.out.println("�����������:"); //���������ӵ�����
//	  String name = sc.next();
//	  System.out.println("����˻�:"); 
//	  String username = sc.next();
//	  System.out.println("�������:"); 
//	  String password = sc.next(); 
//	  String adsl= "�������";
//		  while(true){   
//			  boolean connect = isConnect();
//	          Thread.sleep(100000);//��λ����,�����õ���100��.�Լ����������
//	          if(!connect){
//	        	  System.out.println("������,�������²���");
//	              connAdsl(name,username,password);
//	          }
//		  }
//	  
//	}
    public static String getV4IP(){
    	String ip = "";
    	String chinaz = "http://ip.chinaz.com";
    	
    	StringBuilder inputLine = new StringBuilder();
    	String read = "";
    	URL url = null;
    	HttpURLConnection urlConnection = null;
    	BufferedReader in = null;
    	try {
    		url = new URL(chinaz);
    		urlConnection = (HttpURLConnection) url.openConnection();
    	    in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
    		while((read=in.readLine())!=null){
    			inputLine.append(read+"\r\n");
    		}
    		//System.out.println(inputLine.toString());
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}finally{
    		if(in!=null){
    			try {
    				in.close();
    			} catch (IOException e) {
    				// `
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	
    	Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
    	Matcher m = p.matcher(inputLine.toString());
    	if(m.find()){
    		String ipstr = m.group(1);
    		ip = ipstr;
    		//System.out.println(ipstr);
    	}
    	return ip;


    }
}
