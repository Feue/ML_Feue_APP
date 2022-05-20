/*
 * Copyright (C) 2022 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.feue.ml.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.feue.ml.R;
import com.feue.ml.adapter.entity.ChapterInfo;
import com.feue.ml.adapter.entity.CommentInfo;
import com.feue.ml.adapter.entity.CourseInfo;
import com.feue.ml.adapter.entity.HistoryInfo;
import com.feue.ml.adapter.entity.NewInfo;
import com.feue.ml.adapter.entity.NoticeInfo;
import com.feue.ml.adapter.entity.PracticeInfo;
import com.feue.ml.adapter.entity.QuestionInfo;
import com.feue.ml.adapter.entity.VideoInfo;
import com.xuexiang.xaop.annotation.MemoryCache;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 演示数据
 *
 * @author xuexiang
 * @since 2018/11/23 下午5:52
 */
public class DemoDataProvider {

//    public static String[] titles = new String[]{
//            "伪装者:胡歌演绎'痞子特工'",
//            "无心法师:生死离别!月牙遭虐杀",
//            "花千骨:尊上沦为花千骨",
//            "综艺饭:胖轩偷看夏天洗澡掀波澜",
//            "碟中谍4:阿汤哥高塔命悬一线,超越不可能",
//    };

//    public static String[] urls = new String[]{//640*360 360/640=0.5625
//            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160323071011277.jpg",//伪装者:胡歌演绎"痞子特工"
//            "http://photocdn.sohu.com/tvmobilemvms/20150907/144158380433341332.jpg",//无心法师:生死离别!月牙遭虐杀
//            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160286644953923.jpg",//花千骨:尊上沦为花千骨
//            "http://photocdn.sohu.com/tvmobilemvms/20150902/144115156939164801.jpg",//综艺饭:胖轩偷看夏天洗澡掀波澜
//            "http://photocdn.sohu.com/tvmobilemvms/20150907/144159406950245847.jpg",//碟中谍4:阿汤哥高塔命悬一线,超越不可能
//    };
    public static String[] titles = new String[]{
            "大数据思维与技术",
            "算法设计与问题求解",
            "计算机网络与互联网"
    };

    public static String[] urls = new String[] {
        "https://edu-image.nosdn.127.net/7BA1653E38C5B999C223BAA707CC78A1.png?imageView&thumbnail=510y288&quality=100",// 大数据思维与技术
        "https://edu-image.nosdn.127.net/044ACBDAF5B2A442FA88687D676F616A.jpeg?imageView&thumbnail=510y288&quality=100",// 算法设计与问题求解
        "https://edu-image.nosdn.127.net/73F79DA6B43375B94A5032BBFFFB37BA.png?imageView&thumbnail=510y288&quality=100",// 计算机网络与互联网
    };

    @MemoryCache
    public static List<BannerItem> getBannerList() {
        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];

