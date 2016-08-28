package models;

import javax.persistence.*;

/**
 * Created by camilagarciahernandez on 8/28/16.
 */
@Entity
public class PozoEntity {

    @Id @GeneratedValue
    private Long id;

    private Long lon;

    private Long lat;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(optional=false)
    private CampoEntity campo;

    @OneToMany(mappedBy="campo")
    private List<RegistroEntity> registros;

    private enum Estado{
        ABIERTO, PRODUCCION, PARADO, CLAUSURADO
    }

    public PozoEntity(Long lon, Long lat, Estado estado, CampoEntity campo, List<RegistroEntity> registros) {
        this.lon = lon;
        this.lat = lat;
        this.estado = estado;
        this.campo = campo;
        this.registros = registros;
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

    public List<RegistroEntity> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroEntity> registros) {
        this.registros = registros;
    }
}
