/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import java.util.ArrayList;

/**
 * javac -encoding utf-8 -Xlint:unchecked str\SimpleTag.java
 * java  str.SimpleTag
 * java  str.SimpleTag "卓依婷 流星雨 视频", "视频 卓依婷 流星雨"
 * @author scofier
 */
public class SimpleTag1 {

    private static char[] token = {')', '(', '）', '（', '《', '》', ',', '，', ' ', '>', '<'};
    private static char split_char = ',';//以什么字符分隔

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        if(args.length>1)
        {
            System.out.println("title: [" + args[0] + "] tag: [" + args[1] + "]");
            parseTag(args[0], args[1]);
        }else{
            test();
        }
        
        System.out.println("cost_time: " + (System.currentTimeMillis() - start));
    }
    
    public static boolean isToken(char c)
    {
        for(char cc: token)
        {
            if(c == cc) return true;
        }
        return false;
    }

    public static String parseTag(String title, String tag)
    {
        //需要维护多个index： titleIndex,tagIndex
        //维护两个队列： titleQueue,tagQueue
        //跳过分割符号
        StringBuffer sameSB = new StringBuffer();
        StringBuffer diffSB = new StringBuffer();
        StringBuffer titleQueue = new StringBuffer();
        int tagLength = tag.length();
        int titleLength = title.length();
        int titleIndex = 0;
        //先删除title的分割字符
        for(int i=0;i<titleLength;i++)
        {
            char tChar = title.charAt(i);
            if(!isToken(tChar))
            {
                titleQueue.append(tChar);
            }
        }
        String minTitle = titleQueue.toString();
        titleLength = minTitle.length();
        //获取tag的标签并输出
        boolean preToken = false;
        for(int tagIndex=0;tagIndex<tagLength;tagIndex++)
        {
            boolean same = false;
            char curTagChar = tag.charAt(tagIndex);
            //如果当前是分割字符跳过
            if(isToken(curTagChar)) 
            {
                preToken = true;
                continue;
            }
            //比较title，找到下一个非token的字符
            if(titleIndex < titleLength)
            {
                char curTitleChar = minTitle.charAt(titleIndex);
                if(curTagChar == curTitleChar)
                {
                    ++titleIndex;
                    same = true;
                    sameSB.append(curTagChar);
                }else if(titleIndex > 0){
                    titleIndex = 0;
                    diffSB.append(""+split_char+sameSB);
                    sameSB.setLength(0);
                    //System.out.println("spppp: " + sameSB);
                }
            }
            if(!same)
            {
                if(preToken)
                {
                    diffSB.append(split_char);
                }
                diffSB.append(curTagChar); 
            }
            preToken = false;
        }
        /*
        System.out.println("diffffff:" + diffSB.toString());
        System.out.println("title:" + minTitle);
        System.out.println("tag:" + tag);
        System.out.println("same:" + sameSB.toString());
        System.out.println("diff:" + diffSB.toString());
        System.out.println("---------------");
        */
        //修正输出
        if(sameSB.length() != minTitle.length())
        {
            diffSB = new StringBuffer(tag.replace(" ", ","));
        }
        //去除边界分割符
        if(diffSB.charAt(0) == split_char)
        {
            diffSB.deleteCharAt(0);
        }
        //格式化输出
        String diff = "\"" + diffSB.toString().replace(""+split_char, "\",\"") + "\"";
        System.out.println("diff:" + diff);
        return diff;
        
    }
    
    /**
     * "泰国小姐","20强","佳丽","比基尼","出镜"
"中国优秀旅游城市","河北省","廊坊市2","大学城"
"李羿慧","燃烧","中国媳妇","纪明阳"
"康定情歌"
"视频","卓依婷","流星雨"
     */
    
    public static void test()
    {
        ArrayList<String[]> al = new ArrayList<String[]>();
        al.add(new String[]{"泰国小姐20强出炉,佳丽比基尼出镜", "泰国小姐 20强 出炉 佳丽 比基尼 出镜 泰国小姐 20强 佳丽 比基尼 出镜"});
        al.add(new String[]{"河北廊坊大学城", "中国优秀旅游城市 河北省 廊坊市2 大学城, 河北 廊坊,大学城"});
        al.add(new String[]{"哑巴新娘 李羿慧 伴唱", "李羿慧,哑巴新娘,李羿慧伴唱,燃烧,中国媳妇,纪明阳"});
        al.add(new String[]{"龙飘飘《康定情歌》（KTV）", "龙飘飘，康定情歌，KTV，康定情歌"});
        al.add(new String[]{"卓依婷 流星雨 视频", "视频 卓依婷 流星雨"});

        for(Object a: al)
        {
            String[] k = (String[])a;

            parseTag(k[0],k[1]);

        }
    }
}
