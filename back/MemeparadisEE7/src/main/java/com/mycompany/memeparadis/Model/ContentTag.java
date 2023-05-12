/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.memeparadis.Configuration.Database;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.mycompany.memeparadis.Model.Tags;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author krist
 */
@Entity
@Table(name = "content_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContentTag.findAll", query = "SELECT c FROM ContentTag c"),
    @NamedQuery(name = "ContentTag.findById", query = "SELECT c FROM ContentTag c WHERE c.id = :id")})
public class ContentTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Transient
    @JsonInclude
    private Integer contentId;
    @Transient
    @JsonInclude
    private Integer tagsId;

    public ContentTag() {
    }

    public ContentTag(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getTagsId() {
        return tagsId;
    }

    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
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
        if (!(object instanceof ContentTag)) {
            return false;
        }
        ContentTag other = (ContentTag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.memeparadisee7.Model.ContentTag[ id=" + id + " ]";
    }
    public String createContent_tag(Integer tags, Integer content) throws Exception {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
    EntityManager em = emf.createEntityManager();
    try {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("createContent_tag");
    
        spq.registerStoredProcedureParameter("content_idIN", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("tags_idIN", Integer.class, ParameterMode.IN);
    
        spq.setParameter("content_idIN",content);
        spq.setParameter("tags_idIN", tags);
    
        spq.execute();

   
        Query insertQuery = em.createNativeQuery("INSERT INTO content_tag (content_id, tags_id) VALUES (?, ?)");
        insertQuery.setParameter(1, content);
        insertQuery.setParameter(2, tags);
        insertQuery.executeUpdate();

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
        throw new Exception("" + ex.getMessage());
    } finally {
        em.clear();
        em.close();
        emf.close();
    }
    return "Sikeres beillesztés";
}

}
