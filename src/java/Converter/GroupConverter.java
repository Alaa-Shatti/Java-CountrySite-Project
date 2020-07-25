/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.GroupDao;
import Entity.Group;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ms
 */
@FacesConverter(value = "groupConverter")
public class GroupConverter implements Converter{

    private GroupDao groupDao;

    
   
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return this.getGroupDao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Group gr = (Group) value;
        return gr.getGroup_id().toString();

    }
     public GroupDao getGroupDao() {
        if (this.groupDao == null) {
            this.groupDao = new GroupDao();
        }
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
     


}
