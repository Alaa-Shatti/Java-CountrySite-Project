/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.SessionDao;
import Dao.UsersDao;
import Entity.Users;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "sessions")
@SessionScoped
public class SessionController implements Serializable {

    private SessionDao sessionDao;

    private Users users;
    private UsersDao usersDao;

    public boolean hasPerm(String module, String process) {

        Users current = (Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().getOrDefault("user", null);

        return this.getSessionDao().getPerm(current.getGroup(), module, process);

    }

    public boolean hasPerm2(String module) {

        if (hasPerm(module, "u") ||  hasPerm(module, "d")) {
            return true;
        } else {
            return false;
        }
    }

   
    public SessionDao getSessionDao() {
        if (this.sessionDao == null) {
            this.sessionDao = new SessionDao();
        }
        return sessionDao;
    }

    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public Users getUsers() {
        if (this.users == null) {
            this.users = new Users();
        }
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public UsersDao getUsersDao() {
        if (this.usersDao == null) {
            this.usersDao = new UsersDao();
        }
        return usersDao;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

}
