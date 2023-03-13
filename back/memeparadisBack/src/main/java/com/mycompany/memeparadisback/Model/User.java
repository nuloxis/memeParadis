/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadisback.Model;

import com.mycompany.memeparadisback.Configuration.Database;
import com.mycompany.memeparadisback.Exception.PasswordException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author krist
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByRegistrationDate", query = "SELECT u FROM User u WHERE u.registrationDate = :registrationDate"),
    @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate"),
    @NamedQuery(name = "User.findByAccessType", query = "SELECT u FROM User u WHERE u.accessType = :accessType"),
    @NamedQuery(name = "User.findByIsDeleted", query = "SELECT u FROM User u WHERE u.isDeleted = :isDeleted"),
    @NamedQuery(name = "User.findByRegToken", query = "SELECT u FROM User u WHERE u.regToken = :regToken")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "access_type")
    private boolean accessType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "regToken")
    private String regToken;
    @OneToMany(mappedBy = "userId")
    private Collection<PasswordReset> passwordResetCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "uploaderName")
    private Collection<Content> contentCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String email, String password, Date registrationDate, Date birthDate, boolean accessType, boolean isDeleted, String regToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.birthDate = birthDate;
        this.accessType = accessType;
        this.isDeleted = isDeleted;
        this.regToken = regToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean getAccessType() {
        return accessType;
    }

    public void setAccessType(boolean accessType) {
        this.accessType = accessType;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRegToken() {
        return regToken;
    }

    public void setRegToken(String regToken) {
        this.regToken = regToken;
    }

    public Collection<PasswordReset> getPasswordResetCollection() {
        return passwordResetCollection;
    }

    public void setPasswordResetCollection(Collection<PasswordReset> passwordResetCollection) {
        this.passwordResetCollection = passwordResetCollection;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Collection<Content> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Collection<Content> contentCollection) {
        this.contentCollection = contentCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.Model.User[ id=" + id + " ]";
    }
    public String addnewUser(User u){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        int length = 40;
        boolean useLetters = true;
        boolean useNumbers = true;
//        String token = RandomStringUtils.random(length, useLetters, useNumbers);

 try{
            //Create SPQ and run it
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewUser");
            
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("birth_dateIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tokenIN", String.class, ParameterMode.IN);
            
            
            
            spq.setParameter("nameIN", u.getName());
            spq.setParameter("emailIN", u.getEmail());
            spq.setParameter("passwordIN", u.getPassword());
            spq.setParameter("birth_dateIN", u.getBirthDate());
            spq.setParameter("tokenIN", regToken);
            
            spq.execute();
            return "Succesfully registerd!";
        }
        catch(Exception ex){
            //Handle database exceptions
            if(ex.getMessage().equals("org.hibernate.exception.ConstraintViolationException: Error calling CallableStatement.getMoreResults")){
                return "Some unique value is duplicate!";
            }
            return ex.getMessage();
        }
        finally{
            //clean up metods, and close connections
            em.clear();
            em.close();
            emf.close();
        }
    }
     public static boolean validatePassword(String pw) throws PasswordException {
        // 8 character
        
        if(pw.length() < 8){
            throw new PasswordException("The password is not long enough");
            // return false;
        }
        // lowercase letter
        else if(!pw.matches(".*[a-z].*")){
            throw new PasswordException("It must be a lowercase character");
        }
        // uppercase letter
        else if(!pw.matches(".*[A-Z].*")){
            throw new PasswordException("It must be a uppercase character");
        }
        // number
        else if(!pw.matches(".*[0-9].*")){
            throw new PasswordException("It must be a numeric character");
        }
        // Special character
        else if(!pw.matches(".*[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*")){
            throw new PasswordException("It must be a special character");
        }
        else{
            return true;
        }
    }
     
    }
