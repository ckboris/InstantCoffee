package Bean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Candace
 * 
 * Handles logic for logging in and out.
 * 
 */
@Model
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    private boolean flag = false;
    private String username = null;
    private String password = null; 
    
    /**
     * Empty constructor.
     */
    public LoginBean() {
        
    }
    
    /**
     * Log user in if they are in the appropriate role. Otherwise,
     * throw an error.
     * 
     * @return The correct URL String based on whether or not the user
     * is in the right role.
     */
    public String login() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();
        try {
            req.login(username,password);
            if(req.isUserInRole("Admin")) {
                flag=true;
                return "Admin/AdminPage.xhtml?faces-redirect=true";
            } else {
                System.out.println("User not in role!");
                return null;
            }
        } catch (ServletException se) {
            System.out.println("Bad username or password");
            return null;
        }
    }
    
    /**
     * Log user out.
     * 
     * @return A URL String redirecting to the correct page if the log
     * out is successful, otherwise throw an error.
     */
    public String logout() {
        ExternalContext ec = 
            FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = 
            (HttpServletRequest) ec.getRequest();
        try {
            req.logout();
            ec.invalidateSession();
            ec.redirect(ec.getRequestContextPath() + "/Login.xhtml?faces-redirect=true");
            return "";
        } catch (Exception e) {
            //this.addInfoMessage("Logout failed.");
            LOG.log(Level.INFO, "Logout failed");
            return null;
        }
    }
    
    /**
     * Check whether user is logged in.
     * 
     * @return The status of the boolean flag - true if user is logged 
     * in, false otherwise.
     */
    public boolean getFlag() {
        return flag;
    }

    /**
     * Set the flag indicating if a user is logged in or out.
     * 
     * @param flag Set true if the user is logged in, else set false.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    /**
     * Accessor method for username.
     * 
     * @return String representation of this username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Mutator method for username field.
     * 
     * @param username The String field to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Accessor method for password.
     * 
     * @return This user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mutator method for password field.
     * 
     * @param password The String field to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
