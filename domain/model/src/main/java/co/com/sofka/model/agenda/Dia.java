package co.com.sofka.model.agenda;

import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.Entity;

import java.util.HashMap;
import java.util.List;

public class Dia extends Entity<DiaId> {
    private DiaId id;
    private Fecha fecha;
    private Nombre nombre;
    private List<Hora> horas;



    public Dia(DiaId id, Fecha fecha, Nombre nombre,  List<Hora> horas) {
        super(id);
        this.fecha = fecha;
        this.nombre = nombre;
        this.horas = horas;
    }

    public Fecha fecha() {
        return fecha;
    }

    public Nombre nombre() {
        return nombre;
    }

    public List<Hora> horas(){ return horas;}


}

