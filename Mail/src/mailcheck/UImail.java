package mailcheck;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class UImail extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField daorutxt;
	private JTextField shuchutxt;
	private JLabel jindu;
	private JTextField adslname;
	private JTextField username;
	private JTextField password;
	private JTextField wait;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UImail frame = new UImail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UImail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton daoru = new JButton("\u5BFC\u5165TXT\u6587\u4EF6");
		daoru.setBounds(44, 56, 122, 46);
		
		
		daorutxt = new JTextField();
		daorutxt.setBounds(196, 56, 247, 46);
		contentPane.add(daorutxt);
		daorutxt.setColumns(10);
		
		JButton yanzheng = new JButton("\u5F00\u59CB\u9A8C\u8BC1");
		yanzheng.setBounds(488, 56, 113, 46);
		
		
		shuchutxt = new JTextField();
		shuchutxt.setColumns(10);
		shuchutxt.setBounds(196, 113, 247, 46);
		contentPane.add(shuchutxt);
		
		JButton shuchu = new JButton("\u8F93\u51FA\u7ED3\u679C");		
		shuchu.setBounds(44, 115, 122, 46);
		
		
		JLabel lblNewLabel = new JLabel("\u90AE\u7BB1\u683C\u5F0Fxxxxx@163.com[xxxxxxx]xxxxx@163.com[xxxxxxx");
		lblNewLabel.setBounds(83, 13, 424, 30);
		contentPane.add(lblNewLabel);
		
		jindu = new JLabel("   \u8FDB\u5EA6\uFF1A");
		jindu.setFont(new Font("宋体", Font.PLAIN, 18));
		jindu.setBounds(457, 115, 167, 46);
		contentPane.add(jindu);
		
		JLabel state = new JLabel("  \u8FDE\u63A5\u72B6\u6001\uFF1A");
		state.setFont(new Font("宋体", Font.PLAIN, 18));
		state.setBounds(44, 259, 272, 46);
		contentPane.add(state);
		
		JLabel nowip = new JLabel("   IP\uFF1A");
		nowip.setFont(new Font("宋体", Font.PLAIN, 18));
		nowip.setBounds(351, 259, 260, 46);
		contentPane.add(nowip);
		
		JLabel label = new JLabel("  \u5BBD\u5E26\u540D\u79F0\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(44, 174, 122, 46);
		contentPane.add(label);
		
		adslname = new JTextField();
		adslname.setColumns(10);
		adslname.setBounds(159, 182, 157, 35);
		contentPane.add(adslname);
		
		JLabel label_1 = new JLabel("  \u7528\u6237\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(330, 172, 122, 46);
		contentPane.add(label_1);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(444, 182, 167, 35);
		contentPane.add(username);
		
		JLabel label_2 = new JLabel("  \u7528\u6237\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(44, 217, 122, 46);
		contentPane.add(label_2);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(159, 230, 157, 33);
		contentPane.add(password);
		
		JLabel label_3 = new JLabel("  \u62E8\u53F7\u95F4\u9694\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(330, 217, 122, 46);
		contentPane.add(label_3);
		
		wait = new JTextField();
		wait.setColumns(10);
		wait.setBounds(444, 230, 167, 35);
		contentPane.add(wait);
		
		Netinfo netinfo=new Netinfo();
		netinfo.setState(state);
		netinfo.setNowip(nowip);
		UIinfo uiinfo=new UIinfo(daorutxt, shuchutxt, jindu, adslname, username, password, wait);
		filetool ftl=new filetool(netinfo,uiinfo,this);
		daoru.addActionListener(ftl);
		yanzheng.addActionListener(ftl);
		shuchu.addActionListener(ftl);
		contentPane.add(daoru);
		contentPane.add(yanzheng);
		contentPane.add(shuchu);
	}

}
