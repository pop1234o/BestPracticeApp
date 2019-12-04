package com.liyafeng.buildtool;

public class Main_JDK {


    /**
     *
     * ===========Android修改第三方.aar后重新打包=====================
     * 作者：v587的毅哥
     *\ 链接：https://www.jianshu.com/p/f0a267551493
     *\ 来源：简书
     *\ 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * 步骤：
     *
     * 解压.aar文件
     * 使用jd-gui查看源码并定位到代码再修改
     *
     * 解压.aar文件解压后产生的classes.jar文件
     * 打包源码为classes.jar
     * 打包所有文件(res文件、classes.jar、AndroidManifest.xml等)为xxx.aar
     * 解压.aar文件
     * $ unzip myLib.aar -d tempFolder
     *
     * 使用jd-gui查看源码并定位到代码再修改
     * 进入tempFolder把里面的classes.jar复制出来
     *
     * 再使用jd-gui(mac版下载地址：https://github.com/parcool/resources/blob/master/jd-gui-1.4.0.jar windows自行下载)
     * 打开classes.jar找到需要修改的地方，把这个类的代码copy出来，在android studio中新建项目，
     * 把这个.aar放到新建libs文件夹里再引用它，把之前copy出来的代码新建一个.java后粘贴到里面
     * （注意包名也得一样，部分报错的代码需要手动修改一下。比如：this关键字之类的删掉），
     * 改好后build一下，从/build/intermediates/classes/debug/对应包名找到修改的.class文件。
     *
     *
     *
     *
     * 解压.aar文件解压后产生的classes.jar文件
     * 解压classes.jar。命令：$ unzip classes.jar -d tempFolderClasses
     * 把第二步生成的.class文件放到tempFolderClasses文件夹下对应的地方替换掉以前的.class文件（大功即将告成）
     * 打包源码为classes.jar
     * $ jar cvf newClasses.jar -C tempFolderClasses/ .（注意斜杠后面加空格与.）
     * 再把这个newClasses.jar放回tempFolder并删除之前的，修改文件名为classes.jar
     *
     * 打包所有文件(res文件、classes.jar、AndroidManifest.xml等)为xxx.aar
     * $ jar cvf newAAR.aar -C tempFolder/ .
     *
     * 完成！！！！
     * 没配图啥的，不明白的再交流吧。
     *
     *
     * ==========总结==================
     * 解压aar 可以用unzip
     *
     *
     */
    void a1(){}


    /**
     *
     *  ===============aar反编译&重新打包==============
     *  aar包就是zip文件，直接改后缀解压即可，或者用jd-gui也可以看
     *  里面有classes.jar
     *
     * ==============jar包改代码&重新打包==========
     * 用jd-gui打开，file->save all sources 导出反编译后的java源码
     * 修改
     *
     *
     * ===============jar命令用法========
     * 用法: jar {ctxui}[vfmn0PMe] [jar-file] [manifest-file] [entry-point] [-C dir] files ...
     *
     * {ctxu}代表只能选择一个，[是可选项]
     * 选项:
     *     -c  创建新档案
     *     -t  列出档案目录
     *     -x  从档案中提取指定的 (或所有) 文件
     *     -u  更新现有档案
     *     -v  在标准输出中生成详细输出
     *     -f  指定档案文件名
     *     -m  包含指定清单文件中的清单信息
     *     -n  创建新档案后执行 Pack200 规范化
     *     -e  为捆绑到可执行 jar 文件的独立应用程序
     *         指定应用程序入口点
     *     -0  仅存储; 不使用任何 ZIP 压缩
     *     -P  保留文件名中的前导 '/' (绝对路径) 和 ".." (父目录) 组件
     *     -M  不创建条目的清单文件
     *     -i  为指定的 jar 文件生成索引信息
     *     -C  更改为指定的目录并包含以下文件
     * 如果任何文件为目录, 则对其进行递归处理。
     * 清单文件名, 档案文件名和入口点名称的指定顺序
     * 与 'm', 'f' 和 'e' 标记的指定顺序相同。
     *
     * 示例 1: 将两个类文件归档到一个名为 classes.jar 的档案中:
     *        jar cvf classes.jar Foo.class Bar.class
     * 示例 2: 使用现有的清单文件 'mymanifest' 并
     *            将 foo/ 目录中的所有文件归档到 'classes.jar' 中:
     *        jar cvfm classes.jar mymanifest -C foo/ .
     *
     *  jar cvf classes.jar -C foo/ .
     *  foo/ 是.class开始的目录
     *  . 是输出到用户目录
     */
    void a4_1(){}


