package com.lana.base.syshandle.result;

import com.lana.base.syshandle.exception.LanaExceptionCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/3/16 13:50
 */
@Data
@Schema(description = "响应")
public class LanaResult<T> {
    @Schema(description = "编码 200表示成功，其他值表示失败")
    private int code = 200;

    @Schema(description = "消息内容")
    private String msg = "success";

    //@JsonSerialize(using = LongToStringSerializer.class)
    //@JsonDeserialize(using = StringToLongDeserializer.class)
    @Schema(description = "响应数据")
    private T data;

    public static <T> LanaResult<T> ok() {

        return ok(null);
    }

    public static <T> LanaResult<T> ok(T data) {
        LanaResult<T> lanaResult = new LanaResult<>();
        lanaResult.setData(data);
        return lanaResult;
    }

    public static <T> LanaResult<T> error() {

        return error(LanaExceptionCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> LanaResult<T> error(String msg) {
        return error(LanaExceptionCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static <T> LanaResult<T> error(LanaExceptionCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> LanaResult<T> error(int code, String msg) {
        LanaResult<T> lanaResult = new LanaResult<>();
        lanaResult.setCode(code);
        lanaResult.setMsg(msg);
        return lanaResult;
    }
}
