package com.liyafeng.network.server;

public class Main_Server {

    /**
     * =========服务端架构演变=====
     * https://segmentfault.com/a/1190000018626163 （服务端高并发分布式架构演进之路）-好文推荐
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * ===========关系型数据库========
     * 1970年，IBM的研究员E.F.Codd博士 提出了关系模型的概念
     *
     * 数据库管理系统 DBMS
     * 关系数据库管理系统（RDBMS） Relational
     *
     * 借助于 集合代数 等数学概念和方法来处理数据库中的数据
     * 现实世界中的各种实体以及实体之间的各种联系均用关系模型来表示
     * 一类实体就是一张表，一个实体就是表中的一行，实体和实体之间的关系用主键来关联
     *
     *
     * 除了关系型数据库 还有 网状、层次型数据库
     *
     * 最早出现的是网状DBMS，是美国通用电气公司Bachman等人在1961年开发成功的IDS（Integrated DataStore）
     * 世界上第一个网状DBMS也是第一个数据库管理系统—— 集成数据存储（Integrated DataStore IDS）
     *
     *
     * 层次型DBMS是紧随网络型数据库而出现的。最著名最典型的层次数据库系统是IBM 公司在1968 年开发的IMS
     * （Information Management System），一种适合其主机的层次数据库
     *
     *
     * ===============关系模型中常用的概念：============
     * https://blog.csdn.net/oChangWen/article/details/53423301(关系型数据库和非关系型数据库区别)
     *
     *     关系：可以理解为一张二维表，每个关系都具有一个关系名，就是通常说的表名
     *     元组：可以理解为二维表中的一行，在数据库中经常被称为记录
     *     属性：可以理解为二维表中的一列，在数据库中经常被称为字段
     *     域：属性的取值范围，也就是数据库中某一列的取值限制
     *     关键字：一组可以唯一标识元组的属性，数据库中常称为主键，由一个或多个列组成
     *     关系模式：指对关系的描述。其格式为：关系名(属性1，属性2， ... ... ，属性N)，在数据库中成为表结构
     *  =============关系型数据库遵循ACID规则========
     *  1、A (Atomicity) 原子性 ，一组操作要么全成功，要么全失败，比如a给b转账100，从a扣100，和给b加100 就要是原子的
     *  2.C (Consistency) 一致性 事务的运行不会改变数据库原本的一致性约束
     *  3、I (Isolation) 独立性 事务之间不会互相影响
     *  4、D (Durability) 持久性 一旦事务提交后，它所做的修改将会永久的保存在数据库上，即使出现宕机也不会丢失
     *
     *
     *
     * =======Sql=========
     * 1974 年，IBM的Ray Boyce和Don Chamberlin 提出了SQL(Structured Query Language)语言
     *
     * SQL（Structured Query Language）语言是1974年由Boyce和Chamberlin提出的
     * 种介于关系代数与关系演算之间的结构化查询语言，是一个通用的、功能极强的关系性数据库语言。
     *
     * 直到二十世纪七十年代中期，关系理论才通过SQL在商业数据库Oracle和DB2中使用
     *
     * ============Oracle==========
     * 1977年，林赞·埃里森、汪𬀩庭·迈纳（Bob Miner）与柳士羽·奥茨（Ed Oates）3人，在美国加州圣塔克拉拉合资成立公司，
     * 名为软件发展实验室（英语：Software Development Laboratories，SDL)。1978年，开发出第一版甲骨文系统（Oracle），
     * 以汇编语言写成
     *
     *
     * ==========NOSql================
     * https://www.runoob.com/mongodb/nosql.html(NoSQL 简介)
     * NoSQL(NoSQL = Not Only SQL )，意即"不仅仅是SQL"。
     *
     * NoSQL一词首先是Carlo Strozzi在1998年提出来的，指的是他开发的一个没有SQL功能，轻量级的，开源的关系型数据库
     * 但是NoSQL的发展慢慢偏离了初衷，我们要的不是“no sql”，而是“no relational”，也就是我们现在常说的非关系型数据库了
     *
     *
     *
     * ============nosql数据库分类==========
     * 列存储
     * Hbase
     * Cassandra
     * Hypertable
     * 顾名思义，是按列存储数据的。最大的特点是方便存储结构化和半结构化数据，方便做数据压缩，对针对某一列或者某几列的查询有非常大的IO优势。
     *
     * 文档存储
     * MongoDB
     * CouchDB
     * 文档存储一般用类似json的格式存储，存储的内容是文档型的。这样也就有机会对某些字段建立索引，实现关系数据库的某些功能。
     *
     * key-value存储
     * Tokyo Cabinet / Tyrant
     * Berkeley DB
     * MemcacheDB
     * Redis
     * 可以通过key快速查询到其value。一般来说，存储不管value的格式，照单全收。（Redis包含了其他功能）
     *
     * 图存储
     * Neo4J
     * FlockDB
     * 图形关系的最佳存储。使用传统关系数据库来解决的话性能低下，而且设计使用不方便。
     *
     * 对象存储
     * db4o
     * Versant
     * 通过类似面向对象语言的语法操作数据库，通过对象的方式存取数据。
     *
     * xml数据库
     * Berkeley DB XML
     * BaseX
     * 高效的存储XML数据，并支持XML的内部查询语法，比如XQuery,Xpath。
     *
     *
     * =============orm============
     * ORM = Object Relational Mapping
     * 称为对象关系映射，即业务实体对象和关系数据库之间建立起一种映射关系，
     * 最简单的映射关系就是：一个类对应一张数据表，类的每个实例对应表中的一条记录，
     * 类的每个属性则对应表的每个字段
     *
     *
     * ============jdbc===========
     * Java 访问数据库的标准 API 是 JDBC,（针对不同的数据库有不同的实现）
     * 直接使用 JDBC API 进行编码操作数据库的话，代码非常繁琐，可扩展性非常低，
     * 因此，才会衍生出 Hibernate、MyBatis、SpringJDBC 等对 JDBC 进行了封装的 ORM 框架
     * 目前最流行的还是 MyBatis
     *
     * =========Hibernate MyBatis 比较 ============
     * Hibernate 是一个标准的 ORM 框架，在实体类和数据库之间建立了完整的映射关系，基本不需要开发人员自己写 SQL，
     * 内部会自动生成 SQL 语句，因此，对于数据量非常大的应用，想要优化 SQL 语句就比较困难
     *
     * MyBatis 则只是在实体类和 SQL 之间建立映射关系，并不自动生成 SQL 语句，因此，开发人员是需要自己写 SQL 的。这样，对 SQL 进行优化则比较方便了。
     *
     *
     *
     *
     *
     *
     */
    void nosql(){}


