package one.microstream.liberty.liberty.example;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/")
@ApplicationScoped
public class ProductRestApplication extends Application {
}
