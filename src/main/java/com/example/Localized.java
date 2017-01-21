package com.example;

import java.io.Serializable;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public interface Localized extends Serializable {

    String getValue();

    void setValue(String value);
}
