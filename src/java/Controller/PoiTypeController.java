/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PoiDao;
import Dao.PoiTypeDao;
import Entity.Poi;
import Entity.PoiType;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "poiType")
@SessionScoped
public class PoiTypeController implements Serializable {

    private PoiType poiType;
    private List<PoiType> PTList;
    private PoiTypeDao ptDao;

    private List<Poi> poiList;
    private PoiDao poiDao;

    private int page = 1;
    private int listitemcount = 3;

    public int count() {
        return this.getPtDao().record();
    }

    public boolean hasPrev() {
        return page > 1;
    }

    public boolean hasNext() {
        int sum ;
        sum = this.getPtDao().record();
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
        this.poiType = new PoiType();
    }

    public String back() {
        this.poiType = new PoiType();
        return "/admin/poiType/list?faces-redirect=true ";
    }

    public String createForm() {
        this.poiType = new PoiType();
        return "/admin/poiType/create?faces-redirect=true ";
    }

    public String updateForm(PoiType poiType) {
        this.poiType = poiType;
        return "/admin/poiType/update?faces-redirect=true ";
    }

    public String delete(PoiType PType) {
        this.getPtDao().delete(PType);
        this.clearForm();
        return "/admin/poiType/list?faces-redirect=true ";
    }

    public String update() {
        this.getPtDao().update(this.poiType);
        this.clearForm();
        return "/admin/poiType/list?faces-redirect=true ";
    }

    public String create() {
        this.getPtDao().create(this.poiType);
        this.clearForm();
        return "/admin/poiType/list?faces-redirect=true ";
    }

    public PoiType getPoiType() {
        if (this.poiType == null) {
            this.poiType = new PoiType();
        }
        return poiType;
    }

    public void setPoiType(PoiType poiType) {
        this.poiType = poiType;
    }

    public List<PoiType> getPTList() {
        this.PTList = this.getPtDao().findAll();
        return PTList;
    }

    public void setPTList(List<PoiType> PTList) {
        this.PTList = PTList;
    }

    public PoiTypeDao getPtDao() {
        if (this.ptDao == null) {
            this.ptDao = new PoiTypeDao();
        }
        return ptDao;
    }

    public void setPtDao(PoiTypeDao ptDao) {
        this.ptDao = ptDao;
    }

    public List<Poi> getPoiList() {
        this.poiList = this.getPoiDao().findAll();
        return poiList;
    }

    public void setPoiList(List<Poi> poiList) {
        this.poiList = poiList;
    }

    public PoiDao getPoiDao() {
        if (this.poiDao == null) {
            this.poiDao = new PoiDao();
        }
        return poiDao;
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
