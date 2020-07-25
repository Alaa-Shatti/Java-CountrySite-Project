/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author ms
 */
public class Privileges {
    
    private Long id ;
    private String module ;
    private boolean C ;
    private boolean R ;
    private boolean U ;
    private boolean D ;
    
    private Group group ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isC() {
        return C;
    }

    public void setC(boolean C) {
        this.C = C;
    }

    public boolean isR() {
        return R;
    }

    public void setR(boolean R) {
        this.R = R;
    }

    public boolean isU() {
        return U;
    }

    public void setU(boolean U) {
        this.U = U;
    }

    public boolean isD() {
        return D;
    }

    public void setD(boolean D) {
        this.D = D;
    }

    
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Privileges{" + "id=" + id + ", module=" + module + ", C=" + C + ", R=" + R + ", U=" + U + ", D=" + D + ", group=" + group + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Privileges other = (Privileges) obj;
        if(!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
