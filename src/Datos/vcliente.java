
package Datos;

public class vcliente  extends vpersona{  // el extends me permitira heredar los atributos de la tabla persona
    private String codigo_cliente;
    private String login;
    private String password;

    public vcliente() {
    }

    public vcliente(String codigo_cliente, String login, String password) {
        this.codigo_cliente = codigo_cliente;
        this.login = login;
        this.password = password;
    }

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
