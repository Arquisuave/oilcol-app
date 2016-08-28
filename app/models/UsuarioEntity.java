package models;
import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "usuarioentity")
public class UsuarioEntity extends Model
{
    private enum TipoUsuario
    {

        JEFE_CAMPO("Jefe de Campo"),
        JEFE_PRODUCCION("Jefe de Producción"),
        PERSONAL("Personal");

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
    private TipoUsuario type;

    @NotNull
    private String password;

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

    public void setType(TipoUsuario type)
    {
        this.type = type;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

}