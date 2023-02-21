package com.example.refreshData.Repository;

import com.example.refreshData.Config.Attribute;
import com.example.refreshData.Config.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {

    //@Query(value = "Select * From `attribute` limit 2", nativeQuery = true)
    List<Attribute> findAttributesByView(View view);
}
