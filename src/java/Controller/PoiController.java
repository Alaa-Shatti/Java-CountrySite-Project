/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.OblastDao;
import Dao.PoiDao;
import Entity.Oblast;
import Entity.Poi;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named ( value = "poi")
@SessionScoped
public class PoiController extends ValidateController implements Serializable {

    private Poi poi;
    private List<Poi> poiList;
    private PoiDao poiDao;

    private List<Oblast> obList;

    private OblastDao oblastDao;
    
    private int page = 1;
    private int listitemcount = 3;

    public int count() {
        return this.getPoiDao().record();
    }

    public boolean hasPrev() {
        return page > 1;
    }

    public boolean hasNext() {
        int sum ;
        sum = this.getPoiDao().record();
        if ((this.page * this.listitemcount) >= sum) {
            return false;
        } else {
            return true;
        }
    }

    public void previous() {
        this.setPage(this.page - 1);
    }

    public void next() {
        this.setPage(this.page + 1);
    }

    public void clearForm() {
        this.poi = new Poi();
    }
    
    public String back() {
        this.poi = new Poi();
        this.clearForm();
        return "/admin/poi/list?faces-redirect=true ";
    }
     
    public String createForm() {
        this.poi = new Poi() ;
        return "/admin/poi/create?faces-redirect=true ";
    }

    public String updateForm(Poi poi) {
        this.poi = poi;
        return "/admin/poi/update?faces-redirect=true ";
    }

    public String delete(Poi poi) {
        this.getPoiDao().delete(poi);
        this.clearForm();
        return "/admin/poi/list?faces-redirect=true ";
    }

    public String update() {
        this.getPoiDao().update(this.poi);
        this.clearForm();
        return "/admin/poi/list?faces-redirect=true ";
    }

    public String create() {
        this.getPoiDao().create(this.poi);
        this.clearForm();
        return "/admin/poi/list?faces-redirect=true ";
    }

    public Poi getPoi() {
        if (this.poi == null) {
            this.poi = new Poi();
        }
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

    public List<Poi> getPoiList() {
        this.poiList = this.getPoiDao().findAll();
        return poiList;
    }

    public void setPoiList(List<Poi> poiList) {
        this.poiList = poiList;
    }

    public PoiDao getPoiDao() {
        if (this.poi == null) {
            this.poiDao = new PoiDao();
        }
        return poiDao;
    }

    public void setPoiDao(PoiDao poiDao) {
        this.poiDao = poiDao;
    }

    public List<Oblast> getObList() {
        this.obList = this.getOblastDao().findAll();
        return obList;
    }

    public void setObList(List<Oblast> obList) {
        this.obList = obList;
    }

    public OblastDao getOblastDao() {
        if (this.oblastDao == null) {
            this.oblastDao = new OblastDao();
        }
        return oblastDao;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getListitemcount() {
        return listitemcount;
    }

    public void setListitemcount(int listitemcount) {
        this.listitemcount = listitemcount;
    }
    
    

}
