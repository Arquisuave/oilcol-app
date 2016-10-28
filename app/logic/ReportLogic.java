package logic;

import com.avaje.ebean.Junction;
import models.ParamReport;

/**
 * Created by camilagarciahernandez on 8/30/16.
 */
public class ReportLogic {

    public static Junction crearQuery(Junction clause, ParamReport params){
        System.out.print(params.getPozoId());
        if(params.getFechaInicio()!=null&&params.getFechaFin()!=null){
            clause.between("TIMESTAMP", params.getFechaInicio(),params.getFechaFin());
        }
        else if(params.getFechaInicio()!=null){
            clause.ge("TIMESTAMP",params.getFechaInicio());
        }
        else if(params.getFechaFin()!=null){
            clause.le("TIMESTAMP",params.getFechaFin());
        }
        if(params.getJefeDeCampo()!=null){
            clause.eq("pozo.campo.idJefeCampo.username",params.getJefeDeCampo());
        }
        if(params.getRegion()!=null){
            clause.eq("pozo.campo.region",params.getRegion());
        }
        if(params.getPozoId()>0){
            clause.eq("pozo_id",params.getPozoId());
        }
        return clause;
    }
}
