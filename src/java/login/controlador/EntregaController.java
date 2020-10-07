package login.controlador;

import login.entidades.Entrega;
import login.controlador.util.JsfUtil;
import login.controlador.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

@Named("entregaController")
@SessionScoped
public class EntregaController implements Serializable {

    @EJB
    private login.controlador.EntregaFacade ejbFacade;
    private List<Entrega> items = null;
    private Entrega selected;

    public EntregaController() {
    }

    public Entrega getSelected() {
        return selected;
    }

    public void setSelected(Entrega selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EntregaFacade getFacade() {
        return ejbFacade;
    }

    public Entrega prepareCreate() {
        selected = new Entrega();
        initializeEmbeddableKey();
        return selected;
    }

    public void notificar(){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth","true");
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
        String correoEnvia = "ad.dragasoft@gmail.com";
        String contrasena = "Dragasoft2020";
        String destinatario = "abolivar.sena@gmail.com";
        String asunto = "Prueba correo masivo";
        String mensaje = "Prueba correo masivo gaes - Dragasoft";
        
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (destinatario));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia, contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            
            JOptionPane.showMessageDialog(null, "Correo enviado");
            
        } catch (AddressException ex){
            Logger.getLogger(EntregaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void correoMasivo () throws MessagingException{
    
        try {

            PreparedStatement ps;
            ResultSet rs;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT nombre, email FROM persona";
            String[] correos_destinos = null;

            try {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                ResultSetMetaData rd = rs.getMetaData(); // Obtenemos el metadata desde el resulset
                int filas = rd.getColumnCount();
                correos_destinos = new String[filas + 1];
                int indice = 0;

                while (rs.next()) {
                    correos_destinos[indice] = rs.getString("email");
                    indice++;
                }

            } catch (SQLException ex) {
                Logger.getLogger(EntregaController.class.getName()).log(Level.SEVERE, null, ex);
            }

//---------------------Email
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            

// Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

//Recoger los datos
            String correoRemitente = "ad.dragasoft@gmail.com";
            String passRemitente = "Dragasoft2020";
            String asunto = "Pruaba correos masivos";
            String mensaje = "Hola<br>Se ha creado una nueva entrada en el inventario <b></b>.";

// Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            Address[] receptores = new Address[correos_destinos.length];
            int j = 0;
            while (j < correos_destinos.length) {
                receptores[j] = new InternetAddress(correos_destinos[j]);
                j++;
            }

//receptor.
            message.addRecipients(Message.RecipientType.TO, receptores);
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

// Cierre de la conexion.
            t.close();
            
            System.out.println("Correo Electronico Enviado");

        } catch (AddressException ex) {
            Logger.getLogger(EntregaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EntregaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EntregaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EntregaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EntregaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Entrega> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Entrega getEntrega(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Entrega> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Entrega> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Entrega.class)
    public static class EntregaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EntregaController controller = (EntregaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "entregaController");
            return controller.getEntrega(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Entrega) {
                Entrega o = (Entrega) object;
                return getStringKey(o.getIdentrega());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Entrega.class.getName()});
                return null;
            }
        }

    }

}
