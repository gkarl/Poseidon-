package com.nnk.springboot.service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    Logger logger = LoggerFactory.getLogger(BidListService.class);

    @Autowired
    BidListRepository bidListRepository;

   public List<BidList> home(){
       logger.info("displays the bidList");
        return bidListRepository.findAll();
    }

    public void validate(BidList bid) {
        logger.info("Save a bid");
        bidListRepository.save(bid);
    }


    public BidList showUpdateForm(Integer bidListId) {
        logger.info("Find by id from bidList");
        return bidListRepository.findById(bidListId).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ bidListId));
    }

    public void updateBid(Integer bidListId, BidList bidList) {
        logger.info("Update a definited bid by id");
        bidList.setBidListId(bidListId);
        bidListRepository.save(bidList);
    }


    public void deleteBid(Integer bidListId) {
        logger.info("Delete a definited bid by id");
        BidList bidList = bidListRepository.findById(bidListId).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + bidListId));
        bidListRepository.delete(bidList);
    }


}

