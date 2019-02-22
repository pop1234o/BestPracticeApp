package com.liyafeng.video.game;

public class Js_Main {

    /**
     * ======浏览器内核 webkit和Chromium =======
     * https://www.cnblogs.com/zqunor/articles/6820950.html
     *
     * 第一次浏览器大战的主角是IE和Netscape，最终IE凭借着Windows的庞大身躯推倒了Netscape;第二次浏览器大战Netscape浴火重生化身为火狐狸Firefox，一口咬了IE身上一大块肥肉;正在Firefox和IE正缠绵不息之时，突然凭空杀出个Chrome——这名出身豪门Google的小伙子一下子成长得额外精壮，上串势头凶猛，追得两位前辈娇喘吁吁。
     *
     * Webkit源于KDE开源项目，兴盛于苹果公司的Safari项目，它身上有诸多创新，近年来风行的HTML5以及CSS3潮流都和Webkit脱不开关系。Webkit小巧、灵活但又十分强大，而且源代码开放，深得业界喜爱
     *
     * 2008年9月，Google发布了Chrome的测试版，Chrome面世了。Chrome使用了Webkit的代码，继承了Webkit的优良排版引擎，渲染页面速度惊人
     *
     * 既然Chrome使用了Webkit的源代码，也使用了Webkit的排版引擎，那么我们是否就可以认为，Google只是在Webkit上面加了一层壳就做出了Chrome呢?
     *
     * Chrome是Webkit的马甲，这种说法并不准确。实际上Webkit由两部分组成，一部分是WebCore排版引擎，用以解析HTML语言和 CSS框架;另一部分为JSCore JavaScript执行引擎，用以执行网页JS脚本。Chrome只是继承了Webkit的WebCore部分，在JS引擎上使用了Google引以为豪的“V8”引擎，大大地提高了脚本执行速度，这也是为什么Chrome会如此快的重要原因。
     *
     * Chrome还在Webkit上封装了一层Webkit Glue，Chrome中只调用Webkit Glue层的接口使用Webkit组件。与此同时，Chrome的源代码和Webkit也有了很大区别，Google对Webkit的源代码重新进行了梳理，使代码的可读性更好，编译效率更高，并拥有自己的开源项目——Chromium。
     *
     * 既然提到了Chromium，这里就顺带谈一下Chromium和Chrome的区别。不知道从什么时候开始，流行着这样一种说法——Chrome开源。这是错误的。Chrome并非一款自由软件，也没有开放源代码。甚至和Firefox比起来，Chrome还很封闭——因为它无法提供像Firefox一样繁多而高权限的应用接口，这使得Chrome在扩展以及界面定制方面都不如Firefox自由。那Chrome是开源软件一说何来之有?
     *
     * 之所以这种说法，多是因为人们把Chrome和Chromium搞混了。虽然名字很像，图标界面功能什么的都差不多，也同样隶属于Google，但事实上这两者真的不同。Chromium是一款自由软件，遵循BSD许可证开源，开发者可以使用Chromium的源代码进行开发。我们可以认为 Chromium是Google为了发展Chrome而开启的开源计划，Chromium相当于Chrome的实验开源版。两者功能也有微妙的差异，Chrome比Chromium多了PDF阅读器、Flash Player及Google Update等一些小部件。
     *
     * 前面提到，Google对Webkit的代码重新梳理，Chromium代码的可读性和编译效率远比Webkit高。对比 Chromium的代码，Webkit的代码堪比天书，开发难度高得多。Webkit这货不是你想弄，想弄就能弄的啊。更何况Chromium的V8 JS引擎比Webkit的JSCore效率更高，好用又强大的东西干嘛不用呢。
     *
     * 鉴于Chromium和Webkit拥有较大的区别，我们不应该单纯地认为Chrome/Chromium只是在Webkit上面套一个马甲。给Google的工程师们一些掌声吧，因为他们的优秀和努力，世界上多了一个优异的浏览器内核。
     *
     * =========android webview=============
     * Android 4.4 版本 Google 使用了Chromium 替代 Webkit 作为 WebView 内核
     *
     * 腾讯x5内核提供了很多优化
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
