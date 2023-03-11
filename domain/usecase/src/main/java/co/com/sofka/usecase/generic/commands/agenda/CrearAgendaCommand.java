package co.com.sofka.usecase.generic.commands.agenda;

import co.com.sofka.usecase.generic.Command;


public class CrearAgendaCommand extends Command {

    private String agendaId;
    private String semana;


    public CrearAgendaCommand() {
    }

    public CrearAgendaCommand(String agendaId, String semana){
        this.agendaId = agendaId;
        this.semana = semana;
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
}
