
主题
https://developer.android.google.cn/develop/ui/views/theming/themes?hl=zh-cn

item定义的属性，会应用于所有控件（如果属性能匹配上），不匹配的不生效

设置style只能作用于一个控件，而theme是所有子控件（如果属性能匹配的话）

###样式
继承 Android 平台的默认文本外观
<style name="GreenText" parent="@android:style/TextAppearance">
    <item name="android:textColor">#00FF00</item>
</style>

从库或您自己的项目继承样式，请声明父样式名称，但不要包含上例中所示的 @android:style/ 部分

继承了支持库中的文本外观样式
<style name="GreenText" parent="TextAppearance.AppCompat">
    <item name="android:textColor">#00FF00</item>
</style>


通过使用点表示法来扩展样式的名称（而不是使用 parent 属性）（不能扩展其他库中的样式）
例如，以下样式沿用了上例中 GreenText 的所有样式，然后增加了文本大小：
<style name="GreenText.Large">  这里继承了上面GreenText中的样式
    <item name="android:textSize">22dp</item>
</style>


###设置主题
<manifest ... >
<application ... >
<activity android:theme="@style/Theme.AppCompat.Light" ... >
</activity>
</application>
</manifest>
。如果某个视图仅支持在样式中声明的部分属性，则会仅应用这些属性，而忽略不支持的属性。

style属性优先级大于theme属性的设置，如果一样的话。


<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>
colorPrimary 这些都是系统预定义的，
此 AppTheme 样式扩展了支持库中的主题，并替换了应用栏和悬浮操作按钮（如有）等关键界面元素所使用的颜色属性
如需查看可在主题中使用的属性列表，请参阅位于 R.styleable.Theme 的属性表

大多数属性会应用于特定类型的视图（View），也有些属性会应用于所有视图
支持库中的属性名称不使用 android: 前缀。 该字符串(android: )仅用于 Android 框架中的属性。(而不是support library)


###兼容
res/values/styles.xml        # themes for all versions
res/values-v21/styles.xml    # themes for API level 21+ only
可以继承


###自定义
当您使用支持库中的主题为应用设置theme时，Button 的实例会使用 Widget.AppCompat.Button 样式来设置样式

<Button
style="@style/Widget.AppCompat.Button.Borderless"
... />


如果要将此样式应用于所有按钮，您可以在主题的 buttonStyle 中声明此样式，如下所示：
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="buttonStyle">@style/Widget.AppCompat.Button.Borderless</item>
    ...
</style>

### 直接引用  theme attributes
<ViewGroup …
android:background="?attr/colorSurface">
为什么这样写，因为在深色模式中很有用，直接引用主题中的颜色

?attr/themeAttributeName这个语法是引用主题中的属性。

<ViewGroup …
android:theme="@style/Theme.App.SomeTheme">
<!SomeTheme also applies to all child views. 
ViewGroup>

在布局创建后 setTheme或者 applyStyle将无效


?attr/colorSurface
?android:attr/colorBackground

一个是Android本身定义的，一个是库中定义的。

### AppCompat
需要继承AppCompatActivity ,里面setContentView
AppCompatDelegateImpl ,里面LayoutInflater.Factory2 ，AppCompatViewInflater
把TextView都替换成了 AppCompatTextView  ，所以主题才能用 AppCompat的主题，才能偷天换日
在布局中设置一下 style，或者theme，控件的样式就能在 AppCompatTextView中读取，然后设置生效。
（原本系统的TextView里面就系统属性，根本没法用AppCompat的样式，没那逻辑）


