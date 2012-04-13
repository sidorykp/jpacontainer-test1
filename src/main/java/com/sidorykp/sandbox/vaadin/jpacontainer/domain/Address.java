package com.sidorykp.sandbox.vaadin.jpacontainer.domain;

import javax.persistence.Embeddable;
import javax.persistence.Version;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 4/4/12
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class Address {
    // TODO there is no ID in this class. As a result, when a new Address is created from GUI where some of the fields are NULL
    // then duplicate Address records are created (Hibernate uses such statements that result in duplicate addresses
    // there is no problem when EclipseLink is used
    private String street;
    private String city;
    private String zipCode;

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
}
