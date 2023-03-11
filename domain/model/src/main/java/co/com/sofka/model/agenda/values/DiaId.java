package co.com.sofka.model.agenda.values;


import co.com.sofka.model.generic.Identity;

public class DiaId extends Identity {
    private DiaId(String uuid) {
        super(uuid);
    }

    public DiaId() {
    }

    public static DiaId of(String uuid) {
        return new DiaId(uuid);
    }
}
