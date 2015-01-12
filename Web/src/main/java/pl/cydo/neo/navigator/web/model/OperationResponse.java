package pl.cydo.neo.navigator.web.model;

public class OperationResponse {
    private String result;
    private String message;

    public OperationResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

}