    /**
     * =============redis============
     * https://redis.io/ (官网)
     * https://github.com/antirez/redis
     * https://learnku.com/database/wikis/36945（教程）
     *
     * REmote DIctionary Server 远程字典服务
     * noSql 非关系型数据库的一种
     *
     * Redis是一个开源的、高性能的、基于键值对的缓存与存储系统，通过提供多种键值数据类型来适应不同场景下的缓存与存储需求。
     * 同时Redis的诸多高层级功能使其可以胜任消息队列、任务队列等不同的角色。
     *
     * 用c语言实现的
     *
     * =============由来============
     * 2008年，意大利的一家创业公司Merzia[ http://merzia.com]推出了一款基于MySQL的网站实时统计系统 LLOOGG [ http://lloogg.com]
     * 实时 web 日志分析器
     * 他对mysql性能感到失望，于是重新为LLOOGG开发了一款数据库，于2009年开发完成。后来就开源了
     *
     *
     *
     */
    void redis(){}


    /**
     *
     *
     * ============由来==============
     * MongoDB最初于2007年开发，当时公司正在建立一个类似于窗口天蓝(window azure)的服务平台。
     * MongoDB由位于纽约的一个名为10gen的组织开发，现在被称为MongoDB Inc.，它最初被开发为PAAS(平台即服务)。
     * 2009年晚些时候，它被作为一个由MongoDB公司维护和支持的开源数据库服务器在市场上引入
     * MongoDB的第一个真正产品是从2010年3月发布的MongoDB 1.4版本开始的。
     *
     *
     *
     */
    void mangodb(){}




