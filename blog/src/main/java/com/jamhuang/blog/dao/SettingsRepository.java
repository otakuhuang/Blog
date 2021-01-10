package com.jamhuang.blog.dao;

import com.jamhuang.blog.entity.Settings;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SettingsRepository extends JpaRepository<Settings, Long> {

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("UPDATE Settings s SET s.value = ?2, s.remark = ?3 where s.id = ?1")
    void updateSettingsById(Long id, String value, String remark);
}
