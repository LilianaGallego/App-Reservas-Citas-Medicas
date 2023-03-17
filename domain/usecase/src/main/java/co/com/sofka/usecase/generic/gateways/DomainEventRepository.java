package co.com.sofka.usecase.generic.gateways;


import co.com.sofka.usecase.agenda.model.DisponibilidadModel;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.generic.commands.paciente.cita.AgendarCitaCommand;
import co.com.sofka.usecase.generic.commands.paciente.paciente.CrearPacienteCommand;
import co.com.sofka.usecase.paciente.model.CitaModel;
import co.com.sofka.usecase.paciente.model.PacienteModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> buscarPorId(String aggregateId);
    Mono<DomainEvent> guardarEvento(DomainEvent event);
    Mono<Boolean> existeDiaId(String diaId);
    Mono<Boolean> existePorPacienteId(String aggregateRootId);
    Mono<DisponibilidadModel> guardarDisponibilidad(DefinirDisponibilidadCommand command);
    Mono<Boolean> existePorFecha(String fecha, String hora);
    Mono <DisponibilidadModel> ActualizarHoraDisponible(String hora, String fecha);
    Mono <DisponibilidadModel> CambiarHoraADisponible(String hora, String fecha);
    Mono<CitaModel> guardarCita(AgendarCitaCommand command);
    Mono<PacienteModel> guardarPaciente(CrearPacienteCommand command);

    Flux <DisponibilidadModel> listarDisponibilidad();
    Mono<Boolean> existePorCitaId(String citaId);

    Mono<Void> eliminarCita(String citaId);
    Mono<CitaModel> buscarCitaporId(String citaId);
    Mono<DomainEvent> actualizarCorreo(String pacienteId, String correo);







}
