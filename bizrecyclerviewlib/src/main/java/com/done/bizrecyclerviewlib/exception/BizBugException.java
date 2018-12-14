package com.done.bizrecyclerviewlib.exception;

/**
 * File: com.done.bizrecyclerviewlib.exception.BizBugException.java
 * Description: bug自检异常
 *
 * @author Done
 * @date 2018/12/13
 */

public class BizBugException extends RuntimeException {

    /**
     * Constructs a {@code BizBugException} with no detail message.
     */
    public BizBugException() {
        super();
    }

    /**
     * Constructs a {@code BizBugException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public BizBugException(String s) {
        super(s);
    }
}
