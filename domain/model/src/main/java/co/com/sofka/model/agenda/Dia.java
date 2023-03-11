package co.com.sofka.model.agenda;

import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.Entity;

import java.util.HashMap;

public class Dia extends Entity<DiaId> {
    private DiaId id;
    private Fecha fecha;
    private Nombre nombre;
    private Hora hora;
    private Disponible disponible;
    private HashMap<Hora, Disponible> horas;

    public Dia( DiaId id, Fecha fecha, Nombre nombre, Hora hora, Disponible disponible) {
        super(id);
        this.fecha = fecha;
        this.nombre = nombre;
        this.hora = hora;
        this.disponible = disponible;
    }

    public Fecha fecha() {
        return fecha;
    }

    public Nombre nombre() {
        return nombre;
    }

    public HashMap<Hora, Disponible> horas() {
        return horas;
    }
}
