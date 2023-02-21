package com.example.refreshData.Repository;

import com.example.refreshData.Config.View;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<View, Long> {

    View findViewByName(String name);

}
