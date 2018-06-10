package sandbox.util;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/3/6 0:08
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public final class JudgeResult {
    public static final int MEMORY_LIMIT_EXCEED =0 ;
    public static final int RESTRICT_FUNCTION = 1;
    public static final int TIME_LIMITE_EXCEED =2 ;
    public static final int OUTPUT_LIMIT_EXCEED =3 ;
    public static final int RUNTIME_ERROR =4 ;
    public static final int WRONG_ANSWER = 5;
    public static final int PRESENTING_ERROR =6 ;
    public static final int ACCEPTED =7 ;
    public static final int RUNTIME_STACK_OVERFLOW =8 ;
    public static final int RUNTIME_DIVIDE_BY_ZERO = 9;
    public static final int RUNTIME_ACCESS_VIOLATION =10 ;
    public static final int RUNTIME_ARRAY_BOUNDS_EXCEEDED =11 ;

    public static String toString(int resultType) {
        return resultType+"";
    }
}
