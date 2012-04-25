package com.sidorykp.sandbox.vaadin.jpacontainer.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersonCached {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private int version;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    @ManyToOne
    private PersonCached boss;
    // NOTE cascade=CascadeType.ALL allowed to add AddressEntity entities in a MasterDetailEditor
    // TODO the MasterDetailEditor does not handle the modification and the removal of AddressEntity entities
    @OneToMany(mappedBy = "personCached", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<AddressEntityCached> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PersonCached getBoss() {
        return boss;
    }

    public void setBoss(PersonCached boss) {
        this.boss = boss;
    }

    public Set<AddressEntityCached> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressEntityCached> addresses) {
        this.addresses = addresses;
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PersonCached) {
            PersonCached p = (PersonCached) obj;
            if (this == p) {
                return true;
            }
            if (this.id == null || p.id == null) {
                return false;
            }
            return this.id.equals(p.id);
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

}
