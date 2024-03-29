package com.liyafeng.video;

public class Video {


    /**
     * 视频入门指南
     * https://zhuanlan.zhihu.com/p/28518637 （七牛音视频架构师）
     * https://blog.csdn.net/leixiaohua1020 （雷霄骅(leixiaohua1020)的专栏）
     *
     *
     * ===================3gp================
     * 3GP是一种多媒体单元格式，由Third Generation Partnership Project（3GPP）定义，主要用于3G手机上。
     * 3GP是 MPEG-4 第12部分，又被称为MPEG-4/JPEG2000基本媒体文件格式
     * 3GP是MPEG-4 Part 14（MP4）的一种简化版本，减少了存储空间和较低的带宽需求，
     * 让手机上有限的存储空间可以使用。
     * 3GP文件视频的部分可以用MPEG-4 Part 2、H.263或MPEG-4 Part 10 (AVC/H.264)等格式来存储，
     * 声音的部分则支持AMR-NB、AMR-WB、AMR-WB+、AAC-LC或HE-AAC来当作声音的编码。
     * <p>
     * --------------------------
     * 3gp是一种容器，除了头部的视频信息外，还包括编码后的视频信息，而且支持多种编码方式
     * 很显然头部信息包含了编码方式。而且播放器的原理都是一样的，取出文件的头部信息
     * 然后将视频信息解码，然后播放
     * <p>
     *
     * <p>
     * =======================mp4=========================
     * MP4或称MPEG-4第14部分（英语：MPEG-4 Part 14）是一种标准的数字多媒体容器格式
     * 注意是容器格式
     * ---------------------------------
     * 同时拥有音频視频的MPEG-4文件通常使用标准扩展名.mp4
     * 仅有音频的MPEG-4文件会使用.m4a扩展名
     * <p>
     * * http://www.infoq.com/cn/articles/improving-android-video-on-news-feed-with-litho
     * * Facebook构建高性能Android视频组件实践之路
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * https://m.huxiu.com/article/263561.html （从.JPG到.AVI：视频编码最强入门科普）
     * https://www.jianshu.com/p/1379ed99783e ( 视频相关的理论知识与基础概念)
     * <p>
     * <p>
     * ============基础知识============
     * <p>
     * ---------像素（图像的元素）-----------------
     * 像素点的英文叫Pixel（缩写为PX）。这个单词是由 Picture(图像) 和 Element（元素）这两个单词的字母所组成的。
     * <p>
     * 像素是图像显示的基本单位。我们通常说一幅图片的大小，例如是1920×1080，就是长度为1920个像素点，宽度为1080个像素点。
     * 乘积是2,073,600，也就是说，这个图片是两百万像素的。
     * 1920×1080，这个也被称为这幅图片的分辨率
     * <p>
     * ---------ppi------------
     * PPI，就是“Pixels Per Inch”，每英寸像素数。也就是，手机（或显示器）屏幕上每英寸面积，到底能放下多少个“像素点”。
     * 这个值当然是越高越好啦！PPI越高，图像就越清晰细腻。
     * <p>
     * 以前的功能机，例如诺基亚，屏幕PPI都很低，有很强烈的颗粒感
     * 后来，苹果开创了史无前例的“视网膜”（Retina）屏幕，PPI值高达326（每英寸屏幕有326像素），画质清晰，再也没有了颗粒感
     * <p>
     * -------------rgb-------------
     * 任何颜色，都可以通过红色（Red）、绿色（Green）、蓝色（Blue）按照一定比例调制出来。这三种颜色，被称为“三原色”。
     * 在计算机里，R、G、B也被称为“基色分量”。它们的取值，分别从0到255，一共256个等级
     * 256×256×256=16,777,216种，因此也简称为1600万色。
     * <p>
     * 256是2的8次方，所以最少一个8位的二进制就能表示256个数，所以三个颜色有占用内存的24位
     * 因此，这种方式表达出来的颜色，也被称为24位色。
     * <p>
     * 所以这种表示1一个像素点占用24bit内存
     * <p>
     * ------------帧率（Frame Rate）--------------
     * 在视频中，一个帧（Frame）就是指一幅静止的画面。帧率，
     * 就是指视频每秒钟包括的画面数量（FPS，Frame per second）。
     * 帧率越高，视频就越逼真、越流畅。
     */
    public void f1() {
    }


