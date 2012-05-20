package com.sidorykp.sandbox.vaadin.jpacontainer.util;

/**
 * Created with IntelliJ IDEA.
 * User: pawel
 * Date: 5/19/12
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ErrorCode {
    GENERAL_UNCAUGHT("1"),
    CONTAINER_REFRESH ("1000");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return code;
    }
}
