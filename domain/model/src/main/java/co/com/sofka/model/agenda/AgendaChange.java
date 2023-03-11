package co.com.sofka.model.agenda;

import co.com.sofka.model.agenda.events.AgendaCreada;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.EventChange;

import java.util.ArrayList;
import java.util.HashMap;

public class AgendaChange extends EventChange {

    public AgendaChange(Agenda agenda){
        apply((AgendaCreada event)->
        {
            agenda.semana = new Semana(event.getSemana());
            agenda.dias = new ArrayList<>();



        });

        apply((DisponibilidadDefinida event)-> {
            HashMap<Hora,Disponible> horas = new HashMap<>();
            Hora hora =  new Hora(event.getHora());
            Disponible disponible = new Disponible(event.getDisponible());
            horas.put(hora,disponible);

            Dia dia = new Dia(DiaId.of(event.getId()),
                    new Fecha(event.getFecha()),
                    new Nombre(event.getNombre()),
                   hora,disponible
                    );


            agenda.dias.add(dia);
        });
    }


}
