
package login.controlador;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Acer
 */
@Named("correosMasivos")
//@Entity
//@Table(name = "persona")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "persona.findAll", query = "SELECT e FROM persona e")
//    , @NamedQuery(name = "persona.findById", query = "SELECT e FROM persona e WHERE e.id = :id")
//    , @NamedQuery(name = "persona.findBynombre", query = "SELECT e FROM persona e WHERE e.nombre = :nombre")
//    , @NamedQuery(name = "persona.findByemail", query = "SELECT e FROM persona e WHERE e.email = :email")})

public class CorreosMasivos implements Serializable {

//    @Id
//    private Long id;
    
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
            String asunto = "Nueva entrada en el inventario";
            String mensaje = "Hola<br>Se ha agregado una nueva entrada en el inventario<b></b>.";

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
        
 }