    /**
     * ==================视频采集===============
     * <p>
     * ------------摄像头工作原理-----------
     * https://www.cnblogs.com/fjutacm/p/220631977df995512d136e4dbd411951.html （camera理论基础和工作原理）
     * <p>
     * 光线通过镜头进入摄像头内部，然后经过IR Filter过滤红外光，最后到达sensor（传感器），
     * senor分为按照材质可以分为CMOS和CCD两种，可以将光学信号转换为电信号，
     * 再通过内部的ADC电路转换为数字信号，
     * 然后传输给DSP（如果有的话，如果没有则以DVP的方式传送数据到基带芯片baseband，此时的数据格式Raw Data，后面有讲进行加工）加工处理，
     * 转换成RGB、YUV等格式输出
     * <p>
     * 摄像头模组，Camera Compact Module，简写为CCM，是影响捕捉的重要元器件，我的理解就是硬件上的摄像头
     * 1.镜头
     * 一般由几片透镜组成透镜结构，按材质可分为塑胶透镜(plastic)或玻璃透镜(glass)
     * 透镜越多，成本越高，相对成像效果会更出色（个人理解是光线更均匀、更细致；对光线的选通更丰富；成像畸变更小，但是会导致镜头变长，光通量变小）。
     * 2.红外滤光片 IR Filter
     * 主要是过滤掉进入镜头的光线中的红外光，这是因为人眼看不到红外光，但是sensor却能感受到红外光，所以需要将光线中的红外光滤掉，以便图像更接近人眼看到的效果
     * <p>
     * 3.传感器 Sensor
     * sensor是摄像头的核心，负责将通过Lens的光信号转换为电信号，再经过内部AD转换为数字信号。
     * 每个pixel像素点只能感受R、G、B中的一种，因此每个像素点中存放的数据是单色光，
     * 所以我们通常所说的30万像素或者130万像素，表示的就是有30万或130万个感光点，
     * 每个感光点只能感应一种光，这些最原始的感光数据我们称为RAW Data。
     * Raw Data数据要经过ISP（应该理解为Image Sensor Processor，是Sensor模块的组成部分，下面有解释）的处理才能还原出三原色，
     * 也就是说如果一个像素点感应为R值，那么ISP会根据该感光点周围的G、B的值，
     * 通过插值和特效处理等，计算出该R点的G、B值，这样该点的RGB就被还原了，除此之外，ISP还有很多操作，
     * <p>
     * >>CCD（Charge Coupled Device），电荷耦合器件传感器：使用一种高感光度的半导体材料制成，能把光线转变成电荷，
     * 通过模数转换器芯片转换成电信号。CCD由许多独立的感光单位组成，通常以百万像素为单位。当CCD表面受到光照时，
     * 每个感光单位都会将电荷反映在组件上，所有的感光单位产生的信号加在一起，就构成了一幅完整的图像。CCD传感器以日本厂商为主导，
     * 全球市场上有90%被日本厂商垄断，索尼、松下、夏普是龙头。
     * >>CMOS（Complementary Metal-Oxide Semiconductor），互补性氧化金属半导体：主要是利用硅和锗做成的半导体，
     * 使其在CMOS上共存着带N(-)和P(+)级的半导体，这两个互补效应所产生的电流可以被处理芯片记录并解读成影像。
     * CMOS传感器主要以美国、韩国和中国台湾为主导，主要生产厂家是美国的OmnVison、Agilent、Micron，
     * 中国台湾的锐像、原相、泰视等，韩国的三星、现代。
     * <p>
     * 4.图像处理芯片 DSP （digital singnal processor）
     * DSP是CCM的重要组成部分，它的作用是将感光芯片获得的数据及时地快速地传递到中央处理器并刷新感光芯片，
     * 因此DSP芯片的好坏，直接影响画面品质，如：色彩饱和度、清晰度、流畅度等。
     * 如果sensor没有集成DSP，则通过DVP的方式传输到baseband芯片中（可以理解为外挂DSP），
     * 进入DSP的数据是RAW Data，采集到的原始数据。
     * 如果集成了DSP，则RAW Data会经过AWB、color matrix、lens shading、gamma、sharpness、AE和de-noise处理，
     * 最终输出YUV或者RGB格式的数据。
     *
     *
     * <p>
     * 总结:摄像头模组，光信号 -(传感器)> 电信号  -(A/D)> Raw data  -(DSP)>  rgb或yuv
     * <p>
     * 光学图像再同学半导体的图像传感器生成电学信号；
     * 电学信号由A/D转换器转化为数字图像信号；
     * 数字图像信号经由DSP处理，输出RGB或者YUV
     * <p>
     * --------------A/D转换----------------
     * 将模拟信号转换成数字信号的电路，称为模数转换器
     * （简称a/d转换器或adc,analog to digital converter）
     * <p>
     * A/D转换的作用是将时间连续、幅值也连续的模拟量转换为时间离散、幅值也离散的数字信号，因此，A/D转换一般要经过取样、保持、量化及编码4个过程。
     * 在实际电路中，这些过程有的是合并进行的，例如，取样和保持，量化和编码往往都是在转换过程中同时实现的。
     * <p>
     * <p>
     * ==================采样率================
     * 采样率，从连续的模拟信号中 转换为数字信号 的 频率
     * 比如采样率为 10kHz 就是说1秒内从模拟信号中采取 10k 个数字信号
     * 那么它的倒数就是0.1/1k，代表取一个数字信号所用的时间
     * 采样率越高代表 越接近 真实
     * <p>
     * 连续的光信号转化为连续的电信号，从连续的电信号中取样，取出raw data，输出给dsp
     * <p>
     * =================赫兹=========
     * 赫兹 hz 频率单位，指1秒内 "运动周期"的 次数
     * <p>
     * ============码率/比特率===========
     * 码率 = 比特率 = bit/s = bit per second = bps
     * 采样率*每个样本的bit = 码率
     * 清晰度高，每个样本的bit就大（更清晰）， 或者采样率高（更流畅）  =  视频更接近真实
     * <p>
     * 一个MP4的比特率就是文件字节数*8bit/ 视频长度
     * <p>
     * 采样率是1秒取多少个样点，码率是一秒钟所有样点占用内存的大小
     *
     *
     * ============主流采样方式============
     * 通常用的是YUV4:2:0的采样方式，能获得1/2的压缩率
     *
     *
     *
     *
     */
    void f2() {
    }


