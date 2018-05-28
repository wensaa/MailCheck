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
	 * 执行CMD命令,并返回String字符串
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
	 * 连接ADSL
	 */
	public static String connAdsl(String adslTitle, String adslName, String adslPass) throws Exception {
		System.out.println("正在建立连接.");
		String adslCmd = "rasdial " + adslTitle + " " + adslName + " " + adslPass;
		String tempCmd = executeCmd(adslCmd);
		// 判断是否连接成功
		if (tempCmd.indexOf("已连接") > 0) {
			System.out.println("已成功建立连接.");
			return "已连接";
		} else {
			System.err.println(tempCmd);
			System.err.println("建立连接失败");
			return "建立连接失败";
		}
	}

	/**
	 * 断开ADSL
	 */
	public static String cutAdsl(String adslTitle) throws Exception {
		String cutAdsl = "rasdial " + adslTitle + " /disconnect";
		String result = executeCmd(cutAdsl);

		if (result.indexOf("没有连接") != -1) {
			System.err.println(adslTitle + "连接不存在!");
			return "连接不存在";
		} else {
			System.out.println("连接已断开");
			return "连接已断开";
		}
	}
	/**
   * 测试网络是否连接
   */
	
	 public static String isConnect(){
	        String connect = "断开";
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
	            System.out.println("返回值为:"+sb);  
	            is.close(); 
	            isr.close(); 
	            br.close(); 
	 
	            if (null != sb && !sb.toString().equals("")) { 
	                if (sb.toString().indexOf("TTL") > 0) { 
	                    // 网络畅通  
	                    connect = "连接";
	                } else { 
	                    // 网络不畅通  
	                    connect = "断开";
	                } 
	            } 
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        return connect;
	    }
	 public void changeIp(String username,String password,int wait,JLabel state,JLabel nowip) throws Exception{
//		    Scanner sc = new Scanner(System.in);
//		    System.out.println("宽带连接名称:"); // 看你宽带连接的名称
//		    String name = sc.next();
//		    System.out.println("宽带账户:");
//		    String username = sc.next();
//		    System.out.println("宽带密码:");
//		    String password = sc.next();
//		    System.out.println("更换时间(单位毫秒1秒等于1000毫秒):");
//		    int wait = sc.nextInt();
		 	String strstate;
		    while(true){		    
		      strstate=connAdsl("宽带连接", username, password);
		      state.setText(strstate);
		      Thread.sleep(wait);
		      strstate=cutAdsl("宽带连接");
		      state.setText(strstate);
		      Thread.sleep(wait);
		      //再连，分配一个新的IP
		      strstate=connAdsl("宽带连接", username, password);
		      state.setText(strstate);
		      nowip.setText(getV4IP());
		    }
		    }
	 //测试代码
//	public static void main(String[] args) throws InterruptedException,
//	  Exception { Scanner sc = new Scanner(System.in);
//	  System.out.println("宽带连接名称:"); //看你宽带连接的名称
//	  String name = sc.next();
//	  System.out.println("宽带账户:"); 
//	  String username = sc.next();
//	  System.out.println("宽带密码:"); 
//	  String password = sc.next(); 
//	  String adsl= "宽带连接";
//		  while(true){   
//			  boolean connect = isConnect();
//	          Thread.sleep(100000);//单位毫秒,我设置的是100秒.自己看情况更改
//	          if(!connect){
//	        	  System.out.println("无网络,正在重新拨号");
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
