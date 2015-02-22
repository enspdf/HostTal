
package Datos;

public class vpersona {
    private int idpersona;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String tipo_documento;
    private String num_documento;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;

    public vpersona() {
    }

    public vpersona(int idpersona, String nombre, String apaterno, String amaterno, String tipo_documento, String num_documento,String genero, String direccion, String telefono, String email) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.primer_apellido = apaterno;
        this.segundo_apellido = amaterno;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.genero= genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
  
}
