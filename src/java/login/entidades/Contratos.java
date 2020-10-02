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
import javax.persistence.OneToOne;
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
@Table(name = "contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratos.findAll", query = "SELECT c FROM Contratos c")
    , @NamedQuery(name = "Contratos.findByCodigoContrato", query = "SELECT c FROM Contratos c WHERE c.codigoContrato = :codigoContrato")
    , @NamedQuery(name = "Contratos.findBySedeEntrega", query = "SELECT c FROM Contratos c WHERE c.sedeEntrega = :sedeEntrega")
    , @NamedQuery(name = "Contratos.findByDuracion", query = "SELECT c FROM Contratos c WHERE c.duracion = :duracion")})
public class Contratos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_contrato")
    private String codigoContrato;
    @Size(max = 30)
    @Column(name = "sede_entrega")
    private String sedeEntrega;
    @Column(name = "duracion")
    private Integer duracion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contratosCodigoContrato")
    private Entrega entrega;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratosCodigoContrato")
    private List<Clientes> clientesList;
    @JoinColumn(name = "ciudades_codigo_municipio", referencedColumnName = "codigo_municipio")
    @ManyToOne(optional = false)
    private Ciudades ciudadesCodigoMunicipio;
    @JoinColumn(name = "estado_contrato_id_estado_contrato", referencedColumnName = "id_estado_contrato")
    @ManyToOne(optional = false)
    private EstadoContrato estadoContratoIdEstadoContrato;
    @JoinColumn(name = "inventario_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Inventario inventarioCodigo;
    @JoinColumn(name = "jefe_administrativo_usuarios_id_usuario", referencedColumnName = "usuarios_id_usuario")
    @ManyToOne(optional = false)
    private JefeAdministrativo jefeAdministrativoUsuariosIdUsuario;
    @JoinColumn(name = "tipo_contrato_id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne(optional = false)
    private TipoContrato tipoContratoIdTipoContrato;

    public Contratos() {
    }

    public Contratos(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public String getSedeEntrega() {
        return sedeEntrega;
    }

    public void setSedeEntrega(String sedeEntrega) {
        this.sedeEntrega = sedeEntrega;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public Ciudades getCiudadesCodigoMunicipio() {
        return ciudadesCodigoMunicipio;
    }

    public void setCiudadesCodigoMunicipio(Ciudades ciudadesCodigoMunicipio) {
        this.ciudadesCodigoMunicipio = ciudadesCodigoMunicipio;
    }

    public EstadoContrato getEstadoContratoIdEstadoContrato() {
        return estadoContratoIdEstadoContrato;
    }

    public void setEstadoContratoIdEstadoContrato(EstadoContrato estadoContratoIdEstadoContrato) {
        this.estadoContratoIdEstadoContrato = estadoContratoIdEstadoContrato;
    }

    public Inventario getInventarioCodigo() {
        return inventarioCodigo;
    }

    public void setInventarioCodigo(Inventario inventarioCodigo) {
        this.inventarioCodigo = inventarioCodigo;
    }

    public JefeAdministrativo getJefeAdministrativoUsuariosIdUsuario() {
        return jefeAdministrativoUsuariosIdUsuario;
    }

    public void setJefeAdministrativoUsuariosIdUsuario(JefeAdministrativo jefeAdministrativoUsuariosIdUsuario) {
        this.jefeAdministrativoUsuariosIdUsuario = jefeAdministrativoUsuariosIdUsuario;
    }

    public TipoContrato getTipoContratoIdTipoContrato() {
        return tipoContratoIdTipoContrato;
    }

    public void setTipoContratoIdTipoContrato(TipoContrato tipoContratoIdTipoContrato) {
        this.tipoContratoIdTipoContrato = tipoContratoIdTipoContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoContrato != null ? codigoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratos)) {
            return false;
        }
        Contratos other = (Contratos) object;
        if ((this.codigoContrato == null && other.codigoContrato != null) || (this.codigoContrato != null && !this.codigoContrato.equals(other.codigoContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoContrato;
    }
    
}
