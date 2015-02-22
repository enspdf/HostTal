
package Logica;

import Datos.vcliente;
import Datos.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fcliente {
    private conexion mysql=new conexion(); // crear extancia de esa clase
    private Connection cn=mysql.conectar();
    private String sSQL="";// variable donde se va almacenar la cadena de conexion
    private String sSQL2="";//permite insertar cliente y persona previamente
    public Integer totalregistros;//cuenta total de registros que obtiene de la consulta
    
    
    public DefaultTableModel mostrar(String buscar){ //funcion para msotrar los registros
        DefaultTableModel modelo;
   //vector para crear los titulos de las columnas     
        String [] titulos={"ID","Nombre","P.Apellido","S.Apellido","Doc","numero Documento","Genero","Direccion","Telefono","Email","Codigo","Login","Password"};
   // vector donde se almacena ls registros de cada uno de esos titulos
        String [] registro=new String [13];
        
        totalregistros=0;
        modelo= new DefaultTableModel(null,titulos);
   //funcion buscar
        sSQL ="select p.idpersona, p.nombre, p.primer_apellido, p.segundo_apellido, p.tipo_documento, p.num_documento,"+
                "p.genero, p.direccion, p.telefono, p.email, c.codigo_cliente, c.login, c.password from persona p inner join cliente c "+  // la p y c son prefijos de la tabla persona y cliente
                "on p.idpersona=c.idpersona where num_documento like'%" + 
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
                registro [10]=rs.getString("codigo_cliente");
                registro [11]=rs.getString("login");
                registro [12]=rs.getString("password");
                
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
    public boolean insertar(vcliente dts){
        sSQL="insert into persona (nombre,primer_apellido,segundo_apellido,tipo_documento,num_documento,genero,direccion,telefono,email)" +
                "values (?,?,?,?,?,?,?,?,?)";
        sSQL2="insert into cliente (idpersona,codigo_cliente,login,password)" +
                "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?)";
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
            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setString(2, dts.getLogin());
            pst2.setString(3, dts.getPassword());

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
    public boolean editar(vcliente dts){
        sSQL="update persona set nombre=?,primer_apellido=?,segundo_apellido=?,tipo_documento=?,num_documento=?,"+
                "genero=?,direccion=?,telefono=?,email=? where idpersona=?";
        sSQL2="update cliente set codigo_cliente=?,login=?,password=?"+
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
            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());

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
    public boolean eliminar(vcliente dts){
        sSQL="delete from cliente where idpersona=?";
        sSQL2="delete from persona where idpersona=?";
        try {
            PreparedStatement pst=cn.prepareCall(sSQL);
            PreparedStatement pst2=cn.prepareCall(sSQL2);
            

            pst.setInt(1, dts.getIdpersona());
            pst2.setInt(1, dts.getIdpersona());

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
}
