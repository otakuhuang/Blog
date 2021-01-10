package com.jamhuang.blog.service;

import com.jamhuang.blog.entity.Settings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SettingsService {

    List<Settings> settings();

    Page<Settings> settingsList(Pageable pageable);

    void modifySettingsById(Long id, String value, String remark);
}
