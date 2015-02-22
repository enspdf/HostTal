
package Datos;

public class vtrabajador extends vpersona {
    Double sueldo;
    String departamento;
    String puesto;
    String estado;

    public vtrabajador() {
    }
    
    public vtrabajador (double sueldo, String departamento, String puesto, String estado){
        this.sueldo= sueldo;
        this.departamento= departamento;
        this.puesto= puesto;
        this.estado= estado;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
