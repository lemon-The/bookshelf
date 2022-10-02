package com.lemonthe.bookshelf.data;

import com.lemonthe.bookshelf.Photo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository
    extends CrudRepository<Photo, Long>{
}
