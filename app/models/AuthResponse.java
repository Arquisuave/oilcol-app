package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;


public class AuthResponse extends Model
{
    private String token;
    private String message;
    private Long timestamp;

    public AuthResponse(String token, String message, Long timestamp)
    {
        this.token = token;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getToken()
    {
        return token;
    }

    public String getMessage()
    {
        return message;
    }

    public Long getTimestamp()
    {
        return timestamp;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

}