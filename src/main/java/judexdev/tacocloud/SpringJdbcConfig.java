package judexdev.tacocloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.net.URISyntaxException;

@Configuration
public class SpringJdbcConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/schema.sql")
                .addScript("db/data.sql").build();
    }
}
