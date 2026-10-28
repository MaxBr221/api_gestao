package com.MaxBr221.GitHub.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .schemas("public")
                .load();
        flyway.repair();  // <-- Atualiza os checksums antigos no banco com os arquivos locais
        flyway.migrate(); // <-- Executa as migrações pendentes
        return flyway;
    }
}