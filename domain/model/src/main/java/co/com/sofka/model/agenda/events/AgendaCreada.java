package co.com.sofka.model.agenda.events;

import co.com.sofka.model.generic.DomainEvent;

public class AgendaCreada extends DomainEvent {
    private String semana;

    public AgendaCreada() {
        super("liliana.gallego.agendacreada");
    }

    public AgendaCreada(String semana) {
        super("liliana.gallego.agendacreada");
        this.semana = semana;
    }

    public String getSemana() {
        return semana;
    }
}
