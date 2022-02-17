package one.microstream.cdi.interceptor;

import one.microstream.cdi.Agenda;
import one.microstream.cdi.Store;
import one.microstream.cdi.StoreType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@ApplicationScoped
@Store
public class AgendaService3 {

    @Inject
    private Agenda agenda;

    @Store(fields = "names")
    public void add(String name) {
        Objects.requireNonNull(name, "name is required");
        agenda.add(name);
    }
}
