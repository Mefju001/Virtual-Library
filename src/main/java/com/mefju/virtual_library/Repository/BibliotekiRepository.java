package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Biblioteki;
import com.mefju.virtual_library.Entity.Magazyn;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BibliotekiRepository extends JpaRepository<Biblioteki,String>
{
}
