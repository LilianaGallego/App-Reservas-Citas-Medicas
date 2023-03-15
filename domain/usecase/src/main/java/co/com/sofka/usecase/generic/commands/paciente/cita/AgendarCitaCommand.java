package co.com.sofka.usecase.generic.commands.paciente.cita;

import co.com.sofka.usecase.generic.Command;

public class AgendarCitaCommand extends Command {

    private String PacienteId;
    private String agendaId;
    private String citaId;
    private String fecha;
    private String hora;
    private String estado;

    public AgendarCitaCommand() {
    }

    public AgendarCitaCommand(String pacienteId,String agendaId, String citaId, String fecha, String hora, String estado) {
        PacienteId = pacienteId;
        this.agendaId = agendaId;
        this.citaId = citaId;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public String getPacienteId() {
        return PacienteId;
    }

    public void setPacienteId(String pacienteId) {
        PacienteId = pacienteId;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getCitaId() {
        return citaId;
    }

    public void setCitaId(String citaId) {
        this.citaId = citaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
