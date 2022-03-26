package cz.sda.store.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
public class StoreInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.debug("Creating application context");
        createApplicationContext(servletContext);
    }

    private void createApplicationContext(final ServletContext servletContext) {
        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setServletContext(servletContext);
        applicationContext.register(StoreConfig.class, StorePropertiesConfig.class);

        applicationContext.refresh();
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(getDispatcher()));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

    private AnnotationConfigWebApplicationContext getDispatcher() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setConfigLocation("cz.sda.store.config");
        return ctx;
    }
}
