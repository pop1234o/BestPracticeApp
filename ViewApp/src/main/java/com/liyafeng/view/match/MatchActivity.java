package com.liyafeng.view.match;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.liyafeng.view.R;

public class MatchActivity extends Activity {

    /**
     * dip        ： Density independent pixels ，设备无关像素。
     * dp        ：就是dip
     * px        ： 像素
     * dpi       ：dots per inch ， 直接来说就是一英寸多少个像素点。常见取值 120，160，240。我一般称作像素密度，简称密度
     * ppi:   ，这个在手机屏幕中指的也是像素密度，但是这个是物理上的概念，它是客观存在的不会改变.
     * density ： 直接翻译的话貌似叫 密度。常见取值 1.5 ， 1.0 。和标准dpi的比例（160px/inc）
     * 分辨率   ： 横纵2个方向的像素点的数量，常见取值 480X800 ，320X480
     * 屏幕尺寸： 屏幕对角线的长度。1英寸=2.54cm
     * 电脑电视同理。屏幕比例的问题。因为只确定了对角线长，2边长度还不一定。所以有了4：3、16：9这种，这样就可以算出屏幕边长了。
     * <p>
     * <p>
     * ==========dpi和ppi区别==================
     * dots per inch  pixels per inch （对角线的像素个数/对角线的长度inch） 越高像素点越多，越清晰
     * ppi是物理概念，客观上不会改变
     * dpi 是软件参考了物理像素密度(ppi)后，人为指定的一个值
     * 这样dpi有利于适配
     * ============世界上第一款Android手机========
     * 是08年推出的第一款手机 HTC DreamG1
     * 320×480 3.2英寸 180ppi，Android 1.5  2008年5月上市
     *
     * =========Android原生的dp适配方案===========
     * 如果直接用px，那么相同px 在不同分辨率率手机上 表现不一样
     * 比如都是320px，在320*480手机上是沾满宽度，但是480*800上就不一样
     *
     * 所以要找个可转换为px的单位dp，转换规则不能用ppi，因为相同分辨率ppi有可能不一样
     * 所以Android推出了dpi，这个是手机厂商开发时设置的值，app加载的时候通过
     * SystemProperties.getInt("ro.sf.lcd_density", DENSITY_DEFAULT) 来获取到的
     *
     *
     * 320×480作为基准值，他是160dpi ,所以他的缩放因子(density)是1
     * 因为第一款Android手机就是160dpi，所以在这个手机上1dp = 1px
     * 320dp就能沾满宽
     * 在480*800的手机上，dpi是240 ，density = 240/160 = 1.5
     * 1dp = 1.5px
     * 320也能沾满宽
     * （屏幕分辨率放大的比例和 dpi放大的比例是一样的）
     *
     * 后来由于有高分辨率手机出现，但是新技术导致每每英寸能放下更多的像素点（他们放大比例不同）
     * （手机分辨率高了，但是尺寸也增加了，导致360dp才能占满屏幕）
     * 所以出现了资源文件夹的限定符，xhdpi开始就是360dp为基准，以前的是320基准
     * 用 720*1280设计的图，转换成dp，写到xhdpi中， xhdpi一下的dp要乘以 2/3
     * 他们的表现才能一致。
     *
     *
     * * Android的适配尺寸从 DisplayMetrics类中看
     *
     * ===========为什么不用直接ppi========
     * 比如 都是720*1280的分辨率，但是由于屏幕尺寸不同，导致ppi也不同
     * 所以Android就推出了dpi，一样的分辨率设置一样的dpi，这样dp就能转换为px显示了
     * 这样在不同手机屏幕尺寸（相同分辨率）上dp的表现就一样了。
     *
     * ===========Android为什么用160dpi作为基准=========
     * 为什么 px=dp*（dpi/160）？ - 知乎用户的回答 - 知乎
     * https://www.zhihu.com/question/20697111/answer/22722671
     *
     *
     *
     * =========为什么dp会出现适配问题？===============
     * 通过dp加上自适应布局和weight比例布局可以基本解决不同手机上适配的问题，这基本是最原始的Android适配方案。
     *
     * 不是所有的1080P的手机dpi都是480dpi（这是Android标准的）
     * 有的比如Google 的Pixel2（1920*1080）的dpi是420（没有按照标准来设置系统dpi）
     *
     * 所以为了解决这个，一个是用match_parent或者linearlayout的weight来解决，一个就是通过屏幕宽度百分比动态计算
     * 或者使用(Smallest-width)适配方案 比如上面谷歌的手机宽度是 411.428  sw400dp
     * 系统会向下寻找,
     * 当应用为多个资源目录提供不同的 smallestWidth 限定符值时，系统会使用最接近（但未超出）设备 smallestWidth 的值。
     *
     * <p>
     * =================屏幕适配========================
     * <p>
     * https://developer.android.google.cn/training/multiscreen/screendensities.html
     * https://www.jianshu.com/p/ec5a1a30694b （适配方案）
     * https://www.jianshu.com/p/220404575079 （Android设备兼容处理（二）：屏幕适配）
     * https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA 头条适配方案
     *
     * <p>
     * dpi dots per inch 每英寸的像素点个数 ，一定程度上代表屏幕的大小（屏幕密度）
     * <p>
     * dip 独立像素密度  pixel
     * <p>
     * 标准的是 在dpi为160 的设备上 1px = 1dp ，这个160是认为定义
     * <p>
     * mdpi-160-1  ldpi-120-0.75  hdpi-240-1.5 xhdpi-320-2 xxhdpi- 480-3
     * <p>
     * <p>
     * px = dp * (dpi /160)
     * <p>
     * <p>
     * <p>
     * 一般设计师根据某个分辨率设计出来后，有专门的单位转换工具，比如标你妹
     * 上面可以生成其他 dpi的dp值，我们依照写即可
     * <p>
     * 这个是按每英寸的像素来定义的，但是dpi相同分辨率也可能不同
     * 所以需要单独的 values-xxx适配
     * <p>
     * RelativeLayout + 多文件夹的 dp
     * <p>
     * LinearLayout 的权重
     * <p>
     * <p>
     * <p>
     * 代码动态计算宽高
     * <p>
     * 针对特殊value-xx重写布局
     * <p>
     * 对照生成指定的像素转换文件（鸿翔）
     * <p>
     * ==========为什么标准dpi是160dpi （160像素/英寸）======
     * 1 （1）Android Design [1] 里把主流设备的 dpi 归成了四个档次，120 dpi、160 dpi、240 dpi、320 dpi
     * 160 好换算
     * 2.（2）这个在Google的官方文档中有给出了解释，因为第一款Android设备（HTC的T-Mobile G1）是属于160dpi的。
     * <p>
     * <p>
     * ==========================mipmap===================
     * https://developer.android.google.cn/guide/topics/resources/providing-resources.html（官方的目录介绍）
     * mipmap在图片缩放上会有优化，但是在同等dpi的设备上，图片不缩放，那么和drawable是一样的效果
     * 所以mipmap用来存放app的启动图片。原来的图片还是放在drawable下
     * <p>
     * <p>
     *
     * <p>
     * <p>
     * <p>
     * ==================icon========
     * 放在mipmap-*dpi下，文件名为ic_launcher.png
     * <p>
     * LDPI (Low Density Screen，120 DPI)，其图标大小为 36 x 36 px。
     * MDPI (Medium Density Screen, 160 DPI)，其图标大小为 48 x 48 px。
     * HDPI (High Density Screen, 240 DPI)，其图标大小为 72 x 72 px。
     * xhdpi (Extra-high density screen, 320 DPI)，其图标大小为 96 x 96 px。
     * xxhdpi（xx-high density screen, 480 DPI）,其图标大小为144 x 144 px。
     * xxxhdpi（xxx-high density screen, 640 DPI）,其图标大小为192 x 192 px。
     * <p>
     * <p>
     * ===============分辨率对应dpi=========
     * https://blog.csdn.net/u012741741/article/details/51075518
     * 宽×高(标准值)	240×320	320×480	480×800	720×1280	1080×1920	1440×2560
     * DPI等级	    LDPI	MDPI	HDPI	XHDPI	    XXHDPI	    XXXHDPI
     * DPI数值	    120	    160	    240	    320	        480	        640
     * 对应比例	    3	    4	    6	    8	        12	        16
     * 1DP=？PX	    0.75	1	    1.5	    2	        3	        4
     * sw           320dp   320dp   320dp   360dp       360dp       360dp
     * (Smallest-width)屏幕矩形短边的宽度
     * <p>
     * context.getResources().getDisplayMetrics().density
     * 在160dpi的屏幕上，density = 1  dpi的缩放因子  320dpi就是 density = 2
     * <p>
     * context.getResources().getDisplayMetrics().densityDpi
     * 每英寸的像素点数，屏幕密度的另一种表示。densityDpi = density * 160
     * <p>
     * <p>
     * dpi是个范围不是特定的数值，比如440dpi和500dpi都会划分到xxhdpi
     * <p>
     * android中的 2k屏就是宽度像素为1440？？
     * <p>
     * 720P是一种在逐行扫描下达到1280×720的分辨率的显示格式。
     * <p>
     * 720P是美国电影电视工程师协会(SMPTE)制定的高等级高清数字电视的格式标准，
     * 有效显示格式为：1280×720.SMPTE
     * (美国电影电视工程协会)将数字高清信号数字电视扫描线的不同分为1080p、1080i、720P(i是interlace,隔行的意思，p是Progressive,逐行的意思)。
     * <p>
     * 720指的是屏幕的宽度
     * <p>
     * 由于构成数字图像的像素数量巨大，通常以K来表示210即1024，因此：1K=2^10=1024，2K=2^11=2048，4K=2^12=4096。
     * <p>
     * <p>
     * <p>
     * 一般手机的宽度不变，高度可以变，所以根据手机宽度能确定他是哪个 dpi
     * <p>
     *
     * =================ImageView wrap_content时候的宽高======
     * 比如一个图片是1000*1000放在 xxhdpi 下 ， 在小米 mix2 上，density 为2.75
     *
     * 那么ImageView宽高就是， 1000* （2.75/3）
     * 因为在 xxhdpi下转换为dp是 1000/3,但是这个density是2.75,没有准确对应的 xxhdpi目录
     * 所以找接近的，然后按比例缩放即可
     *
     * =========RelativeLayout====
     * 如果里面的控件是 imageview或者textview,是warp_content 这个时候如果外部指定的高度小了
     * 里面会压缩
     * <p>
     * =========layout适配===========================
     * res文件下资源文件夹的命名规则
     * * https://developer.android.google.cn/guide/topics/resources/providing-resources.html
     * (表格上面的的限定符优先级高)
     * <p>
     * res/layout-sw480dp的意思，sw的意思是smallest width最小宽度，
     * 设备的 smallestWidth 是屏幕可用高度和宽度的最小尺寸（您也可以将其视为屏幕的“最小可能宽度”）
     * 应用为多个资源目录提供不同的 smallestWidth 限定符值时，系统会使用最接近（但未超出）设备 smallestWidth 的值。
     * <p>
     * <p>
     * 5.0英寸1080P（1080x1920）的手机屏幕宽度就是1080/(440/160) = 392dp
     * <p>
     * layout-sw480dp-land/
     * <p>
     * <p>
     * 限定符命名规则:
     * 您可以为单组资源指定多个限定符，并使用短划线分隔。例如，drawable-en-rUS-land 适用于横排美国英语设备
     * 这些限定符必须遵循表 2 中列出的顺序。例如：
     * 错误：drawable-hdpi-port/
     * 正确：drawable-port-hdpi/
     * <p>
     * <p>
     * 将备用资源保存到以这些限定符命名的目录中之后，Android 会根据当前设备配置在应用中自动应用这些资源。
     * 每次请求资源时，Android 都会检查备用资源目录是否包含所请求的资源文件，然后查找最佳匹配资源（下文进行介绍）。
     * 如果没有与特定设备配置匹配的备用资源，则 Android 会使用相应的默认资源（一组用于不含配置限定符的特定资源类型的资源）
     * <p>
     * large限定符是 Android3.2 以前的版本
     * sw 限定符是 3.2后的版本
     * a. 版本低于 3.2 的平板会匹配 large的文件
     * b. 版本高于 3.2 的平板会匹配 sw600dp的文件
     * <p>
     * =============平板适配===============
     * 如下文，很显然，平板的smallest-width 都是600dp以上（500dp以上，1600*2560是density=3 是533dp）
     * values-sw500dp
     * drawable-sw500dp-xxxdpi
     * 而手机的sw是320dp或者360dp
     * 所以我们能根据这个来确定哪个是平板，哪个是手机
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * =========1080像素===============
     * https://www.zhihu.com/question/20440405
     * <p>
     * 比如有 1080*1920 （4.95英寸）的手机，和vivo nex 是 1080*2316（6.59英寸）
     * <p>
     * 横屏情况下，他们的高度是一样的，只不过（4.95英寸）的手机屏幕窄，只是显示的内容少了
     * 如果是基于 alignParentRight 的布局那么也会相应缩短，（如果是横向listview那么只是显示内容少了）
     * 如果宽高给指定数值都是100dp，那么这个方块在两款手机上的大小是一样的
     * 但这样，横向占据的屏幕比例就不一样了
     * <p>
     * 16/9=1.77777
     * 4/3 = 1.33333
     * <p>
     * =================主流手机屏幕尺寸=================
     *
     * 红米2       1280x720  4.7英寸
     * 红米6       1440x720  5.45英寸
     * 荣耀畅玩8A  1560x720  6.088英寸
     * vivo Y93   1520x720  6.2英寸  (水滴屏)
     *
     * Android主流的手机 都是1080p，只不过长度会长，
     * 华为p20 ：2244x1080 5.8英寸,
     * vivo nex 2316*1080 6.59英寸
     * 小米mix2 2160*1080 5.99英寸
     * OPPO R17 2340x1080 6.4英寸,
     * 小米6    1920x1080 5.15英寸
     * 小米8    2248x1080 6.21英寸
     * 小米9    2340x1080 6.39英寸,
     * 荣耀V20  2310x1080 6.4英寸,
     * 一加6T   2340x1080 6.41英寸,
     * 魅族16th 2160x1080 6英寸,
     * 小米Max3 2160x1080 6.9英寸
     * 红米Note7 2340x1080 6.3英寸
     * Pixel 4  2220x1080 5.6英寸 普通屏幕
     * 华为麦芒5 1920x1080 5.5英寸 普通屏幕
     * 360 N6  1920x1080  5.5英寸 普通屏幕
     *
     * <p>
     * 华为P30        2880x1440 6英寸, 2:1
     * 华为Mate20Pro  3120×1440 6.39英寸
     * 三星Note9      2960x1440  6.4英寸
     * 三星Galaxy S10  3040x1440 6.1英寸,
     * 一加7 Pro      3120x1440  6.67英寸
     *
     * <p>
     * 很明显，720*1280  1080*1920  1440*2560是个底线，只有比他们长尺寸的手机，不会出现短的
     * 他们三个的宽高比都是相等的(16:9 = 1.777...8 )，所以只是缩放问题
     *
     * 所以UI给的 iphone 尺寸 750*1334 = 1.778666...7 所以我们可以用这个设计图等比缩放即可用在Android上
     * =========平板 ===============
     * 华为的平板就三个尺寸
     * 1280x800（HDPI）    这个对应的手机的是 480×800  缩放因子1.3312501
     * 1920x1200（XHDPI）  这个对应的手机的是 720×1280  缩放因子2.0
     * 2560x1600（XXHDPI） 这个对应的手机的是 1080×1920  缩放因子3.0
     * 这个屏幕扩大的1.333倍 ，而缩放因子却扩大了1.5倍，所以同样的10dp，在2560x1600的屏幕上显示要大1.125倍
     * （如果这两个分辨率的屏幕在物理上一样大）
     * <p>
     * 所以 1920x1200 的中点位置是 1920/2=960px =480dp
     * 2560x1600 的中点是    2560/2 = 1280px = 426.66dp
     * <p>
     * 480dp/426.66dp = 1.125 ( 3/2/(4/3)=1.125 )
     * <p>
     * 这三个的比例一致都是1.6
     * 1920x1200 => 2560x1600 大了 4/3=1.33 倍 而缩放因子大了1.5倍
     * <p>
     * 1280x800 => 1920x1200 大了 1.5倍 ，而缩放因子大了 1.50倍
     * <p>
     * 所以10dp宽度在 1280x800  1920x1200 大小是一样的，所以只需要一套dp即可
     * <p>
     * 1920x1200 => 2560x1600 在前者的10dp,后者就需要写 10dp/1.125 = 8.8888dp
     * 所以在这两个屏幕上 一个写10dp 一个写8.88dp ，两者在屏幕上的占比是一样的
     * <p>
     * <p>
     * <p>
     * ==================主流平板=================
     * 主流的平板都是上面的尺寸
     * 还有             1024*768 2048x1536 这种尺寸就是模仿 ipad  4/3 = 1.33
     * 三星、神州都有这种尺寸的平板
     * <p>
     * 所以Android pad会长点
     * <p>
     * 华为平板 M5 青春版
     * 1920x1200 10.1英寸
     * <p>
     * 华为平板 M5 8.4英寸
     * 2560x1600
     * <p>
     * 荣耀畅玩平板2（9.6英寸）
     * 1280x800
     * <p>
     * <p>
     * mi pad 4   8英寸
     * 1920*1200
     * <p>
     * <p>
     * 小米平板1-3 7.9英寸 （停产）
     * 2048x1536
     * <p>
     * <p>
     * 三星Galaxy Tab S5e 10.5英寸
     * 2560x1600
     * <p>
     * <p>
     * 华硕ZenPad 3S 10（Z500M/64GB） 9.7英寸
     * 2048x1536
     * <p>
     * <p>
     * ==========2018.1月市售手机分辨率=============
     * 安卓：
     * 2560 x 1440
     * 1920 x 1080
     * 1280 x 720
     * ==========适配===============
     * 适配目的，就是所有分辨率变为宽度一样，然后宽向的10dp的大小是一样的这样达到目的
     * <p>
     * <p>
     * 不管什么分辨率的手机，我们都可以将宽度缩放到 720 来看
     * 这时手机宽度一样，只是宽高比例不一样
     * 所以我们只需要360dp的ui图（比如 720*1280 1080*1920 1440*2560）
     * 然后换算成dp即可，10dp 在这三个手机上的大小是一样的（如果这三个手机尺寸一样）
     * <p>
     * 现在手机长度都长，所以我们还是可以根据dp，只不过长短不一样
     * <p>
     * <p>
     * <p>
     * <p>
     * =========适配总结================
     * 使用"wrap_content"、"match_parent"和"weight“来控制视图组件的宽度和高度
     * 使用ConstantLayout 适配
     * <p>
     * 我们需要一个基准设计图，比如 360dp的，就是像素是 720*1280  1080*1920 1440*2560 其中之一
     * 用ios的设计图可以改一下宽高，例如我们用 720*1280 的，然后就把上面控件的宽高除以2 就是dp值
     * 写入xml中，然后在Oncreate中使用头条的适配方案（重新计算density）即可
     *
     * <p>
     * <p>
     * ==========头条适配方案============
     * 1920*1080，屏幕尺寸为5吋的话，那么dpi为440
     * <p>
     * 假设我们UI设计图是按屏幕宽度为360dp来设计的，那么在上述设备上，屏幕宽度其实为1080/(440/160)=392.7dp，
     * 也就是屏幕是比设计图要宽的。这种情况下， 即使使用dp也是无法在不同设备上显示为同样效果的。 同时还存在部分设备屏幕宽度不足360dp，
     * 这时就会导致按360dp宽度来开发实际显示不全的情况。
     * 而且上述屏幕尺寸、分辨率和像素密度的关系，很多设备并没有按此规则来实现， 因此dpi的值非常乱，没有规律可循，从而导致使用dp适配效果差强人意。
     * <p>
     * 总结下大致需求如下：
     * <p>
     * 支持以宽或者高一个维度去适配，保持该维度上和设计图一致；
     * 支持dp和sp单位，控制迁移成本到最小。
     * <p>
     * <p>
     * 找兼容突破口
     * <p>
     * 从dp和px的转换公式 ：px = dp * density
     * <p>
     * 可以看出，如果设计图宽为360dp，想要保证在所有设备计算得出的px值都正好是屏幕宽度的话，我们只能修改 density 的值。
     * <p>
     * <p>
     * <p>
     * 通过阅读源码，我们可以得知，density 是 DisplayMetrics 中的成员变量，
     * 而 DisplayMetrics 实例通过 Resources#getDisplayMetrics 可以获得，
     * 而Resouces通过Activity或者Application的Context获得。
     * <p>
     * <p>
     * 先来熟悉下 DisplayMetrics 中和适配相关的几个变量：
     * DisplayMetrics#density 就是上述的density
     * DisplayMetrics#densityDpi 就是上述的dpi
     * DisplayMetrics#scaledDensity 字体的缩放因子，正常情况下和density相等，但是调节系统字体大小后会改变这个值
     * 所以 dp是 value* density   sp = value* scaledDensity
     * 因为系统能调节字体大小，所以才有了sp
     * <p>
     * <p>
     * 首先来看看布局文件中dp的转换，最终都是调用 TypedValue#applyDimension(int unit, float value, DisplayMetrics metrics) 来进行转换
     * <p>
     * 再看看图片的decode，BitmapFactory#decodeResourceStream方法:可见也是通过 DisplayMetrics 中的值来计算的。
     * <p>
     * <p>
     * 下面假设设计图宽度是360dp，以宽维度来适配。
     * 那么适配后的 density = 设备真实宽(单位px) / 360，接下来只需要把我们计算好的 density 在系统中修改下即可，代码实现如下：
     * <p>
     * 比如 720*1280为基准，他的density=2   有个1080的设备 ，那就是density=(1080/720)*2 =1080/360=3
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
    }


    /**
     * 这个在baseActivity的onCreate中调用即可
     */
    public static class ScreenUtil {
        public static float sNoncompatDensity;//原始的Density
        public static float sNoncompatScaledDensity;