            list.add(item);
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
    @MemoryCache
    public static List<NewInfo> getDemoNewInfos() {
        List<NewInfo> list = new ArrayList<>();
        list.add(new NewInfo("公众号", "X-Library系列文章视频介绍")
                .setSummary("获取更多咨询，欢迎点击关注公众号:【我的Android开源之旅】，里面有一整套X-Library系列文章视频介绍！\n")
                .setDetailUrl("http://mp.weixin.qq.com/mp/homepage?__biz=Mzg2NjA3NDIyMA==&hid=5&sn=bdee5aafe9cc2e0a618d055117c84139&scene=18#wechat_redirect")
                .setImageUrl("https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/463930705a844f638433d1b26273a7cf~tplv-k3u1fbpfcp-watermark.image"));

        list.add(new NewInfo("Android UI", "XUI 一个简洁而优雅的Android原生UI框架，解放你的双手")
                .setSummary("涵盖绝大部分的UI组件：TextView、Button、EditText、ImageView、Spinner、Picker、Dialog、PopupWindow、ProgressBar、LoadingView、StateLayout、FlowLayout、Switch、Actionbar、TabBar、Banner、GuideView、BadgeView、MarqueeView、WebView、SearchView等一系列的组件和丰富多彩的样式主题。\n")
                .setDetailUrl("https://juejin.im/post/5c3ed1dae51d4543805ea48d")
                .setImageUrl("https://user-gold-cdn.xitu.io/2019/1/16/1685563ae5456408?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new NewInfo("Android", "XUpdate 一个轻量级、高可用性的Android版本更新框架")
                .setSummary("XUpdate 一个轻量级、高可用性的Android版本更新框架。本框架借鉴了AppUpdate中的部分思想和UI界面，将版本更新中的各部分环节抽离出来，形成了如下几个部分：")
                .setDetailUrl("https://juejin.im/post/5b480b79e51d45190905ef44")
                .setImageUrl("https://user-gold-cdn.xitu.io/2018/7/13/16492d9b7877dc21?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new NewInfo("Android/HTTP", "XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp进行组装")
                .setSummary("一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。还不赶紧点击使用说明文档，体验一下吧！")
                .setDetailUrl("https://juejin.im/post/5b6b9b49e51d4576b828978d")
                .setImageUrl("https://user-gold-cdn.xitu.io/2018/8/9/1651c568a7e30e02?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));

        list.add(new NewInfo("源码", "Android源码分析--Android系统启动")
                .setSummary("其实Android系统的启动最主要的内容无非是init、Zygote、SystemServer这三个进程的启动，他们一起构成的铁三角是Android系统的基础。")
                .setDetailUrl("https://juejin.im/post/5c6fc0cdf265da2dda694f05")
                .setImageUrl("https://user-gold-cdn.xitu.io/2019/2/22/16914891cd8a950a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
        return list;
    }

    public static List<AdapterItem> getGridItems(Context context) {
        return getGridItems(context, R.array.grid_titles_entry, R.array.grid_icons_entry);
    }


    private static List<AdapterItem> getGridItems(Context context, int titleArrayId, int iconArrayId) {
        List<AdapterItem> list = new ArrayList<>();
        String[] titles = ResUtils.getStringArray(titleArrayId);
        Drawable[] icons = ResUtils.getDrawableArray(context, iconArrayId);
        for (int i = 0; i < titles.length; i++) {
            list.add(new AdapterItem(titles[i], icons[i]));
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
    @MemoryCache
    public static List<NewInfo> getEmptyNewInfo() {
        List<NewInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new NewInfo());
        }
        return list;
    }

    @MemoryCache
    public static List<CourseInfo> getDemoCourseInfos() {
        List<CourseInfo> list = new ArrayList<>();
        list.add(new CourseInfo("C语言程序设计", "本课程是一门学习程序设计的入门课程，课程以C语言为载体，讲授程序设计的思想和方法。",
                "http://edu-image.nosdn.127.net/CBE2AB75BF5450085811FDFCB4E38870.jpg?imageView&thumbnail=426y240&quality=100&type=webp"));
        list.add(new CourseInfo("Java程序设计", "本课程的先修课程：最好是学过一门程序设计语言。",
                "http://edu-image.nosdn.127.net/886435E06FF8C59E3839C67EA58EDD49.jpg?imageView&thumbnail=426y240&quality=100&type=webp"));
        list.add(new CourseInfo("数据结构", "本课程只涉及最基础的数据结构和与之关联的最基本的算法。",
                "http://edu-image.nosdn.127.net/8A2FD753FE609F6F62064E95F74432E6.jpg?imageView&thumbnail=426y240&quality=100&type=webp"));
        return list;
    }

    @MemoryCache
    public static List<ChapterInfo> getDemoChapterInfos() {
        List<ChapterInfo> list = new ArrayList<>();
        list.add(new ChapterInfo(0, "第一章 C语言的过去与未来", "简介：C语言的过去与未来"));
        list.add(new ChapterInfo(0, "第二章 C语言快速入门", "简介：C语言快速入门"));
        list.add(new ChapterInfo(0, "第三章 C语言的数据类型", "简介：C语言的数据类型"));
        list.add(new ChapterInfo(0, "第四章 C语言中的基本输入输出", "简介：C语言中的基本输入输出"));
        list.add(new ChapterInfo(0, "第五章 运算符和表达式", "简介：运算符和表达式"));
        return list;
    }

    @MemoryCache
    public static List<VideoInfo> getDemoVideoInfos() {
        List<VideoInfo> list = new ArrayList<>();
        list.add(new VideoInfo("C语言变量与常量数据", "在程序的世界中，可以让计算机按照指令做很多事情，如进行数值计算、图像显示、语音对话、视频播放、" +
                "天文计算、发送邮件、游戏绘图以及任何我们可以想象到的事情。要完成这些任务，程序需要使用数据，即承载信息的数字与字符。 ", 600));
        list.add(new VideoInfo("C语言数据类型和关键字", "C语言数据类型和关键字", 600));
        list.add(new VideoInfo("C语言实例", "C语言实例", 600));
        return list;
    }

    @MemoryCache
    public static List<CommentInfo> getDemoCommentInfos() {
        List<CommentInfo> list = new ArrayList<>();
        list.add(new CommentInfo("风同学", "思路清晰，结构合理，一门值得推荐的C语言程序设计课程，并以C与面向对象无关的" +
                "新语法特性对其进行扩充，显著提高编程效率.", "2022年4月21日 20:13", "第三章 C语言的数据类型"));
        list.add(new CommentInfo("云同学", "一门值得推荐的C语言程序设计课程，并以C与面向对象无关的新语法特性对其进行扩充，" +
                "显著提高编程效率，实践性较强，作业和考试均采取OJ评价系统。", "2022年4月21日 10:23", "第三章 C语言的数据类型"));
        list.add(new CommentInfo("雨同学", "以C与面向对象无关的新语法特性对其进行扩充，显著提高编程效率，实践性较强，" +
                "作业和考试均采取OJ评价系统。简言之，“学会程序和算法，走遍天下都不怕！”", "2022年4月22日 15:24", "第三章 C语言的数据类型"));
        return list;
    }

    @MemoryCache
    public static List<NoticeInfo> getDemoNoticeInfos() {
        List<NoticeInfo> list = new ArrayList<>();
        list.add(new NoticeInfo("不走弯路的四六级", "不走弯路的四六级学习课程，助力轻松通过四六级，拿高分!", "2022年4月24日 12:00", "金老师"));
        list.add(new NoticeInfo("关于线上学习截止时间", "C语言程序设计实验课程需要在线上完成的学习任务，最后截止日期为4月30日，逾期没有完成不再开放和延长。", "2022年4月20日 12:00", "木老师"));
        list.add(new NoticeInfo("关于2022春的问卷", "为了努力提升教学质量，不断探索以学生为中心的创新教学模式，现发放教学调查问卷。", "2022年4月26日 12:00", "水老师"));
        return list;
    }

    @MemoryCache
    public static List<HistoryInfo> getDemoHistoryInfos() {
        List<HistoryInfo> list = new ArrayList<>();
        list.add(new HistoryInfo("C语言中的基本输入输出", "简介：C语言中的基本输入输出", "2022-5-3 16:23",
                "http://edu-image.nosdn.127.net/CBE2AB75BF5450085811FDFCB4E38870.jpg?imageView&thumbnail=426y240&quality=100&type=webp"));
        list.add(new HistoryInfo("C语言的数据类型", "简介：C语言的数据类型", "2022-5-2 10:20",
                "http://edu-image.nosdn.127.net/CBE2AB75BF5450085811FDFCB4E38870.jpg?imageView&thumbnail=426y240&quality=100&type=webp"));
        list.add(new HistoryInfo("C语言快速入门", "简介：C语言快速入门", "2022-5-1 11:20",
                "http://edu-image.nosdn.127.net/CBE2AB75BF5450085811FDFCB4E38870.jpg?imageView&thumbnail=426y240&quality=100&type=webp"));
        return list;
    }

    @MemoryCache
    public static List<PracticeInfo> getDemoPracticeInfos() {
        List<PracticeInfo> list = new ArrayList<>();
        list.add(new PracticeInfo("设计模式", 56));
        list.add(new PracticeInfo("网络基础", 1742));
        list.add(new PracticeInfo("数据库", 1004));
        list.add(new PracticeInfo("操作系统", 1520));
        list.add(new PracticeInfo("软件工程", 547));
        return list;
    }

    @MemoryCache
    public static List<QuestionInfo> getDemoQuestionInfos() {
        List<QuestionInfo> list = new ArrayList<>();
        list.add(new QuestionInfo("ICMP是因特网控制报文协议。在网络中，ICMP测试的目的是(   )。",
                new String[] {"测定信息是否到达其目的地，若没有达到，则确定是什么原因", "保证网络的所有活动都受监视", "测定网络是否根据模型建立", "测定网络上处于控制模型还是用户模型"},
                0));
        list.add(new QuestionInfo("EIA232定义了DTE-DCE接口的（ ）特性",
                new String[] {"机械", "电气", "功能", "以上都是"},
                3));
        return list;
    }
}
