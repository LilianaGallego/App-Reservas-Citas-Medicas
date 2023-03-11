package co.com.sofka.model.paciente.entities;

import co.com.sofka.model.generic.Entity;
import co.com.sofka.model.paciente.values.*;

public class Cita extends Entity<CitaId> {
    private Fecha fecha;
    private Hora hora;
    private Estado estado;

    public Cita(CitaId id, Fecha fecha, Hora hora, Estado estado) {
        super(id);
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }


}