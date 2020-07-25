/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ActivitiesDao;
import Dao.PoiDao;
import Entity.Activities;
import Entity.Poi;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "activity")
@SessionScoped
public class ActivitiesController implements Serializable {

    private Activities activities;
    private List<Activities> activityList;
    private ActivitiesDao activitiesDao;

    private List<Poi> poiList;
    private PoiDao poiDao;

    private int page = 1;
    private int listitemcount = 3;

    public boolean hasPrev() {
        return page > 1;
    }

    public boolean hasNext() {
        int sum;
        sum = this.getActivitiesDao().record();
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
        this.activities = new Activities();
    }

    public String back() {
        this.activities = new Activities();
        return "/admin/activities/list?faces-redirect=true ";
    }

    public String createForm() {
        this.activities = new Activities();
        return "/admin/activities/create?faces-redirect=true ";
    }

    public String updateForm(Activities activities) {
        this.activities = activities;
        return "/admin/activities/update?faces-redirect=true ";
    }

    public String delete(Activities activity) {
        this.getActivitiesDao().delete(activity);
        this.clearForm();
        return "/admin/activities/list?faces-redirect=true ";
    }

    public String update() {
        this.getActivitiesDao().update(this.activities);
        this.clearForm();
        return "/admin/activities/list?faces-redirect=true ";
    }

    public String create() {
        this.getActivitiesDao().create(this.activities);
        this.clearForm();
        return "/admin/activities/list?faces-redirect=true ";
    }

    public Activities getActivities() {
        if (this.activities == null) {
            this.activities = new Activities();
        }
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public List<Activities> getActivityList() {
        this.activityList = this.getActivitiesDao().findAll(page, listitemcount);
        return activityList;
    }

    public void setActivityList(List<Activities> activityList) {
        this.activityList = activityList;
    }

    public ActivitiesDao getActivitiesDao() {
        if (this.activitiesDao == null) {
            this.activitiesDao = new ActivitiesDao();
        }
        return activitiesDao;
    }

    public void setActivitiesDao(ActivitiesDao activitiesDao) {
        this.activitiesDao = activitiesDao;
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
