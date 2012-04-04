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
