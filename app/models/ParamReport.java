package models;

import java.util.Date;

/**
 * Created by camilagarciahernandez on 8/30/16.
 */
public class ParamReport {

    private Date fechaInicio;
    private Date fechaFin;
    private String jefeDeCampo;
    private String region;

    private ParamReport(){

    }

    public ParamReport(Date fechaInicio, Date fechaFin, String jefeDeCampo, String region) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jefeDeCampo = jefeDeCampo;
        this.region = region;
    }

    public ParamReport(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ParamReport(String jefeDeCampo) {
        this.jefeDeCampo = jefeDeCampo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getJefeDeCampo() {
        return jefeDeCampo;
    }

    public void setJefeDeCampo(String jefeDeCampo) {
        this.jefeDeCampo = jefeDeCampo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString(){
        String total = "";
        total+= "fecha incio :"+fechaInicio+"\n";
        total+= "fecha fin :"+fechaFin+"\n";
        total+= "jefe :"+jefeDeCampo+"\n";
        total+= "region :"+region+"\n";
        return total;
    }
}
