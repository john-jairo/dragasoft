/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHNJ
 */
@Entity
@Table(name = "orden_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o")
    , @NamedQuery(name = "OrdenCompra.findByIdordenCompra", query = "SELECT o FROM OrdenCompra o WHERE o.idordenCompra = :idordenCompra")
    , @NamedQuery(name = "OrdenCompra.findByObservacionOc", query = "SELECT o FROM OrdenCompra o WHERE o.observacionOc = :observacionOc")})
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idorden_compra")
    private Integer idordenCompra;
    @Size(max = 100)
    @Column(name = "observacion_oc")
    private String observacionOc;
    @JoinColumn(name = "estado_orden_idestado_orden", referencedColumnName = "idestado_orden")
    @ManyToOne(optional = false)
    private EstadoOrden estadoOrdenIdestadoOrden;
    @JoinColumn(name = "inventario_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Inventario inventarioCodigo;
    @JoinColumn(name = "usuarios_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuariosIdUsuario;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer idordenCompra) {
        this.idordenCompra = idordenCompra;
    }

    public Integer getIdordenCompra() {
        return idordenCompra;
    }

    public void setIdordenCompra(Integer idordenCompra) {
        this.idordenCompra = idordenCompra;
    }

    public String getObservacionOc() {
        return observacionOc;
    }

    public void setObservacionOc(String observacionOc) {
        this.observacionOc = observacionOc;
    }

    public EstadoOrden getEstadoOrdenIdestadoOrden() {
        return estadoOrdenIdestadoOrden;
    }

    public void setEstadoOrdenIdestadoOrden(EstadoOrden estadoOrdenIdestadoOrden) {
        this.estadoOrdenIdestadoOrden = estadoOrdenIdestadoOrden;
    }

    public Inventario getInventarioCodigo() {
        return inventarioCodigo;
    }

    public void setInventarioCodigo(Inventario inventarioCodigo) {
        this.inventarioCodigo = inventarioCodigo;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordenCompra != null ? idordenCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.idordenCompra == null && other.idordenCompra != null) || (this.idordenCompra != null && !this.idordenCompra.equals(other.idordenCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "login.entidades.OrdenCompra[ idordenCompra=" + idordenCompra + " ]";
    }
    
}
