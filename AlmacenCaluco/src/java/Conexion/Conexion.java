package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection cnx = null;

    public Connection Conexion() throws SQLException {
        //if (cnx == null) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen?user=root&password=root");
            //cnx = DriverManager.getConnection("jdbc:mysql://45.55.47.25:3306/almacen?user=CALUCO&password=Secreto123*&allowPublicKeyRetrieval=true&useSSL=false");
           // cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false", "root", "root");
            //cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false", "root", "root");
              //produccion            
            cnx = DriverManager.getConnection("jdbc:mysql://base-administrada-do-user-12296041-0.b.db.ondigitalocean.com:25060/Caluco?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false", "caluco", "AVNS_kJlhVOYsPw7CH6EbELY");
           //pruebas
                  // cnx = DriverManager.getConnection("jdbc:mysql://db-mysql-nyc1-13705-do-user-12423640-0.b.db.ondigitalocean.com:25060/Caluco?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false", "caluco", "AVNS_HSmsBW3gYs0lkszEsry");

            
            System.out.println("Conexion Exitosa a Base de Datos...!");
        } catch (Exception ex) {
            System.out.println("Conexion Fallida...!");
            throw new SQLException(ex);
        }
//////      //}
        return cnx;
    }

    public void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }

    public Connection desconectar() throws SQLException {
        cnx.close();
        cnx = null;
        System.out.println("Cerrando Session Esquema control_de_trasporte.: " + cnx);
        return cnx;
    }

//     Connection conn = null;
//
//   
//    public Connection Conexion() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//
//            String dato = "jdbc:mysql://45.55.47.25:3306/Almacen?useSSL=false";
//
//            conn = DriverManager.getConnection(dato, "CALUCO", "SmVkaS0xMTQ5NjA=");
//
//            return conn;
//
//        } catch (ClassNotFoundException e) {
//            return null;
//        } catch (SQLException e) {
//            return conn;
//        }
//    }
//
//    public Connection getConnection() {
//        return Conexion();
//    }
//
//    public void cerrar() throws SQLException {
//        conn.close();
//        conn = null;
//
//    }
}