    /**
     * ==============音频采集===============
     * PCM Pulse-code modulation 脉冲编码调制  （调制=调整，转化）模拟信号-》数字信号 叫调制
     * PCM是一种调制规则，并不是编码（压缩）规则
     * pcm是音频 由模拟信号转换为数字信号的规则
     *
     * AudioRecord 采集pcm数据
     * 双声道(stereo)  单声道 mono
     *
     * 在16位声卡中有22KHz、44KHz等几级,当中，22KHz相当于普通FM广播的音质，44KHz已相当于CD音质了，眼下的经常使用採样频率都不超过48KHz。
     *
     *  音频 PCM 8/16 bit per sample 44100hz
     *
     *  8bit    1 字节(也就是8bit) 只能记录 256 个数, 也就是只能将振幅划分成 256 个等级;
     *  16 bit  2 字节(也就是16bit) 可以细到 65536 个数, 这已是 CD 标准了;
     *
     */
    void f2_1(){}


    /**
     * ===============yuv==================
     * YUV（Y'CbCr，Y'PbPr）和RGB都是颜色编码的方案，
     * Y代表亮度，UV代表色彩信息
     * yuv y表示亮度 u和v表示色差(u和v也被称为：Cb－蓝色差，Cr－红色差)，
     * b-blue r-red
     * u和v作用是描述影像色彩及饱和度
     * <p>
     * Y'UV最大的优点在于只需占用极少的带宽。
     * <p>
     * YUV数据有两种格式
     * 紧缩格式（packed formats）yuv数据聚集在一起的数组
     * 平面格式（planar formats）三个分量存储在不同的矩阵中（代表一种存储风格）
     *
     *
     *  YUV，分为三个分量，
     *  “Y”表示明亮度（Luminance或Luma），也就是灰度值；（从黑到白，所以黑白电视只要Y即可）
     *  而“U”和“V” 表示的则是色度（Chrominance或Chroma），
     *  作用是描述影像色彩及饱和度，用于指定像素的颜色。
     *  采样，光信号转换为数字信号
     *  主流的采样方式有三种，YUV4:4:4，YUV4:2:2，YUV4:2:0 （见drawable）
     *  1. YUV 4:4:4采样，每一个Y对应一组UV分量8+8+8 = 24bits,3个字节。
     *  2. YUV 4:2:2采样，每两个Y共用一组UV分量,一个YUV占8+4+4 = 16bits 2个字节。
     *  3. YUV 4:2:0采样，每四个Y共用一组UV分量一个YUV占8+2+2 = 12bits  1.5个字节。
     *  也就是一个y是一个像素点，每个y大小是8位（一个byte） u是8位，v是8位
     *  而YUV 4:2:2 是每两个y用一个uv ，所以一个y就是4位的u和v，
     *
     * =====存储
     * 是 yyyyyyyyuvuv 代表8个像素，如此循环
     *
     * ========YUV420===============
     * 因为YUV420比较常用，（4个y共享一个u和一个v） 在这里就重点介绍YUV420。YUV420分为两种：YUV420p和YUV420sp
     * (格式见drawable)
     * 如图
     * YUV420p：又叫planer平面模式，Y ，U，V分别再不同平面，也就是有三个平面。
     * YUV420sp：又叫bi-planer或two-planer双平面，Y一个平面，UV在同一个平面交叉存储。
     * <p>
     * 根据uv的存储顺序不同
     * <p>
     * YUV420p 又细分：（这个是平面模式，所以yuv是存在三个数组中）
     * I420(又叫YU12): 安卓的模式。存储顺序是先存Y，再存U，最后存V。yyyyyyyyuuvv(4个y公用一个u和v)
     * YV12:存储顺序是先存Y，再存V，最后存U。yyyyyyyyvvuu
     * <p>
     * YUV420sp又分为 (这个是uv交错)
     * NV12:IOS只有这一种模式。存储顺序是先存Y，再UV交替存储。yyyyyyyyuvuv
     * NV21:安卓的模式。存储顺序是先存Y，再存U，再VU交替存储。yyyyyyyyvuvu
     * <p>
     * 所以一个yuv420图片大小计算的方法是
     * 一个像素大小= 1byte y + 0.25（2/8） byte的 u 和0.25byte的v =1.5byte
     * 所以 height*width*1.5byte就是这个帧的大小了
     *  <p>
     *
     *  常用的YUV存储（采样）格式（平面格式） 有I420（4:2:0）、YV12、IYUV等（具体的存储格式）
     *  4:4:4表示完全取样。
     *  4:2:2表示2:1的水平取样，垂直完全采样。(具体见drawable)
     *  4:2:0表示2:1的水平取样，垂直2：1采样。
     *  4:1:1表示4:1的水平取样，垂直完全采样。
     *  <p>
     *  DVD-Video是以YUV 4:2:0的方式记录，也就是我们俗称的I420
     *  <p>
     *  https://www.jianshu.com/p/e67f79f10c65（图片表示）
     *  <p>
     *
     *
     */
    void f3() {}


