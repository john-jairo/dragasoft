/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findByCodigo", query = "SELECT m FROM Mantenimiento m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "Mantenimiento.findByObsMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.obsMantenimiento = :obsMantenimiento")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "obs_mantenimiento")
    private String obsMantenimiento;
    @JoinColumn(name = "estado_mantenimiento_id_estado_mantenimiento", referencedColumnName = "id_estado_mantenimiento")
    @ManyToOne(optional = false)
    private EstadoMantenimiento estadoMantenimientoIdEstadoMantenimiento;
    @JoinColumn(name = "inventario_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Inventario inventarioCodigo;
    @JoinColumn(name = "tipo_mantenimiento_id_tipo_mantenimiento", referencedColumnName = "id_tipo_mantenimiento")
    @ManyToOne(optional = false)
    private TipoMantenimiento tipoMantenimientoIdTipoMantenimiento;
    @JoinColumn(name = "usuarios_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuariosIdUsuario;
    @OneToMany(mappedBy = "mantenimientoCodigo")
    private List<AlertaMant> alertaMantList;

    public Mantenimiento() {
    }

    public Mantenimiento(String codigo) {
        this.codigo = codigo;
    }

    public Mantenimiento(String codigo, String obsMantenimiento) {
        this.codigo = codigo;
        this.obsMantenimiento = obsMantenimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObsMantenimiento() {
        return obsMantenimiento;
    }

    public void setObsMantenimiento(String obsMantenimiento) {
        this.obsMantenimiento = obsMantenimiento;
    }

    public EstadoMantenimiento getEstadoMantenimientoIdEstadoMantenimiento() {
        return estadoMantenimientoIdEstadoMantenimiento;
    }

    public void setEstadoMantenimientoIdEstadoMantenimiento(EstadoMantenimiento estadoMantenimientoIdEstadoMantenimiento) {
        this.estadoMantenimientoIdEstadoMantenimiento = estadoMantenimientoIdEstadoMantenimiento;
    }

    public Inventario getInventarioCodigo() {
        return inventarioCodigo;
    }

    public void setInventarioCodigo(Inventario inventarioCodigo) {
        this.inventarioCodigo = inventarioCodigo;
    }

    public TipoMantenimiento getTipoMantenimientoIdTipoMantenimiento() {
        return tipoMantenimientoIdTipoMantenimiento;
    }

    public void setTipoMantenimientoIdTipoMantenimiento(TipoMantenimiento tipoMantenimientoIdTipoMantenimiento) {
        this.tipoMantenimientoIdTipoMantenimiento = tipoMantenimientoIdTipoMantenimiento;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    @XmlTransient
    public List<AlertaMant> getAlertaMantList() {
        return alertaMantList;
    }

    public void setAlertaMantList(List<AlertaMant> alertaMantList) {
        this.alertaMantList = alertaMantList;
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
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "login.entidades.Mantenimiento[ codigo=" + codigo + " ]";
    }
    
}