### 常见的属性
https://medium.com/androiddevelopers/android-styling-common-theme-attributes-8f7c50c9eaba
<ConstraintLayout ...
-  android:foreground="@drawable/some_ripple"
-  android:background="@color/blue" />
+  android:foreground="?attr/selectableItemBackground"
+  android:background="?attr/colorPrimarySurface" />
+ 
   ?attr/colorPrimary The primary branding color for the app.
   ?attr/colorSecondary The secondary branding color for the app, usually a bright complement to the primary branding color.
   ?attr/colorOn[Primary, Secondary, Surface etc] A color which contrasts against the named color.
   ?attr/color[Primary, Secondary]Variant An alternate shade of the given color.
   ?attr/colorSurface A color for surfaces of components, such as cards, sheets, and menus.
   ?android:attr/colorBackground The background for the screen.
   ?attr/colorPrimarySurface switches between colorPrimary in the Light themes, colorSurface in the Dark theme.
   ?attr/colorError A color for displaying errors.
+ ?android:attr/textColorPrimary The most prominent text color.
  ?android:attr/textColorSecondary Secondary text color.

+ 上面这些都是 compat库中定义的，可以直接用， 当然compat中的控件也是默认用了上面规则的颜色。 
+ 比如colorPrimary用在tabbar或者其他背景上（如果你没有自己设置的话），这些定义都遵循的mert设计规范。
+ 
### 自定义属性
https://medium.com/androiddevelopers/android-styling-common-theme-attributes-8f7c50c9eaba
如果你像自定义一些颜色规则。
attrs.xml 中定义属性
<attr name="sessionListKeyline" format="dimension" />
在不同的theme中提供
<style name="Theme.IOSched.Schedule">
  <item name="sessionListKeyline">72dp</item>
</style>

<style name="Theme.IOSched.Speaker">
  <item name="sessionListKeyline">16dp</item>
</style>
使用属性
<Guideline …
app:layout_constraintGuide_begin="?attr/sessionListKeyline" />


### 深色模式适配
定义两个不同的主题，在 value 和 value-night下，相同名称
然后下面定义颜色属性（颜色只定义一套） 可以参考新建的project

<ViewGroup …
android:background="?attr/colorSurface">

好处就是
providing a different background color in light and dark themes) 

without having to create multiple layouts or styles which 
are mostly identical but for a few color variations.（大部分一样，但是只有颜色不一样）

或者直接写@color/xxx ，定义两套color也是可以的。theme只不过是可以通用了，页面中所有控件都生效。
你可以不用重复写textColor 那种代码了。

动态切换
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
在 API 级别 31 及更高级别上 （Android12 ）
系统便可以在启动画面期间匹配主题  ,不重新启动app??
(context?.getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager)?.setApplicationNightMode(
UiModeManager.MODE_NIGHT_YES
)
} else {

从 AppCompat v1.1.0 开始，setDefaultNightMode() 会自动重新创建任何已启动的 activity。
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
}



###Force Dark
1.必须浅色主题 2 开启了forcedark，
Android 10 提供了 Force Dark 功能，此功能可让开发者快速实现深色主题，而无需明确设置 DayNight 主题

如果您的应用使用深色主题（例如 Theme.Material），则系统不会应用 Force Dark。同样，如果应用的主题继承自 DayNight 主题，则系统不会应用 Force Dark，因为会自动切换主题



### 常用属性
?attr/colorPrimary The primary branding color for the app.
?attr/colorSecondary The secondary branding color for the app, usually a bright complement to the primary branding color.
?attr/colorOn[Primary, Secondary, Surface etc] A color which contrasts against the named color.
?attr/color[Primary, Secondary]Variant An alternate shade of the given color.
?attr/colorSurface A color for surfaces of components, such as cards, sheets, and menus.
?android:attr/colorBackground The background for the screen.
?attr/colorPrimarySurface switches between colorPrimary in the Light themes, colorSurface in the Dark theme.
?attr/colorError A color for displaying errors.
Other handy colors:

?attr/colorControlNormal The color applied to icons/controls in their normal state.
?attr/colorControlActivated The color applied to icons/controls in their activated state (e.g. checked).
?attr/colorControlHighlight The color applied to control highlights (e.g. ripples, list selectors).
?android:attr/textColorPrimary The most prominent text color.
?android:attr/textColorSecondary Secondary text color.

