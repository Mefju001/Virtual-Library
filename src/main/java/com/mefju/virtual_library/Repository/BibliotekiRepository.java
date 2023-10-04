package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Biblioteki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BibliotekiRepository extends JpaRepository<Biblioteki,String>
{
}
