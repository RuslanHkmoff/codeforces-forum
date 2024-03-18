package ru.itmo.wp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(
        indexes = {@Index(columnList = "name", unique = true)}
)
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Pattern(regexp = "\\s*([a-zA-Z]+\\s+)*[a-zA-Z]*\\s*", message = "Only lowercase latin letters expected")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) return false;
        if (o == this) return true;
        Tag other = (Tag) o;
        return other.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
