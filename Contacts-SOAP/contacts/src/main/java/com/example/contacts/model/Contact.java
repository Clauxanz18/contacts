package com.example.contacts.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ks_Contact")
public class Contact{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column
    private String lastName;

    @Column
    private String firstName;
    
    @Column
    private String email;
    
    @Column
    private boolean favorite;
    
    @OneToMany(mappedBy = "contact", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Telephone> phoneNumbers;
    
    @ManyToOne
    @JoinColumn(name="group_ID")
    private Group group;

    
    
    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return List<String> return the phoneNumbers
     */
    public List<Telephone> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * @param phoneNumbers the phoneNumbers to set
     */
    public void setPhoneNumbers(List<Telephone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Group return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return boolean return the favorite
     */
    public boolean isFavorite() {
        return favorite;
    }

    /**
     * @param favorite the favorite to set
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    

    public Contact() {
      
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}