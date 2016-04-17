package njit.cs602.qiyi.assignment3.genericMethod;

/**
 * <p>
 * InvalidSubscriptException
 * </p>
 *
 * @author qiyi
 * @version 2016-4-15
 */
@SuppressWarnings("serial")
public class InvalidSubscriptException extends RuntimeException {

    public InvalidSubscriptException(String msg){
        super(msg);
    }
}
