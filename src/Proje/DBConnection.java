package Proje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private String url = "jdbc:mysql://localhost:3306/hastane?useSSL=false&serverTimezone=UTC";
    private String username = "root";
    private String password = ""; // kendi şifreni yaz

    private DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL Driver bulunamadı: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }
    public class TestConnection {
        public void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/hastane?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = ""; // Şifrenizi girin

            try {
                Connection con = DriverManager.getConnection(url, user, password);
                System.out.println("Bağlantı başarılı!");
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
