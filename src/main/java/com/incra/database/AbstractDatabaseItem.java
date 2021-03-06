package com.incra.database;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The <i>AbstractDatabaseItem</i> class is the superclass of entities.
 *
 * @author Jeffrey Risberg
 * @since 09/10/11
 */
@MappedSuperclass
public abstract class AbstractDatabaseItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Object methods
     */
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "]";
    }

}
