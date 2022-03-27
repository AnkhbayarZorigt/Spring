package cz.sda.store.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@Import(StoreConfig.class)
public class StoreFlywayConfig {

    @Value("classpath:db/migration")
    private Resource migrationFolder;

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) throws IOException {
        FluentConfiguration flyway = Flyway.configure();
        flyway.baselineOnMigrate(false);
        flyway.locations(migrationFolder.getFile().getAbsolutePath());
        flyway.dataSource(dataSource);
        return flyway.load();
    }
}
