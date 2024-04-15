package result;

/**
 * @author sunyinuo
 */
public class ResultUtil {
    /**
     * HaveData
     * @param code 包装类
     * @param object 数据
     * @param msg 消息
     * @return 包装类
     */
    public static Result result(Integer code,Object object,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    /**
     * NoData
     * @param code 状态码
     * @param msg 消息
     * @return 包装类
     */
    public static Result result(Integer code,String msg){
        return result(code,null,msg);
    }
}