    /**
     * ===============视频编码============
     * yuv格式的视频还是很大，而且冗余信息很多，比如上一帧和这一帧只有一个像素点颜色不同，
     * 那么这一帧就不需要存其他像素点的信息了。
     *
     * 而且不压缩的数据非常大，使得存储和传输变为不可接受
     * 所以要制定一套视频压缩（编码）标准是很有必要的。
     *
     * 提到视频编码标准，先介绍几个制定标准的组织
     * -----------ITU（国际电信联盟）-------------
     *  * ITU 国际电信联盟  International Telecommunication Union
     *  * VCEG video coding expert group 是ITU下的子组织（工作组）
     *
     *1865年5月17日，为了顺利实现国际电报通信，法、德、俄、意、奥等20个欧洲国家的代表在巴黎签订了《国际电报公约》，国际电报联盟（International Telegraph Union ，ITU）也宣告成立。
     * 随着电话与无线电的应用与发展，ITU的职权不断扩大。
     *
     * 1906年，德、英、法、美、日等27个国家的代表在柏林签订了《国际无线电报公约》。
     * 1932年，70多个国家的代表在西班牙马德里召开会议，将《国际电报公约》与《国际无线电报公约》合并， 制定《国际电信公约》，并决定自1934年1月1日起正式改称为“国际电信联盟” ，也就是现在的ITU。
     * ITU是联合国下属的一个专门机构，其总部在瑞士的日内瓦。
     *
     * ITU下属有三个部门，分别是ITU-R（前身是国际无线电咨询委员会CCIR）、ITU-T（前身是国际电报电话咨询委员会CCITT）、ITU-D
     * ITU-R 无线电通信部门
     * ITU-T 电信标准化部门
     * ITU-D 电信发展部门
     *
     *
     * ------------MPEG Moving Picture Expert Group（动态图像专家组）-------------
     *
     *
     * 除了ITU之外，另外两个和视频编码关系密切的组织，是ISO/IEC
     * ISO大家都知道，就是推出ISO9001质量认证的那个“国际标准化组织”。IEC，是“国际电工委员会”。
     *
     * 1988年，ISO和IEC联合成立了一个专家组，负责开发电视图像数据和声音数据的编码、解码和它们的同步等标准。
     * 这个专家组，就是大名鼎鼎的MPEG，Moving Picture Expert Group（动态图像专家组）。
     *
     *   ISO international organization for standardization  (国际标准化组织)
     *   IEC 国际电工委员会
     *   MPEG moving picture expert group 移动图像专家组，是iso/IEC下的一个工作组
     *   几百名成员组成，专门负责 音频视频 编码标准制定的工作
     *
     * -------------Video coding format(视频编码格式) /编码标准----------
     *
     * 三十多年以来，世界上主流的视频编码标准，基本上都是它们提出来的。
     * ITU提出了H.261、H.262、H.263、H.263+、H.263++，这些统称为H.26X系列，主要应用于实时视频通信领域，如会议电视、可视电话等
     *
     * ISO/IEC提出了MPEG1、MPEG2、MPEG4、MPEG7、MPEG21，统称为MPEG系列
     *
     * ITU和ISO/IEC一开始是各自捣鼓，后来，两边成立了一个联合小组，名叫JVT（Joint Video Team，视频联合工作组）
     *
     * JVT致力于新一代视频编码标准的制定，后来推出了包括H.264在内的一系列标准。
     *
     * 时间线见 video_encode_timeline.jpg
     *
     *
     *
     * 1.H.262 or MPEG-2 Part 2 = MPEG-2 Part 2 = H.262
     * <p>
     * 2.MPEG-4 Part 2 = 兼容H.263
     * <p>
     * 3.AVC Advanced Video Coding  = H.264 or MPEG-4 Part 10 = MPEG-4 AVC =H.264 AVC (这是MPEG组织和ITU-T组织联合定义的)
     * <p>
     * 4.High Efficiency Video Coding (HEVC) = H.265 and MPEG-H Part 2 = H.265 =  MPEG-H Part 2
     *
     *
     *  MPEG组织，他们制定了
     * · MPEG-1  （这个组织在1990年制定的第一个视频 和音频 压缩（编码）标准） 用于CD 、VCD
     * <p>
     * · MPEG-2 也叫"ISO/IEC 13818-2"   94年制定的第二个版本，用于DVD
     * <p>
     * · MPEG-3 本来是用于为HDTV（High Definition Television 高清电视）制定的压缩标准，
     * 但后来发现MPEG-2就足以满足需求，所以就合并到MPEG-2中了，其实没有MPEG-3的叫法
     * <p>
     * · MPEG-4 99年制定，用于网络流媒体
     * · MPEG-7 ？？？
     * · MPEG-21 正在开发？
     * · MPEG-H ？？
     * <p>
     * 每个MPEG-xxx都由很多部分（part）组成，每个部分定义了不同的规则
     * 比如MP3压缩规则是在MPEG-1 Layer 3 中定义的
     * 当然后来有改进 MPEG-1 or MPEG-2 Audio Layer III 是一种音频编码 ，有损压缩
     *
     *
     * ITU-T 是ITU下的一个部门，他下的一个叫VCEG的工作组制定了音频视频编码标准
     *  H.261
     *  H.262 就是MPEG-2 的视频部分
     *  H.263
     *  H.264 其实就是 MPEG-4 part 10 ,AVC 这个VCEG和MPEG一起制定的，只不过他们的叫法不一样，就像圣西罗和煤阿茶
     *  H.265 就是MPEG-H 的第二部分 ，他们内容一样，叫法不一样
     *
     *
     *
     *
     */
    void f4(){}


