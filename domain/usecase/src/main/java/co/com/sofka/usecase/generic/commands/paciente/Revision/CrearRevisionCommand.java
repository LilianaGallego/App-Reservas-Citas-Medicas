package co.com.sofka.usecase.generic.commands.paciente.Revision;

import co.com.sofka.usecase.generic.Command;

public class CrearRevisionCommand extends Command {

    private String pacienteId;
    private String citaId;
    private String revisionId;
    private String observacion;
    private String fecha;

    public CrearRevisionCommand() {
    }

    public CrearRevisionCommand(String pacienteId, String revisionId,  String fecha, String observacion) {
        this.pacienteId = pacienteId;
        this.citaId = citaId;
        this.revisionId = revisionId;
        this.observacion = observacion;
        this.fecha = fecha;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getCitaId() {
        return citaId;
    }

    public void setCitaId(String citaId) {
        this.citaId = citaId;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
