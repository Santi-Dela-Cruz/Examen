package DKDataAccess.DKDataHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DKDataHelper {
    private static final String dkDBURL = "jdbc:sqlite:dataBase//EcuFauna.sqlite";
    private static Connection dkConnection = null;

    public static synchronized Connection dkConection() {
        try {
            if (dkConnection == null || dkConnection.isClosed()) {
                dkConnection = DriverManager.getConnection(dkDBURL);
            }
            System.out.println("Se conecto a la base de datos");
            return dkConnection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static synchronized void dkDesconection() {
        try {
            if (dkConnection != null && !dkConnection.isClosed()) {
                dkConnection.close();
                System.out.println("Se desconecto a la base de datos");
                dkConnection = null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
