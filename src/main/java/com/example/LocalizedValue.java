package com.example;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Access(AccessType.FIELD)
@Embeddable
public class LocalizedValue implements Localized {

    @Column(name = "val")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
