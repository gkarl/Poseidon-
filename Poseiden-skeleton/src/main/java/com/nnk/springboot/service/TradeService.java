package com.nnk.springboot.service;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Trade Service use CRUD TradeRepository methods to communicate whith trade table
 * @see TradeRepository
 */
@Service
public class TradeService {

    Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeRepository tradeRepository;

    /**
     * Query all the rows of the table trade table
     * @return all Trade items
     */
    public List<Trade> home(){
        logger.info("displays the trade");
        return tradeRepository.findAll();
    }

    /**
     * Create new trade
     * @param trade item to save
     */
    public void validate(Trade trade) {
        logger.info("Save a trade");
        tradeRepository.save(trade);
    }

    /**
     * Show edit form for one trade using it's id
     * @param id Item id
     * @return Item whith this id
     */
    public Trade showUpdateForm(Integer id) {
        logger.info("Find by id from trade");
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    /**
     * Update trade by id
     * @param id Item id
     * @param trade item to save
     */
    public void updateTrade(Integer id, Trade trade) {
        logger.info("Update a definited trade by id");
        trade.setTradeId(id);
        tradeRepository.save(trade);
    }

    /**
     * Delete trade by id
     * @param id Item id
     */
    public void deleteTrade(Integer id) {
        logger.info("Delete a definited trade by id");
        Trade trade = tradeRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        tradeRepository.delete(trade);
    }


}
