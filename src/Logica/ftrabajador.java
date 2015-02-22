package Logica;

import Datos.vcliente;
import Datos.vproducto;
import Datos.vtrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ftrabajador {
   private conexion mysql=new conexion(); // crear extancia de esa clase
    private Connection cn=mysql.conectar();
    private String sSQL="";// variable donde se va almacenar la cadena de conexion
    private String sSQL2="";//permite insertar cliente y persona previamente
    public Integer totalregistros;//cuenta total de registros que obtiene de la consulta
    
//funcion para msotrar los registros    
    public DefaultTableModel mostrar(String buscar){ 
        DefaultTableModel modelo;
   //vector para crear los titulos de las columnas     
        String [] titulos={"ID","Nombre","P.Apellido"," S.Apellido","Doc","numero Documento","Genero","Direccion","Telefono","Email","Sueldo","Departamento","Puesto","Estado"};
   // vector donde se almacena ls registros de cada uno de esos titulos
        String [] registro=new String [14];
        
        totalregistros=0;
        modelo= new DefaultTableModel(null,titulos);
   //funcion buscar
        sSQL ="select p.idpersona, p.nombre, p.primer_apellido, p.segundo_apellido, p.tipo_documento, p.num_documento,"+
                "p.genero, p.direccion, p.telefono, p.email, t.sueldo, t.departamento, t.puesto, t.estado from persona p inner join trabajador t "+  // la p y t son prefijos de la tabla persona y trabajador
                "on p.idpersona=t.idpersona where num_documento like'%" + 
                buscar + "%' order by idpersona desc";
        
        try {
            java.sql.Statement st= cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){//navegacion de todos lo sregistros recorriendo de uno en uno
                registro [0]=rs.getString("idpersona");
                registro [1]=rs.getString("nombre");
                registro [2]=rs.getString("primer_apellido");
                registro [3]=rs.getString("segundo_apellido");
                registro [4]=rs.getString("tipo_documento");
                registro [5]=rs.getString("num_documento");
                registro [6]=rs.getString("genero");
                registro [7]=rs.getString("direccion");
                registro [8]=rs.getString("telefono");
                registro [9]=rs.getString("email");
                registro [10]=rs.getString("sueldo");
                registro [11]=rs.getString("departamento");
                registro [12]=rs.getString("puesto");
                registro [13]=rs.getString("estado");
                
                
                totalregistros=totalregistros+1;
                modelo.addRow(registro);
                
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }    
    }
// funcion insertar
    public boolean insertar(vtrabajador dts){
        sSQL="insert into persona (nombre,primer_apellido,segundo_apellido,tipo_documento,num_documento,genero,direccion,telefono,email)" +
                "values (?,?,?,?,?,?,?,?,?)";
        sSQL2="insert into trabajador (idpersona,sueldo,departamento,puesto,estado)" +
                "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?)"; //obtiene el ultimo registro
        try {
            PreparedStatement pst=cn.prepareCall(sSQL);
            PreparedStatement pst2=cn.prepareCall(sSQL2);
            
            
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
            pst2.setString(2, dts.getDepartamento());
            pst2.setString(3, dts.getPuesto());
            pst2.setString(4, dts.getEstado()); 
            

//crear variable que me alamcena el resultadod e la ejecucion del statement            
            int n=pst.executeUpdate();
// declaro condicion para ver si han ingresado datos o no
            if (n!=0){
                int n2=pst2.executeUpdate();
                if (n2!=0) {
                    return true;    
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
           
        }
    }
//funcion editar
    public boolean editar(vtrabajador dts){
        sSQL="update persona set nombre=?,primer_apellido=?,segundo_apellido=?,tipo_documento=?,num_documento=?,"+
                "genero=?,direccion=?,telefono=?,email=? where idpersona=?";
        sSQL2="update trabajador set sueldo=?, departamento=?, puesto=?, estado=?"+
                "where idpersona=?";
        try {
            PreparedStatement pst=cn.prepareCall(sSQL);
            PreparedStatement pst2=cn.prepareCall(sSQL2);
            
            
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
            pst2.setString(2, dts.getDepartamento());
            pst2.setString(3, dts.getPuesto());
            pst2.setString(4, dts.getEstado());
            pst2.setInt(5, dts.getIdpersona());

//crear variable que me alamcena el resultadod e la ejecucion del statement            
            int n=pst.executeUpdate();
// declaro condicion para ver si han ingresado datos o no
            if (n!=0){
                int n2=pst2.executeUpdate();
                if (n2!=0) {
                    return true;    
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
           
        }
    }
    public boolean eliminar(vtrabajador dts){
        sSQL="delete from trabajador where idpersona=?";
        sSQL2="delete from persona where idpersona=?";
        try {
            PreparedStatement pst=cn.prepareCall(sSQL);
            PreparedStatement pst2=cn.prepareCall(sSQL2);
            

            pst.setInt(1, dts.getIdpersona());
            pst2.setInt(1, dts.getIdpersona());

//crear variable que me alamcena el resultado e la ejecucion del statement            
            int n=pst.executeUpdate();
// declaro condicion para ver si han ingresado datos o no
            if (n!=0){
                int n2=pst2.executeUpdate();
                if (n2!=0) {
                    return true;    
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
           
        }
    }
}



