package co.com.sofka.model.paciente.events.revision;

import co.com.sofka.model.paciente.generic.DomainEvent;

public class RevisionCreada  extends DomainEvent {

    private String id;
    private String observacion;

    private String fecha;

    public RevisionCreada() {
        super("liliana.gallego.revisioncreada");
    }

    public RevisionCreada( String id,  String fecha, String observacion) {
        super("liliana.gallego.revisioncreada");
        this.id = id;
        this.observacion = observacion;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getFecha() {
        return fecha;
    }
}