    /**
     * ================视频编码原理===========
     * 具体可以参考《深入理解视频编解码技术——基于H.264标准及参考模型》
     *
     * 要实现压缩，就要设计各种算法，将视频数据中的冗余信息去除
     * 如果一段1分钟的视频，有十几秒画面是不动的，或者，有80%的图像面积，整个过程都是不变（不动）的。那么，是不是这块存储开销，就可以节约掉了？
     * 是的，所谓编码算法，就是寻找规律，构建模型。谁能找到更精准的规律，建立更高效的模型，谁就是厉害的算法。
     * 通常来说，视频里面的冗余信息包括： 见 video_encode_content.jpg 和 video_encode_example.jpg
     *
     * 空间冗余，这一块的像素区域颜色都是一样的，而且和上一个区域也是一样的
     * 时间冗余，这一帧和上一帧有很大部分像素都是一样的
     *
     * 视频编码技术优先消除目标，就是空间冗余和时间冗余。
     *
     * 所以把这些帧分为三类，分别是I帧，B帧，P帧。
     * I帧，是自带全部信息的独立帧，是最完整的画面（占用的空间最大），无需参考其它图像便可独立进行解码。视频序列中的第一个帧，始终都是I帧。
     * P帧，“帧间预测编码帧”，需要参考前面的I帧和/或P帧的不同部分，才能进行编码。P帧对前面的P和I参考帧有依赖性。但是，P帧压缩率比较高，占用的空间较小
     * B帧，“双向预测编码帧”，以前帧和后作为参考帧。不仅参考前面，还参考后面的帧，所以，它的压缩率最高，可以达到200:1。不过，因为依赖后面的帧，所以不适合实时传输（例如视频会议）
     *
     * 见：video_encode_frame.jpg
     *
     * 我们可以用软件查看帧的信息，它里面有标明这一帧是哪种类型的帧
     *
     * 对I帧的处理，是采用帧内编码方式，只利用本帧图像内的空间相关性。 流程见 video_encode_flow_based_one_frame.jpg
     * （帧内编码就是只根据本帧的数据去除冗余）
     * 对P帧的处理，采用帧间编码（前向运动估计），同时利用空间和时间上的相关性。简单来说，采用运动补偿(motion compensation)算法来去掉冗余信息。
     * （帧间编码就是本帧根据上一帧的数据去除冗余）
     *
     * ==========视频压缩编码的主要原理===
     * 帧内编码（变换编码和熵编码）：像素点之间存在相关性。图像变换到频域可以实现去相关和能量集中。
     * 帧间编码（运动估计和运动补偿）：将图像划分为一个个小区块，进行预测。
     *
     */
    void f5(){}


    /**
     * ===============音频编码=================
     * 音频编码规则
     * ACC Advanced Audio Coding 97年由杜比实验室、AT&T、Sony、Nokia 共同研发
     * 基于MPEG-2  所以又叫 MPEG-2 ACC
     * 后来2000年MPEG-4标准出现后，经过改良 成为 MPEG-4 ACC
     * <p>
     * MP1 MPEG-1 Audio Layer I 是mp2的简化，它不用很复杂的压缩算法(计算快)，但是压缩率比较低，现在几乎都不支持这个格式 .mp1
     * MP2 MPEG-1 Audio Layer II .mp2 这个压缩规则多用于广播
     * MP3 MPEG-1 or MPEG-2 Audio Layer III 这个用于网络音乐（压缩率高）
     * <p>
     * 注意不要混淆MPEG-1 和MP3的关系，MPEG-1是一组规则，而MP3是MPEG-1中音频压缩规则的一种
     * <p>
     * WMV 99年微软提出的音频编码格式
     *
     * ACC /MPEG-1 Layer 3 /杜比数字（Dolby Digital），或称AC-3----音频编码规则
     *
     *
     */
    void f4_1(){}

