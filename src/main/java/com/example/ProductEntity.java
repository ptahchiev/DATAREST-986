package com.example;

import org.springframework.data.annotation.AccessType;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Entity
@AccessType(AccessType.Type.FIELD)
public class ProductEntity {

    @Id
    private long id;

    @ElementCollection(targetClass = LocalizedValue.class)
    @CollectionTable(name = "product_name_lv", joinColumns = { @JoinColumn(name = "product_id") },
                    indexes = { @Index(name = "idx_product_name_lv", columnList = "product_id") }, foreignKey = @ForeignKey(name = "fk_product_name_lv"))
    @MapKeyColumn(name = "locale")
    private Map<Locale, LocalizedValue> name = new HashMap<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Locale, LocalizedValue> getName() {
        return name;
    }

    public void setName(Map<Locale, LocalizedValue> name) {
        this.name = name;
    }
}
