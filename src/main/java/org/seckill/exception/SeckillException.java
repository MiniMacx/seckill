package org.seckill.exception;

/**所有秒杀相关的异常
 * Created by airmacx on 17-3-14.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}

