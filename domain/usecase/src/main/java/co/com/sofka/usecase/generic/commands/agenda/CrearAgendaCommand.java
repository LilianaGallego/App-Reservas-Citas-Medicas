package co.com.sofka.usecase.generic.commands.agenda;

import co.com.sofka.usecase.generic.Command;

import java.util.List;


public class CrearAgendaCommand extends Command {

    private String agendaId;
    private String semana;
    private String diaId;
    private String fecha;
    private String nombre;
    private List<String> horas;

    public CrearAgendaCommand() {
    }

    public CrearAgendaCommand(String agendaId, String semana){
        this.agendaId = agendaId;
        this.semana = semana;
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

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public List<String> getHoras() {
        return horas;
    }


    public void setHoras(List<String> horas) {
        this.horas = horas;
    }
}
