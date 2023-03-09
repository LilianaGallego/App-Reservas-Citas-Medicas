package co.com.sofka.model.paciente.events;

import co.com.sofka.model.paciente.generic.DomainEvent;

public class PacienteCreado extends DomainEvent {
    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;
    /*private String historiaMedicaId;
    private String anexo;

    private String revisionId;
    private String observacion;
    private String fecha;
    private String citaId;
    private String hora;
    private boolean estado;*/


    public PacienteCreado() {
        super("liliana.gallego.pacientecreado");
    }

    public PacienteCreado(String nombres,
                          String apellidos,
                          String celular,
                          String correo/*,
                          String historiaMedicaId,
                          String anexo,
                          String revisionId,
                          String observacion,
                          String fecha,
                          String citaId,
                          String hora,
                          boolean estado¨*/) {
        super("liliana.gallego.pacientecreado");
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        /*this.historiaMedicaId = historiaMedicaId;
        this.anexo = anexo;
        this.revisionId = revisionId;
        this.observacion = observacion;
        this.fecha = fecha;
        this.citaId = citaId;
        this.hora = hora;
        this.estado = estado;*/
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public String getCorreo() {
        return correo;
    }

    /*public String getHistoriaMedicaId() {
        return historiaMedicaId;
    }

    public String getAnexo() {
        return anexo;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCitaId() {
        return citaId;
    }

    public String getHora() {
        return hora;
    }

    public boolean getEstado() {
        return estado;
    }*/
}
