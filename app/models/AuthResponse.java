package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;


public class AuthResponse extends Model
{
    private String token;
    private String message;
    private UsuarioEntity.TipoUsuario role;
    private Long timestamp;

    public AuthResponse(String token, String message, Long timestamp, UsuarioEntity.TipoUsuario type)
    {
        this.token = token;
        this.message = message;
        this.timestamp = timestamp;
        this.role = type;
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

    public UsuarioEntity.TipoUsuario getRole()
    {
        return role;
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

    public void setRole(UsuarioEntity.TipoUsuario role)
    {
        this.role = role;
    }

}