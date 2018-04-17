
### eclipse
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

创建表，并插入数据
```
#创建
create table member(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(255),
phone varchar(50),
sex tinyint(4),
birthday varchar(100),
money float );
#插入数据
insert into member(name,phone,sex,birthday,money)values('李丽','18518552972',0,'20180909',55.5);
#查询
select *from member;
```





然后还要下载连接器的jar包 https://dev.mysql.com/downloads/connector/
放入工程的library中，
（记住，在eclipse中要在build path设置中将这个包 在order and export 中选择
这个jar包，否则会报找不到 com.mysql.jdbc.Driver）(还是不行就得在tomcat目录下
的lib中加入jar包，这样根据类加载机制就会优先使用lib中的mysql jar包)


### jsp
http://www.runoob.com/jsp/jsp-tutorial.html 
菜鸟教程的jsp教程，简单易懂

设置响应编码
```
response.setCharacterEncoding("UTF-8");
response.setHeader("content-type","text/html;charset=UTF-8");
```
#### 项目结构
在项目的根目录下，有src 和 WebContent目录
src下是包名，然后是servlet类
WebContent下是一些非java文件，比如jsp，html ,img，css，js
WebContent/WEB-INF/下有web.xml和lib，web.xml中是项目配置信息，
lib中存储要用到的jar包，比如，mysql的jdbc.jar
而在index.html中引用css或者js可以用相对目录，/js/xx.js  ,/css/xx.css

### sql语句

```sql
create table member(
id int PRIMARY KEY AUTOINCREMENT,
name varchar(255),
phone varchar(50),
sex tinyint(4),
birthday varchar(100),
money float ,
card varchar(255)
);

insert into member(name,phone,sex,birthday,money,card)values('李丽','18518552972',0,'20180909',55.5,'123123');

select *from member;

UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

ALTER TABLE member ADD card varchar(255);

ALTER TABLE tableName ALTER column columnName varchar(4000)  ;

alter table tableName drop column columnName   ;
```

### AJAX

```
        var request = new XMLHttpRequest();
		request.onreadystatechange = function() { // 状态发生变化时，函数被回调
			if (request.readyState === 4) { // 成功完成
				// 判断响应结果:
				if (request.status === 200) {
					// 成功，通过responseText拿到响应的文本:
					return success(request.responseText);
				} else {
					// 失败，根据响应码判断失败原因:
					alert("error:" + request.status);

				}
			} else {
				// HTTP请求还在继续...
			}
		}

        //获取值
		var a = document.getElementById("memberNum");
		//a.value =123;
		// 发送请求:
		request.open('GET',
				'http://localhost:8080/member/MemberQueryServlet?memberNum='
						+ a.value);
		request.send();
```


### js
关于<select>标签获取和设置内容的一些用法
https://blog.csdn.net/whynottrythis/article/details/44460659
