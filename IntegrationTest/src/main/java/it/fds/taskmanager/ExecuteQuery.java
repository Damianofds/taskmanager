package it.fds.taskmanager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.sql.SQLException;

public class ExecuteQuery {

        @Test
        public void endPoint() throws SQLException {

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DBConnection.ENDPOINT.getPostgresUrl());
            config.setUsername(DBConnection.ENDPOINT.getPostgresUser());
            config.setPassword(DBConnection.ENDPOINT.getPostgresPassword());
            config.setDriverClassName("org.postgresql.Driver");
            config.addDataSourceProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
            HikariDataSource ds = new HikariDataSource(config);

        }

        public void scheduler() throws SQLException {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DBConnection.SCHEDULER.getPostgresUrl());
        config.setUsername(DBConnection.SCHEDULER.getPostgresUser());
        config.setPassword(DBConnection.SCHEDULER.getPostgresPassword());
        config.setDriverClassName("org.postgresql.Driver");
        config.isInitializationFailFast();
        config.addDataSourceProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        HikariDataSource ds = new HikariDataSource(config);

    }
    }
