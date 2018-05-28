package mailcheck;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;

public class ADSLip extends Thread{
	/**
	 * ִ��CMD����,������String�ַ���
	 */
	String adslname;String username;String password;int wait;JLabel state;JLabel nowip;
	public ADSLip(String adslname,String username,String password,int wait,JLabel state,JLabel nowip) {
		this.adslname=adslname;
		this.username=username;
		this.password=password;
		this.wait=wait;
		this.state=state;
		this.nowip=nowip;
	}
	public void run() {
		try {
			if(Netinfo.bb) {
			this.changeIp(adslname, username, password, wait, state, nowip);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	 public void changeIp(String adslname,String username,String password,int wait,JLabel state,JLabel nowip) throws Exception{
		 Netinfo.bb=false;
		 	String strstate;		    	
		    strstate=cutAdsl("��������");
		      state.setText(strstate);
		      Thread.sleep(wait);	   
		 	  state.setText("����״̬����������");
		      strstate=connAdsl("��������", username, password);
		      nowip.setText("IP:"+getV4IP());	
		      System.err.println(getV4IP() + "+++++++++");
		      state.setText("����״̬"+strstate);
     		 Netinfo.bb=true;
		      //Thread.sleep(wait);
		      //����������һ���µ�IP
		      //strstate=connAdsl("��������", username, password);	      	      
		    
		    }

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