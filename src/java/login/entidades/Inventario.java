/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHNJ
 */
@Entity
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i")
    , @NamedQuery(name = "Inventario.findByCodigo", query = "SELECT i FROM Inventario i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "Inventario.findByReferencia", query = "SELECT i FROM Inventario i WHERE i.referencia = :referencia")
    , @NamedQuery(name = "Inventario.findByAnoFabricacion", query = "SELECT i FROM Inventario i WHERE i.anoFabricacion = :anoFabricacion")
    , @NamedQuery(name = "Inventario.findByCicloVida", query = "SELECT i FROM Inventario i WHERE i.cicloVida = :cicloVida")
    , @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "Inventario.findByAltura", query = "SELECT i FROM Inventario i WHERE i.altura = :altura")
    , @NamedQuery(name = "Inventario.findByAncho", query = "SELECT i FROM Inventario i WHERE i.ancho = :ancho")
    , @NamedQuery(name = "Inventario.findByProfundidad", query = "SELECT i FROM Inventario i WHERE i.profundidad = :profundidad")
    , @NamedQuery(name = "Inventario.findByPeso", query = "SELECT i FROM Inventario i WHERE i.peso = :peso")
    , @NamedQuery(name = "Inventario.findByColor", query = "SELECT i FROM Inventario i WHERE i.color = :color")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano_fabricacion")
    private int anoFabricacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ciclo_vida")
    private int cicloVida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "altura")
    private Integer altura;
    @Column(name = "ancho")
    private Integer ancho;
    @Column(name = "profundidad")
    private Integer profundidad;
    @Column(name = "peso")
    private Integer peso;
    @Size(max = 15)
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioCodigo")
    private List<Mantenimiento> mantenimientoList;
    @JoinColumn(name = "categoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "estado_inventario", referencedColumnName = "id_estado_inventario")
    @ManyToOne(optional = false)
    private EstadoInventario estadoInventario;
    @JoinColumn(name = "marcas_id_marca", referencedColumnName = "id_marca")
    @ManyToOne(optional = false)
    private Marcas marcasIdMarca;
    @JoinColumn(name = "sub_categoria", referencedColumnName = "id_sub_categoria")
    @ManyToOne(optional = false)
    private SubCategoria subCategoria;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioCodigo")
    private List<OrdenCompra> ordenCompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioCodigo")
    private List<Contratos> contratosList;

    public Inventario() {
    }

    public Inventario(String codigo) {
        this.codigo = codigo;
    }

    public Inventario(String codigo, String referencia, int anoFabricacion, int cicloVida, int cantidad) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.anoFabricacion = anoFabricacion;
        this.cicloVida = cicloVida;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public int getCicloVida() {
        return cicloVida;
    }

    public void setCicloVida(int cicloVida) {
        this.cicloVida = cicloVida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @XmlTransient
    public List<Mantenimiento> getMantenimientoList() {
        return mantenimientoList;
    }

    public void setMantenimientoList(List<Mantenimiento> mantenimientoList) {
        this.mantenimientoList = mantenimientoList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public EstadoInventario getEstadoInventario() {
        return estadoInventario;
    }

    public void setEstadoInventario(EstadoInventario estadoInventario) {
        this.estadoInventario = estadoInventario;
    }

    public Marcas getMarcasIdMarca() {
        return marcasIdMarca;
    }

    public void setMarcasIdMarca(Marcas marcasIdMarca) {
        this.marcasIdMarca = marcasIdMarca;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<OrdenCompra> getOrdenCompraList() {
        return ordenCompraList;
    }

    public void setOrdenCompraList(List<OrdenCompra> ordenCompraList) {
        this.ordenCompraList = ordenCompraList;
    }

    @XmlTransient
    public List<Contratos> getContratosList() {
        return contratosList;
    }

    public void setContratosList(List<Contratos> contratosList) {
        this.contratosList = contratosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  codigo;
    }
    
}
