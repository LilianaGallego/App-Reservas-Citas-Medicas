package co.com.sofka.model.paciente.values;

import co.com.sofka.model.paciente.generic.ValueObject;

public class Fecha implements ValueObject<String> {
    private String fecha;

    public Fecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String value(){
        return fecha;
    }


}
