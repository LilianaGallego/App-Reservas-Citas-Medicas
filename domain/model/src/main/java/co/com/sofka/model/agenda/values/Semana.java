package co.com.sofka.model.agenda.values;

import co.com.sofka.model.generic.ValueObject;

public class Semana implements ValueObject<String> {
    private String value;

    public Semana(String value) {
        this.value = value;
    }

    @Override
    public String value(){
        return value;
    }


}
