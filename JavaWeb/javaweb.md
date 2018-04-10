
###eclipse
 去官网下载eclipse，https://www.eclipse.org/downloads/
 下载后点击安装，然后选择javaee安装。安装完成后创建一个 dynamic web project
 
 
### tomcat
http://tomcat.apache.org/ 左侧栏有download，选择版本后下载
然后解压，然后在命令行运行 bin/目录下的 startup。
然后提示配置各种XXX_HOME ，配置完成后，启动tomcat
浏览器访问 localhost:8080 会出现Tomcat的页面

### mysql
https://dev.mysql.com/downloads/ 下载后解压，
然后安装，`mysqld -install`
根目录放置 my-default.ini 配置文件
```
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
 
[mysqld]
# 设置3306端口
port = 3306
# 设置mysql的安装目录
basedir=C:\wamp-all\mysql-5.7.13
# 设置mysql数据库的数据的存放目录
datadir=C:\wamp-all\sqldata
# 允许最大连接数
max_connections=20
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```
5.7版本后需要初始化
`mysqld --initialize-insecure` 自动生成无密码的root用户，
然后启动mysql
`net start mysql` 会显示启动成功
我们进入mysql，`mysql -u root`
然后就可以输入mysql的命令来操作mysql了
`show databases` 显示出默认的数据库
`use [dbname]`进入[dbname]数据库
`show tables` 显示出数据库中的所有表


然后还要下载连接器的jar包 https://dev.mysql.com/downloads/connector/
放入工程的library中，



### jsp
http://www.runoob.com/jsp/jsp-tutorial.html 
菜鸟教程的jsp教程，简单易懂
