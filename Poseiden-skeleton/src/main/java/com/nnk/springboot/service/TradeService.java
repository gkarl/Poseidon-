package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    Logger logger = LoggerFactory.getLogger(TradeService.class);

    @Autowired
    TradeRepository tradeRepository;

    public List<Trade> home(){
        logger.info("displays the trade");
        return tradeRepository.findAll();
    }

    public void validate(Trade trade) {
        logger.info("Save a trade");
        tradeRepository.save(trade);
    }

    public Trade showUpdateForm(Integer id) {
        logger.info("Find by id from bidList");
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    public void updateTrade(Integer id, Trade trade) {
        logger.info("Update a definited trade by id");
        trade.setTradeId(id);
        tradeRepository.save(trade);
    }

    public void deleteTrade(Integer id) {
        logger.info("Delete a definited trade by id");
        Trade trade = tradeRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        tradeRepository.delete(trade);
    }


}
