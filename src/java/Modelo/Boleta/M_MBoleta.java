package Modelo.Boleta;

public class M_MBoleta {

    private int id;
    private String codigoTrae;
    private String nombreProveedor;
    private String nombreProducto;
    private String dnUsuario;
    private String nombreUsuario;
    private String codigoBoleta;
    private double precio;
    private int cantidad;
    private String empresa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoTrae() {
        return codigoTrae;
    }

    public void setCodigoTrae(String codigoTrae) {
        this.codigoTrae = codigoTrae;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDnUsuario() {
        return dnUsuario;
    }

    public void setDnUsuario(String dnUsuario) {
        this.dnUsuario = dnUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCodigoBoleta() {
        return codigoBoleta;
    }

    public void setCodigoBoleta(String codigoBoleta) {
        this.codigoBoleta = codigoBoleta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

}
