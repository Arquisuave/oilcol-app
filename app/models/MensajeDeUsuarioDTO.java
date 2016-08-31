package models;

/**
 * Created by jg.tamura10 on 30/08/2016.
 */


public class MensajeDeUsuarioDTO
{
    private String user;
    private  String password;
    private String type;
    private String payload;

    //en el payload viene el mensaje en json en el caso de cambio de estado en pozo: "nuevoEstado" : "CLAUSURADO"
    public MensajeDeUsuarioDTO()
    {

    }

    public String getUser()
    {
        return user;
    }
    public void setUser(String user)
    {
        this.user = user;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String pass)
    {
        this.password = pass;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String typ)
    {
        this.type = typ;
    }
    public String getPayload()
    {
        return payload;
    }
    public void setPayload(String payload)
    {
        this.payload = payload;
    }


}
