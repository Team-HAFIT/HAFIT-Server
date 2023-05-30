package com.feedback.hafit.service;

import com.feedback.hafit.entity.SetExecDTO;
import com.feedback.hafit.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetService {

    @Autowired
    SetRepository setRepository;

    public SetExecDTO execute(SetExecDTO setExecDTO) {
        return null;
    }
}
