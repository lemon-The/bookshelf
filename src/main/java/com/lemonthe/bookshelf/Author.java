package com.lemonthe.bookshelf;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTHORS")
public class Author implements Serializable {
    @Id
    @SequenceGenerator(name = "a_s", 
        sequenceName = "AUTHOR_SEQUENCE", 
        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a_s")
    @Column(nullable = false)
    private Long id;
    @NotBlank(message = "Author name is required")
    private String name;
    @NotNull(message = "Author birthday is required")
    private LocalDate birthDay;
    ////////////////////////////////////////////////////////////
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
    ////////////////////////////////////////////////////////////
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public LocalDate getBirthDay() {
        return this.birthDay;
    }
    ////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        Author other = (Author)otherObject;
        return id == other.id
            && Objects.equals(name, other.name)
            && Objects.equals(birthDay, other.birthDay);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDay);
    }
    @Override
    public String toString() {
        return getClass().getName() + "[id=" + id
            + ", name=" + name
            + ", birthDay=" + birthDay + "]";
    }
}
