package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Biblioteki;
import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Entity.Magazyn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagazynRepository extends JpaRepository<Magazyn,Integer> {
    @Query(value = "SELECT * FROM inz.magazyn WHERE idksiazki LIKE :id1 AND idbiblioteki LIKE :id2", nativeQuery = true)
    List<Magazyn> FindByID(@Param("id1") int id1, @Param("id2") String id2);


}
