package com.nnk.springboot.service;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Curvepoint Service use CRUD CurvePointRepository methods to communicate whith curvepoint table
 * @see CurvePointRepository
 */
@Service
public class CurvePointService {

    Logger logger = LoggerFactory.getLogger(CurvePointService.class);

    @Autowired
    CurvePointRepository curvePointRepository;

    /**
     * Query all the rows of the table curvePoint table
     * @return all CurvePoint items
     */
    public List<CurvePoint> home(){
        logger.info("displays the curvePoint");
        return curvePointRepository.findAll();
    }

    /**
     * Create new curvePoint
     * @param curvePoint item to save
     */
    public void validate(CurvePoint curvePoint) {
        logger.info("Save a curvePoint");
        curvePointRepository.save(curvePoint);
    }

    /**
     * Show edit form for one curvePoint using it's id
     * @param curvePointId Item id
     * @return Item whith this id
     */
    public CurvePoint showUpdateForm(Integer curvePointId) {
        logger.info("Find by id from curvePoint");
        return curvePointRepository.findById(curvePointId).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ curvePointId));
    }

    /**
     * Update curvePoint by id
     * @param curvePointId Item id
     * @param curvePoint item to save
     */
    public void updateCurvePoint(Integer curvePointId, CurvePoint curvePoint) {
        logger.info("Update a definited curvePoint by id");
        curvePoint.setId(curvePointId);
        curvePointRepository.save(curvePoint);
    }

    /**
     * Delete curvePoint by id
     * @param curvePointId Item id
     */
    public void deleteCurvePoint(Integer curvePointId) {
        logger.info("Delete a definited curvePoint by id");
        CurvePoint curvePoint = curvePointRepository.findById(curvePointId).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + curvePointId));
        curvePointRepository.delete(curvePoint);
    }

}
