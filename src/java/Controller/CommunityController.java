/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CommunityDao;
import Entity.Community;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "community")
@SessionScoped
public class CommunityController implements Serializable {

    private Community community;
    private List<Community> CommunityList;
    private CommunityDao CommunityDao;

    @Inject
    private CountyController countyController;
    //private Long selectedOblast;

    private int page = 1;
    private int listitemcount = 3;

    public boolean hasPrev() {
        return page > 1;
    }

    public boolean hasNext() {
        int sum;
        sum = this.getCommunityDao().record();
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
        return this.getCommunityDao().record();
    }

    // @Inject
    // private CountryController countryController;
    public String back() {
        this.community = new Community();
        return "/admin/community/list?faces-redirect=true ";
    }

    public String createForm() {
        this.community = new Community();
        return "/admin/community/create?faces-redirect=true ";
    }

    public void clearForm() {
        this.community = new Community();
    }

    public String updateForm(Community community) {
        this.community = community;
        return "/admin/community/update?faces-redirect=true ";
    }

    public String delete(Community community) {
        this.getCommunityDao().delete(community);
        this.clearForm();
        return "/admin/community/list?faces-redirect=true ";
    }

    public String update() {
        this.getCommunityDao().update(this.community);
        this.clearForm();
        return "/admin/community/list?faces-redirect=true ";
    }

    public String create() {
        this.getCommunityDao().create(this.community);
        this.clearForm();
        return "/admin/community/list?faces-redirect=true ";
    }

    public Community getCommunity() {
        if (this.community == null) {
            this.community = new Community();
        }
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<Community> getCommunityList() {
        this.CommunityList = this.getCommunityDao().findAll();
        return CommunityList;
    }

    public void setCommunityList(List<Community> CommunityList) {
        this.CommunityList = CommunityList;
    }

    public CommunityDao getCommunityDao() {
        if (this.CommunityDao == null) {
            this.CommunityDao = new CommunityDao();
        }
        return CommunityDao;
    }

    public void setCommunityDao(CommunityDao CommunityDao) {
        this.CommunityDao = CommunityDao;
    }

    public CountyController getCountyController() {
        return countyController;
    }

    public void setCountyController(CountyController countyController) {
        this.countyController = countyController;
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
