/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CommentsDao;
import Dao.PoiDao;
import Entity.Comments;
import Entity.Poi;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named ( value = "comment")
@SessionScoped
public class CommentsController implements Serializable {

    private Comments commnets;
    private List<Comments> commList;
    private CommentsDao commentsDao;

    private List<Poi> poiList;
    private PoiDao poiDao;
    
    public void clearForm (){
        this.commnets = new Comments() ;
    }
    
    public String back() {
        this.commnets = new Comments();
        return "/admin/comments/list?faces-redirect=true ";
    }

    public String createForm() {
        this.commnets = new Comments();
        return "/admin/comments/create?faces-redirect=true ";
    }
    
    //public String updateForm (Comments comment){
    //    this.commnets = comment ;
    //}
    /* public void update (){
        this.getCommentsDao().update(this.commnets) ;
        this.clearForm() ;
    }
    */
    public String create (){
        this.getCommentsDao().create(this.commnets) ;
        this.clearForm() ;
        return "/admin/comments/list?faces-redirect=true ";
    }
    
    public String delete(Comments comments){
        this.getCommentsDao().delete(comments) ;
        this.clearForm() ;
        return "/admin/comments/list?faces-redirect=true ";
    }

    public Comments getCommnets() {
        if (this.commnets == null) {
            this.commnets = new Comments();
        }
        return commnets;
    }

    public void setCommnets(Comments commnets) {
        this.commnets = commnets;
    }

    public List<Comments> getCommList() {
        this.commList = this.getCommentsDao().findAll();
        return commList;
    }

    public void setCommList(List<Comments> commList) {
        this.commList = commList;
    }

    public CommentsDao getCommentsDao() {
        if (this.commentsDao == null) {
            this.commentsDao = new CommentsDao();
        }
        return commentsDao;
    }

    public void setCommentsDao(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
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

}
