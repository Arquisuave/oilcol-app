package models;

import java.util.Date;

/**
 * Created by camilagarciahernandez on 8/30/16.
 */
public class ParamReport {

    private Date fechaInicio;
    private Date fechaFin;
    private UsuarioEntity jefeDeCampo;

    private ParamReport(){

    }

    public ParamReport(Date fechaInicio, Date fechaFin, UsuarioEntity jefeDeCampo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jefeDeCampo = jefeDeCampo;
    }

    public ParamReport(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ParamReport(UsuarioEntity jefeDeCampo) {
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

    public UsuarioEntity getJefeDeCampo() {
        return jefeDeCampo;
    }

    public void setJefeDeCampo(UsuarioEntity jefeDeCampo) {
        this.jefeDeCampo = jefeDeCampo;
    }
}