    /**
     * ===============封装格式==================
     * 将已经编码压缩好的视频轨和音频轨按照一定的格式放到一个文件中
     * 目前主要的视频容器有如下：MPG、VOB、MP4、3GP、ASF、RMVB、WMV、MOV、Divx、MKV、FLV、TS/PS等
     *
     *  MPEG制定的Container Format(（视频的） 封装格式/（放置视频的）容器格式)
     *  有.mp4 .mpg .mpeg
     *  <p>
     *  当然还有其他组织/公司制定的container format
     *  比如苹果的 .mov 微软的.avi  realnetworks 的.rmvb adobe的.flv
     *  <p>
     *  container format 容器格式 封装格式 是定义编码后的视频和音频 如何存放的格式
     *
     *
     * .mp4 .rmvb .avi .mkv  .flv（flash video）--都是不同组织定义的封装视频的规则
     * .mp3 .acc .ogg  --封装音频规则
     *
     *
     */
    void f6(){}


    /**
     * ===========flv封装格式===============
     * http://www.mamicode.com/info-detail-422904.html  如何实现一个c/s模式的flv视频点播系统
     * https://www.cnblogs.com/chyingp/p/flv-getting-started.html FLV 5分钟入门浅析
     * 见flv_box.jpg
     *
     * FLV (Flash Video) 是 Adobe 公司推出的另一种视频格式，是一种在网络上传输的流媒体数据存储容器格式。
     * 其格式相对简单轻量，不需要很大的媒体头部信息。整个 FLV 由 The FLV Header, The FLV Body 以及其它 Tag 组成。
     * 因此加载速度极快。采用 FLV 格式封装的文件后缀为 .flv。
     *
     * 而我们所说的 HTTP-FLV 即将流媒体数据封装成 FLV 格式，然后通过 HTTP 协议传输给客户端。
     *
     * 是一种专门用来在网络上传输的视频存储容器格式。
     * 其格式相对简单，不需要很大的媒体头部信息，因此加载速度极快。国内各大视频网站，均有采用FLV格式作为其点播、甚至直播的视频格式。
     * FLV容器格式的主要特点是tag，整个FLV由Video Tag, Audio Tag以及其他Tag组成，没有映射表。
     *
     *
     *
     *
     */
    void f6_1(){}

    /**
     * ============mp4封装格式===================
     * https://zhuanlan.zhihu.com/p/52842679  短视频宝贝=慢？阿里巴巴工程师这样秒开短视频。
     * MP4目前被广泛用于封装h.264视频和AAC音频，是高清视频的代表
     *
     *
     *
     * MP4(MPEG-4 Part 14)是一种常见的多媒体容器格式，它是在“ISO/IEC 14496-14”标准文件中定义的，属于MPEG-4的一部分，
     * 是“ISO/IEC 14496-12(MPEG-4 Part 12 ISO base media file format)”标准中所定义的媒体格式的一种实现（在H.264标准文档约14章位置），
     * 后者定义了一种通用的媒体文件结构标准。MP4是一种描述较为全面的容器格式，被认为可以在其中嵌入任何形式的数据，
     * 各种编码的视频、音频等都不在话下，不过我们常见的大部分的MP4文件存放的AVC(H.264)或MPEG-4(Part 2)编码的视频和AAC编码的音频。
     * MP4格式的官方文件后缀名是“.mp4”，还有其他的以mp4为基础进行的扩展或者是阉割版的格式，如：M4V, 3GP, F4V等
     * ————————————————
     * 版权声明：本文为CSDN博主「码农突围」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/hejjunlin/article/details/73162841
     *
     * mp4是由一个个“box”组成的，大box中存放小box，一级嵌套一级来存放媒体信息。box的基本结构如下：
     *
     *
     * ================具体格式====
     * 见 mp4_box.jpg
     *
     *
     */
    void f7(){}


    /**
     * ===================ffmepg====================
     *
     * https://blog.csdn.net/leixiaohua1020/article/details/15811977 ([总结]FFMPEG视音频编解码零基础学习方法)
     *
     *FFmpeg是一个开源的跨平台音视频处理工具，它包含了一系列用于处理多媒体数据的库和工具，可以用于录制、转换和流式传输音视频内容。FFmpeg具有以下特点：
     *
     * 1. 功能丰富：FFmpeg支持多种音视频格式的编解码、转换和处理，包括但不限于MP4、AVI、FLV、MP3、AAC等。
     *
     * 2. 跨平台：FFmpeg可以在多种操作系统上运行，包括Windows、macOS和Linux等。
     *
     * 3. 灵活性：FFmpeg提供了丰富的命令行工具和API，可以满足各种音视频处理需求，如剪辑、合并、转码、添加水印等。
     *
     * 4. 广泛应用：FFmpeg被广泛应用于音视频处理、流媒体服务、视频编辑软件、转码工具等领域。
     *
     * 5. 开源：FFmpeg是一个开源项目，可以免费获取并在遵循其许可协议的情况下进行使用和修改。
     *
     * 总的来说，FFmpeg是一个功能强大、灵活且广泛应用的音视频处理工具，为开发者和用户提供了丰富的多媒体处理能力。
     *
     *
     *
     */
    void f8(){}


