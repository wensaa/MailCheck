package changgeIP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class ChangeIP {
	public static void main(String[] args) throws InterruptedException, IOException {
		ChangeIP chip = new ChangeIP();

		// connectIP conip=new connectIP();
		// chip.setip(str.split(":"));
		for (int i = 0; i < 100; i++) {
			String str = chip.Getips(i);
			String[] strS = str.split(":");
			String port = strS[1].toString().trim();
			chip.checkip(strS[0], Integer.parseInt(port));
			// Thread.sleep(1000);
			// System.out.println("++++1+++"+connectIP.getV4IP());
		}
	}

	// 用于休眠所有线程
	String[] trueips=new String[100];
	int trueflog=0;
	public String Getips(int i) throws IOException {
		String IPSTR = null;
		// SocketAddress addr = new InetSocketAddress("175.22.80.193",
		// Integer.parseInt("8060"));
		// Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
		URL url = new URL("http://qsrdk.daili666api.com/ip/?tid=556604539534146&num=100、");
		URLConnection connection = url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		byte[] bytes = new byte[9000];
		while (inputStream.read(bytes) >= 0) {
			String ipstrs = new String(bytes);
			String str = ipstrs.replaceAll("\\r|\\n", ";");
			// 输出全部代理hip
			// System.out.println("++++++++++"+str);
			String[] strs = str.split(";;");
			// Random random=new Random();
			// int i=random.nextInt(99);
			IPSTR = strs[i];
			System.out.println(i + "++++++" + strs.length + "++++++" + strs[i]);
		}
		return IPSTR;
	}

	// 唯一变量
	private static ChangeIP open = new ChangeIP();
	private BufferedWriter writer;

	public void changeip(String[] strip) {
		synchronized (open) {
			if (ChangeIP.bb) {
				ChangeIP.bb = false;
				System.getProperties().setProperty("proxySet", "true");
				System.getProperties().setProperty("http.proxyHost", strip[0]);
				System.getProperties().setProperty("http.proxyPort", strip[1]);
				ChangeIP.bb = true;
			}
		}
	}

	public void setip(String[] strip) {
		System.getProperties().setProperty("proxySet", "true");
		System.getProperties().setProperty("http.proxyHost", strip[0]);
		System.getProperties().setProperty("http.proxyPort", strip[1]);
	}

	public void checkip(String ip, int port) throws IOException {
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
		} catch (MalformedURLException e) {
			System.out.println("url invalidate");
		}
		InetSocketAddress addr = null;
		addr = new InetSocketAddress(ip, port);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http proxy
		InputStream in = null;
		try {
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(1000);
			in = conn.getInputStream();
		} catch (Exception e) {
			System.out.println("ip " + ip + " is not aviable");// 异常IP
		}
		String s = convertStreamToString(in);
		System.out.println(s);
		// System.out.println(s);
		if (s.indexOf("baidu") > 0) {// 有效IP
			System.out.println(ip + ":" + port + " is ok");
			writer = new BufferedWriter(new FileWriter("H:\\代理ip.txt", true));
			trueips[trueflog]=ip+":";
			writer.write("{" + ip + "：" + port + "|is ok}");
			writer.flush();
		}

	}

	public static String convertStreamToString(InputStream is) {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String GetTrueip() {
		return null;
	}
}