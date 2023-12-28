package Controlador;

import Configuracion.Conexion;
import Modelos.Activos;
import Modelos.Administrador;
import Modelos.Funcionario;
import Modelos.Validacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controlador {

    Conexion con = new Conexion();
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Funcionario> obtenerFuncionario() {
        try {
            String sql = "SELECT DISTINCT validacion.id_persona,persona.nombre,persona.apellido FROM validacion "
                    + "INNER JOIN persona on validacion.id_persona = persona.cedula";
            List<Funcionario> lista = new ArrayList<>();
            conexion = con.conectar();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario func = new Funcionario(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                lista.add(func);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }
    
    public List<Funcionario> obtenerFuncionario(String dato) {
        try {
            String sql = "SELECT DISTINCT validacion.id_persona,persona.nombre,persona.apellido FROM validacion "
                    + "INNER JOIN persona on validacion.id_persona = persona.cedula "
                    + "WHERE id_persona LIKE '%" + dato + "%'"
                    + "OR persona.nombre LIKE '%" + dato + "%'"
                    + "OR persona.apellido LIKE '%" + dato + "%'";
            List<Funcionario> lista = new ArrayList<>();
            conexion = con.conectar();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario func = new Funcionario(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                lista.add(func);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

    public List<Activos> obtenerActivosFuncionario(String ci) {
        try {
            List<Activos> lista = new ArrayList<>();
            String sql = "SELECT activos.id_activo, activos.nombre, validacion.estado FROM validacion "
                    + "INNER JOIN activos on validacion.id_activo = activos.id_activo "
                    + "INNER JOIN persona on validacion.id_persona = persona.cedula "
                    + "WHERE id_persona = '" + ci + "'";

            Activos act = null;

            conexion = con.conectar();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                act = new Activos(rs.getString(1), rs.getString(2), rs.getString(3));
                lista.add(act);
            }
            return lista;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public int guardarFuncionario(String cedula, String nombre, String estado) {

        conexion = con.conectar();
        try {
            ps = conexion.prepareStatement("insert into val_funcionarios values (?,?,?)");
            ps.setString(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, estado);
            int n = ps.executeUpdate();
            if (n > 0) {
                return n;
            } else {
                return n;
            }
        } catch (Exception e) {
            System.err.println(e);
            return 0;
        }

    }

    public List<Validacion> mostrarValidaciones() {
        try {
            List<Validacion> lista = new ArrayList<>();
            conexion = con.conectar();
            ps = conexion.prepareStatement("select * from val_funcionarios");
            rs = ps.executeQuery();
            while (rs.next()) {
                Validacion val = new Validacion(rs.getString(1));
                lista.add(val);
            }
            return lista;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public List<Administrador> listaAdmin(String idAdmin) {
        try {
            List<Administrador> lista = new ArrayList<>();
            conexion = con.conectar();
            ps = conexion.prepareStatement("select * from administradores where cedula=" + idAdmin + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                Administrador admin = new Administrador(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                lista.add(admin);
            }
            return lista;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public List<Funcionario> listaValidacion(String val) {
        try {
            List<Funcionario> lista = new ArrayList<>();
            conexion = con.conectar();
            ps = conexion.prepareStatement("SELECT persona.cedula, persona.nombre, persona.apellido, val_funcionarios.estado,"
                    + "IFNULL((SELECT COUNT(V.id_persona) FROM validacion V WHERE V.estado<>'OK' AND V.id_persona=val_funcionarios.cedula GROUP BY V.id_persona),0) as contNOOK "
                    + "FROM val_funcionarios "
                    + "INNER JOIN persona on val_funcionarios.cedula = persona.cedula "
                    + "WHERE nom_val='" + val + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                String est=rs.getString(5);
                String estado="Revisión";
                if( Integer.parseInt(est)==0){
                    estado="OK";
                }
                Funcionario func = new Funcionario(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        estado);
                lista.add(func);
            }
            return lista;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public List<Validacion> nombreValidacion() {
        try {
            List<Validacion> lista = new ArrayList<>();
            conexion = con.conectar();
            ps = conexion.prepareStatement("SELECT DISTINCT nom_val FROM `val_funcionarios`");
            rs = ps.executeQuery();
            while (rs.next()) {
                Validacion val = new Validacion(rs.getString(1));
                lista.add(val);
            }
            return lista;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public String guardarValidacion(String nomVal, String cedula, String fecha) {
        try {
            conexion = con.conectar();
            ps = conexion.prepareStatement("insert into val_funcionarios values(?,?,?,?)");
            ps.setString(1, nomVal);
            ps.setString(2, cedula);
            ps.setString(3, fecha);
            ps.setString(4, "Revisión");
            int n = ps.executeUpdate();
            if (n > 0) {
                return "Insertado";
            } else {
                return "No Insertado";
            }
        } catch (Exception e) {
            System.err.println(e);
            return "" + e;
        }
    }
    
    public String actualizarValidacion(String cedula, String idActivo) {
        try {
            conexion = con.conectar();
            ps = conexion.prepareStatement("UPDATE validacion SET estado='OK' WHERE id_persona=? AND id_activo=?");
            ps.setString(1, cedula);
            ps.setString(2, idActivo);
            
            int n = ps.executeUpdate();
            if (n > 0) {
                return "Revisado";
            } else {
                return "No Revisado";
            }
        } catch (Exception e) {
            System.err.println(e);
            return "" + e;
        }
    }

    public int obtenerNActivos(String ci) {
        int n = 0;
        try {
            conexion = con.conectar();
            ps = conexion.prepareStatement("SELECT COUNT(*) AS Total FROM validacion WHERE validacion.id_persona=" + ci + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                n = Integer.valueOf(rs.getString(1));
            }
            return n;
        } catch (Exception e) {
            System.err.println(e);
            return n;
        }
    }

    public List<Validacion> listaValFuncionario(String ci) {
        List<Validacion> lista = new ArrayList<>();
        try {
            conexion = con.conectar();
            ps = conexion.prepareStatement("SELECT nom_val FROM `val_funcionarios` where cedula = " + ci + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                Validacion val = new Validacion(rs.getString(1));

                lista.add(val);
            }
            return lista;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
    
    public String EliminarValidacion(String nomVal, String cedula) {
        try {
            conexion = con.conectar();
            ps = conexion.prepareStatement("DELETE FROM val_funcionarios "
                    + "WHERE val_funcionarios.nom_val = '" + nomVal + "' AND val_funcionarios.cedula = '" + cedula + "'");
            int n = ps.executeUpdate();
            if (n > 0) {
                return "Eliminado";
            } else {
                return "No Eliminado";
            }
        } catch (Exception e) {
            System.err.println(e);
            return "" + e;
        }
    }

}
