package com.lemonthe.bookshelf;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lemonthe.bookshelf.data.PathByStringConverter;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Entity
@Table(name = "PHOTOS")
public class Photo {
    @Id
    @SequenceGenerator(name = "p_s",
        sequenceName = "PHOTO_SEQUENCE",
        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p_s")
    private Long id;
    //@Column(name = "PHOTO_PATH")
    //@Convert(converter = PathByStringConverter.class)
    //private Path path;
    @Column(name = "PHOTO_DATA")
    private byte[] data;
    @Transient
    private Logger logger = LoggerFactory.getLogger(Photo.class);
    ////////////////////////////////////////////////////////////
    public void setId(Long id) {
        this.id = id;
    }
    //public void setPath(Path path) {
    //    this.path = path;
    //}
    public void setData(byte[] data) {
        this.data = data;
    }
    ////////////////////////////////////////////////////////////
    public Long getId() {
        return this.id;
    }
    //@Column(name = "PHOTO_PATH")
    //public Path getPath() {
    //    return this.path;
    //}
    public byte[] getData() {
        return this.data;
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
        Photo other = (Photo)otherObject;
        return Objects.equals(id, other.id)
    //        && Objects.equals(path, other.path);
            && Arrays.equals(data, other.data);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }
    @Override
    public String toString() {
        return getClass().getName() + "[id=" + id
            + /*", path=" + path + */"]";
    }
}
