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

    private Long idJefeCampo;

    @OneToMany(mappedBy ="campo")
    private List<PozoEntity> pozos;

    public CampoEntity(Long pId, Long pIdJefeCampo){
        id = pId;
        idJefeCampo = pIdJefeCampo;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long pId){
        id = pId;
    }

    public Long getIdJefeCampo(){
        return idJefeCampo;
    }

    public void setIdJefeCampo(Long pIdJefeCampo){
        idJefeCampo = pIdJefeCampo;
    }

    public java.util.List<PozoEntity> getPozos() {
        return pozos;
    }

}
