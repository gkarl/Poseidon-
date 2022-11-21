package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    Logger logger = LoggerFactory.getLogger(RuleNameService.class);

    @Autowired
    RuleNameRepository ruleNameRepository;

    public List<RuleName> home(){
        logger.info("displays the ruleName");
        return ruleNameRepository.findAll();
    }

    public void validate(RuleName ruleName) {
        logger.info("Save a ruleName");
        ruleNameRepository.save(ruleName);
    }

    public RuleName showUpdateForm(Integer id) {
        logger.info("Find by id from bidList");
        return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    public void updateRuleName(Integer id, RuleName ruleName) {
        logger.info("Update a definited bid by id");
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Integer id) {
        logger.info("Delete a definited ruleName by id");
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        ruleNameRepository.delete(ruleName);
    }

}
