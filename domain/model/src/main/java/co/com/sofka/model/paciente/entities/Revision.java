package co.com.sofka.model.paciente.entities;

import co.com.sofka.model.generic.Entity;
import co.com.sofka.model.paciente.values.Fecha;
import co.com.sofka.model.paciente.values.Observacion;
import co.com.sofka.model.paciente.values.RevisionId;

public class Revision extends Entity<RevisionId> {
    private Fecha fecha;
    private Observacion observacion;

    public Revision(RevisionId id, Fecha fecha, Observacion observacion) {
        super(id);
        this.fecha = fecha;
        this.observacion = observacion;
    }
}

