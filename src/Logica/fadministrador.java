/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vcliente;
import Datos.vproducto;
import Datos.vadministrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sebastian-pc
 */
public class fadministrador {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";

    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "P.Apellido", "S.Apellido ", "Doc", "Número documento","Genero", "Dirección", "Teléfono", "Email", "Sueldo","Acceso","Login","Password","Estado"};

        String[] registro = new String[15];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombre,p.primer_apellido,p.segundo_apellido,p.tipo_documento,p.num_documento,"
                + "p.genero, p.direccion,p.telefono,p.email,a.sueldo,a.acceso,a.login,a.password,a.estado from persona p inner join administracion a "
                + "on p.idpersona=a.idpersona where num_documento like '%"
                + buscar + "%' order by idpersona desc"; 
                
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("primer_apellido");
                registro[3] = rs.getString("segundo_apellido");
                registro[4] = rs.getString("tipo_documento");
                registro[5] = rs.getString("num_documento");
                registro[6] = rs.getString("genero");
                registro[7] = rs.getString("direccion");
                registro[8] = rs.getString("telefono");
                registro[9] = rs.getString("email");
                registro[10] = rs.getString("sueldo");
                registro[11] = rs.getString("acceso");
                registro[12] = rs.getString("login");
                registro[13] = rs.getString("password");
                registro[14] = rs.getString("estado");
                

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(vadministrador dts) {
        sSQL = "insert into persona (nombre,primer_apellido,segundo_apellido,tipo_documento,num_documento,genero,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?,?,?)";
        sSQL2 = "insert into administracion (idpersona,sueldo,acceso,login,password,estado)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getPrimer_apellido());
            pst.setString(3, dts.getSegundo_apellido());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getGenero());
            pst.setString(7, dts.getDireccion());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getEmail());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(vadministrador dts) {
        sSQL = "update persona set nombre=?,primer_apellido=?,segundo_apellido=?,tipo_documento=?,num_documento=?,"
                + "genero=?,direccion=?,telefono=?,email=? where idpersona=?";
        
        sSQL2 = "update administracion set sueldo=?,acceso=?,login=?,password=?,estado=?"
                + " where idpersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getPrimer_apellido());
            pst.setString(3, dts.getSegundo_apellido());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getGenero());
            pst.setString(7, dts.getDireccion());
            pst.setString(8, dts.getTelefono());
            pst.setString(9, dts.getEmail());
            pst.setInt(10, dts.getIdpersona());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdpersona());


            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vadministrador dts) {
        sSQL = "delete from administracion where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, dts.getIdpersona());
            
            pst2.setInt(1, dts.getIdpersona());


            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public DefaultTableModel login(String login, String password) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "P.Apellido", "S.Apellido ","Acceso","Login","Password","Estado"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombre,p.primer_apellido,p.segundo_apellido,"
                + "a.acceso,a.login,a.password,a.estado from persona p inner join administracion a "
                + "on p.idpersona=a.idpersona where a.login='"
                + login + "' and a.password='" + password + "' and a.estado='A'"; 
                
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("primer_apellido");
                registro[3] = rs.getString("segundo_apellido");
                
                registro[4] = rs.getString("acceso");
                registro[5] = rs.getString("login");
                registro[6] = rs.getString("password");
                registro[7] = rs.getString("estado");
                

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
    
    
    
    
    
    
    
}
