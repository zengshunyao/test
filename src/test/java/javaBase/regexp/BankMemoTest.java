package javaBase.regexp;

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
public class BankMemoTest {
    public static void main(String[] args) {
        List<Map<String, String>> mapList = accountMemoHandler("类型  计费  金额 \r\n银行卡:  \r\n欠费   2  0.22\r\n侧小   33   0.33\r\n  DDC消费 34  5.44");

        for (Map<String, String> map : mapList) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.print(entry);
                System.out.print(";");
            }
            System.out.println();
        }
    }

    /**
     * 检索出pos机备注信息
     */
    public static List<Map<String, String>> accountMemoHandler(final String resultStr, final String... keys) {
        return new ArrayList<Map<String, String>>(0x2 << 4) {
            {
                //此正则检索出   类别   计费   金额
                for (final Matcher matcher = Pattern.compile("([A-Z\\u4e00-\\u9fa5]{2,})[\\s:]+(\\d+)[\\s:]+(\\d+\\.\\d+)")
                        .matcher(resultStr);
                     matcher.find(); System.out.println(matcher.group(0))) {
                    add(new HashMap<String, String>(0x2 << 2) {{
                        put("类型", matcher.group(1));
                        put("计费", matcher.group(2));
                        put("金额", matcher.group(3));
                    }});
                }
            }
        };
    }
}