        public static void setCustomDensity(Activity activity, final Application application) {
            DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
            if (sNoncompatDensity == 0) {
                sNoncompatDensity = displayMetrics.density;
                sNoncompatScaledDensity = displayMetrics.scaledDensity;
                application.registerComponentCallbacks(new ComponentCallbacks() {

                    @Override
                    public void onConfigurationChanged(Configuration newConfig) {
                        if (newConfig != null && newConfig.fontScale > 0) {
                            sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                        }


                        //如果我们是横屏，那么息屏变竖屏，然后唤醒后会发生横竖屏的变化，所以变为横屏的时候需要重新计算一次 density
//                        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                            Toast.makeText(BrandyApplication.getInstance(), "现在是竖屏==", Toast.LENGTH_SHORT).show();
//                        }
//                        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                            Toast.makeText(BrandyApplication.getInstance(), "现在是横屏==", Toast.LENGTH_SHORT).show();
//                            WeakReference<Activity> reference = new WeakReference<>(activity);
//                            ScreenUtil.setCustomDensity(reference.get(), BrandyApplication.getInstance());
//
//                        }
                    }

                    @Override
                    public void onLowMemory() {

                    }
                });
            }


            float targetDensity = displayMetrics.widthPixels / 360.0f;//横屏情况下width是高，而且横屏要减去状态栏高度
            float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
            int targetDensityDpi = (int) (160 * targetDensity);

            displayMetrics.density = targetDensity;
            displayMetrics.scaledDensity = targetScaledDensity;
            displayMetrics.densityDpi = targetDensityDpi;

            DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

            activityDisplayMetrics.density = targetDensity;
            activityDisplayMetrics.scaledDensity = targetScaledDensity;
            activityDisplayMetrics.densityDpi = targetDensityDpi;


        }
    }


    /**
     * ============主流适配方案===========
     * dp + weight + 百分比动态代码计算像素 + 相对布局 + 滚动设计 +match_parent +ConstraintLayout
     * 优点：方便，原生支持，能适配主流机器
     * 缺点：对于个别机型还得特殊处理
     *
     * 加上value-hdpi适配
     *
     * ----------
     * 宽高限定符适配
     * values-768*1024
     * 这样给特殊的像素特殊的dp
     *
     * -------
     * smallestWidth 限定符适配方案
     * 写各个sw360dp是标准的  sw320dp是小屏
     *
     *
     *
     *
     *
     *
     */
    void a1(){}

}
