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
 */
@Model
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    private boolean flag = false;
    private String username = null;
    private String password = null; 
    
    /**
     * Empty constructor for LoginBean
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
                System.out.println("User in role! Logged in!!!");
                return "Admin/AdminPage.xhtml?faces-redirect=true";
            } else {
                //this.addErrorMessageRedirect("User not found");
                System.out.println("User not in role!");
                return null;
            }
        } catch (ServletException se) {
            //this.addErrorMessage("Incorrect username or password.");
            LOG.log(Level.INFO, "Bad username or password");
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
            System.out.println("User logged OUT!!!");
            ec.redirect(ec.getRequestContextPath() + "/Login.xhtml?faces-redirect=true");
            return "";
        } catch (Exception e) {
            //this.addInfoMessage("Logout failed.");
            LOG.log(Level.INFO, "Logout failed");
            System.out.println("Logout failed.");
            return null;
        }
    }
    
    /**
     * Check whether user is logged in.
     * 
     * @return The status of the boolean flag - true if user is logged 
     * in, false otherwise.
     */
    public boolean isLoggedIn() {
        return flag;
    }
    
    /* Getters and setters for the fields */
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getUsn() {
        return username;
    }

    public void setUsn(String username) {
        this.username = username;
    }

    public String getPsw() {
        return password;
    }

    public void setPsw(String password) {
        this.password = password;
    }
}
