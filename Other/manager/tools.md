=============项目工具
### 禅道
从需求管理，到研发周期管理，到bug管理，包含了产品的整个生命周期
可以见禅道使用手册

### teambition
https://www.teambition.com
协作工具，类似白板

### tower 
https://tower.im
管理项目任务 共享文档文件，撰写团队周报，提高团队协作效率

### confluence
https://www.atlassian.com/zh/software/confluence
文档协作工具，加入你项目所在的空间
一般吧团队的产品文档，技术文档，项目日历啥的都放到这


### 石墨文档


### bugtags
https://www.bugtags.cn/
bug管理，崩溃管理

### 蓝湖
https://lanhuapp.com
上传设计图，可以看标注，取色值，可以自定义单位，直接下载切图
把宽度调整到360dp,就是Android用的尺寸
切图可以单独在一张图片中

### 钉钉消息机器人
异常报警
gitlab push 通知
jenkins 打包通知

### gitlab静态代码扫描工具 SonarQube
https://zhuanlan.zhihu.com/p/79047213 （SonarQube 集成gitlab/jenkins）


=========打包=======
jenkins 自动打包
测试可以自动选择分支，里面url都自动变，
而且代码中不控制是release还是debug，这样合并代码就不用担心里面的变量忘记改
要什么包直接根据buildtype打包即可

根据 buildtype 打包
debug - 联调环境  和后端联调的环境
beta - 测试环境  给测试人员测试的环境  
prepare - 预上线环境/仿真环境  和线上用的一个库，模仿线上环境
online - 线上环境  用户用的环境

【打包】通知

打包成功
打包分支: 
包名: 
打包环境: test
构建产物: 下载包()
跳转到：
包描述:【二维码扫描下载】




===========会议

### Kick-off Meeting 项目启动会议
https://www.douban.com/note/671899566/

what?
该会议是PM激励其团队的最佳机会。 在这次会议上，项目管理人员可以建立共同目标，并开始了解每个人

When (何时开)
在项目规划过程完成之后（即项目管理计划都已经完成之后），执行过程开始之前。这个会议的召开一般意味着项目开始进入执行阶段。

Who（与会者）
项目团队的所有人都参加，最好有高层领导加入。高层领导的参加不但能体现项目的重要性，还能隐性的增加项目经理的威信。以后当项目需要增加资源或出现重大变更的时候，比如范围、进度、成本等，高层的支持不仅有助于推动项目，也对项目经理和团队是一种保护。
注意不要遗漏跟开发以外的成重要干系人，例如质量、采购人员等，以避免后期在项目配合过程中出现问题。

### 每日站会
昨天做了什么，今天做了什么，有什么风险，每个人轮流组织
这样不仅能杜绝迟到，还能激励你多做事

### 技术分享
App组件化分享

### 月会
介绍新同事；总结过去的问题，团队问题，项目问题。表扬优秀案例；同步团队未来的发展方向，产品方向，介绍团队价值观，
产品价值，让组员心中目标统一。

### 需求评审
App测试阶段，后台和ui可以先行，如果有需求改动也可以这个时候改，会上讨论完同步到群里

### 技术评审
如果有技术方案选型，或者前后端技术方案实现方式，都可以在这里讨论，在需求评审后，开始开发前
没有疑问可以不开

### 知识分享会
比如教研/产品/技术 和其他团队讲解一些知识，一个是其他团队能大概了解
你们那个模块是怎么做的，一个是展现你团队的能力和价值，展示你们团队的产出

=============邮件
### 提测邮件

提测需求版本： V1.0
提测环境：
提测类型：正常提测
提测分支：
Git地址：
开发人员：xxx
提测时间：
apk下载地址：
apk下载二维码：

提测内容：
相关内容链接
需求地址：
设计地址：



