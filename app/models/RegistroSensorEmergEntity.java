package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by jg.tamura10 on 28/08/2016.
 */
@Entity
public class RegistroSensorEmergEntity implements Serializable
{
    private static final long serialVersionUID = 3L;

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
        timeStamp = timeStampP;
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
    private Date timeStamp;

    public Date getTimeStamp()
    {
        return timeStamp;
    }
}