    /**
     * ======网页服务器 / web服务器 / HTTP Server ===========
     * https://zhuanlan.zhihu.com/p/22544725 （什么是 Web 服务器（server））
     * 浏览器发出http请求 ，服务器的操作系统上得有一个进程来监听这个端口，
     * 随时接受http请求，找到网页文件（html、js、css image 等）然后返回结果（放在http响应体中）
     *
     * 主流的网页服务器
     * Apache（Apache HTTP Server）Apache	48.5%
     * nginx	            NGINX, Inc.	35.4%
     * IIS	                 Microsoft	10.8%
     * LiteSpeed Web Server	LiteSpeed Technologies	2.9%
     * GWS	                Google	1.1%
     *
     * 一般来说， Web Server 对外提供的是 HTTP 服务（也可以是其他服务），这就是为什么我们的网址都以「http://」开头。
     *
     * 下面是有 Node.js 写的一个最简单的 HTTP server
     * // 文件名 index.js
     * // 使用 node index.js 可运行本程序
     *
     * var http = require('http')
     *
     * var server = http.createServer( function (request, response){
     *     response.end('这是页面内容，你请求的路径是：' + request.url)
     * })
     *
     * server.listen(8080, function(){
     *     console.log("正在监听 %s 端口", 8080);
     * });
     *
     *
     * 只要监听8080端口，根据请求返回相应的数据，就是一个web（http）服务
     *
     *
     *
     * 提供 HTTP 服务的 server 分为两类。
     * 静态的：
     * 这种服务器简单地根据访问路径，返回对应的文件
     * 比如用户访问 http:// 123.123.123.123:8080/a/b/c/d.html，那么这种服务器就会在网站根目录找到 a/b/c/d.html 文件，原样返回给用户。
     *
     *
     * --------动态生成页面-----------
     * Apache HTTP Server和Nginx本身不支持生成动态页面，
     * 但它们可以通过其他模块来支持（例如通过Shell、PHP、Python脚本程序来动态生成内容）。
     *
     * ---------过程--------
     * 一般是 HTTP Server 接收到http请求，然后依照配置，转发到 php或者Python监听的端口，
     * php程序处理完后返回响应，给HTTP Server，然后返回给客户端
     *
     *
     * =========== Application Server ==============
     * 动态的：
     *  http:// http://weibo.com/home 每次内容都不一样，是动态返回的
     *
     * 比如tomcat等
     * 或者更准确的来说，是一个「Servlet/JSP」应用的容器（Ruby/Python 等其他语言开发的应用也无法直接运行在 Tomcat 上）。
     * An open source software implementation of the Java Servlet and JavaServer Pages technologies
     * servlet技术的一种实现
     *
     *
     * =================nginx和tomcat区别=========
     * https://www.zhihu.com/question/32212996 （tomcat 与 nginx，apache的区别是什么？）
     *
     * nginx 和 Apache http server 都是http server（http 服务，监听80端口，返回资源）
     * 单独的nginx只能返回静态的资源，内容不能动态生成
     * 而tomcat也能当做http server 但是它能返回动态的内容。
     * Java Servlet技术以及衍生的Java Server Pages技术可以让Java程序
     * 也具有处理HTTP请求并且返回内容（由程序动态控制）的能力，
     * Tomcat正是支持运行Servlet/JSP应用程序的容器（Container）:
     *
     * Tomcat运行在JVM之上，它和HTTP服务器一样，绑定IP地址并监听TCP端口，同时还包含以下指责：
     * 管理Servlet程序的生命周期
     * 将URL映射到指定的Servlet进行处理
     * 与Servlet程序合作处理HTTP请求
     *
     *
     * 虽然Tomcat也可以认为是HTTP服务器，但通常它仍然会和Nginx配合在一起使用
     *
     * 动静态资源分离，
     * 运用Nginx的反向代理功能分发请求：所有动态资源的请求交给Tomcat，
     * 而静态资源的请求（例如图片、视频、CSS、JavaScript文件等）则直接由Nginx返回到浏览器，
     * 这样能大大减轻Tomcat的压力。
     *
     * 负载均衡，
     * 当业务压力增大时，可能一个Tomcat的实例不足以处理，那么这时可以启动多个Tomcat实例进行水平扩展，
     * 而Nginx的负载均衡功能可以把请求通过算法分发到各个不同的实例进行处理
     *
     *
     * ===============反向代理和正向代理==========
     * 反向代理就是 客户端请求到达代理服务器，代理服务器把请求分发给要处理请求的真正的服务器，处理后再交给代理服务器返回响应
     * 为什么叫反向，因为正向代理是 ，比如手机上设置一个电脑的ip:port作为程序（比如抓包工具），让它去代替客户端请求数据
     * 这个叫正向代理。
     * 见 reverse_proxy.jpg
     *
     * 代理：代替处理
     * 总结就是
     * 给服务端代替处理（请求/响应） 叫反向代理
     * 给客户端代替处理（请求/响应）叫正向代理
     *
     *
     * =================负载均衡============
     * 生产环境中我们都用什么做负载均衡器？
     * 财大气粗的用硬件F5
     * 不差钱的使用DNS负载均衡
     * 技术牛逼的用LVS Linux Virtual Server 在传输层工作（tcp udp 层）
     * 苦逼的创业型小公司只能使用Nginx   工作在应用层
     *
     * DNS负载均衡的控制权在域名服务商手里，NDS存在多级解析，
     * 缓存A记录的问题，以及网站自身无法做更多的管理。
     * 这样导致了一般中小公司很少使用。
     *
     * 可以参考阿里云的负载均衡SLB
     * 四层采用开源软件LVS（Linux Virtual Server）+ keepalived的方式实现负载均衡。
     * 七层采用Tengine实现负载均衡
     *
     * ============双机热备===========
     * 双机热备是高可用的一种体现形式，生产环境中我们存在两个负载均衡节点，主节点处于激活状态，
     * 另一个节点处于备用状态，当主节点意外宕机，可以通过keepalived检测并迅速切换到备用服务，保障业务正常运转
     *
     * 一个备用nginx监控主nginx，主nginx （软件）挂了 备用的立即负责工作
     *
     *
     *
     */
    void server(){}