    /**
     * ==============java命令==============
     * 执行class或者jar
     *
     * java [-options] class [args...]
     *            (执行类)
     *    或  java [-options] -jar jarfile [args...]
     *            (执行 jar 文件)
     * 其中选项包括:
     *     -d32	  使用 32 位数据模型 (如果可用)
     *     -d64	  使用 64 位数据模型 (如果可用)
     *     -server	  选择 "server" VM
     *                   默认 VM 是 server,
     *                   因为您是在服务器类计算机上运行。
     *
     *
     *     -cp <目录和 zip/jar 文件的类搜索路径>
     *     -classpath <目录和 zip/jar 文件的类搜索路径>
     *                   用 : 分隔的目录, JAR 档案
     *                   和 ZIP 档案列表, 用于搜索类文件。
     *     -D<名称>=<值>
     *                   设置系统属性
     *     -verbose:[class|gc|jni]
     *                   启用详细输出
     *     -version      输出产品版本并退出
     *     -version:<值>
     *                   警告: 此功能已过时, 将在
     *                   未来发行版中删除。
     *                   需要指定的版本才能运行
     *     -showversion  输出产品版本并继续
     *     -jre-restrict-search | -no-jre-restrict-search
     *                   警告: 此功能已过时, 将在
     *                   未来发行版中删除。
     *                   在版本搜索中包括/排除用户专用 JRE
     *     -? -help      输出此帮助消息
     *     -X            输出非标准选项的帮助
     *     -ea[:<packagename>...|:<classname>]
     *     -enableassertions[:<packagename>...|:<classname>]
     *                   按指定的粒度启用断言
     *     -da[:<packagename>...|:<classname>]
     *     -disableassertions[:<packagename>...|:<classname>]
     *                   禁用具有指定粒度的断言
     *     -esa | -enablesystemassertions
     *                   启用系统断言
     *     -dsa | -disablesystemassertions
     *                   禁用系统断言
     *     -agentlib:<libname>[=<选项>]
     *                   加载本机代理库 <libname>, 例如 -agentlib:hprof
     *                   另请参阅 -agentlib:jdwp=help 和 -agentlib:hprof=help
     *     -agentpath:<pathname>[=<选项>]
     *                   按完整路径名加载本机代理库
     *     -javaagent:<jarpath>[=<选项>]
     *                   加载 Java 编程语言代理, 请参阅 java.lang.instrument
     *     -splash:<imagepath>
     *                   使用指定的图像显示启动屏幕
     * 有关详细信息, 请参阅 http://www.oracle.com/technetwork/java/javase/documentation/index.html
     */
    void a4_2(){}

    /**
     * ================javac命令================
     * 用来编译java文件为class文件
     * 默认情况下javac是在当前目录下查找类文件
     *
     *
     * 用法: javac <options> <source files>
     *     javac [ options ] [ sourcefiles ] [ @files ]
     *
     *     options
     *          命令行选项。
     *      sourcefiles
     *          一个或多个要编译的源文件（例如 MyClass.java）。
     *      @files
     *          一个或多个对源文件进行列表的文件。可以是txt文件，里面用java文件的列表
     *
     *
     * 其中, 可能的选项包括:
     *   -g                         生成所有调试信息
     *   -g:none                    不生成任何调试信息
     *   -g:{lines,vars,source}     只生成某些调试信息
     *   -nowarn                    不生成任何警告
     *   -verbose                   输出有关编译器正在执行的操作的消息
     *   -deprecation               输出使用已过时的 API 的源位置
     *   -classpath <路径>            指定查找用户类文件和注释处理程序的位置
     *   -cp <路径>                   指定查找用户类文件和注释处理程序的位置
     *   -sourcepath <路径>           指定查找输入源文件的位置
     *   -bootclasspath <路径>        覆盖引导类文件的位置
     *   -extdirs <目录>              覆盖所安装扩展的位置
     *   -endorseddirs <目录>         覆盖签名的标准路径的位置
     *   -proc:{none,only}          控制是否执行注释处理和/或编译。
     *   -processor <class1>[,<class2>,<class3>...] 要运行的注释处理程序的名称; 绕过默认的搜索进程
     *   -processorpath <路径>        指定查找注释处理程序的位置
     *   -parameters                生成元数据以用于方法参数的反射
     *   -d <目录>                    指定放置生成的类文件的位置
     *   -s <目录>                    指定放置生成的源文件的位置
     *   -h <目录>                    指定放置生成的本机标头文件的位置
     *   -implicit:{none,class}     指定是否为隐式引用文件生成类文件
     *   -encoding <编码>             指定源文件使用的字符编码
     *   -source <发行版>              提供与指定发行版的源兼容性
     *   -target <发行版>              生成特定 VM 版本的类文件
     *   -profile <配置文件>            请确保使用的 API 在指定的配置文件中可用
     *   -version                   版本信息
     *   -help                      输出标准选项的提要
     *   -A关键字[=值]                  传递给注释处理程序的选项
     *   -X                         输出非标准选项的提要
     *   -J<标记>                     直接将 <标记> 传递给运行时系统
     *   -Werror                    出现警告时终止编译
     *   @<文件名> 从文件读取选项和文件名
     */
    void a4_3(){}


    /**
     * ========== MANIFEST.MF 文件==============
     * https://zhuanlan.zhihu.com/p/29345229(利用原始的javac编译整个Java项目)
     *
     * 注意 MANIFEST.MF 文件 定义主函数 即程序入口;定义依赖库文件的路径 内容如下：
     * Manifest-Version: 1.0
     * Main-Class: com.travel.app.AppMainEntry
     * Class-Path: bin/fastjson-1.2.37.jar bin/json-20170516.jar bin/org.restlet.ext.json-2.3.10.jar bin/org.restlet-2.3.10.jar
     *
     *
     *
     */
    void a4_4(){}




}
