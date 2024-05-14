package com.jd.assessment.response;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import com.jd.assessment.enums.ResultCode;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @Author Jiade.Xu
 * @Date 2023/5/8 14:17
 */
@Schema(description = "Standard response in json format")
@Data
public class HttpResponse {

    @Parameter(description = "whether request process success.")
    private boolean success;

    @Parameter(description = "response status code")
    private int resultCode;

    @Parameter(description = "response message, if request process fail, this parameter means error message")
    private String message;

    public static HttpResponse success(){
        HttpResponse response = new HttpResponse();
        response.setSuccess(true);
        response.setResultCode(ResultCode.SUCCESS.getCode());
        response.setMessage(ResultCode.SUCCESS.getMessage());
        return response;
    }

    public static HttpResponse fail(ResultCode resultCode){
        return fail(resultCode, null);
    }

    public static HttpResponse fail(ResultCode resultCode, String errorMessage){
        HttpResponse response = new HttpResponse();
        response.setResultCode(resultCode.getCode());
        if(StringUtils.hasText(errorMessage)){
            response.setMessage(errorMessage);
        }else{
            response.setMessage(response.getMessage());
        }
        response.setSuccess(false);
        return response;
    }
}
