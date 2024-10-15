package com.jianan.demomodule.service;

import jakarta.annotation.Resource;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: jn
 * @Date: 2024/10/12
 * @description
 **/
@Service
public class ActivitiService {
    @Resource
    private ProcessEngine processEngine;
    
    public void start(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessInstance leave = processEngine.getRuntimeService().startProcessInstanceByKey("leave");
    }
}
