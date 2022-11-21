package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    Logger logger = LoggerFactory.getLogger(CurvePointService.class);

    @Autowired
    CurvePointRepository curvePointRepository;

    public List<CurvePoint> home(){
        logger.info("displays the curvePoint");
        return curvePointRepository.findAll();
    }

    public void validate(CurvePoint curvePoint) {
        logger.info("Save a curvePoint");
        curvePointRepository.save(curvePoint);
    }

    public CurvePoint showUpdateForm(Integer curvePointId) {
        logger.info("Find by id from curvePoint");
        return curvePointRepository.findById(curvePointId).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ curvePointId));
    }

    public void updateBid(Integer curvePointId, CurvePoint curvePoint) {
        logger.info("Update a definited curvePoint by id");
        curvePoint.setId(curvePointId);
        curvePointRepository.save(curvePoint);
    }

    public void deleteBid(Integer curvePointId) {
        logger.info("Delete a definited curvePoint by id");
        CurvePoint curvePoint = curvePointRepository.findById(curvePointId).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + curvePointId));
        curvePointRepository.delete(curvePoint);
    }

}
