package mailcheck;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

	public class filetool implements ActionListener {
		JFrame JF;
		Netinfo netinfo;
		UIinfo uiIinfo;
		String str="";
		String[] strs=new String[2];
		public filetool(Netinfo netinfo,UIinfo uiIinfo,JFrame JF){
			this.netinfo=netinfo;
			this.uiIinfo=uiIinfo;
			this.JF=JF;
					}	
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("导入TXT文件")){
				this.openfile();
				strs[0]=str;
				JTextField JT=uiIinfo.getDaorutxt();
				JT.setText(str);
	}
			else if (e.getActionCommand().equals("输出结果")) {
				if(str!="") {
				this.openfile();
				strs[1]=str;
				JTextField JT=uiIinfo.getShuchutxt();
				JT.setText(str);}
				else {
					JOptionPane.showMessageDialog(JF, "请选择导入TXT文件", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (e.getActionCommand().equals("开始验证")) {
				netinfo.setAdslname(uiIinfo.getAdslname().getText());
				netinfo.setPassword(uiIinfo.getPassword().getText());
				netinfo.setUsername(uiIinfo.getUsername().getText());
				int wait=Integer.parseInt(uiIinfo.getWait().getText());
				netinfo.setWait(wait);
				mailLog mL=new mailLog();
				mL.startup(strs,uiIinfo.getJindu(),netinfo);
			}
			}
		public void openfile() {
			JFileChooser choose=new JFileChooser();
			  choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
			    int returnVal = choose.showDialog(JF,"就是这个");					    
			    System.out.println("代码执行到这里了");
			    if( returnVal == JFileChooser.APPROVE_OPTION){
			    	System.out.println("确定");
			    	//File file = choose.getSelectedFile();					   
			    	System.out.println(choose.getSelectedFile().getAbsolutePath());
			    	str=choose.getSelectedFile().getAbsolutePath();
			    }else if(returnVal == JFileChooser.CANCEL_OPTION){
			    	System.out.println("取消");
			    }					
		}

		}


