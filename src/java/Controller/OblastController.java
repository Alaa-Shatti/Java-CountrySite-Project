/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CountryDao;
import Dao.OblastDao;
import Entity.Country;
import Entity.Oblast;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named (value = "oblast")
@SessionScoped
public class OblastController implements Serializable{
    private Oblast oblast;
    private List<Oblast> oblastList;
    private OblastDao oblastDao;
    
    private List<Country> LCoun;
    private CountryDao countryDao;
    
    private int page = 1;
    private int listItemCount = 3;
   
    
    public boolean hasPrev() {
        return page > 1;
    }

    public boolean hasNext() {
        int sum ;
        sum = this.getOblastDao().record();
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
    
    
    public int count (){
        return this.getOblastDao().record();
    }
    public void clearForm(){
        this.oblast = new Oblast();
    }
    
     public String back() {
        this.oblast = new Oblast();
        return "/admin/oblast/list?faces-redirect=true ";
    }
     
    public String createForm() {
        this.oblast = new Oblast() ;
        return "/admin/oblast/create?faces-redirect=true ";
    }
    
    public String updateForm(Oblast oblast){
        this.oblast = oblast ;
        return "/admin/oblast/update?faces-redirect=true ";
    }
    
    public String delete(Oblast oblast){
     this.getOblastDao().delete(oblast);
     this.clearForm();
     return "/admin/oblast/list?faces-redirect=true ";
    }
    public String update(){
        this.getOblastDao().update(this.oblast);
        this.clearForm();
        return "/admin/oblast/list?faces-redirect=true ";
    }
    public String create(){
       this.getOblastDao().create(this.oblast);
       this.clearForm();
       return "/admin/oblast/list?faces-redirect=true ";
    }

    public Oblast getOblast() {
        if(this.oblast == null)
            this.oblast = new Oblast();
        return oblast;
    }

    public void setOblast(Oblast oblast) {
        this.oblast = oblast;
    }

    public List<Oblast> getOblastList() {
        this.oblastList = this.getOblastDao().findAll();
        return oblastList;
    }

    public void setOblastList(List<Oblast> oblastList) {
        this.oblastList = oblastList;
    }

    public OblastDao getOblastDao() {
        if(this.oblastDao == null)
            this.oblastDao = new OblastDao();
        return oblastDao;
    }

    public void setOblastDao(OblastDao oblastDao) {
        this.oblastDao = oblastDao;
    }

    public List<Country> getLCoun() {
        this.LCoun = this.getCountryDao().findAll();
        return LCoun;
    }

    public void setLCoun(List<Country> LCoun) {
        this.LCoun = LCoun;
    }

    public CountryDao getCountryDao() {
        if(this.countryDao == null )
            this.countryDao = new CountryDao();
        return countryDao;
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
