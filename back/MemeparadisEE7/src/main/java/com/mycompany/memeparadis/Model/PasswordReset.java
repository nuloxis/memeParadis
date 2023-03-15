/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "password_reset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasswordReset.findAll", query = "SELECT p FROM PasswordReset p"),
    @NamedQuery(name = "PasswordReset.findById", query = "SELECT p FROM PasswordReset p WHERE p.id = :id"),
    @NamedQuery(name = "PasswordReset.findByToken", query = "SELECT p FROM PasswordReset p WHERE p.token = :token"),
    @NamedQuery(name = "PasswordReset.findByTokenExpire", query = "SELECT p FROM PasswordReset p WHERE p.tokenExpire = :tokenExpire"),
    @NamedQuery(name = "PasswordReset.findByUsed", query = "SELECT p FROM PasswordReset p WHERE p.used = :used"),
    @NamedQuery(name = "PasswordReset.findByCreatedAt", query = "SELECT p FROM PasswordReset p WHERE p.createdAt = :createdAt")})
public class PasswordReset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Column(name = "token_expire")
    @Temporal(TemporalType.DATE)
    private Date tokenExpire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "used")
    private boolean used;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public PasswordReset() {
    }

    public PasswordReset(Integer id) {
        this.id = id;
    }

    public PasswordReset(Integer id, String token, Date tokenExpire, boolean used, Date createdAt) {
        this.id = id;
        this.token = token;
        this.tokenExpire = tokenExpire;
        this.used = used;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Date tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof PasswordReset)) {
            return false;
        }
        PasswordReset other = (PasswordReset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.memeparadisee7.Model.PasswordReset[ id=" + id + " ]";
    }
    
}
