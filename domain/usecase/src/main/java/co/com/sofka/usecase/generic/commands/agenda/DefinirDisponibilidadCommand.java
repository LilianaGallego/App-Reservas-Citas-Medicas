package co.com.sofka.usecase.generic.commands.agenda;

import co.com.sofka.usecase.generic.Command;

import java.util.List;

public class DefinirDisponibilidadCommand extends Command {
    private String agendaId;
    private String diaId;
    private String fecha;
    private String nombre;
    private List<String> horas;


    public DefinirDisponibilidadCommand() {
    }

    public DefinirDisponibilidadCommand(
            String agendaId,
            String diaId,
            String fecha,
            String nombre,
            List<String> horas)
    {
        this.agendaId = agendaId;
        this.diaId = diaId;
        this.fecha = fecha;
        this.nombre = nombre;
        this.horas = horas;

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

    public List<String> getHoras() {
        return horas;
    }

    public void setHoras(List<String> horas) {
        this.horas = horas;
    }
}