    /**
     * =============微服务架构===========
     * https://www.zhihu.com/question/65502802（什么是微服务架构？）
     * Spring-Cloud相关微服务组件
     *
     *
     * ===========集群============
     *
     * 集群：每台机器上都是相同的服务
     * 多台服务器部署相同应用构成一个集群
     * 作用：通过负载均衡设备共同对外提供服务
     *
     * 工程师们已分别把各个业务功能模块复制部署了多份。
     *
     * 集群引入了几个新问题：
     * 1、每个服务器的ip地址不一样，如何让用户知道到底要访问哪一个？
     * 通过nginx等做负债均衡可以解决
     * 2、session问题，之前用户登录信息，购物车信息等等都是存在服务器的内存中，服务器集群后如何保证每个服务器共享session数据
     * 可以采用redis或者其他缓存服务器进行处理
     *
     * ===============分布式==========
     *
     * 分布式：
     * 不同模块部署在不同服务器上
     * 作用：分布式解决网站高并发带来问题
     *
     * 将一个大的系统划分为多个业务模块，业务模块分别部署到不同的机器上，
     * 各个业务模块之间通过接口进行数据交互。区别分布式的方式是根据不同机器不同业务。
     *
     *
     * --------解决的问题---------
     * 团队的成员也越来越多，系统慢慢变的越来越庞大，又带来了一些问题：
     * 1、只是修改了某个业务的一些小功能却需要把整个系统重新发布一次；
     * 2、系统业务复杂，开发人员过多不好管理，开发效率低；
     * 3、无法针对某个特定的业务扩大服务器处的理能力
     *
     *
     *
     * =============微服务和分布式的区别======
     * https://www.jianshu.com/p/da20ae66bc21 （集群&分布式&微服务，简述服务器架构的演变过程）
     *
     * 集群：分散压力
     * 分布式：业务解耦，提高效率。
     * 微服务：分散能力。
     *
     *
     *
     *
     *
     *
     *
     */
    void micro_server(){}


    /**
     * 通过不同的域名访问到同一一个服务器上的不同网站了
     * 通过配置服务器就好
     *
     *
     */
    void many_host(){}

    /**
     *
     *
     * https://cloud.tencent.com/developer/news/338619 (Java中主流的Web服务器有哪些？这5种必用)
     * 开发Java Web应用所采用的服务器主要是与JSP/Servlet兼容的Web服务器，
     * 比较常用的有Tomcat、Resin、JBoss、WebSphere 和 WebLogic等
     *
     * ================选型=============
     * https://www.zhihu.com/question/21694651 （Java 做的大型网站用什么服务器软件？Tomcat、Weblogic 还是其它服务器？）
     * 我们公司(Alibaba)用的 JBoss 和 Jetty。感觉网站应用处理高并发瓶颈往往不在应用服务器吧，机器撑不住加机器就好了，
     * 撑不住的更可能是后面的数据库之类的。为了处理网站应用的高并发有很多办法，
     * 比如静态化，这样请求在 Apache/Nginx 一层就被挡掉了；
     * 比如分库分表，把流量分到多台数据库服务器上去。
     *
     * 或者微服务
     *
     *
     *
     *
     *
     *
     *
     *
     */
    void java_server(){}


    /**
     * 云硬盘快照
     * https://help.aliyun.com/document_detail/25391.html （快照概述
     * 系统盘快照和数据盘快照）
     *
     * 某一个时间点对云盘进行备份，比如在重大业务升级前对云盘进行快照
     * 如果升级出现问题可以快速用快照恢复到以前的状态
     *
     * ==========使用场景============
     * 容灾备份：为云盘创建快照，再使用快照创建云盘获取基础数据，实现同城容灾和异地容灾。
     * 环境复制：使用系统盘快照创建自定义镜像，再使用自定义镜像创建ECS实例，实现环境复制。
     * 数据开发：为数据挖掘、报表查询和开发测试等应用提供近实时的真实生产数据。
     * 提高容错率：出现操作失误时，能及时回滚数据，降低操作风险，实现版本回退。
     * 定期创建快照，避免常见的失误操作。例如，团队成员不慎在云盘上存储了错误的数据、ECS实例被误释放、应用错误导致了数据错误、或者骇客利用应用漏洞恶意删除业务数据等。
     * 执行重要操作前创建一份快照，常见的重要运维操作包括更换操作系统、应用软件升级或业务数据迁移等
     *
     *
     *
     *
     *
     */
    void snapshot(){}


    /**
     * 修改nginx配置文件，  在安装目录conf/nginx.conf中，保存 .切换到sbin 执行，nginx -s reload 重载配置文件
     * pm2 start www/bin --name='xxx' 启动
     *
     *
     */
    void a11(){}


}
