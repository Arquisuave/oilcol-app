package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by jg.tamura10 on 28/08/2016.
 */
@Entity
public class RegistroSensorEmergEntity extends Model
{

    public enum TipoEmergencia
    {
        INCENDIO("Incendio"),
        BLOQUEO_POZO("Bloqueo"),
        FALLA_ELECTRICA("Falla Eléctrica");

        public String type;

        TipoEmergencia(String type)
        {
            this.type = type;
        }

        public String getType()
        {
            return type;
        }
    }

    private static final long serialVersionUID = 4L;
    public static Model.Finder<Long,RegistroSensorEmergEntity> FINDER = new Model.Finder<>(RegistroSensorEmergEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSensorEmerg;

    public RegistroSensorEmergEntity()
    {

    }
    public RegistroSensorEmergEntity(Long idP)
    {
        idSensorEmerg = idP;
    }
    public RegistroSensorEmergEntity(PozoEntity pozoP, Date timeStampP,  String msg)
    {
        pozo = pozoP;
        timestamp = timeStampP;
        info = msg;
    }
    @NotNull
    @ManyToOne(optional = false)
    private PozoEntity pozo;

    public PozoEntity getPozo()
    {
        return pozo;
    }

    @NotNull
    @Column(name = "INFO")
    private String info;

    public String getInfo()
    {
        return info;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    private Date timestamp;

    public Date getTimestamp()
    {
        return timestamp;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEmergencia tipo;

    public TipoEmergencia getTipo()
    {
        return tipo;
    }

    @OneToOne(mappedBy="register")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private NotificationEntity notification;
    public NotificationEntity getNotification()
    {
        return notification;
    }    


}
