package co.com.sofka.model.paciente;
import co.com.sofka.model.paciente.entities.Cita;
import co.com.sofka.model.paciente.entities.HistoriaMedica;
import co.com.sofka.model.paciente.entities.Revision;
import co.com.sofka.model.paciente.events.cita.CitaAgendada;
import co.com.sofka.model.paciente.events.paciente.PacienteCreado;
import co.com.sofka.model.paciente.events.revision.RevisionCreada;
import co.com.sofka.model.paciente.generic.AggregateRoot;
import co.com.sofka.model.paciente.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class Paciente extends AggregateRoot<PacienteId> {
    protected Nombres nombres;
    protected Apellidos apellidos;
    protected Celular celular;
    protected Correo correo;
    protected HistoriaMedica historiaMedica;
    protected List<Revision> revisiones;
    protected List<Cita> citas;

    public Paciente(PacienteId id,
                    Nombres nombres,
                    Apellidos apellidos,
                    Celular celular,
                    Correo correo
    ) {
        super(id);
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        subscribe(new PacienteChange(this));
        appendChange(new PacienteCreado(
                nombres.value(),
                apellidos.value(),
                celular.value(),
                correo.value()
        )).apply();
    }

    private Paciente (PacienteId pacienteId){
        super(pacienteId);
        subscribe(new PacienteChange(this));
    }

    public static  Paciente from(PacienteId pacienteId, List<DomainEvent> events){
        Paciente paciente = new Paciente(pacienteId);
        events.forEach(event -> paciente.applyEvent(event));
        return paciente;
    }


    public void agendarCita(CitaId citaId, Fecha fecha, Hora hora, Estado estado){
        Objects.requireNonNull(citaId);
        Objects.requireNonNull(fecha);
        Objects.requireNonNull(hora);
        Objects.requireNonNull(estado);
        appendChange( new CitaAgendada(citaId.value(),fecha.value(), hora.value(), estado.value()  )).apply();
    }

    public void crearRevision(RevisionId revisionId,  Fecha fecha, Observacion observacion){
        Objects.requireNonNull(revisionId);
        Objects.requireNonNull(fecha);
        Objects.requireNonNull(observacion);

        appendChange( new RevisionCreada(revisionId.value(),fecha.value(),observacion.value())).apply();
    }



    protected Optional<Cita> findClientById(CitaId citaId) {
        return this.citas.stream().filter(cita -> cita.identity().equals(citaId)).findFirst();
    }

    public List<Cita> getCitas(){
        return citas;
    }
    public List<Revision> getRevisiones(){
        return revisiones;
    }


}