    /**
     * ================android 播放器=============
     * https://www.jianshu.com/p/5345ab4cf979 （ijkplayer 源码分析（上））
     *
     * AndroidMediaPlayer：即安卓系统自带的播放器 MediaPlayer，基于 MediaCodec、AudioTrack 等安卓系统 API.
     * IjkExoMediaPlayer：即谷歌新推出的 ExoPlayer，同样是基于 MediaCodec、AudioTrack 等安卓系统 API，但相比 MediaPlayer 具有支持 DASH、高级 HLS、自定义扩展等优点。
     * IjkMediaPlayer：IjkPlayer 基于 FFmpeg 的 ffplay，集成了 MediaCodec 硬解码器、Opengl 渲染方式等。
     *
     *
     * ===MediaCodec
     * MediaCodec 是一个用于音视频编解码的API，它允许开发者在Android设备上进行低延迟的音视频编解码操作
     *
     * 如果你想使用 MediaCodec 来解码 MP4 文件，你需要使用 MediaExtractor 从 MP4 文件中提取音视频数据，
     * 然后使用 MediaCodec 进行解码。但这种方式相对复杂，一般情况下更推荐使用成熟的播放器来处理视频播放。
     *
     * ==============ijkplayer播放流程========
     *
     * ijkplayer播放主要流程
     *
     * 根据链接的schema找到对应的URLProtocol。
     * 如Http的链接，对应libavformat/http.c
     * 而http的请求后续会转换成Tcp的协议，对应libavformat/tcp.c
     * 进行DNS解析ip地址，并且解析完后进行缓存，以便下次复用
     * 从链路中读取数据到Buffer
     * 有可能从tcp链路，也有可能从磁盘链路
     * TCP链路则会需要等待三次握手的时间
     * 读取Buffer进行文件类型的probe
     * 探测文件格式，判断是mp4，flv等等
     * 读取Buffer的头部信息进行解析
     * 解析文件头部，判断是否为该格式文件，如果失败则返回错误
     * 解析audio，video，subtitle流
     * 根据文件信息找到多媒体流
     * 优先使用H264的视频流
     * 根据流信息找到解码器
     * 开启各个线程开始对各个流进行解码成packet
     * 同步到read_thread线程后，装入pakcetQueue中
     * 在video_refresh_thread线程中，读取packetQueue中的包，进行时钟同步
     * 开始绘制视频，播放音频内容
     *
     * ==============秒开优化==========
     * https://cloud.tencent.com/developer/article/1357997 （IjkPlayer起播速度优化）
     * https://www.jianshu.com/p/843c86a9e9ad （IjkPlayer播放器秒开优化以及常用Option设置）
     *
     * 要实现网络视频的“秒开”优化，可以考虑以下几个方面的优化策略：
     *
     * 1. 视频编码格式：选择适合网络传输和快速解码的视频编码格式，如H.264。这样可以减小视频文件大小，提高网络传输速度，并且在解码时能够更快地展示视频画面。
     *
     * 2. 视频预加载：在用户观看视频之前，可以提前加载视频的关键帧或部分视频数据，以便在用户点击播放时能够快速展示视频画面，从而实现“秒开”的效果。
     *
     * 3. 视频流优化：使用适当的视频码率和分辨率，以确保视频能够在较低的网络带宽下也能够快速加载和播放。
     *
     * 4. CDN 加速：利用内容分发网络（CDN）来加速视频的传输，将视频内容缓存在离用户较近的节点上，减小视频加载时间。
     *
     * 5. 视频预缓存：在用户观看视频时，可以预先缓存一段时间的视频数据，以确保用户在观看过程中不会遇到卡顿和加载延迟。
     *
     * 6. 逐帧渐进式加载：使用逐帧渐进式加载的方式，先加载视频的低清晰度画面，然后逐渐加载高清晰度画面，以实现快速展示视频画面。
     *
     * 通过以上优化策略，可以有效提高网络视频的加载速度，实现“秒开”的效果，提升用户的观看体验。
     *
     * ======surface texture
     * https://developer.android.google.cn/guide/topics/media/ui/playerview?hl=zh-cn
     *
     * 与 TextureView 相比，SurfaceView 在视频播放方面具有多项优势：
     *
     * 在许多设备上显著降低功耗。
     * 更准确的帧时间，带来更流畅的视频播放。
     * 在符合条件的设备上支持更高质量的 HDR 视频输出。
     * 支持播放受 DRM 保护的内容时的安全输出。
     * 在提高界面层的 Android TV 设备上以完整显示屏分辨率呈现视频内容。
     * 因此，应尽可能优先使用 SurfaceView，而非 TextureView。仅当 SurfaceView 无法满足您的需求时，
     * 才应使用 TextureView。
     * 例如，在 Android 7.0（API 级别 24）之前，需要流畅的动画或视频界面滚动，
     * 如以下说明中所述。在这种情况下，最好仅在 SDK_INT 小于 24 (Android 7.0) 时使用 TextureView，否则使用 SurfaceView。
     *
     * 在 Android 7.0（API 级别 24）之前，SurfaceView 渲染无法与视图动画正确同步。
     * 在早期版本中，当 SurfaceView 被放入滚动容器或对其进行动画处理时，这可能会导致不必要的效果。
     * 此类效果包括视图的内容显示稍微滞后于应显示的位置，以及在受到动画处理后视图变为黑色。
     * 在 Android 7.0 之前，为了实现流畅的视频动画或滚动，需要使用 TextureView，而不是 SurfaceView。
     *
     *
     *
     * ==============ijkplayer切换后台黑屏问题===========
     * https://cloud.tencent.com/developer/article/1192700 （IJKPlayer问题集锦之不定时更新）
     * 暂停的时候，退到后台再回到前台，画面黑了？
     *
     * 1、这时候个人处理方式是，可以在暂停的时候，通过TextureView.getBitmap(point.x, point.y);获取到暂停的画面
     *  ，用ImageView显示它，在onSurfaceTextureUpdated的时候隐藏ImageView，来实现画面的衔接。
     * 2、暂停时绘制静态画面多TextureView的Surface上，详细参考GSYVideoPlayer。
     *
     */
    void f9(){}


