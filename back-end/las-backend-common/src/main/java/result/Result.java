package result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sunyinuo
 */
@Data
@AllArgsConstructor
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public Result() {
        super();
    }

}
