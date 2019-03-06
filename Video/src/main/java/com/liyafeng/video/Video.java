package com.liyafeng.video;

public class Video {


    /**
     * 视频入门指南
     * https://zhuanlan.zhihu.com/p/28518637 （七牛音视频架构师）
     * ===================3gp================
     * 3GP是一种多媒体单元格式，由Third Generation Partnership Project（3GPP）定义，主要用于3G手机上。
     * 3GP是 MPEG-4 第12部分，又被称为MPEG-4/JPEG2000基本媒体文件格式
     * 3GP是MPEG-4 Part 14（MP4）的一种简化版本，减少了存储空间和较低的带宽需求，
     * 让手机上有限的存储空间可以使用。
     * 3GP文件视频的部分可以用MPEG-4 Part 2、H.263或MPEG-4 Part 10 (AVC/H.264)等格式来存储，
     * 声音的部分则支持AMR-NB、AMR-WB、AMR-WB+、AAC-LC或HE-AAC来当作声音的编码。
     *
     * --------------------------
     * 3gp是一种容器，除了头部的视频信息外，还包括编码后的视频信息，而且支持多种编码方式
     * 很显然头部信息包含了编码方式。而且播放器的原理都是一样的，取出文件的头部信息
     * 然后将视频信息解码，然后播放
     *
     * ===========================YUV和RGB===================
     * YUV（YCbCr，YPbPr）和RGB都是颜色编码的方案，Y代表亮度，UV代表色彩信息
     * U是色度，V是浓度，因此黑白电影可省略UV
     * Y'UV最大的优点在于只需占用极少的带宽。
     *
     * YUV数据有两种格式
     * 紧缩格式（packed formats）yuv数据聚集在一起的数组
     * 平面格式（planar formats）三个分量存储在不同的矩阵中（代表一种存储风格）
     *
     * 常用的YUV存储（采样）格式（平面格式） 有I420（4:2:0）、YV12、IYUV等（具体的存储格式）
     * 4:4:4表示完全取样。
     * 4:2:2表示2:1的水平取样，垂直完全采样。(具体见drawable)
     * 4:2:0表示2:1的水平取样，垂直2：1采样。
     * 4:1:1表示4:1的水平取样，垂直完全采样。
     *
     * DVD-Video是以YUV 4:2:0的方式记录，也就是我们俗称的I420
     *
     *  https://www.jianshu.com/p/e67f79f10c65（图片表示）
     *
     * ============YUV==========
     * YUV，分为三个分量，
     * “Y”表示明亮度（Luminance或Luma），也就是灰度值；（从黑到白，所以黑白电视只要Y即可）
     * 而“U”和“V” 表示的则是色度（Chrominance或Chroma），
     * 作用是描述影像色彩及饱和度，用于指定像素的颜色。
     * 采样，光信号转换为数字信号
     * 主流的采样方式有三种，YUV4:4:4，YUV4:2:2，YUV4:2:0 （见drawable）
     * 1. YUV 4:4:4采样，每一个Y对应一组UV分量8+8+8 = 24bits,3个字节。
     * 2. YUV 4:2:2采样，每两个Y共用一组UV分量,一个YUV占8+4+4 = 16bits 2个字节。
     * 3. YUV 4:2:0采样，每四个Y共用一组UV分量一个YUV占8+2+2 = 12bits  1.5个字节。
     * 也就是一个y是一个像素点，每个y大小是8位（一个byte） u是8位，v是8位
     * 而YUV 4:2:2 是每两个y用一个uv ，所以一个y就是4位的u和v，
     *
     * ========YUV420===============
     * 因为YUV420比较常用，（4个y共享一个u和一个v） 在这里就重点介绍YUV420。YUV420分为两种：YUV420p和YUV420sp
     * (格式见drawable)
     * 如图
     * YUV420p：又叫planer平面模式，Y ，U，V分别再不同平面，也就是有三个平面。
     * YUV420sp：又叫bi-planer或two-planer双平面，Y一个平面，UV在同一个平面交叉存储。
     *
     * 根据uv的存储顺序不同
     *
     * YUV420p 又细分：（这个是平面模式，所以yuv是存在三个数组中）
     *  I420(又叫YU12): 安卓的模式。存储顺序是先存Y，再存U，最后存V。yyyyyyyyuuvv(4个y公用一个u和v)
     *  YV12:存储顺序是先存Y，再存V，最后存U。yyyyyyyyvvuu
     *
     * YUV420sp又分为 (这个是uv交错)
     *  NV12:IOS只有这一种模式。存储顺序是先存Y，再UV交替存储。yyyyyyyyuvuv
     *  NV21:安卓的模式。存储顺序是先存Y，再存U，再VU交替存储。yyyyyyyyvuvu
     *
     * 所以一个yuv420图片大小计算的方法是
     * 一个像素大小= 1byte y + 0.25（2/8） byte的 u 和0.25byte的v =1.5byte
     * 所以 height*width*1.5byte就是这个帧的大小了
     *
     *
     *
     *  =======================mp4=========================
     *  MP4或称MPEG-4第14部分（英语：MPEG-4 Part 14）是一种标准的数字多媒体容器格式
     *  注意是容器格式
     * ---------------------------------
     * 同时拥有音频視频的MPEG-4文件通常使用标准扩展名.mp4
     * 仅有音频的MPEG-4文件会使用.m4a扩展名
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * http://www.infoq.com/cn/articles/improving-android-video-on-news-feed-with-litho
     * Facebook构建高性能Android视频组件实践之路
     *
     *
     */
    public void f1(){}
}
