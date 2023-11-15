import java.sql.Connection;
import java.sql.DriverManager;
import static java.lang.Class.forName;

public class ModuloConexao {
    public static Connection conector(){
        java.sql.Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url ="jdbc:mysql://127.0.0.1/dbpr";
        String user ="root";
        String password = "Pa201720";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

