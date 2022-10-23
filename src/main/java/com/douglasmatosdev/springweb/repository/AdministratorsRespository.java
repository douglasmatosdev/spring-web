package com.douglasmatosdev.springweb.repository;

import com.douglasmatosdev.springweb.models.Administrator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorsRespository extends CrudRepository<Administrator, Integer> {

    @Query(value = "select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END from administrators where id = :id", nativeQuery = true)
    public boolean exist(int id);

    @Query(value="select * from administrators where email = :email or name = :name and password = :password", nativeQuery = true)
    public Administrator Login(String name, String email, String password);
}
