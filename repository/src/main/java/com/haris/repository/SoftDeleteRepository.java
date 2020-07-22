/*
 * Copyright © 2020 Ericsson. A written permission from Ericsson is required to use this software.
 */

package com.haris.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {

    @Override
    @Query("select e from #{#entityName} e where e.deleted=false order by e.createdAt desc")
    List<T> findAll();

    @Override
    @Query("select e from #{#entityName} e where e.id=?1 and e.deleted=false")
    Optional<T> findById(ID id);

    @Override
    @Query("update #{#entityName} e set e.deleted=true where e.id=?1")
    @Modifying
    void deleteById(ID id);

}
