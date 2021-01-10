package com.jamhuang.blog.service.impl;

import com.jamhuang.blog.dao.SettingsRepository;
import com.jamhuang.blog.entity.Settings;
import com.jamhuang.blog.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public List<Settings> settings() {
        return settingsRepository.findAll();
    }

    @Override
    public Page<Settings> settingsList(Pageable pageable) {
        return settingsRepository.findAll(pageable);
    }

    @Override
    public void modifySettingsById(Long id, String value, String remark) {
        settingsRepository.updateSettingsById(id, value, remark);
    }
}
