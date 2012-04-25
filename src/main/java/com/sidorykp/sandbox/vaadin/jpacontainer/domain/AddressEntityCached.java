package com.sidorykp.sandbox.vaadin.jpacontainer.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/13/12
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AddressEntityCached {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private int version;

    private String street;
    private String city;
    private String zipCode;
    @ManyToOne
    private PersonCached personCached;

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

    public PersonCached getPersonCached() {
        return personCached;
    }

    public void setPersonCached(PersonCached personCached) {
        this.personCached = personCached;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddressEntityCached) {
            AddressEntityCached p = (AddressEntityCached) obj;
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
