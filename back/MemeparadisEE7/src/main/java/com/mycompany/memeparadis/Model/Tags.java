/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Model;

import com.mycompany.memeparadis.Configuration.Database;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author krist
 */
@Entity
@Table(name = "tags")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tags.findAll", query = "SELECT t FROM Tags t"),
    @NamedQuery(name = "Tags.findById", query = "SELECT t FROM Tags t WHERE t.id = :id"),
    @NamedQuery(name = "Tags.findByTag", query = "SELECT t FROM Tags t WHERE t.tag = :tag")})
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tag")
    private String tag;
    @OneToMany(mappedBy = "tagsId")
    private Collection<ContentTag> contentTagCollection;

    public Tags() {
    }

    public Tags(Integer id) {
        this.id = id;
    }

    public Tags(Integer id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @XmlTransient
    public Collection<ContentTag> getContentTagCollection() {
        return contentTagCollection;
    }

    public void setContentTagCollection(Collection<ContentTag> contentTagCollection) {
        this.contentTagCollection = contentTagCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.memeparadisee7.Model.Tags[ id=" + id + " ]";
    }
    public String createTag(Tags tag) throws Exception {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
    EntityManager em = emf.createEntityManager();
    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("createTag");
        spq.registerStoredProcedureParameter("tagIN", String.class, ParameterMode.IN);
        
        spq.setParameter("tagIN", tag.getTag());
        
        spq.execute();
        
        Query selectQuery = em.createNativeQuery("SELECT LAST_INSERT_ID()");
        String lastInsertId = selectQuery.getSingleResult().toString();
        
        return lastInsertId;
    } catch(Exception ex) {
        System.out.println(ex.getMessage());
        throw new Exception("" + ex.getMessage());
    } finally {
        em.clear();
        em.close();
        emf.close();
    }
}


}
