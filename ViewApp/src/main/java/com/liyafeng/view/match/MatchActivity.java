package com.liyafeng.view.match;

import android.app.Activity;
import android.os.Bundle;

import com.liyafeng.view.R;

public class MatchActivity extends Activity {

    /**
     *
     * dip        ： Density independent pixels ，设备无关像素。
     * dp        ：就是dip
     * px        ： 像素
     * dpi       ：dots per inch ， 直接来说就是一英寸多少个像素点。常见取值 120，160，240。我一般称作像素密度，简称密度
     * density ： 直接翻译的话貌似叫 密度。常见取值 1.5 ， 1.0 。和标准dpi的比例（160px/inc）
     * 分辨率   ： 横纵2个方向的像素点的数量，常见取值 480X800 ，320X480
     * 屏幕尺寸： 屏幕对角线的长度。1英寸=2.54cm
     * 电脑电视同理。屏幕比例的问题。因为只确定了对角线长，2边长度还不一定。所以有了4：3、16：9这种，这样就可以算出屏幕边长了。
     *
     *
     *
     * =================屏幕适配========================
     *
     * https://developer.android.google.cn/training/multiscreen/screendensities.html
     * https://www.jianshu.com/p/ec5a1a30694b （适配方案）
     * https://www.jianshu.com/p/220404575079 （Android设备兼容处理（二）：屏幕适配）
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
     *  160 好换算
     * 2.（2）这个在Google的官方文档中有给出了解释，因为第一款Android设备（HTC的T-Mobile G1）是属于160dpi的。
     *
     *
     * ==========================mipmap===================
     * https://developer.android.google.cn/guide/topics/resources/providing-resources.html（官方的目录介绍）
     * mipmap在图片缩放上会有优化，但是在同等dpi的设备上，图片不缩放，那么和drawable是一样的效果
     * 所以mipmap用来存放app的启动图片。原来的图片还是放在drawable下
     * <p>
     * <p>
     * ===================res文件下资源文件夹的命名规则======================
     * https://developer.android.google.cn/guide/topics/resources/providing-resources.html
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
     *
     *
     * ===============分辨率对应dpi=========
     * https://blog.csdn.net/u012741741/article/details/51075518
     * 宽×高(标准值)	240×320	320×480	480×800	720×1280	1080×1920	1440×2560
     * DPI等级	    LDPI	MDPI	HDPI	XHDPI	    XXHDPI	    XXXHDPI
     * DPI数值	    120	    160	    240	    320	        480	        640
     * 对应比例	    3	    4	    6	    8	        12	        16
     * 1DP=？PX	    0.75	1	    1.5	    2	        3	        4
     *
     *
     * dpi是个范围不是特定的数值，比如440dpi和500dpi都会划分到xxhdpi
     *
     * android中的 2k屏就是宽度像素为1440？？
     *
     * 720P是一种在逐行扫描下达到1280×720的分辨率的显示格式。
     *
     * 720P是美国电影电视工程师协会(SMPTE)制定的高等级高清数字电视的格式标准，
     * 有效显示格式为：1280×720.SMPTE
     * (美国电影电视工程协会)将数字高清信号数字电视扫描线的不同分为1080p、1080i、720P(i是interlace,隔行的意思，p是Progressive,逐行的意思)。
     *
     * 720指的是屏幕的宽度
     *
     * 由于构成数字图像的像素数量巨大，通常以K来表示210即1024，因此：1K=2^10=1024，2K=2^11=2048，4K=2^12=4096。
     *
     *
     *
     * 一般手机的宽度不变，高度可以变，所以根据手机宽度能确定他是哪个 dpi
     *
     * =========RelativeLayout====
     * 如果里面的控件是 imageview或者textview,是warp_content 这个时候如果外部指定的高度小了
     * 里面会压缩
     *
     *
     *
     * =========1080像素===============
     * https://www.zhihu.com/question/20440405
     *
     * 比如有 1080*1920 （4.95英寸）的手机，和vivo nex 是 1080*2316（6.59英寸）
     *
     * 横屏情况下，他们的高度是一样的，只不过（4.95英寸）的手机屏幕窄，只是显示的内容少了
     * 如果是基于 alignParentRight 的布局那么也会相应缩短，（如果是横向listview那么只是显示内容少了）
     * 如果宽高给指定数值都是100dp，那么这个方块在两款手机上的大小是一样的
     * 但这样，横向占据的屏幕比例就不一样了
     *
     * 16/9=1.77777
     * 4/3 = 1.33333
     *
     * Android主流的手机 都是1080p，只不过长度会长，
     * 华为p20 ：2244x1080 5.8英寸,
     * vivo nex 1080*2316 6.59英寸
     * 小米mix2 1080*2160 5.99英寸
     * OPPO R17 2340x1080 6.4英寸,
     * 小米9    2340x1080 6.39英寸,
     * 荣耀V20  2310x1080 6.4英寸,
     * 一加6T   2340x1080 6.41英寸,
     * 魅族16th 2160x1080 6英寸,
     * 小米Max3 2160x1080 6.9英寸
     *
     * 华为P30  2880x1440 6英寸, 2:1
     * 三星Galaxy S10  3040x1440 6.1英寸,
     *
     * =========平板 ===============
     * 华为的平板就三个尺寸 1280x800 1920x1200 2560x1600
     *
     * 华为平板 M5 青春版
     * 1920x1200 10.1英寸
     *
     * 华为平板 M5 8.4英寸
     * 2560x1600
     *
     * 荣耀畅玩平板2（9.6英寸）
     * 1280x800
     *
     *
     * mi pad 4   8英寸
     * 1920*1200
     *
     *
     * 小米平板1-3 7.9英寸 （停产）
     * 2048x1536
     *
     *
     * 三星Galaxy Tab S5e 10.5英寸
     * 2560x1600
     *
     *
     *
     *
     *
     *
     *
     * ==========2018.1月市售手机分辨率=============
     * 安卓：
     * 2560 x 1440
     * 1920 x 1080
     * 1280 x 720
     *
     *
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
    }
}
