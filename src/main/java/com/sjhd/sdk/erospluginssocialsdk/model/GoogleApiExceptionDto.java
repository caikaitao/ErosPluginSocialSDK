package com.sjhd.sdk.erospluginssocialsdk.model;

public class GoogleApiExceptionDto {
    private String errorMsg;

    private GoogleApiExceptionDto(Builder builder) {
        this.errorMsg = builder.errorMsg;
    }

    public static Builder newGoogleApiExceptionDto() {
        return new Builder();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static final class Builder {
        private String errorMsg;

        private Builder() {
        }

        public GoogleApiExceptionDto build() {
            return new GoogleApiExceptionDto(this);
        }

        public Builder errorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }
    }
}
