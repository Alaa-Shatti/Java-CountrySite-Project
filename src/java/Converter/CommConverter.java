/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.CommunityDao;
import Entity.Community;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ms
 */
@FacesConverter(value = "commConverter")
public class CommConverter implements Converter {

    private CommunityDao communityDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getCommunityDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Community comm = (Community) value;
        return comm.getComm_id().toString();

    }

    public CommunityDao getCommunityDao() {
        if (this.communityDao == null) {
            this.communityDao = new CommunityDao();
        }
        return communityDao;
    }

}
