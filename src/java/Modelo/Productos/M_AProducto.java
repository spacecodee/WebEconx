package Modelo.Productos;

public class M_AProducto {
    
    private int id;
    private String codigo;
    private String nombre;
    private double precio;
    private int Stock;
    private int idCate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getCategoria() {
        return idCate;
    }

    public void setCategoria(int idCate) {
        this.idCate = idCate;
    }  
    
    
}