    /**
     * ================Android中音视频播放==============
     * 视频可以用 MediaCodec 进行硬解码
     * 解码后用Android框架内置的OpenGL es框架进行播放？？
     * Android用SurfaceView来渲染视频帧。
     *
     * https://www.jianshu.com/p/13320a8549db（用openGL ES+MediaPlayer 渲染播放视频+滤镜效果）
     *
     *
     * 音频用AudioTrack这个类可以直接播放pcm数据
     *
     *
     * https://blog.csdn.net/moruihong/article/details/7739086 （ Android MediaPlayer的核心原理）
     * MediaPlayer在底层是基于OpenCore(PacketVideo)的库实现的
     * OpenCorePlayer的实现libopencoreplayer.so
     *
     *
     */
    void a10(){}


    /**
     * ========Android编码============
     * https://cloud.tencent.com/developer/article/1006240 微信 Android 视频编码爬过的那些坑
     * https://blog.csdn.net/u010126792/article/details/86580878 MediaCodec
     * https://www.jianshu.com/p/343d7e10cf6b Android MediaCodec
     * 硬编码：
     * 用设备GPU去实现编解码，这样可以减轻CPU的压力。
     * 软编码：
     * 让CPU来进行编解码，在c层代码来进行编解码，因为c/c++有很多好的编解码库。
     *
     *
     * =========== android.media.MediaCodec ==============
     * 在Android 4.1之前没有提供硬编解码的API，所以基本都是采用开源的那些库，比如著名的FFMpeg实现软编解码。
     * 在Android4.1出来了一个新的API：MediaCodec可以支持硬编解码,MediaCodec可以支持对音频和视频的编解码.
     *
     * MediaCodec支持的视频格式有vp8 、VP9 、H.264、H.265、MPEG4、H.263等。
     * MediaCodec支持的音频格式有3gpp、amr-wb、amr-wb、amr-wb、g711-A、g711-U 、AAC等。
     * 类型：
     * “video/x-vnd.on2.vp8” - VP8 video (i.e. video in .webm)
     * “video/x-vnd.on2.vp9” - VP9 video (i.e. video in .webm)
     * “video/avc” - H.264/AVC video
     * “video/hevc” - H.265/HEVC video
     * “video/mp4v-es” - MPEG4 video
     * “video/3gpp” - H.263 video
     * “audio/3gpp” - AMR narrowband audio
     * “audio/amr-wb” - AMR wideband audio
     * “audio/amr-wb” - MPEG1/2 audio layer III
     * “audio/mp4a-latm” - AAC audio (note, this is raw AAC packets, not packaged in LATM!)
     * “audio/amr-wb” - vorbis audio
     * “audio/g711-alaw” - G.711 alaw audio
     * “audio/g711-mlaw” - G.711 ulaw audio
     * ————————————————
     * 版权声明：本文为CSDN博主「lidongxiu0714」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/u010126792/article/details/86580878
     *
     * ====== ffmpeg =============
     * https://cloud.tencent.com/developer/article/1006240 微信 Android 视频编码爬过的那些坑
     * 另外一种比较流行的方案就是使用ffmpeg+x264/openh264进行软编码，ffmpeg是用于一些视频帧的预处理。这里主要是使用x264/openh264作为视频的编码器。
     *
     *
     * x264 基本上被认为是当今市面上最快的商用视频编码器，而且基本上所有h264的特性都支持，通过合理配置各种参数还是能够得到较好的压缩率和编码速度的，
     *
     * openh264 (https://github.com/cisco/openh264)则是由思科开源的另外一个h264编码器，项目在2013年开源，对比起x264来说略显年轻，不过由于思科支付满了h264的年度专利费，所以对于外部用户来说，相当于可以直接免费使用了，另外，firefox直接内置了openh264，作为其在webRTC中的视频的编解码器使用。
     *
     *
     */
    void a11(){}


    /**
     * ======android.media.MediaRecorder===
     * 录制音频，视频。直接输出成文件，
     * 对于录制视频的需求，不少app都需要对每一帧数据进行单独处理，因此很少会直接用到MediaRecorder来直接录取视频
     *
     * =====android.media.MediaMuxer ====
     * 将 camera输出nv21用 mediacodec 编码为h264后，将audioRecord输出的pcm用mediacodec aac后，用MediaMuxer合并为mp4
     * 是Android 4.3新增的API
     * Currently MediaMuxer supports MP4, Webm and 3GP file as the output
     *
     *
     */
    void a12(){}
}
