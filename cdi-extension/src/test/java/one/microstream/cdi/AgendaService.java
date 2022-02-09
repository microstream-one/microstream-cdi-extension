package one.microstream.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
public class AgendaService {

    @Inject
    private Agenda agenda;

    @Store
    public void addNameLazy(String name) {
        Objects.requireNonNull(name, "name is required");
        agenda.add(name);
    }

    @Store(StoreType.EAGER)
    public void addNameEager(String name) {
        Objects.requireNonNull(name, "name is required");
        agenda.add(name);
    }
}
