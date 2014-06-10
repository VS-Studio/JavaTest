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
public class SimpleTag {

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
    /**
     * 格式化标签输出
     * @param str
     * @return 
     */
    public static String filterStr(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        char pre_char = '`';//不等于split_char的字符
        for (int i = 0; i < len; i++) {
            boolean find = false;
            char cur_char = str.charAt(i);
            for (char t : token) {
                if (t == cur_char) {
                    find = true;break;
                }
            }
            //如果前一个是切割字符或者最后一个是切割字符则不输出
            if((find && pre_char == split_char) || (find && i+1==len))
            {
                continue;
            }
            pre_char = find ? split_char : cur_char;
            sb.append(pre_char);
        }
        return sb.toString();
    }
    
    public static String removeTitle(String str ,int pos, int len)
    {
        if(pos <= -1) return str;
        
        StringBuilder sb = new StringBuilder();
        char[] strs = str.toCharArray();
        int index = 0;//不包含分隔符的index
        for(int i=0;i<strs.length;i++)
        {
            if(strs[i] != split_char)
            {
                ++index;
            }
            if(index > pos && index <= pos + len)
            {
                continue;
            }
            sb.append(strs[i]);
        }
        return sb.toString();
    }
    
    public static String formatOutput(String str)
    {
        //处理边界的分隔符
        int index = str.length()-1;
        int start = str.charAt(0) == split_char ? 1 : 0;
        int length = str.charAt(index) == split_char ? index : index + 1;
        if(start == 0 || length == index)
        {
            str = str.substring(start, length);
        }
        //格式化输出
        if(str.length()>0)
        {
            return "\""+str.replace(",", "\",\"")+"\"";
        }
        return null;
    }
    
    /**
     * 泰国小姐20强出炉佳丽比基尼出镜 <=>泰国小姐20强出炉佳丽比基尼出镜泰国小姐20强佳丽比基尼出镜
     * pos = 0 len = 16
     * @param title
     * @param tag
     * @return 
     */
    public static String parseTag(String title, String tag)
    {
        String _title = filterStr(title);
        String _tag = filterStr(tag);
        String pureTitle = _title.replace(""+split_char, "");
        String pureTag = _tag.replace(""+split_char, "");
        int pos = pureTag.indexOf(pureTitle);
        
        String ret = formatOutput(removeTitle(_tag, pos, pureTitle.length()));
        System.out.println(ret);
        return ret;
    }
    
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
