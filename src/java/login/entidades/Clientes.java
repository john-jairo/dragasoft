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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findByNumeroDocumento", query = "SELECT c FROM Clientes c WHERE c.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "Clientes.findByPrimerNombre", query = "SELECT c FROM Clientes c WHERE c.primerNombre = :primerNombre")
    , @NamedQuery(name = "Clientes.findBySegundoNombre", query = "SELECT c FROM Clientes c WHERE c.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Clientes.findByPrimerApellido", query = "SELECT c FROM Clientes c WHERE c.primerApellido = :primerApellido")
    , @NamedQuery(name = "Clientes.findBySegundoApellido", query = "SELECT c FROM Clientes c WHERE c.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Clientes.findByEmail", query = "SELECT c FROM Clientes c WHERE c.email = :email")
    , @NamedQuery(name = "Clientes.findByTelefono", query = "SELECT c FROM Clientes c WHERE c.telefono = :telefono")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_documento")
    private Integer numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 15)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 15)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono")
    private int telefono;
    @JoinColumn(name = "contratos_codigo_contrato", referencedColumnName = "codigo_contrato")
    @ManyToOne(optional = false)
    private Contratos contratosCodigoContrato;
    @JoinColumn(name = "tipo_documento_id_tipo_doc", referencedColumnName = "id_tipo_doc")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumentoIdTipoDoc;
    @JoinColumn(name = "usuarios_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuariosIdUsuario;
    @JoinColumn(name = "empresa_nit", referencedColumnName = "nit")
    @ManyToOne(optional = false)
    private Empresa empresaNit;

    public Clientes() {
    }

    public Clientes(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Clientes(Integer numeroDocumento, String primerNombre, String primerApellido, int telefono) {
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.telefono = telefono;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Contratos getContratosCodigoContrato() {
        return contratosCodigoContrato;
    }

    public void setContratosCodigoContrato(Contratos contratosCodigoContrato) {
        this.contratosCodigoContrato = contratosCodigoContrato;
    }

    public TipoDocumento getTipoDocumentoIdTipoDoc() {
        return tipoDocumentoIdTipoDoc;
    }

    public void setTipoDocumentoIdTipoDoc(TipoDocumento tipoDocumentoIdTipoDoc) {
        this.tipoDocumentoIdTipoDoc = tipoDocumentoIdTipoDoc;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    public Empresa getEmpresaNit() {
        return empresaNit;
    }

    public void setEmpresaNit(Empresa empresaNit) {
        this.empresaNit = empresaNit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.numeroDocumento == null && other.numeroDocumento != null) || (this.numeroDocumento != null && !this.numeroDocumento.equals(other.numeroDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "login.entidades.Clientes[ numeroDocumento=" + numeroDocumento + " ]";
    }
    
}
