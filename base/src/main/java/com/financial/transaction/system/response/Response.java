package com.financial.transaction.system.response;

import java.util.List;

public class Response {

    private boolean successful;
    private List<String> errors;

    public Response() {
    }

    public Response(boolean successful, List<String> errors) {
        this.successful = successful;
        this.errors = errors;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
