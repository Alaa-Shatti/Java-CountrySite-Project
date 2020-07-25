/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.GroupDao;
import Dao.PoiDao;
import Dao.UsersDao;
import Entity.Group;
import Entity.Poi;
import Entity.Users;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "user")
@SessionScoped
public class UsersController extends ValidateController implements Serializable {

    private Users users;
    private List<Users> userList;
    private UsersDao usersDao;

    private List<Poi> poiList;
    private PoiDao poiDao;

    private List<Group> grList;
    private GroupDao groupDao;

    // private List<Group> groupList;
    @Inject
    private GroupsController groupController;

    public int count() {
        return this.getUsersDao().record();
    }

    public void clearForm() {
        this.users = new Users();
    }

    public String back() {
        this.users = new Users();
        return "/admin/user/list?faces-redirect=true ";
    }

    public String createForm() {
        this.users = new Users();
        return "/admin/user/create?faces-redirect=true ";
    }

    public String updateForm(Users users) {
        this.users = users;
        return "/admin/user/update?faces-redirect=true ";
    }

    public String create() {
        this.getUsersDao().create(this.users);
        this.clearForm();
        return "/admin/user/list?faces-redirect=true ";
    }

    public String delete(Users users) {
        this.getUsersDao().delete(users);
        this.clearForm();
        return "/admin/user/list?faces-redirect=true ";
    }

    public String update() {
        this.getUsersDao().update(this.users);
        this.clearForm();
        return "/admin/user/list?faces-redirect=true ";
    }

    public Users getUsers() {
        if (this.users == null) {
            this.users = new Users();
        }
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Users> getUserList() {
        this.userList = this.getUsersDao().findAll();
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public UsersDao getUsersDao() {
        if (this.usersDao == null) {
            this.usersDao = new UsersDao();
        }
        return usersDao;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public List<Group> getGrList() {
        this.grList = this.getGroupDao().findAll();
        return grList;
    }

    public void setGrList(List<Group> grList) {
        this.grList = grList;
    }

    public GroupDao getGroupDao() {
        if (this.groupDao == null) {
            this.groupDao = new GroupDao();
        }
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
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

    public GroupsController getGroupController() {
        return groupController;
    }

    public void setGroupController(GroupsController groupController) {
        this.groupController = groupController;
    }

}
