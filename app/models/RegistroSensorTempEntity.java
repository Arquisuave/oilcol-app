package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idSensorTemp;

    public RegistroSensorTempEntity()
    {

    }

    public RegistroSensorTempEntity(Long idP)
    {

        idSensorTemp = idP;
    }

    public RegistroSensorTempEntity(PozoEntity pozoP, Date timeStampP, double msg)
    {
        pozo = pozoP;
        timeStamp = timeStampP;
        numEntradas = 0;
        info = msg;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public void setIdSensorTemp(Long idSensorTemp) {
        this.idSensorTemp = idSensorTemp;
    }

    public void setPozo(PozoEntity pozo) {
        this.pozo = pozo;
    }

    public void setInfo(double info) {
        this.info = info;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }
}
