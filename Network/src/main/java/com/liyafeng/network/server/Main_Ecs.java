package com.liyafeng.network.server;

public class Main_Ecs {


    /**
     * > 如果想拥有一个自己的博客网站，网上有很多框架可以用，但是不如自己手动搭建一个，这样对技术的印象更深刻
     *
     *   首先你可以买一个一年的阿里云服务器，然后注册一个域名，服务器的系统选择CentOS就行。
     *   接下来我们要远程连接服务器，我们可以用终端上的ssh命令，然后输入密码就登录成功了。
     * ```
     * ssh root@liyafeng.net
     * ```
     *   接下来就可以为服务器安装Tomcat，我们可以用`wget`命令，这个命令是自动下载URL的内容，我们可以到Tomcat找到下载的URL。
     *   下载完成后我们将tomcat的tar.gz压缩文件用mv命令移动到usr/local下。然后用tar 命令来解压。
     * ```
     * tar xvfz tomcat.tar.gz
     * ```
     *   其中x代表解压，v代表显示解压过程，f是解压到指定文件夹，z代表压缩包格式是gzip的。解压完成后我们就可以启动tomcat了。在./bin/目录下运行startup.sh。然后我们访问www.liyafeng.net:8080 就可以看到tomcat的页面了。
     *   下面我们要部署javaweb到服务器，首先下载Eclipse，这个要选择支持javaee版本的，然后我们本地也要有tomcat，接下来用Eclipse新建一个 Dynamic Web Project，这个要配置RunTime，也就是配置运行时的服务器类型，我们要选择对应的tomcat版本。新建完成后我们看到依赖包除了jre，还有tomcat的lib，里面包含了javaee的jar包。它实际是关联了本地的目录，你自己的工程里是没有这些jar包的，这些jar包只是编译的时候用。上传到服务器后就用服务器的这些jar包，因为服务器也有tomcat和jre。
     *   我们新建一个jsp，然后我们将这个工程导出为war包，然后我们要上传到服务器，可以用tar命令
     * ```
     * tar cvz project.war | ssh root@liyafeng.net 'tar xz'
     * ```
     *   这个是压缩，然后上传，然后解压的意思。我们也可以用sftp这个命令，s代表安全的。
     * ```
     * sftp root@liyafeng.net
     * ```
     *   输入密码后我们就可以连接上了，我们用put命令上传，get下载。在我们不用连接的时候可以control+d来退出。
     *
     *
     */
    void create_your_web(){}





    /**
     * ssh的使用
     * 首先我们用ssh命令可以登录远程服务器
     * ```
     * ssh user@liyafeng.net
     * 输入密码Lyf
     * ```
     * 要每次要输入密码，很不方便，我们可以配置ssh证书登录，每个操作系统都要用户登录，比如Window默认用户是Administrator，Linux是root，我们使用命令来为当前用户生成证书
     * ```
     * ssh-keygen
     * ```
     * 下面我们输入保存的位置：` ~/.ssh/id_rsa_xxx`，这样我们就为当前用户生成了公钥文件，和私钥文件，他们是 `~/.ssh/id_rsa_xxx`，`~/.ssh/id_rsa_xxx.pub` 。
     * 然后我们把公钥上传到服务器，我们可以用sftp命令
     * ```
     * sftp user@liyafeng.net
     * put ~/.ssh/id_rsa_xxx.pub
     * ```
     * 这样我们就在服务器的 `~/`目录下看到`id_rsa_xxx.pub`，我们要将这个拷贝到服务器的~/.ssh下，然后将内容追加到authorized_keys这个文件中
     * ```
     * #移动公钥
     * mv id_rsa_xxx.pub .ssh
     * #追加公钥内容到authorized_keys中
     * cat id_rsa_xxx.pub>>authorized_keys
     * ```
     * 然后我们还要确定` ~/.ssh` 和` ~/.ssh/authorized_keys`的权限，如果非本帐号有authorized_keys文件的读写权限，那么ssh证书认证是不起效果的。
     * ```
     * #修改权限
     * chmod 7 ~/.ssh
     * chmod 6 ~/.ssh/authorized_keys
     * ```
     * 下面我们还要保证ssh软件的配置文件`/etc/ssh/sshd_config`，要开启公钥认证功能
     * ```
     * vi /etc/ssh/sshd_config
     * ```
     * 将`# RSAAuthentication yes` 和`# PublicKeyAuthentication yes `前面的“#”去掉，然后保存一下，就可以了。
     * 如果我们本地有多个网站的ssh证书，那么我们可以在本地的`~/.ssh`目录下新建一个`config`文件，里面配置每个网站对应要使用的私钥
     * ```
     * Host liyafeng.net
     *   HostName liyafeng.net
     *   IdentityFile ~/.ssh/id_rsa_xxx
     *   user username
     * Host github
     *   HostName github.com
     *   IdentityFile ~/.ssh/id_rsa_github
     *   user username
     * ```
     * 这样，我们就可以用ssh liyafeng.net来登录远程服务器了。
     *
     */
    void ssh(){}


