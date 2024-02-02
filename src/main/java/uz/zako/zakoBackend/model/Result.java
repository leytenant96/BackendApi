package uz.zako.zakoBackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    String message;
    boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String exception;

    public Result success(Object data){
        return new Result("Success",true,data,null);
    }

    public Result failed(Exception exception){
        return new Result("Failed",false,null,exception.getMessage());
    }
}
