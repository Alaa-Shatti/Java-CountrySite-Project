/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.OblastDao;
import Entity.Oblast;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ms
 */
@FacesConverter(value = "oblastConverter")
public class OblastConverter implements Converter {

    private OblastDao oblastDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getOblastDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Oblast ob = (Oblast) value;
        return ob.getOb_id().toString();
    }

    public OblastDao getOblastDao() {
        if (this.oblastDao == null) {
            this.oblastDao = new OblastDao();
        }
        return oblastDao;
    }

}
