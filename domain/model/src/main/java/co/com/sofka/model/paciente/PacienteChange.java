package co.com.sofka.model.paciente;

import co.com.sofka.model.paciente.entities.Cita;
import co.com.sofka.model.paciente.entities.HistoriaMedica;
import co.com.sofka.model.paciente.events.CitaAgendada;
import co.com.sofka.model.paciente.events.PacienteCreado;
import co.com.sofka.model.paciente.generic.EventChange;
import co.com.sofka.model.paciente.values.*;

import java.util.ArrayList;

public class PacienteChange extends EventChange {

    public PacienteChange(Paciente paciente){

        apply((PacienteCreado event)-> {
            paciente.nombres = new Nombres(event.getNombres());
            paciente.apellidos = new Apellidos(event.getApellidos());
            paciente.celular = new Celular(event.getCelular());
            paciente.correo = new Correo(event.getCorreo());
            paciente.citas = new ArrayList<>();


        });

        apply((CitaAgendada event)-> {
            Cita cita = new Cita(CitaId.of(event.getId()),
                    new Fecha(event.getFecha()),
                    new Hora(event.getHora()),
                    new Estado(event.getEstado()));
            paciente.citas.add(cita);
        });

    }
}
