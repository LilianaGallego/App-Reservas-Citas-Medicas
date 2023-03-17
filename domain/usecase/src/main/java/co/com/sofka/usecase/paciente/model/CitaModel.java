package co.com.sofka.usecase.paciente.model;

import co.com.sofka.usecase.generic.commands.paciente.cita.AgendarCitaCommand;

public class CitaModel {

    private String PacienteId;
    private String agendaId;
    private String citaId;
    private String correoPaciente;
    private String fecha;
    private String hora;
    private String estado;

    public CitaModel() {
    }

    public CitaModel(AgendarCitaCommand command) {
        PacienteId = command.getPacienteId();
        this.agendaId = command.getAgendaId();
        this.citaId = command.getCitaId();
        this.correoPaciente = getCorreoPaciente();
        this.fecha = command.getFecha();
        this.hora = command.getHora();
        this.estado = command.getEstado();
    }

    public CitaModel(String pacienteId, String agendaId, String citaId, String correoPaciente, String fecha, String hora, String estado) {
        PacienteId = pacienteId;
        this.agendaId = agendaId;
        this.citaId = citaId;
        this.correoPaciente = correoPaciente;
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

    public String getCorreoPaciente() {
        return correoPaciente;
    }

    public void setCorreoPaciente(String correoPaciente) {
        this.correoPaciente = correoPaciente;
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
