/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UsersDao;
import Entity.Users;
import Util.SessionUtility;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ms
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private Users user;
    private UsersDao userDao;

    public String Login() {

        Users user2 = this.getUserDao().login(this.user);
        if (user2 != null) {
            HttpSession session = SessionUtility.getSession();
            session.setAttribute("user", user2);
            return "/admin/index?faces-redirect=true";

        } else {
            return "/index?faces-redirect=true";

        }

    }

    public String Logout() {

        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
    public String startPage() {
        return "/frontend/index.xhtml";
    }

    public Users getUser() {
        if (this.user == null) {
            this.user = new Users();
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UsersDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UsersDao();
        }
        return userDao;
    }

    public void setUserDao(UsersDao userDao) {
        this.userDao = userDao;
    }

}
