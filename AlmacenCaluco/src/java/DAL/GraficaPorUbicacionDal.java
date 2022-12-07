package DAL;

import Conexion.Conexion;
import MD.GraficaPorTipoMd;
import MD.GraficaPorUbicacionMd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.zul.SimplePieModel;

public class GraficaPorUbicacionDal {

    public List<GraficaPorUbicacionMd> Grafica1(String sFecha_inicial) throws SQLException {
        List<GraficaPorUbicacionMd> lstDatos = new ArrayList<GraficaPorUbicacionMd>();

        PreparedStatement smt = null;
        Connection conn = null;
        Conexion conex = new Conexion();
        conn = conex.Conexion();
        String sql = null;
        ResultSet rs = null;

        try {

            sql = "  select CASE pro_ubicacion\n"
                    + " when 'S' then 'SALA DE VENTAS'\n"
                    + " when 'B' then 'BODEGA NUEVA'\n"
                    + " when 'C' then 'BODEGA CABLES'\n"
                    + " when 'L' then 'BODEGA LLANTAS'\n"
                    + " when 'A' then 'ALA 1, DERECHO'\n"
                    + " when 'I' then 'ALA 2, IZQUIERDO'\n"
                    + "	when 'D' then 'PASILLO 1, DERECHO'\n"
                    + " when 'P' then 'PASILLO 2, IZQUIERDO'\n"
                    + "	when 'T' then 'TALLER'\n"
                    + " END AS UBICACION,\n"
                    + " count(pro_id) as CANTIDAD\n"
                    + "	from  productos\n"
                    + " group by UBICACION";

            smt = conn.prepareStatement(sql);
            rs = smt.executeQuery();

            while (rs.next()) {
                GraficaPorUbicacionMd temp = new GraficaPorUbicacionMd();
                temp.setUbicacion(rs.getString(1));
                temp.setSumaUbicacion(rs.getString(2));

                lstDatos.add(temp);
            }

        } finally {
            if (smt != null) {
                smt.close();
                smt = null;
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (conn != null) {
                conex.cerrar();
            }
        }
        return lstDatos;
    }
    
      public SimplePieModel Grafica2(String sFecha_inicial) throws SQLException {
        List<GraficaPorUbicacionMd> lstDatos = new ArrayList<GraficaPorUbicacionMd>();
        
        SimplePieModel model = new SimplePieModel();
        PreparedStatement smt = null;
        Connection conn;
        Conexion conex = new Conexion();
        conn = conex.Conexion();
        String sql = null;
        ResultSet result2 = null;
        try {
            
            sql = "  select CASE pro_ubicacion\n"
                    + " when 'S' then 'SALA DE VENTAS'\n"
                    + " when 'B' then 'BODEGA NUEVA'\n"
                    + " when 'C' then 'BODEGA CABLES'\n"
                    + " when 'L' then 'BODEGA LLANTAS'\n"
                    + " when 'A' then 'ALA 1, DERECHO'\n"
                    + " when 'I' then 'ALA 2, IZQUIERDO'\n"
                    + "	when 'D' then 'PASILLO 1, DERECHO'\n"
                    + " when 'P' then 'PASILLO 2, IZQUIERDO'\n"
                    + "	when 'T' then 'TALLER'\n"
                    + " END AS UBICACION,\n"
                    + " count(pro_stock) as CANTIDAD\n"
                    + "	from  productos\n"
                    + " group by UBICACION";
            
            smt = conn.prepareStatement(sql);
            result2 = smt.executeQuery();

            while (result2.next()) {
        
                GraficaPorUbicacionMd temp = new GraficaPorUbicacionMd();
                
                temp.setUbicacion(result2.getString(1));
                temp.setSumaUbicacion(result2.getString(2));
                               
                lstDatos.add(temp);
            }

            for (int i = 0; i < lstDatos.size(); i++) {
                model.setValue(lstDatos.get(i).getUbicacion(), Integer.parseInt(lstDatos.get(i).getSumaUbicacion()));
            }

        } finally {
            if (smt != null) {
                smt.close();
                smt = null;
            }
            if (result2 != null) {
                result2.close();
                result2 = null;
            }
            if (conn != null) {
                conex.cerrar();
            }
        }
        return model;
    }

}
