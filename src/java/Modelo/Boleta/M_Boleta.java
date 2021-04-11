package Modelo.Boleta;

public class M_Boleta {
    
    private int id;
    private int idIntermedia;
    private int idComprador;
    private String codigo;
    private double precio;
    private int cantidad;
    private String empresa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdIntermedia() {
        return idIntermedia;
    }

    public void setIdIntermedia(int idIntermedia) {
        this.idIntermedia = idIntermedia;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
