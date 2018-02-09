/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
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
    //declare serialVersionUID ...
    private boolean flag = false;
    private String usn = null;
    private String psw = null; 
    
    // empty constructor for LoginBean
    public LoginBean() {
    }
    
    /**
     * TODO: Sort out login/logout and roles.
     * @return 
     */
    public String login() {
        ExternalContext ec = 
            FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = 
            (HttpServletRequest) ec.getRequest();
        try {
            req.login(usn,psw);
            if(req.isUserInRole("Admin")) {
                flag=true;
                return "index.xhtml?faces-redirect=true";
            } else {
                //this.addErrorMessageRedirect("User not found");
                System.out.println("User logged in!!!");
                return null;
            }
        } catch (ServletException se) {
            //this.addErrorMessage("Incorrect username or password.");
            System.out.println("Bad username or password");
            return null;
        }
    }
    public String logout() {
        //Declare an ExternalContext and HttpServletRequest
        ExternalContext ec = 
            FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = 
            (HttpServletRequest) ec.getRequest();
        try {
            req.logout();
            ec.invalidateSession();
            System.out.println("User logged OUT!!!");
            return "index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            //this.addInfoMessage("Logout failed.");
            System.out.println("Logout failed.");
            return null;
        }
    }
    public boolean isLoggedIn() {
        return flag;
    }
    //And the getters and setters for the fields.

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
