package DAL;

import Conexion.Conexion;
import MD.ClientesMd;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;

public class ClientesDal {

    Connection conn;
    Conexion obtener = new Conexion();
    ClientesMd cliMd = new ClientesMd();

    public ClientesMd BuscarClientes(String nit) throws SQLException {
        PreparedStatement smt = null;

        conn = obtener.Conexion();

        ResultSet result = null;

        ClientesMd Buscar = null;

        String sql = "Select CL_ID, "
                + "          CL_NOMBRE, "
                + "          CL_NIT, "
                + "          CL_DIRECCION, "
                + "          CL_TELEFONO, "
                + "          DATE_FORMAT(CL_FECHA_ALTA, '%d/%m/%Y') "
                + "   from cliente U"
                + "   where TRIM(CL_NIT) = ?";

        try {
            smt = conn.prepareStatement(sql);
            smt.setString(1, nit.replace("-", ""));

            result = smt.executeQuery();

            while (result.next()) {
                Buscar = new ClientesMd();

                Buscar.setCodigoCliente(result.getString(1));
                Buscar.setNombreComercial(result.getString(2));
                Buscar.setNit(result.getString(3));
                Buscar.setDireccion(result.getString(4));
                Buscar.setTelefono(result.getString(5));
                Buscar.setFechaAlta(result.getString(6));

            }
        } catch (Exception e) {
        } finally {
            if (smt != null) {
                smt.close();
            }
            if (result != null) {
                smt.close();
            }
            if (conn != null) {
                obtener.cerrar();
                conn.close();
                conn = null;
            }
        }
        return Buscar;
    }

    public ClientesMd saveCL(ClientesMd data) {
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String id = "";
        int resp = 0;
        cliMd = new ClientesMd();
        String query1 = " SELECT max(CL_ID)+1 as id FROM cliente; ";
        String sql = " INSERT INTO almacen.cliente (CL_ID, CL_NOMBRE, CL_NIT, CL_DIRECCION, CL_TELEFONO, CL_FECHA_ALTA, CL_USUARIO_ALTA, CL_CORREO)\n"
                + "VALUES (?,?,?,?,?,NOW(),?,?);";

        try {
            conn = obtener.Conexion();
            conn.setAutoCommit(false);

            st = conn.createStatement();
            rs = st.executeQuery(query1);
            while (rs.next()) {
                id = rs.getString("id");
            }
            st.close();
            rs.close();

            ps = conn.prepareStatement(sql);

            ps.setString(1, id);
            ps.setString(2, data.getNombreComercial());
            ps.setString(3, data.getNit());
            ps.setString(4, data.getDireccion());
            ps.setString(5, data.getTelefono());
            ps.setString(6, data.getUsuario());
            ps.setString(7, data.getCorreCleinte());

            ps.executeUpdate();
            ps.close();
            conn.commit();
            cliMd.setResp("1");
            cliMd.setMsg("REGISTRO GUARDADO CORRECTAMENTE");

            conn.close();
            obtener.desconectar();

        } catch (Exception e) {
            System.out.println("ERROR CATCH.: " + e.getMessage());
            cliMd.setResp("0");
            cliMd.setMsg(e.getMessage());
        }

        return cliMd;
    }
}
