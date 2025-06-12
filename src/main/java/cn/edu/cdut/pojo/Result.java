package cn.edu.cdut.pojo;

import lombok.Data;

import java.util.HashMap;

@Data
public class Result extends HashMap<String, Object> {

    public Result(Integer code, String msg, Object data) {
        this.put("code", code);
        this.put("msg", msg);
        this.put("data", data);
    }

    public static Result success(Object data){
        return new Result(200, null, data);
    }
    public static Result success(String msg){
        return new Result(200, msg, null);
    }

    public static Result success(){
        return new Result(200, null, null);
    }

    public static Result error(Integer code, String msg){
        return new Result(code, msg, null);
    }


    public static Result error(String msg){
        return new Result(500, msg, null);
    }
}
