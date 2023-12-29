https://developer.android.google.cn/studio/intro/keyboard-shortcuts?hl=zh-cn
Redo  ctrl y
undo  ctrl z
Back ctrl left  //定位上次浏览的位置
Forward ctrl right



####################定位

  <action id="FindInPath"> 全局查找
    <keyboard-shortcut first-keystroke="ctrl h" />

  <action id="find  Usages">  找到当前变量都在哪里引用的，在窗口里展示，  如何是浮窗中展示，则ctrl+点击就可以了
    <keyboard-shortcut first-keystroke="shift ctrl g" />

  <action id="GotoLine"> 跳转行
    <keyboard-shortcut first-keystroke="ctrl l" />
  </action>


 <action id="Select In"> project中定位到这个文件
    <keyboard-shortcut first-keystroke="alt f1 " />
  </action>

recent files 最近打开的文件  包括类
ctrl e

Next Highlighted Error  下一个error
f2 

find  文件内查找
ctrl f

replace 文件内替换
ctrl r

#####移动代码
<action id="MoveLineDown">
<keyboard-shortcut first-keystroke="alt down" />
</action>
<action id="MoveLineUp">
<keyboard-shortcut first-keystroke="alt up" />
</action>

<action id="EditorDuplicate"> 向下复制
    <keyboard-shortcut first-keystroke="ctrl alt down" />



#####删除代码

<action id="EditorDeleteLine"  "ctrl d" />

#####自动整理代码
<action id="OptimizeImports"> 优化import
<keyboard-shortcut first-keystroke="shift ctrl o" />

<action id="ReformatCode">
    <keyboard-shortcut first-keystroke="shift ctrl f" />

######注释
ctrl /
 Ctrl + Shift + /：Comment with Block Comment（使用块注释注释）


#####生产代码

type matching  自动弹出补全建议
alt /

<action id="Generate"> 生成类的代码 构造函数，getter ,tosting 
    <keyboard-shortcut first-keystroke="shift alt s" />
  </action>


<action id="IntroduceField"> 生成成员变量
    <keyboard-shortcut first-keystroke="ctrl 3" />
  </action>

 <action id="IntroduceVariable"> 局部变量
    <keyboard-shortcut first-keystroke="ctrl alt v" />
    <keyboard-shortcut first-keystroke="ctrl 2" />
  </action>

 <action id="ShowQuickFixed"> 快速修改代码错误
    <keyboard-shortcut first-keystroke="alt enter" />
    <keyboard-shortcut first-keystroke="ctrl 1" />
  </action>

<action id="SurroundWith">  快速包裹 try catch if for 
    <keyboard-shortcut first-keystroke="shift alt z" />
 


