package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "admin";

    // Instancia única de DatabaseConnection
    private static DatabaseConnection instance;

    // Conexión a la base de datos
    private Connection connection;

    // Constructor privado para prevenir la instanciación directa
    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    // Método para obtener la instancia única de DatabaseConnection
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}