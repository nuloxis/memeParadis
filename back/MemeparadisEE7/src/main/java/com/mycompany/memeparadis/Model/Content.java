/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.memeparadis.Configuration.Database;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author krist
 */
@Entity
@Table(name = "content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c"),
    @NamedQuery(name = "Content.findById", query = "SELECT c FROM Content c WHERE c.id = :id"),
    @NamedQuery(name = "Content.findByAdultContent", query = "SELECT c FROM Content c WHERE c.adultContent = :adultContent"),
    @NamedQuery(name = "Content.findByLanguage", query = "SELECT c FROM Content c WHERE c.language = :language"),
    @NamedQuery(name = "Content.findByLikes", query = "SELECT c FROM Content c WHERE c.likes = :likes"),
    @NamedQuery(name = "Content.findByContentType", query = "SELECT c FROM Content c WHERE c.contentType = :contentType"),
    @NamedQuery(name = "Content.findByUploadedDate", query = "SELECT c FROM Content c WHERE c.uploadedDate = :uploadedDate")})
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adult_content")
    private boolean adultContent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "language")
    private String language;
    @Basic(optional = false)
    @NotNull
    @Column(name = "likes")
    private int likes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "content_type")
    private String contentType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uploaded_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date uploadedDate;
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    @ManyToOne
    private Comment commentId;
    @JoinColumn(name = "uploader_name", referencedColumnName = "id")
    @ManyToOne
    private User uploaderName;

    public Content() {
    }

    public Content(Integer id) {
        this.id = id;
    }

    public Content(Integer id, boolean adultContent, String language, int likes, String contentType, Date uploadedDate) {
        this.id = id;
        this.adultContent = adultContent;
        this.language = language;
        this.likes = likes;
        this.contentType = contentType;
        this.uploadedDate = uploadedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getAdultContent() {
        return adultContent;
    }

    public void setAdultContent(boolean adultContent) {
        this.adultContent = adultContent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public Comment getCommentId() {
        return commentId;
    }

    public void setCommentId(Comment commentId) {
        this.commentId = commentId;
    }

    public User getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(User uploaderName) {
        this.uploaderName = uploaderName;
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
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.memeparadis.Model.Content[ id=" + id + " ]";
    }
    public String createContent(Content c,byte[] fileBytes, String fileName) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("createContent");
            spq.registerStoredProcedureParameter("uploader_id",String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("adult_content", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("language",String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("content_type",String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("file_name", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("file_bytes", byte[].class, ParameterMode.IN);
            
            spq.setParameter("uploader_id",c.getUploaderName());
            spq.setParameter("adult_content",c.getAdultContent());
            spq.setParameter("language",c.getLanguage());
            spq.setParameter("content_type",c.getContentType());
            spq.setParameter("file_name", fileName);
            spq.setParameter("file_bytes", fileBytes);
            
            spq.execute();
            
            return fileName;
           
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new Exception(""+ex.getMessage());
        }
        finally{
            em.clear();
            em.close();
            emf.close();    
        }
    }
    public List<Content> getAllContent() throws Exception{
         List<Content> resultList = new ArrayList<>();
        try{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
         resultList = em.createQuery("SELECT c FROM Content c", Content.class).getResultList();
         
          em.clear();
          em.close();
          emf.close();   
        }catch(Exception ex){
             System.out.println(ex.getMessage());
            throw new Exception(""+ex.getMessage());
        }
        return resultList;
}
}

