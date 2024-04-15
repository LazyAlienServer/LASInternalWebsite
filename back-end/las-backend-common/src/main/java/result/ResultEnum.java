package result;

/**
 * @author sunyinuo
 */
public enum ResultEnum {
    //常见code
    SUCCESS(200),
    SERVER_ERROR(403),
    NOT_FOUND(404);


    private final Integer code;

    ResultEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
