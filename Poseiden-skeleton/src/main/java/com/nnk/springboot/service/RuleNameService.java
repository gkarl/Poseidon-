package com.nnk.springboot.service;


import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RuleName Service use CRUD RuleNameRepository methods to communicate whith ruleName table
 * @see RuleNameRepository
 */
@Service
public class RuleNameService {

    Logger logger = LoggerFactory.getLogger(RuleNameService.class);

    @Autowired
    RuleNameRepository ruleNameRepository;

    /**
     * Query all the rows of the table ruleName table
     * @return all RuleName items
     */
    public List<RuleName> home(){
        logger.info("displays the ruleName");
        return ruleNameRepository.findAll();
    }

    /**
     * Create new ruleName
     * @param ruleName item to save
     */
    public void validate(RuleName ruleName) {
        logger.info("Save a ruleName");
        ruleNameRepository.save(ruleName);
    }

    /**
     * Show edit form for one ruleName using it's id
     * @param id Item id
     * @return Item whith this id
     */
    public RuleName showUpdateForm(Integer id) {
        logger.info("Find by id from ruleName");
        return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    /**
     * Update ruleName by id
     * @param id Item id
     * @param ruleName item to save
     */
    public void updateRuleName(Integer id, RuleName ruleName) {
        logger.info("Update a definited ruleName by id");
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
    }

    /**
     * Delete ruleName by id
     * @param id Item id
     */
    public void deleteRuleName(Integer id) {
        logger.info("Delete a definited ruleName by id");
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        ruleNameRepository.delete(ruleName);
    }

}
