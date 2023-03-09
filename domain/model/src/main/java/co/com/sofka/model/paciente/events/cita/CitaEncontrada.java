package co.com.sofka.model.paciente.events.cita;

import co.com.sofka.model.paciente.generic.DomainEvent;

public class CitaEncontrada extends DomainEvent {
    private String id;

    public CitaEncontrada() {
        super("liliana.gallego.citaencontrada");
    }

    public CitaEncontrada(String id) {
        super("liliana.gallego.citaencontrada");
        this.id = id;
    }
}
