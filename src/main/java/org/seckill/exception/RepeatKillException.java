package org.seckill.exception;

/**
 * 重复秒杀异常
 * 运行期异常
 * Created by airmacx on 17-3-14.
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message ,Throwable cause)
    {
        super(message,cause);
    }
}
