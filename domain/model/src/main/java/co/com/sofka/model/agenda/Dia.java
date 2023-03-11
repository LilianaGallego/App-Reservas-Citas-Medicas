package co.com.sofka.model.agenda;

import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.Entity;

import java.util.HashMap;
import java.util.List;

public class Dia extends Entity<DiaId> {
    private DiaId id;
    private Fecha fecha;
    private Nombre nombre;
    private Hora hora;
    private Disponible disponible;
    private List<String> horas;


    public Dia(DiaId id, Fecha fecha, Nombre nombre, Hora hora, Disponible disponible, List<String> horas) {
        super(id);
        this.fecha = fecha;
        this.nombre = nombre;
        this.hora = hora;
        this.disponible = disponible;
        this.horas = horas;
    }

    public Fecha fecha() {
        return fecha;
    }

    public Nombre nombre() {
        return nombre;
    }

    public void setHoras(String hora, String disponible) {
        this.horas.add(hora);
        this.horas.add(disponible);
    }

    public List<String> horasDefinidas() {
        return horas;
    }
}

