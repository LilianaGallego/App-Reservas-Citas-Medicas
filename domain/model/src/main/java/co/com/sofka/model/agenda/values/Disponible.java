package co.com.sofka.model.agenda.values;


import co.com.sofka.model.generic.ValueObject;

public class Disponible implements ValueObject<String> {
    private String disponible;

    public Disponible(String disponible) {
        this.disponible = disponible;
    }

    @Override
    public String value(){
        return disponible;
    }

    @Override
    public String toString() {
        return "Disponible{" +
                "disponible='" + disponible + '\'' +
                '}';
    }
}
