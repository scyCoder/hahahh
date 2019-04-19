package com.jrx.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 封装返回数据的实体类
 *
 * @Author: sunchuanyin
 * @Date: 2019/4/16 10:40
 * @Version 1.0
 */
@ApiModel(value = "ResponseData", description = "返回数据的封装类")
public class ResponseData {

    /**
     * 返回的数据
     */
    @ApiModelProperty("返回的数据")
    private Object responseData;

    /**
     * 返回的状态码
     */
    @ApiModelProperty("状态码")
    private String responseStatusCode;

    /**
     * 返回的提示信息
     */
    @ApiModelProperty("提示信息")
    private String responseMessgae;

    public ResponseData(Object responseData, String responseStatusCode, String responseMessgae) {
        this.responseData = responseData;
        this.responseStatusCode = responseStatusCode;
        this.responseMessgae = responseMessgae;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(String responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getResponseMessgae() {
        return responseMessgae;
    }

    public void setResponseMessgae(String responseMessgae) {
        this.responseMessgae = responseMessgae;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseData{");
        sb.append("responseData=").append(responseData);
        sb.append(", responseStatusCode='").append(responseStatusCode).append('\'');
        sb.append(", responseMessgae='").append(responseMessgae).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
