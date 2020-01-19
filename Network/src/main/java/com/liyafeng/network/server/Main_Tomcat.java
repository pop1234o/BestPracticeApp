package com.liyafeng.network.server;

public class Main_Tomcat {


    /**
     * https://wiki.jikexueyuan.com/project/linux/tomcat.html（极客学院）
     *
     * Tomcat 是由 Apache 开发的一个 Servlet 容器，实现了对 Servlet 和 JSP 的支持
     *
     * 由于 Tomcat 本身也内含了一个 HTTP 服务器，它也可以被视作一个单独的 Web 服务器
     *
     *
     * /bin - Tomcat 脚本存放目录（如启动、关闭脚本）。 *.sh 文件用于 Unix 系统； *.bat 文件用于 Windows 系统。
     * /conf - Tomcat 配置文件目录。
     * /logs - Tomcat 默认日志目录。
     * /webapps - webapp 运行的目录。
     *
     *
     * webapp：工程发布文件夹。其实每个 war 包都可以视为 webapp 的压缩包。
     *
     * META-INF：META-INF 目录用于存放工程自身相关的一些信息，元文件信息，通常由开发工具，环境自动生成。
     *
     * WEB-INF：Java web应用的安全目录。所谓安全就是客户端无法访问，只有服务端可以访问的目录。
     *
     * /WEB-INF/classes：存放程序所需要的所有 Java class 文件。
     *
     * /WEB-INF/lib：存放程序所需要的所有 jar 文件。
     *
     * /WEB-INF/web.xml：web 应用的部署配置文件。它是工程中最重要的配置文件，它描述了 servlet 和组成应用的其它组件，以及应用初始化参数、安全管理约束等。
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * =============tomcat配置===============
     * https://tomcat.apache.org/tomcat-8.0-doc/config(官方配置文档)
     * https://www.cnblogs.com/kismetv/p/7228274.html （详解Tomcat 配置文件server.xml）
     *
     * tomcat结构见，tomcat_arch.jpg
     * 通过Connector，将请求分发给Engine，找到对应的host，一个host下可以有多个context（应用）
     *
     *
     *
     */
    void conf(){}
}
