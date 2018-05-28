package mailcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class connectIP {
//	public static void main(String[] args) {
//		InetAddress host;
//		try {
//			host = InetAddress.getByName("106.14.13.32");
//			int port = 3128;
//			connectIP con=new connectIP();
//			con.consocket(host, port);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//public void consocket(InetAddress host,int port) {
//	try {
//		//Socket soc=new Socket(host, port);			System.out.println("++++++1");
//		Socket soc=new Socket();
//		SocketAddress address = new InetSocketAddress(host, port);
//		soc.connect(address, 5000);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		System.out.println("连接失败");
//	}
//}
//	public static void main(String[] args) {
//		System.getProperties().setProperty("http.proxyHost", "175.22.80.193");  
//		System.getProperties().setProperty("http.proxyPort", "8060");  
//		//System.out.println(getV4IP());
//        String host = "167.99.61.206";
//
//        int port = 3128;
//
//        if (args.length>1) {
//
//            host = args[0];
//
//            port = Integer.parseInt(args[1]);
//
//        }
//
//        new connectIP().connect(host, port);
//
//    }

     

    public void connect(String host,int port){

        SocketAddress address = new InetSocketAddress(host, port);
    	//InetAddress address = null;
        Socket socket = null;

        String result = "";

         

        try {
        //address= InetAddress.getByName(host);
        long begin = System.currentTimeMillis();//计算开始连接的时间
        //InetAddress remoteAddr = InetAddress.getByName("120.76.77.152");

       // InetAddress localAddr = InetAddress.getByName("121.58.213.130");
        //socket = new Socket(remoteAddr,1080,localAddr,port);//开始建立连接      
       socket = new Socket();
        //SocketAddress socaddress = new InetSocketAddress(host, port);
        socket.connect( address,6000);//设置连接超时时间
System.out.println(socket.isConnected()+"++++"+connectIP.getV4IP()+"++++"+socket.isBound());
        long end = System.currentTimeMillis();// 计算机连接结束的时间

        result = (end-begin)+"ms";

        } catch (BindException e) {

            result = "IP地址或端口绑定异常！";

        } catch (UnknownHostException e) {

            result = "未识别主机地址！";

        }catch (SocketTimeoutException e) {

            result = "连接超时！";

        }catch (ConnectException e) {

            result = "拒绝连接！";

        }catch (Exception e) {

            result =  "失败啦！";

        }finally{

            if (socket!=null) {

                try {

                    socket.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }
        //InetAddress localAddr = InetAddress.getLocalHost();
		 System.out.println("远程地址信息==>"+":"+result);
   

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
    				// TODO Auto-generated catch block
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
    
    	public  String getPublicIp() throws SocketException {
    		String localip = null;// 本地IP，如果没有配置外网IP则返回它
    		String netip = null;// 外网IP

    		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
    		InetAddress ip = null;
    		boolean finded = false;// 是否找到外网IP
    		while (netInterfaces.hasMoreElements() && !finded) {
    			NetworkInterface ni = netInterfaces.nextElement();
    			Enumeration<InetAddress> address = ni.getInetAddresses();
    			while (address.hasMoreElements()) {
    				ip = address.nextElement();
    				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
    					netip = ip.getHostAddress();
    					finded = true;
    					break;
    				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
    						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
    					localip = ip.getHostAddress();
    				}
    			}
    		}

    		if (netip != null && !"".equals(netip)) {
    			return netip+"外网ip";
    		} else {
    			return localip+"本地ip";
    		}
    	}



}