    /**
     * ===========linux目录===========
     *
     * https://www.runoob.com/linux/linux-system-contents.html （Linux 系统目录结构）
     * 系统启动必须：
     * /boot：存放的启动Linux 时使用的内核文件，包括连接文件以及镜像文件。
     * /etc：存放所有的系统需要的配置文件和子目录列表，更改目录下的文件可能会导致系统不能启动。
     * /lib：存放基本代码库（比如c++库），其作用类似于Windows里的DLL文件。几乎所有的应用程序都需要用到这些共享库。
     * /sys： 这是linux2.6内核的一个很大的变化。该目录下安装了2.6内核中新出现的一个文件系统 sysfs 。sysfs文件系统集成了下面3种文件系统的信息：针对进程信息的proc文件系统、针对设备的devfs文件系统以及针对伪终端的devpts文件系统。该文件系统是内核设备树的一个直观反映。当一个内核对象被创建的时候，对应的文件和目录也在内核对象子系统中
     *
     * 指令集合：
     * /bin：存放着最常用的程序和指令
     * /sbin：只有系统管理员能使用的程序和指令。
     *
     * 外部文件管理：
     * /dev ：Device(设备)的缩写, 存放的是Linux的外部设备。注意：在Linux中访问设备和访问文件的方式是相同的。
     * /media：类windows的其他设备，例如U盘、光驱等等，识别后linux会把设备放到这个目录下。
     * /mnt：临时挂载别的文件系统的，我们可以将光驱挂载在/mnt/上，然后进入该目录就可以查看光驱里的内容了。
     *
     * 临时文件：
     * /run：是一个临时文件系统，存储系统启动以来的信息。当系统重启时，这个目录下的文件应该被删掉或清除。如果你的系统上有 /var/run 目录，应该让它指向 run。
     * /lost+found：一般情况下为空的，系统非法关机后，这里就存放一些文件。
     * /tmp：这个目录是用来存放一些临时文件的。
     *
     * 账户：
     * /root：系统管理员的用户主目录。
     * /home：用户的主目录，以用户的账号命名的。
     * /usr：用户的很多应用程序和文件都放在这个目录下，类似于windows下的program files目录。
     * /usr/bin：系统用户使用的应用程序与指令。
     * /usr/sbin：超级用户使用的比较高级的管理程序和系统守护程序。
     * /usr/src：内核源代码默认的放置目录。
     *
     * 运行过程中要用：
     * /var：存放经常修改的数据，比如程序运行的日志文件（/var/log 目录下）。
     * /proc：管理内存空间！虚拟的目录，是系统内存的映射，我们可以直接访问这个目录来，获取系统信息。这个目录的内容不在硬盘上而是在内存里，我们也可以直接修改里面的某些文件来做修改。
     *
     * 扩展用的：
     * /opt：默认是空的，我们安装额外软件可以放在这个里面。
     * /srv：存放服务启动后需要提取的数据（不用服务器就是空）
     *
     *
     */
    void linux_directory(){}


}
