/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PrivilegesDao;
import Entity.Privileges;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "privileges")
@SessionScoped
public class PrivilegesController implements Serializable {

    private Privileges privileges;
    private List<Privileges> privList;
    private PrivilegesDao privDao;

    @Inject
    private GroupsController groupsController;
    
    
    public void clearForm() {
        this.privileges = new Privileges();
    }
    
    public String back() {
        this.privileges = new Privileges();
        return "/admin/privileges/list?faces-redirect=true ";
    }
     
    public String createForm() {
        this.privileges = new Privileges();
        return "/admin/privileges/create?faces-redirect=true ";
    }

    public String updateForm(Privileges priv ) {
        this.privileges = priv;
        return "/admin/privileges/update?faces-redirect=true ";
    }

    public String delete(Privileges priv) {
        this.getPrivDao().delete(priv);
        this.clearForm();
        return "/admin/privileges/list?faces-redirect=true ";
    }

    public String update() {
        this.getPrivDao().update(this.privileges);
        this.clearForm();
        return "/admin/privileges/list?faces-redirect=true ";
    }

    public String create() {
        this.getPrivDao().create(this.privileges);
        this.clearForm();
        return "/admin/privileges/list?faces-redirect=true ";
    }

    public void toggle(Privileges p, String op) {
        try {
            Method m = Privileges.class.getDeclaredMethod("is"+op);
            boolean tmp = (boolean) m.invoke(p);
            tmp = !tmp;

            m = Privileges.class.getDeclaredMethod("set"+op, boolean.class);
            m.invoke(p, tmp);
            this.getPrivDao().update(p);

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(PrivilegesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public static void main (String args []){
        PrivilegesController pc = new PrivilegesController();
        pc.getPrivList();
        System.out.println(pc.getPrivList());
    }*/
    
    
    
    public Privileges getPrivileges() {
        if (this.privileges == null) {
            this.privileges = new Privileges();
        }
        return privileges;
    }

    public void setPrivileges(Privileges privileges) {
        this.privileges = privileges;
    }

    public List<Privileges> getPrivList() {
        this.privList = this.getPrivDao().findAll();
        return privList;
    }

    public void setPrivList(List<Privileges> privList) {
        this.privList = privList;
    }

    public PrivilegesDao getPrivDao() {
        if (this.privDao == null) {
            this.privDao = new PrivilegesDao();
        }
        return privDao;
    }

    public void setPrivDao(PrivilegesDao privDao) {
        this.privDao = privDao;
    }

    public GroupsController getGroupsController() {
        return groupsController;
    }

    public void setGroupsController(GroupsController groupsController) {
        this.groupsController = groupsController;
    }
    
}
