package cn.ccsgroup.ccsframework.common.task.exceptions;

import cn.ccsgroup.ccsframework.common.exceptions.DexcoderException;
import org.quartz.SchedulerException;

/**
 * 自定义异常
 * <p/>
 * Created by shunyao.zeng on 12/19/14.
 */
public class ScheduleException extends DexcoderException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1921648378954132894L;

    /**
     * Instantiates a new ScheduleException.
     *
     * @param e the e
     */
    public ScheduleException(Throwable e) {
        super(e);
    }

    /**
     * Constructor
     *
     * @param message the message
     */
    public ScheduleException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param code    the code
     * @param message the message
     */
    public ScheduleException(String code, String message) {
        super(code, message);
    }

    /**
     * @param message
     * @param e
     */
    public ScheduleException(String message, SchedulerException e) {
        super(message, e);
    }
}
