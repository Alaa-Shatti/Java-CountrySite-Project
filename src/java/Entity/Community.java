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
public class Community {

    private Long comm_id;
    private String name;

    private County county;
    private List<County> communitycounty;

    public Long getComm_id() {
        return comm_id;
    }

    public void setComm_id(Long comm_id) {
        this.comm_id = comm_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.comm_id);
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
        final Community other = (Community) obj;
        if (!Objects.equals(this.comm_id, other.comm_id)) {
            return false;
        }
        return true;
    }

    public List<County> getCommunitycounty() {
        return communitycounty;
    }

    public void setCommunitycounty(List<County> communitycounty) {
        this.communitycounty = communitycounty;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "Community{" + "comm_id=" + comm_id + ", name=" + name + ", communitycounty=" + communitycounty + '}';
    }

}
