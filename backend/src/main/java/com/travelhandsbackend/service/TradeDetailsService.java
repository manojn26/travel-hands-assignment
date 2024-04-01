package com.travelhandsbackend.service;

import com.travelhandsbackend.dto.TradeDetailsDto;

import java.util.List;

public interface TradeDetailsService {

    TradeDetailsDto createTradeDetails(TradeDetailsDto tradeDetailsDto);

    TradeDetailsDto getTradeDetailsById(Long id);

    List<TradeDetailsDto> getAllTradeDetails();

    TradeDetailsDto updateTradeDetails(Long id, TradeDetailsDto tradeDetailsDto);

    void deleteTradeDetails(Long id);

}
