package co.com.sofka.usecase.generic.commands.agenda;

import co.com.sofka.usecase.generic.Command;

public class DefinirDisponibilidadCommand extends Command {
    private String agendaId;
    private String diaId;
    private String fecha;
    private String nombre;

    private String hora;
    private String disponible;

    public DefinirDisponibilidadCommand() {
    }

    public DefinirDisponibilidadCommand(
            String agendaId,
            String diaId,
            String fecha,
            String nombre,
            String hora,
            String disponible)
    {
        this.agendaId = agendaId;
        this.diaId = diaId;
        this.fecha = fecha;
        this.nombre = nombre;
        this.hora = hora;
        this.disponible = disponible;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getDiaId() {
        return diaId;
    }

    public void setDiaId(String diaId) {
        this.diaId = diaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }
}
