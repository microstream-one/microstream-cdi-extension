package one.microstream.payara.cdi.extension;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 */
@ApplicationPath("/")
@ApplicationScoped
public class RestaurantRestApplication extends Application {
}
