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
@Table(name = "jefe_administrativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JefeAdministrativo.findAll", query = "SELECT j FROM JefeAdministrativo j")
    , @NamedQuery(name = "JefeAdministrativo.findByNumeroDocumento", query = "SELECT j FROM JefeAdministrativo j WHERE j.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "JefeAdministrativo.findByPrimerNombre", query = "SELECT j FROM JefeAdministrativo j WHERE j.primerNombre = :primerNombre")
    , @NamedQuery(name = "JefeAdministrativo.findBySegundoNombre", query = "SELECT j FROM JefeAdministrativo j WHERE j.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "JefeAdministrativo.findByPrimerApellido", query = "SELECT j FROM JefeAdministrativo j WHERE j.primerApellido = :primerApellido")
    , @NamedQuery(name = "JefeAdministrativo.findBySegundoApellido", query = "SELECT j FROM JefeAdministrativo j WHERE j.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "JefeAdministrativo.findByEmail", query = "SELECT j FROM JefeAdministrativo j WHERE j.email = :email")
    , @NamedQuery(name = "JefeAdministrativo.findByTelefono", query = "SELECT j FROM JefeAdministrativo j WHERE j.telefono = :telefono")})
public class JefeAdministrativo implements Serializable {

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
    @Size(min = 1, max = 10)
    @Column(name = "telefono")
    private String telefono;
    @JoinColumn(name = "tipo_documento_id_tipo_doc", referencedColumnName = "id_tipo_doc")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumentoIdTipoDoc;
    @JoinColumn(name = "usuarios_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuariosIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jefeAdministrativoUsuariosIdUsuario")
    private List<Contratos> contratosList;

    public JefeAdministrativo() {
    }

    public JefeAdministrativo(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public JefeAdministrativo(Integer numeroDocumento, String primerNombre, String primerApellido, String telefono) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JefeAdministrativo)) {
            return false;
        }
        JefeAdministrativo other = (JefeAdministrativo) object;
        if ((this.numeroDocumento == null && other.numeroDocumento != null) || (this.numeroDocumento != null && !this.numeroDocumento.equals(other.numeroDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return primerNombre+" "+primerApellido;
    }
    
}
