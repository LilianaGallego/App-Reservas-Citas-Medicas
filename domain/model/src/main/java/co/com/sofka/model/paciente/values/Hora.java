package co.com.sofka.model.paciente.values;

import co.com.sofka.model.generic.ValueObject;

public class Hora implements ValueObject<String> {
    private String hora;

    public Hora(String hora) {
        this.hora = hora;
    }

    @Override
    public String value(){
        return hora;
    }


}
