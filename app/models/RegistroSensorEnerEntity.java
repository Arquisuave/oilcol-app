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
public class RegistroSensorEnerEntity extends Model
{
    private static final long serialVersionUID = 3L;
    public static Model.Finder<Long,RegistroSensorEnerEntity> FINDER = new Model.Finder<>(RegistroSensorEnerEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSensorEner;

    public RegistroSensorEnerEntity()
    {

    }
    public RegistroSensorEnerEntity(Long idP)
    {
        idSensorEner = idP;
    }
    public RegistroSensorEnerEntity(PozoEntity pozoP, Date timeStampP, int entradas, double msg)
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
