package njit.cs602.qiyi.assignment3.genericMethod;

/**
 * <p>
 * InvalidSubscriptException
 * </p>
 *
 * @author qiyi
 * @version 2016-4-15
 */
public class InvalidSubscriptException extends RuntimeException {

    private static final long serialVersionUID = -5115758291750062068L;
    public InvalidSubscriptException(String msg){
        super(msg);
    }
}
