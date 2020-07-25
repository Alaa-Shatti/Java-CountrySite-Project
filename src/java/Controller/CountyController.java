/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CountyDao;
import Dao.OblastDao;
import Entity.County;
import Entity.Oblast;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "county")
@SessionScoped
public class CountyController implements Serializable {

    private County county;
    private List<County> countyList;
    private CountyDao countyDao;

    private List<Oblast> obList;
    private OblastDao oblastDao;
    
    @Inject
    private CommunityController commController;
    //private Long selectedOblast;

    private int page = 1;
    private int listitemcount = 3;

    public boolean hasPrev() {
        return page > 1;
    }

    public boolean hasNext() {
        int sum;
        sum = this.getCountyDao().record();
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

    public int count() {
        return this.getCountyDao().record();
    }

    // @Inject
    // private CountryController countryController;
    public String back() {
        this.county = new County();
        return "/admin/county/list?faces-redirect=true ";
    }

    public String createForm() {
        this.county = new County();
        return "/admin/county/create?faces-redirect=true ";
    }

    public void clearForm() {
        this.county = new County();
    }

    public String updateForm(County county) {
        this.county = county;
        return "/admin/county/update?faces-redirect=true ";
    }

    public String delete(County county) {
        this.getCountyDao().delete(county);
        this.clearForm();
        return "/admin/county/list?faces-redirect=true ";
    }

    public String update() {
        this.getCountyDao().update(this.county);
        this.clearForm();
        return "/admin/county/list?faces-redirect=true ";
    }

    public String create() {
        this.getCountyDao().create(this.county);
        this.clearForm();
        return "/admin/county/list?faces-redirect=true ";
    }

    public County getCounty() {
        if (this.county == null) {
            this.county = new County();
        }
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public List<County> getCountyList() {
        this.countyList = this.getCountyDao().findAll();
        return countyList;
    }

    public void setCountyList(List<County> countyList) {
        this.countyList = countyList;
    }

    public CountyDao getCountyDao() {
        if (this.countyDao == null) {
            this.countyDao = new CountyDao();
        }
        return countyDao;
    }

    public void setCountyDao(CountyDao countyDao) {
        this.countyDao = countyDao;
    }

    /* public Long getSelectedOblast() {
        return selectedOblast;
    }

    public void setSelectedOblast(Long selectedOblast) {
        this.selectedOblast = selectedOblast;
    }*/
    public OblastDao getOblastDao() {
        if (this.oblastDao == null) {
            this.oblastDao = new OblastDao();
        }
        return oblastDao;
    }

    public List<Oblast> getObList() {
        this.obList = this.getOblastDao().findAll();
        return obList;
    }

    public void setObList(List<Oblast> obList) {
        this.obList = obList;
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

    public CommunityController getCommController() {
        return commController;
    }

    public void setCommController(CommunityController commController) {
        this.commController = commController;
    }

}
