package javaBase.regexp;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class RegexpTest {
    public static void main(String[] args) {

        //被替换关键字的的数据源
        Map<String, String> tokens = new HashMap<String, String>();
        tokens.put("cat", "Garfield");
        tokens.put("beverage", "coffee");

        //匹配类似velocity规则的字符串
        String template = "${cat} really needs some ${beverage}.";
        //生成匹配模式的正则表达式
        String patternString = "\\$\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}";
//        Regexp regexp=new Regexp();
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(template);

        //两个方法：appendReplacement, appendTail
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
        }
        matcher.appendTail(sb);

        //out: Garfield really needs some coffee.
        System.out.println(sb.toString());

        //对于特殊含义字符"\","$"，使用Matcher.quoteReplacement消除特殊意义
        matcher.reset();
        //out: cat really needs some beverage.
        System.out.println(matcher.replaceAll("$1"));
        //out: $1 really needs some $1.
        System.out.println(matcher.replaceAll(Matcher.quoteReplacement("$1")));

        //到得邮箱的前缀名。插一句，其实验证邮箱的正则多种多样，根据自己的需求写对应的正则才是王道
        String emailPattern = "^([a-z0-9_\\.\\-\\+]+)@([\\da-z\\.\\-]+)\\.([a-z\\.]{2,6})$";
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher("test@qq.com");
        //验证是否邮箱
        System.out.println(matcher.find());
        //得到@符号前的邮箱名  out: test
        System.out.println(matcher.replaceAll("$1"));

        //获得匹配值
        String temp = "<meta-data android:name=\"appid\" android:value=\"joy\"></meta-data>";
        pattern = Pattern.compile("android:(name|value)=\"(.+?)\"");
        matcher = pattern.matcher(temp);
        while (matcher.find()) {
            //out: appid, joy
            System.out.println(matcher.group(2));
        }
        System.out.println("A&&RAA".matches("[A-Z&&[RFG]]"));
        List<Map<String, String>> mapList = test("类型  计费  金额 \r\n银行卡:  \r\n欠费   2  0.22\r\n侧小   33   0.33\r\n  DDC消费 34  5.44");
        for (Map<String, String> map : mapList) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.print(entry);
                System.out.print(";");
            }
            System.out.println();
        }
        //System.exit(0);
        //test("类型  计费  金额 \r\n银行卡:  \r\n欠费   2  0.22\r\n侧小   33   0.33\r\n  DDC消费 34  5.44");
    }

    /**
     * 检索出pos机备注信息
     */
    public static List<Map<String, String>> test(final String resultStr) {
        return new ArrayList<Map<String, String>>(0x2 << 4) {
            {
                //此正则检索出   类别   计费   金额
//                for (final Matcher matcher = Pattern.compile("([A-Z\\u4e00-\\u9fa5]{2,})\\|(\\d+)\\|(\\d+\\.\\d+)")
//                .matcher(resultStr.replaceAll("[\\s:]+", "|"));//将 一个或者多个空字符替换为|
                //此正则检索出   类别   计费   金额
                for (final Matcher matcher = Pattern.compile("([A-Z\\u4e00-\\u9fa5]{2,})[\\s:]+(\\d+)[\\s:]+(\\d+\\.\\d+)")
                        .matcher(resultStr);
                     matcher.find(); System.out.println(matcher.group(0))) {
                    add(new HashMap<String, String>(0x2<<2) {{
                        put("类型", matcher.group(1));
                        put("计费", matcher.group(2));
                        put("金额", matcher.group(3));
                    }});
                }
            }
        };
    }
}
