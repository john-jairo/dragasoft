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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHNJ
 */
@Entity
@Table(name = "entrega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e")
    , @NamedQuery(name = "Entrega.findByIdentrega", query = "SELECT e FROM Entrega e WHERE e.identrega = :identrega")
    , @NamedQuery(name = "Entrega.findByPuntoInicio", query = "SELECT e FROM Entrega e WHERE e.puntoInicio = :puntoInicio")
    , @NamedQuery(name = "Entrega.findByUbicacionActual", query = "SELECT e FROM Entrega e WHERE e.ubicacionActual = :ubicacionActual")})
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identrega")
    private Integer identrega;
    @Size(max = 25)
    @Column(name = "punto_inicio")
    private String puntoInicio;
    @Size(max = 25)
    @Column(name = "ubicacion_actual")
    private String ubicacionActual;
    @JoinColumn(name = "contratos_codigo_contrato", referencedColumnName = "codigo_contrato")
    @OneToOne(optional = false)
    private Contratos contratosCodigoContrato;
    @JoinColumn(name = "estado_entrega_idestado_entrega", referencedColumnName = "idestado_entrega")
    @ManyToOne(optional = false)
    private EstadoEntrega estadoEntregaIdestadoEntrega;
    @JoinColumn(name = "usuarios_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuariosIdUsuario;

    public Entrega() {
    }

    public Entrega(Integer identrega) {
        this.identrega = identrega;
    }

    public Integer getIdentrega() {
        return identrega;
    }

    public void setIdentrega(Integer identrega) {
        this.identrega = identrega;
    }

    public String getPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(String puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public Contratos getContratosCodigoContrato() {
        return contratosCodigoContrato;
    }

    public void setContratosCodigoContrato(Contratos contratosCodigoContrato) {
        this.contratosCodigoContrato = contratosCodigoContrato;
    }

    public EstadoEntrega getEstadoEntregaIdestadoEntrega() {
        return estadoEntregaIdestadoEntrega;
    }

    public void setEstadoEntregaIdestadoEntrega(EstadoEntrega estadoEntregaIdestadoEntrega) {
        this.estadoEntregaIdestadoEntrega = estadoEntregaIdestadoEntrega;
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
        hash += (identrega != null ? identrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;
        if ((this.identrega == null && other.identrega != null) || (this.identrega != null && !this.identrega.equals(other.identrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "login.entidades.Entrega[ identrega=" + identrega + " ]";
    }
    
}
