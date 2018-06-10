package sandbox.util;

import org.apache.commons.lang.math.RandomUtils;

import java.io.File;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/3/6 0:01
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public final class ConstantParam {
    public static final String SEPARATOR = File.separator;
    public static final RandomUtils RANDOM =new RandomUtils();
    public static final int OUTPUT_LIMIT = 1024;
    public static final byte SPACE =1 ;
}
