/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CountryDao;
import Entity.Country;
import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named ( value = "country")
@SessionScoped
public class CountryController implements Serializable {

    private List<Country> LCoun;
    private CountryDao CounDao;
    private Country country;
    
    private int page = 1;
    private int listItemCount = 3;
    
    public int count (){
        return this.getCounDao().record();
    }
    
    public boolean hasPrev() {
        return page > 1;
    }

    public boolean hasNext() {
        int sum ;
        sum = this.getCounDao().record();
        if ((this.page * this.listItemCount) >= sum) {
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
    
    public void clearForm(){
        this.country = new Country();
    }
    
    public String back() {
        this.country = new Country();
        return "/admin/country/list?faces-redirect=true ";
    }
    
    public String start() {
        this.country = new Country() ;
        return "/admin/index?faces-redirect=true";
    }
    
    public String createForm() {
        this.country = new Country() ;
        return "/admin/country/create?faces-redirect=true ";
    }
    
    public String updateForm(Country country) {
        this.country = country;  
        return "/admin/country/update?faces-redirect=true ";
    }
    
    public String update() {
        this.getCounDao().update(this.country);
        this.country = new Country();
        return "/admin/country/list?faces-redirect=true ";
    }
    public void deleteConfirm(Country coun){
        this.country = coun ;
    }

    public String delete(Country country) {
        this.getCounDao().delete(country);
        this.clearForm();
        return "/admin/country/list?faces-redirect=true ";
    }

    public String create() {
        this.getCounDao().create(this.country);
        this.country = new Country();
        return "/admin/country/list?faces-redirect=true ";
    }

    public List<Country> getLCoun() {
        this.LCoun = this.getCounDao().findAll(page , listItemCount);
        return LCoun;
    }

    public void setLCoun(List<Country> LCoun) {
        this.LCoun = LCoun;
    }

    public CountryDao getCounDao() {
        if (this.CounDao == null) {
            this.CounDao = new CountryDao();
        }
        return CounDao;
    }

    public void setCounDao(CountryDao CounDao) {
        this.CounDao = CounDao;
    }

    public Country getCountry() {
        if (this.country == null) {
            this.clearForm();
        }
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getListItemCount() {
        return listItemCount;
    }

    public void setListItemCount(int listItemCount) {
        this.listItemCount = listItemCount;
    }
    
    

}
