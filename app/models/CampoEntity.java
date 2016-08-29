package models;

import com.sun.javafx.beans.IDProperty;

import java.util.List;
import javax.persistence.*;
import com.avaje.ebean.Model;

/**
 * Created by mm.gomez10 on 28/08/2016.
 */
@Entity
@Table(name = "campoentity")
public class CampoEntity extends Model{

    public enum Region{
        ANDINA,
        CARIBE,
        PACIFICA,
        ORINOQUIA,
        AMAZONAS
    }

    public static Finder<Long,CampoEntity> FINDER = new Finder<>(CampoEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Campo")
    private Long id;

    private String idJefeCampo;

    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy ="campo")
    private List<PozoEntity> pozos;

    public CampoEntity(Long pId, String pIdJefeCampo){
        id = pId;
        idJefeCampo = pIdJefeCampo;
    }

    public CampoEntity(String idJefeCampo, Region region, List<PozoEntity> pozos) {
        this.idJefeCampo = idJefeCampo;
        this.region = region;
        this.pozos = pozos;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long pId){
        id = pId;
    }

    public String getIdJefeCampo(){
        return idJefeCampo;
    }

    public void setIdJefeCampo(String pIdJefeCampo){
        idJefeCampo = pIdJefeCampo;
    }

    public List<PozoEntity> getPozos() {
        return pozos;
    }

    public void setPozos(List<PozoEntity> pPozos){pozos=pPozos;}

}
