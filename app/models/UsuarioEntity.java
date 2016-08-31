package models;
import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "usuarioentity")
public class UsuarioEntity extends Model
{
    public TipoUsuario getTipoUsuario(String tipo)
    {
         return TipoUsuario.valueOf(tipo);
    }
    public enum TipoUsuario
    {

        JEFE_CAMPO("Jefe de Campo"),
        JEFE_PRODUCCION("Jefe de Producción"),
        PERSONAL("Personal"),
        NONE("Ninguno");

        public String type;

        TipoUsuario(String type)
        {
            this.type = type;
        }

        public String getType()
        {
            return type;
        }
    } 

    public static Model.Finder<String,UsuarioEntity> FINDER = new Model.Finder<>(UsuarioEntity.class);

    @Id
    private String username;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoUsuario type;

    @NotNull
    private String password;

    @OneToMany(mappedBy="username")
    private List<NotificationEntity> notifications;

    public UsuarioEntity() 
    {
        username = null;
    }

    public UsuarioEntity(String username, TipoUsuario type, String password) 
    {
        this.username = username;
        this.type = type;
        this.password = password;
    }

    public UsuarioEntity(String username) {
        this();
        this.username = username;
    }

    public TipoUsuario getType()
    {
        return type;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public List<NotificationEntity> getNotifications()
    {
        return notifications;
    }

    public void setType(TipoUsuario type)
    {
        this.type = type;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}