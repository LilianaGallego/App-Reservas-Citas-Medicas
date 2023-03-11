package co.com.sofka.model.agenda;

import co.com.sofka.model.agenda.events.AgendaCreada;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.EventChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgendaChange extends EventChange {

    public AgendaChange(AgendaSemanal agenda){
        apply((AgendaCreada event)->
        {
            agenda.semana = new Semana(event.getSemana());
            agenda.dias = new ArrayList<>();

        });

        apply((DisponibilidadDefinida event)-> {
            List<String> horas = new ArrayList<>();
            String hora =  new Hora(event.getHora()).value();
            String disponible = new Disponible(event.getDisponible()).value();
            horas.add(hora);
            horas.add(disponible);

            Dia dia = new Dia(DiaId.of(event.getId()),
                    new Fecha(event.getFecha()),
                    new Nombre(event.getNombre()),
                    new Hora(event.getHora()),
                    new Disponible(event.getDisponible()),
                    horas);


            agenda.dias.add(dia);
        });
    }


}
