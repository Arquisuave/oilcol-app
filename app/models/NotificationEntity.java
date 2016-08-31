package models;

import com.sun.javafx.beans.IDProperty;

import java.util.List;
import javax.persistence.*;
import com.avaje.ebean.Model;
import javax.validation.constraints.NotNull;

/**
 * Created by ea.margffoy10 on 28/08/2016.
 */
@Entity
public class NotificationEntity extends Model{

    public static Finder<Long,NotificationEntity> FINDER = new Finder<>(NotificationEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Notification")
    private Long id;

    @ManyToOne
    private UsuarioEntity username;

    @NotNull
    private String message;

    @OneToOne
    private RegistroSensorEmergEntity register;

    @NotNull
    private boolean resolved;

    public NotificationEntity(Long id, UsuarioEntity username, String message, RegistroSensorEmergEntity register)
    {
        this.id = id;
        this.username = username;
        this.message = message;
        this.register = register;
        resolved = false;
    }

    public UsuarioEntity getUsername()
    {
        return username;
    }

    public Long getId()
    {
        return id;
    }

    public String getMessage()
    {
        return message;
    }

    public RegistroSensorEmergEntity getRegister()
    {
        return register;
    }

    public void setUsername(UsuarioEntity username)
    {
        this.username = username;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public boolean getStatus()
    {
        return resolved;
    }

    public void setRegister(RegistroSensorEmergEntity register)
    {
        this.register = register;
    }

    public void setResolved(boolean status)
    {
        this.resolved = status;
    }

}
