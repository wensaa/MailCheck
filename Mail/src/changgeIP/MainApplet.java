//package changgeIP;
//
//import javax.swing.DefaultListModel;
//import javax.swing.JApplet;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JTextField;
//
//public class MainApplet extends JApplet {
//
//	private JButton jbtSave = new JButton("Save");
//
//	private JButton jbtDelete = new JButton("Delete");
//
//	private JButton jbtOK = new JButton("OK");
//
//	private JButton jbtAuto = new JButton("Auto");
//
//	private JButton jbtExit = new JButton("Exit");
//
//	private JLabel jlblIPAddress = new JLabel("IP地址：");
//
//	private JTextField jtfIPAddress = new JTextField();
//
//	private JLabel jlblSubnetMask = new JLabel("子网掩码: ");
//	private JTextField jtfSubnetMask = new JTextField();
//	private JLabel jlblGateway = new JLabel("默认网关：");
//	private JTextField jtfGateway = new JTextField();
//	private JLabel jlblDNSfirst = new JLabel("首选DNS服务器：");
//	private JTextField jtfDNSfirst = new JTextField();
//	private JLabel jlblDNSremark = new JLabel("备用DNS服务器：");
//	private JTextField jtfDNSremark = new JTextField();
//	private JLabel jlblConfigFileName = new JLabel("配置项名称：");
//	private JTextField jtfConfgFileName = new JTextField();
//	private JList jlConfigItem = new JList();
//	DefaultListModel dlmConfigItem = new DefaultListModel();
//	SystemVars configInfo = new SystemVars();         
//
//	public void init()
//    {
//     super.setSize(1000,500);
//        setLayout(new BorderLayout());
//       
//        //添加控制按钮面板
//     JPanel jpControl = new JPanel();
//        jpControl.setLayout(new FlowLayout());
//        // 设置按钮属性
//        jbtSave.setMnemonic(KeyEvent.VK_S);
//        jbtDelete.setMnemonic(KeyEvent.VK_DELETE);
//        jbtOK.setMnemonic(KeyEvent.VK_O);
//        jbtAuto.setMnemonic(KeyEvent.VK_A);
//        jbtExit.setMnemonic(KeyEvent.VK_E);
//        //添加控制按钮
//        jpControl.add(jbtSave);
//        jpControl.add(jbtDelete);
//        jpControl.add(jbtOK);
//        jpControl.add(jbtAuto);
//        jpControl.add(jbtExit);
//       
//        add(jpControl,BorderLayout.SOUTH);
//       
//        //添加输入配置信息面板
//        JPanel jpEnterConfigInfo = new JPanel();
//        jpEnterConfigInfo.setLayout(new GridLayout(6,2,0,40));
//       
//        jpEnterConfigInfo.add(this.jlblIPAddress);
//        jpEnterConfigInfo.add(this.jtfIPAddress);
//        jpEnterConfigInfo.add(this.jlblSubnetMask);
//        jpEnterConfigInfo.add(this.jtfSubnetMask);
//        jpEnterConfigInfo.add(this.jlblGateway);
//        jpEnterConfigInfo.add(this.jtfGateway);
//        jpEnterConfigInfo.add(this.jlblDNSfirst);
//        jpEnterConfigInfo.add(this.jtfDNSfirst);
//        jpEnterConfigInfo.add(this.jlblDNSremark);
//        jpEnterConfigInfo.add(this.jtfDNSremark);
//       
//        jpEnterConfigInfo.add(this.jlblConfigFileName);
//        jpEnterConfigInfo.add(this.jtfConfgFileName);
//       
//        add(jpEnterConfigInfo,BorderLayout.EAST);
//       
//        //添加配置项列表框面板
//        JPanel jpConfigItem = new JPanel();
//        jpConfigItem.setLayout(new BorderLayout());
//       
//        //设置列表框属性
//        jlConfigItem.setSelectionBackground(Color.gray);
//        jlConfigItem.setSelectionForeground(Color.red);
//        jlConfigItem.setVisibleRowCount(10);
//        jlConfigItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//       
//        jlConfigItem.setFixedCellHeight(30);
//        jlConfigItem.setFixedCellWidth(200);
//       
//        jlConfigItem.setVisible(true);
//       
//       
//       
//       
//        //添加配置项列表框
//        jpConfigItem.add(jlConfigItem,BorderLayout.CENTER);
//       
//        add(new JScrollPane(jlConfigItem),BorderLayout.WEST);
//       
//       
//       
//        File configPath = new File("D:\\IP_CONFIG\");
//        configPath.mkdir();
//       
//       
//        this.jbtSave.addActionListener(new ActionListener()
//        {
//         public void actionPerformed(ActionEvent e)
//         {
//          if(MainApplet.this.jtfConfgFileName.getText().isEmpty() ||
//             MainApplet.this.jtfDNSfirst.getText().isEmpty() ||
//             MainApplet.this.jtfDNSremark.getText().isEmpty() ||
//             MainApplet.this.jtfGateway.getText().isEmpty() ||
//             MainApplet.this.jtfIPAddress.getText().isEmpty() ||
//             MainApplet.this.jtfSubnetMask.getText().isEmpty()  )
//          {
//           //添加配置信息为空异常代码
//           System.out.println("+------>请填入配置信息后再点保存按钮！");
//          }
//          else
//          {
//           
//           configInfo.setIp_address(MainApplet.this.jtfIPAddress.getText());
//           configInfo.setMask(MainApplet.this.jtfSubnetMask.getText());
//           configInfo.setGateway(MainApplet.this.jtfGateway.getText());
//           configInfo.setDns_first(MainApplet.this.jtfDNSfirst.getText());
//           configInfo.setDns_remark(MainApplet.this.jtfDNSremark.getText());
//           configInfo.setNetwork_name("\u672C\u5730\u8FDE\u63A5");
//           configInfo.setFile_url("D:\\IP_CONFIG\");
//           configInfo.setWeb_count("5");
//           configInfo.setWeb_url("http://www.baidu.com/");
//           configInfo.setGwmetric("1");
//           configInfo.setConfigFileName(MainApplet.this.jtfConfgFileName.getText());
//      
//           //生成IP配置文件
//           
//           ConfigFile.WriteConfigFile(configInfo);
//           
//           //在配置项列表框中添加所保存的配置项名
//           showConfigFileNameToList();
//          }
//         }
//        });                     this.jbtOK.addActionListener(new ActionListener()       
//
//	{
//         public void actionPerformed(ActionEvent e)
//         {
//          //选择配置项后才能修改IP地址信息
//          if(jlConfigItem.isSelectionEmpty())
//          {
//           //添加为选择配置项提示信息代码
//           System.out.println("+------->没有选择配置项，请选择一个配置项后再点OK");
//          }
//          else
//          {
//           //读取配置文件
//           configInfo = ConfigFile.ReadConfigFile((String)jlConfigItem.getSelectedValue());
//           //创建p文件
//           ConfigFile.createPFile(configInfo);
//           
//           //修改配置信息
//           ExecCmd.exec(configInfo);
//          }
//         }
//        });          
//
//	}// end init()
//	                  
//
//	public void start()
//    {
//     
//     showConfigFileNameToList();
//     
//     
//        this.jlConfigItem.addListSelectionListener(new ListSelectionListener()
//        {
//         public void valueChanged(ListSelectionEvent e)
//         {
//          if(jlConfigItem.isSelectionEmpty())
//          {
//           //添加为选择配置项提示信息代码
//           System.out.println("+------->没有选择配置项，请选择一个配置项");
//          }
//          else
//          {
//           //读取配置文件
//           configInfo = ConfigFile.ReadConfigFile((String)jlConfigItem.getSelectedValue());
//           
//           //显示所选配置信息
//           MainApplet.this.jtfIPAddress.setText(configInfo.getIp_address());
//           MainApplet.this.jtfSubnetMask.setText(configInfo.getMask());
//           MainApplet.this.jtfGateway.setText(configInfo.getGateway());
//           MainApplet.this.jtfDNSfirst.setText(configInfo.getDns_first());
//           MainApplet.this.jtfDNSremark.setText(configInfo.getDns_remark());
//           MainApplet.this.jtfConfgFileName.setText(configInfo.getConfigFileName());
//          }
//         }
//        });                     this.jbtDelete.addActionListener(new ActionListener()       
//
//	{
//         public void actionPerformed(ActionEvent e)
//         {
//          if(jlConfigItem.isSelectionEmpty())
//          {
//           //添加为选择配置项提示信息代码
//           System.out.println("+------->没有选择配置项，请选择一个配置项删除");
//          }
//          else
//          {
//           String filename = (String)jlConfigItem.getSelectedValue();
//           //删除所选配置文件
//           ConfigFile.delete("D:\\IP_CONFIG\"+filename+".properties");
//           //删除对应P文件
//           ConfigFile.delete("D:\\IP_CONFIG\"+filename+".txt");
//           //更新列表框
//           showConfigFileNameToList();
//           //清除文本域
//           jtfIPAddress.setText(null);
//           jtfSubnetMask.setText(null);
//           jtfGateway.setText(null);
//           jtfDNSfirst.setText(null);
//           jtfDNSremark.setText(null);
//              jtfConfgFileName.setText(null);
//          }
//         }
//        });                     this.jbtExit.addActionListener(new ActionListener()       
//
//{
//	        
//
//	public void actionPerformed(ActionEvent e)         {
//		         System.exit(1);        }       
//});                     this.jbtAuto.addActionListener(new ActionListener()       
//
//{
//	        
//
//	public void actionPerformed(ActionEvent e)         {
//		         ExecCmd.dhcp();        }       
//	});   
//
//}         
//
//public void showConfigFileNameToList()    {
//		    // 清除原来列表框内容
//		    this.dlmConfigItem.clear();    this.jlConfigItem.setModel(dlmConfigItem);    // 获得配置文件名
//		    ArrayList<String>al=ConfigFile.getConfigFileName();    // 显示到列表框中
//		    for(int i=0;i<al.size();i++)    {       this.dlmConfigItem.addElement((String)al.get(i));    }    this.jlConfigItem.setModel(dlmConfigItem);   }
//
//}
