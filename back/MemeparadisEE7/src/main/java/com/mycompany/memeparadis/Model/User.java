/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.memeparadis.Configuration.Database;
import com.mycompany.memeparadis.Exception.PasswordException;
import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "User.findByIsDeleted", query = "SELECT u FROM User u WHERE u.isDeleted = :isDeleted")})
public class User implements Serializable {
//
//    @OneToMany(mappedBy = "userId")
//    private Collection<PasswordReset> passwordResetCollection;
//    @OneToMany(mappedBy = "userId")
//    private Collection<Comment> commentCollection;
//    @OneToMany(mappedBy = "uploaderName")
//    private Collection<Content> contentCollection;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "access_type")
    private boolean accessType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String email, String password, Date registrationDate, Date birthDate, boolean accessType, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.birthDate = birthDate;
        this.accessType = accessType;
        this.isDeleted = isDeleted;
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
        return "com.mycompany.memeparadisee7.Model.User[ id=" + id + " ]";
    }
     public String addNewUser(User u){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        

 try{
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewUser");
            
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("birth_dateIN", Date.class, ParameterMode.IN);
            
            
            
            spq.setParameter("nameIN", u.getName());
            spq.setParameter("emailIN", u.getEmail());
            spq.setParameter("passwordIN", u.getPassword());
            spq.setParameter("birth_dateIN", u.getBirthDate());
            
            spq.execute();
            return "Succesfully registerd!";
        }
        catch(Exception ex){
          
            if(ex.getMessage().equals("org.hibernate.exception.ConstraintViolationException: Error calling CallableStatement.getMoreResults")){
                return "Some unique value is duplicate!";
            }
            return ex.getMessage();
        }
        finally{
         
            em.clear();
            em.close();
            emf.close();
        }
    }
     public static boolean validatePassword(String pw) throws PasswordException {
    
         
        if(pw.length() < 8){
            throw new PasswordException("The password is not long enough");

        }

        else if(!pw.matches(".*[a-z].*")){
            throw new PasswordException("It must be a lowercase character");
        }

        else if(!pw.matches(".*[A-Z].*")){
            throw new PasswordException("It must be a uppercase character");
        }

        else if(!pw.matches(".*[0-9].*")){
            throw new PasswordException("It must be a numeric character");
        }

        else if(!pw.matches(".*[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*")){
            throw new PasswordException("It must be a special character");
        }
        else{
            return true;
        }
    }
     public static Integer login(String email, String pw) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
            EntityManager em = emf.createEntityManager();

            try{
                StoredProcedureQuery spq = em.createStoredProcedureQuery("login");
                

                spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("pwIN", String.class, ParameterMode.IN);
//                spq.registerStoredProcedureParameter("idOUT", Integer.class, ParameterMode.OUT);

                spq.setParameter("emailIN", email);
                spq.setParameter("pwIN", pw);
                spq.execute();

                List<Object[]> result = spq.getResultList();
                Object[] r = result.get(0);
                Integer idd=(Integer) r[0];
//                throw new Exception(""+r[0].toString());
                return idd;
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
                throw new Exception(ex.getMessage());
            }
            finally{
     
                em.clear();
                em.close();
                emf.close();
            }
            
    }
     public boolean checkEmailUnique(String email){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try{
    
            StoredProcedureQuery spq = em.createStoredProcedureQuery("checkEmailUnique");
            
            spq.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            
            spq.setParameter("emailIN", email);
            spq.execute();
            Integer result = Integer.parseInt(spq.getOutputParameterValue("result").toString());
            
            return result==0 ? true : false;
            
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        finally{

            em.clear();
            em.close();
            emf.close();
        }
    }

//
//    public void setContentCollection(Collection<Content> contentCollection) {
//        this.contentCollection = contentCollection;
//    }
    public User getUserByID(Integer id) throws Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try{
    
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getUserByID");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("idIN", id);
            spq.execute();
            List<Object[]> result = spq.getResultList();
            Object[] r = result.get(0);
            Integer idd = Integer.valueOf(r[0].toString());
            String name = r[1].toString();
            String email = r[2].toString();
            String password = r[3].toString();
            Date registrationDate =new Date(r[4].toString());
            Date birthDate =new Date(r[5].toString());
            Boolean accessType = Boolean.valueOf(r[6].toString());
            
            User user=new User(idd,name,email,password,registrationDate,birthDate,accessType,false);
            
            throw new Exception(""+id);
//            return user;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new Exception(""+ex.getMessage());
//            return new User();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
            
        }
        
}
}
