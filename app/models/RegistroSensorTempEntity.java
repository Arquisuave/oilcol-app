package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by jg.tamura10 on 28/08/2016.
 */
@Entity
public class RegistroSensorTempEntity extends Model
{
    private static final long serialVersionUID = 6L;
    public static Model.Finder<Long,RegistroSensorTempEntity> FINDER = new Model.Finder<>(RegistroSensorTempEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSensorTemp;

    public RegistroSensorTempEntity()
    {

    }

    public RegistroSensorTempEntity(Long idP)
    {
        idSensorTemp = idP;
    }
    public RegistroSensorTempEntity(PozoEntity pozoP, Date timeStampP, int entradas, double msg)
    {
        pozo = pozoP;
        timeStamp = timeStampP;
        numEntradas = entradas;
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
    private double info;

    public double getInfo()
    {
        return info;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    private Date timeStamp;

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    @NotNull
    @Column(name = "ENTRADAS")
    private int numEntradas;

    public int getNumEntradas()
    {
        return numEntradas;
    }

}
