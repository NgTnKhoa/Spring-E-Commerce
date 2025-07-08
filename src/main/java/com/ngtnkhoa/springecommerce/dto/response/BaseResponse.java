package com.ngtnkhoa.springecommerce.dto.response;

public class BaseResponse {
    public String message;
    public boolean status;
    public int statusCode;
    public Object data;

    private BaseResponse() {}
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String message;
        private boolean status;
        private int statusCode;
        private Object data;
        
        public Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public Builder status(boolean status) {
            this.status = status;
            return this;
        }
        
        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }
        
        public Builder data(Object data) {
            this.data = data;
            return this;
        }
        
        public BaseResponse build() {
            BaseResponse response = new BaseResponse();
            response.message = message;
            response.status = status;
            response.statusCode = statusCode;
            response.data = data;
            
            return response;
        }
    }
}
