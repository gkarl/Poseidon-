package com.nnk.springboot.service;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


/**
 * BidList Service use CRUD BidListRepository methods to communicate whith bidlist table
 * @see BidListRepository
 */
@Service
public class BidListService {

    Logger logger = LoggerFactory.getLogger(BidListService.class);

    @Autowired
    BidListRepository bidListRepository;

    /**
     * Query all the rows of the table bidlist table
     * @return all bidlist items
     */
   public List<BidList> home(){
       logger.info("displays the bidList");
        return bidListRepository.findAll();
    }

    /**
     * Create new bid
     * @param bid item to save
     */
    public void validate(BidList bid) {
        logger.info("Save a bid");
        bidListRepository.save(bid);
    }

    /**
     * Show edit form for one bid using it's id
     * @param bidListId Item id
     * @return Item whith this id
     */
    public BidList showUpdateForm(Integer bidListId) {
        logger.info("Find by id from bidList");
        return bidListRepository.findById(bidListId).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ bidListId));
    }

    /**
     * Update bid by id
     * @param bidListId Item id
     * @param bidList item to save
     */
    public void updateBid(Integer bidListId, BidList bidList) {
        logger.info("Update a definited bid by id");
        bidList.setBidListId(bidListId);
        bidListRepository.save(bidList);
    }

    /**
     * Delete bid by id
     * @param bidListId Item id
     */
    public void deleteBid(Integer bidListId) {
        logger.info("Delete a definited bid by id");
        BidList bidList = bidListRepository.findById(bidListId).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + bidListId));
        bidListRepository.delete(bidList);
    }


}

