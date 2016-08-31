package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by camilagarciahernandez on 8/28/16.
 */
@Entity
public class PozoEntity extends Model{

    public static Finder<Long,PozoEntity> FINDER = new Finder<>(PozoEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Product")
    private Long id;

    private Long lon;


    private Long lat;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CampoEntity campo;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pozo")
    private List<RegistroSensorTempEntity> registrosTemp;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pozo")
    private List<RegistroSensorEmergEntity> registrosEmerg;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pozo")
    private List<RegistroSensorEnerEntity> registrosEner;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pozo")
    private List<RegistroSensorBarrilesEntity> registrosBarriles;

    private enum Estado {
        ABIERTO, PRODUCCION, PARADO, CLAUSURADO
    }
    public Estado getEstado(String estado)
    {
        return Estado.valueOf(estado);
    }

    public PozoEntity(Long lon, Long lat, Estado estado, CampoEntity campo,
                      List<RegistroSensorTempEntity> registrosTemp, List<RegistroSensorEmergEntity> registrosEmerg,
                      List<RegistroSensorEnerEntity> registrosEner, List<RegistroSensorBarrilesEntity> registrosBarriles) {
        this.lon = lon;
        this.lat = lat;
        this.estado = estado;
        this.campo = campo;
        this.registrosTemp = registrosTemp;
        this.registrosEmerg = registrosEmerg;
        this.registrosEner = registrosEner;
        this.registrosBarriles = registrosBarriles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public CampoEntity getCampo() {
        return campo;
    }

    public void setCampo(CampoEntity campo) {
        this.campo = campo;
    }

    public List<RegistroSensorTempEntity> getRegistrosTemp() {
        return registrosTemp;
    }

    public void setRegistrosTemp(List<RegistroSensorTempEntity> registrosTemp) {
        this.registrosTemp = registrosTemp;
    }

    public List<RegistroSensorEmergEntity> getRegistrosEmerg() {
        return registrosEmerg;
    }

    public void setRegistrosEmerg(List<RegistroSensorEmergEntity> registrosEmerg) {
        this.registrosEmerg = registrosEmerg;
    }

    public List<RegistroSensorEnerEntity> getRegistrosEner() {
        return registrosEner;
    }

    public void setRegistrosEner(List<RegistroSensorEnerEntity> registrosEner) {
        this.registrosEner = registrosEner;
    }

    public List<RegistroSensorBarrilesEntity> getRegistrosBarriles() {
        return registrosBarriles;
    }

    public void setRegistrosBarriles(List<RegistroSensorBarrilesEntity> registrosBarriles) {
        this.registrosBarriles = registrosBarriles;
    }
}