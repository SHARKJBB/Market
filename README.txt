1.在.classpath文件里第六行修改	<classpathentry kind="lib" path="你的mysql-connector-java-5.1.41-bin.jar"/>
2.在config文件夹下修改jdbc.properties(jdbc配置文件)里面的数据库地址和用户名密码
	String url = "jdbc:mysql://IP地址/数据库名";
        String userName = "用户名";
        String password = "密码";
3.自己的数据库中需要建一张customer表，表项包括
int CustNum;	 //会员卡号
String name;    //会员名字
double money;    //会员余额
String level;	//会员级别，可设置VIP等

