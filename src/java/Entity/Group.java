/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ms
 */
public class Group {
    
    private Long group_id ;
    private String group_name ;
    
    private List<Users> groupUser ;
    
    

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long proup_id) {
        this.group_id = proup_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public List<Users> getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(List<Users> groupUser) {
        this.groupUser = groupUser;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.group_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (!Objects.equals(this.group_id, other.group_id)) {
            return false;
        }
        return true;
    }
    
}
