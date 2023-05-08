/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mycompany.memeparadis.Configuration.Database;
import com.mycompany.memeparadis.Exception.PasswordException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javax.persistence.Transient;
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

    @OneToMany(mappedBy = "userId")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "uploaderName")
    private Collection<Content> contentCollection;
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
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
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
    @Transient
    @JsonInclude
    private String currentPw;
    @Transient
    @JsonInclude
    private String newPw;

    public String getCurrentPw() {
        return currentPw;
    }

    public void setCurrentPw(String currentPw) {
        this.currentPw = currentPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

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
    public String encryptString(String input) throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("MD5");
        
        byte[] messageDigest=md.digest(input.getBytes());
        BigInteger bigInt =new BigInteger(1,messageDigest);
        return bigInt.toString(16);
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
            spq.setParameter("passwordIN", encryptString(u.getPassword()));
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
                Integer idd= Integer.parseInt(r[0].toString());
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
            Integer idd = Integer.parseInt(r[0].toString());
            String namee = r[1].toString();
            String emaile = r[2].toString();
            String passworde = r[3].toString();
            Date registrationDatee =Timestamp.valueOf(r[4].toString());
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date birthDatee = formatter.parse(r[5].toString());
            
            Boolean accessTypee = Boolean.valueOf(r[6].toString());
            
            User user=new User(idd,namee,emaile,passworde,registrationDatee,birthDatee,accessTypee,false);
            

            return user;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            throw new Exception(""+ex.getMessage());

        }
        finally{
            em.clear();
            em.close();
            emf.close();
            
        }
        
}

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Content> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Collection<Content> contentCollection) {
        this.contentCollection = contentCollection;
    }
    public String updateUserName(String username,Integer id) throws Exception{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
    EntityManager em = emf.createEntityManager();
    try{
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateUserName");
         spq.registerStoredProcedureParameter("usernameIN", String.class, ParameterMode.IN);
         spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        
         spq.setParameter("usernameIN",username );
         spq.setParameter("idIN", id);
         
         spq.execute();
         
         return "Sikeres módosítás";
    }catch(Exception ex){
          System.out.println(ex.getMessage());
           throw new Exception(""+ex.getMessage());
    }finally{
        em.clear();
        em.close();
        emf.close();
    }
    
    }
    public String updateEmail(String email,Integer id) throws Exception{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
    EntityManager em = emf.createEntityManager();
    try{
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateEmail");
         spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
         spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        
         spq.setParameter("emailIN",email );
         spq.setParameter("idIN", id);
         
         spq.execute();
         
         return "Sikeres módosítás";
    }catch(Exception ex){
          System.out.println(ex.getMessage());
           throw new Exception(""+ex.getMessage());
    }finally{
        em.clear();
        em.close();
        emf.close();
    }
}
    
   
    public String updatePassword(String currentPw, String newPw, Integer id) throws Exception{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
    EntityManager em = emf.createEntityManager();
    try{
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updatePassword");
        spq.registerStoredProcedureParameter("currentPwIN", String.class, ParameterMode.IN);       
        spq.registerStoredProcedureParameter("newPwIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("result", String.class, ParameterMode.OUT);
        
        spq.setParameter("currentPwIN", encryptString(currentPw));
        spq.setParameter("newPwIN",newPw);
        spq.setParameter("idIN",id);
        
        spq.execute();
        String result = (String) spq.getOutputParameterValue("result");
        return result;
    }catch(Exception ex){
        System.out.println(ex.getMessage());
        throw new Exception(""+ex.getMessage());
    }finally{
        em.clear();
        em.close();
        emf.close();
    }
    }
    public String updateBirthDate(Date date,Integer id) throws Exception{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
    EntityManager em = emf.createEntityManager();
    try{
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateBirthDate");
         spq.registerStoredProcedureParameter("birthDateIN", Date.class, ParameterMode.IN);
         spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
        
         spq.setParameter("birthDateIN",date );
         spq.setParameter("idIN", id);
         
         spq.execute();
         
         return "Sikeres módosítás";
    }catch(Exception ex){
          System.out.println(ex.getMessage());
           throw new Exception(""+ex.getMessage());
    }finally{
        em.clear();
        em.close();
        emf.close();
    }
}
    public String deleteUser(Integer id,String currentPw) throws Exception{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
    EntityManager em = emf.createEntityManager();
    try{
    StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser");
    
    spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
    spq.registerStoredProcedureParameter("currentPwIN", String.class, ParameterMode.IN);
    spq.registerStoredProcedureParameter("result", String.class, ParameterMode.OUT);
    
    spq.setParameter("idIN",id);
    spq.setParameter("currentPwIN", encryptString(currentPw));
    
    spq.execute();
    String result = (String) spq.getOutputParameterValue("result");
    return result;
    }catch(Exception ex){
    System.out.println(ex.getMessage());
    throw new Exception(""+ex.getMessage());
    }finally{
    em.clear();
    em.close();
    emf.close();
    }
    }
}