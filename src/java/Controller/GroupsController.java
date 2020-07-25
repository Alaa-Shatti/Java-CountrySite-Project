/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.GroupDao;
import Entity.Group;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "group")
@SessionScoped
public class GroupsController implements Serializable {

    private Group group;
    private List<Group> grList;
    private GroupDao groupDao;
    
    @Inject
    private UsersController usersController ;

    public void clearForm() {
        this.group = new Group();
    }

    public String back() {
        this.group = new Group();
        return "/admin/groups/list?faces-redirect=true ";
    }

    public String createForm() {
        this.group = new Group();
        return "/admin/groups/create?faces-redirect=true ";
    }

    public String updateForm(Group group) {
        this.group = group;
        return "/admin/groups/update?faces-redirect=true ";
    }

    public String delete(Group group) {
        this.getGroupDao().delete(group);
        this.clearForm();
        return "/admin/groups/list?faces-redirect=true ";
    }

    public String update() {
        this.getGroupDao().update(this.group);
        this.clearForm();
        return "/admin/groups/list?faces-redirect=true ";
    }

    public String create() {
        this.getGroupDao().create(this.group);
        this.clearForm();
        return "/admin/groups/list?faces-redirect=true ";
    }

    public Group getGroup() {
        if (this.group == null) {
            this.group = new Group();
        }
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Group> getGrList() {
        this.grList = this.getGroupDao().findAll();
        return grList;
    }

    public void setGroupList(List<Group> grList) {
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

    public UsersController getUsersController() {
        return usersController;
    }

    public void setUsersController(UsersController usersController) {
        this.usersController = usersController;
    }
    
    

}
