package com.jd.assessment.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Jiade.Xu
 * @Date 2023/4/25 16:13
 */
@Getter
@Setter
@ToString
public class HttpDataResponse<T> extends HttpResponse {

    private T data;

    public static <T> HttpDataResponse<T> success(T data){
        HttpDataResponse<T> response = new HttpDataResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setResultCode(200);
        response.setMessage("SUCCESS");
        return response;
    }
}
