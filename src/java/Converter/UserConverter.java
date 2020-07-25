/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.UsersDao;
import Entity.Users;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ms
 */
@FacesConverter(value="userConverter")
public class UserConverter implements Converter{
    
    private UsersDao userDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return this.getUserDao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Users user = (Users) value ;
        return user.getUser_id().toString();
        
    }

    public UsersDao getUserDao() {
        if(this.userDao == null)
            this.userDao = new UsersDao();
        return userDao;
    }

    public void setUserDao(UsersDao userDao) {
        this.userDao = userDao;
    }
    
    
    
}
