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
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private UsuarioEntity idJefeCampo;

    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy ="campo")
    private List<PozoEntity> pozos;

   // public CampoEntity(Long pId, UsuarioEntity pIdJefeCampo){
     //   id = pId;
       // idJefeCampo = pIdJefeCampo;
   // }

    public CampoEntity(long id, UsuarioEntity idJefeCampo, Region region, List<PozoEntity> pozos) {
        this.id = id;
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

    public UsuarioEntity getIdJefeCampo(){
        return idJefeCampo;
    }

    public void setIdJefeCampo(UsuarioEntity pIdJefeCampo){
        idJefeCampo = pIdJefeCampo;
    }

    public List<PozoEntity> getPozos() {
        return pozos;
    }

    public void setPozos(List<PozoEntity> pPozos){pozos=pPozos;}

}
