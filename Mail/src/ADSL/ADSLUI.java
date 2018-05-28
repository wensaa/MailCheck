package ADSL;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ADSLUI extends JFrame {
	static Boolean bool=true;
	private JPanel contentPane;
	private JTextField name;
	private JPasswordField password;
	private JLabel label;
	private JTextField timeout;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel state;
	private JButton chongbo;
	private JLabel lblNewLabel_5;
	private JLabel nowip;
	private JLabel adslname;
	private JTextField textField;
	private JButton endbutt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADSLUI frame = new ADSLUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ADSLUI() {
		setTitle("ADSL\u91CD\u62E8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setBounds(98, 23, 146, 24);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(26, 26, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801  \uFF1A");
		lblNewLabel_1.setBounds(26, 57, 72, 27);
		contentPane.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(98, 57, 146, 27);
		contentPane.add(password);
		
		label = new JLabel("\u91CD\u64AD\u95F4\u9694\uFF1A");
		label.setBounds(14, 134, 84, 18);
		contentPane.add(label);
		
		timeout = new JTextField();
		timeout.setBounds(98, 131, 86, 24);
		contentPane.add(timeout);
		timeout.setColumns(10);
		
		lblNewLabel_2 = new JLabel("ms");
		lblNewLabel_2.setBounds(203, 134, 33, 18);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("\u8FDE\u63A5\u72B6\u6001\uFF1A");
		lblNewLabel_3.setBounds(14, 168, 86, 18);
		contentPane.add(lblNewLabel_3);
		
		state = new JLabel("\u672A\u77E5");
		state.setBounds(98, 168, 146, 18);
		contentPane.add(state);		
		
		
		lblNewLabel_5 = new JLabel("IP\uFF1A");
		lblNewLabel_5.setBounds(53, 199, 33, 18);
		contentPane.add(lblNewLabel_5);
		
		nowip = new JLabel("\u672A\u77E5");
		nowip.setBounds(98, 199, 146, 18);
		contentPane.add(nowip);
		
		adslname = new JLabel("\u5BBD\u5E26\u540D\u79F0\uFF1A");
		adslname.setBounds(14, 97, 98, 18);
		contentPane.add(adslname);
		
		textField = new JTextField();
		textField.setBounds(98, 97, 146, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		endbutt = new JButton("\u505C\u6B62");
		endbutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(e.getActionCommand().equals("Í£Ö¹")) {
				//ADSLUI.bool=false;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				chongbo.setEnabled(true);
				
			}
			}});
		endbutt.setBounds(140, 230, 98, 37);
		contentPane.add(endbutt);
		
		chongbo = new JButton("\u542F\u52A8");
		chongbo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getActionCommand().equals("Æô¶¯")) {
					//ADSLUI.bool=true;
					endbutt.setEnabled(true);
					chongbo.setEnabled(false);
					String strname=name.getText();
					String strpass=String.valueOf(password.getPassword()); 
					String stradsl=adslname.getText();
					int wait=Integer.parseInt(timeout.getText());
					ADSLip adslip=new ADSLip(stradsl,strname, strpass, wait,state,nowip);
					try {
						System.out.println(strname+"+++++++++"+strpass);
						adslip.start();					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		chongbo.setBounds(14, 230, 98, 37);
		contentPane.add(chongbo);
	}
}
