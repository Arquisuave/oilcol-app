package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by jg.tamura10 on 28/08/2016.
 */
@Entity
public class RegistroSensorBarrilesEntity extends Model
{
    private static final long serialVersionUID = 5L;
    public static Model.Finder<Long,RegistroSensorBarrilesEntity> FINDER = new Model.Finder<>(RegistroSensorBarrilesEntity.class);
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idSensorBarriles;

    public RegistroSensorBarrilesEntity()
    {

    }
    public RegistroSensorBarrilesEntity(Long idP)
    {
        idSensorBarriles = idP;
    }
    public RegistroSensorBarrilesEntity(PozoEntity pozoP, Date timeStampP, int entradas, double msg)
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
    public void setInfo(double inf)
    {
         info = inf;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
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
    public void setNumEntradas(int nuevoNum)
    {
         numEntradas = nuevoNum;
    }
}
