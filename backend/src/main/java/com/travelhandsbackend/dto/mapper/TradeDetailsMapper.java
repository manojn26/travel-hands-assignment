package com.travelhandsbackend.dto.mapper;

import com.travelhandsbackend.dto.TradeDetailsDto;
import com.travelhandsbackend.entity.TradeDetails;

public class TradeDetailsMapper {

//    Map TradeDetailsJPA into TradeDetailsDto
    public static TradeDetailsDto mapTradeDetailsDto(TradeDetails tradeDetails){
        return new TradeDetailsDto(
                tradeDetails.getId(),
                tradeDetails.getTradeDataTime(),
                tradeDetails.getStockName(),
                tradeDetails.getListingPrice(),
                tradeDetails.getQuantity(),
                tradeDetails.getType(),
                tradeDetails.getPricePerUnit()
        );

    }

//    Map TradeDetailsDto into TradeDetailsJPA
    public static TradeDetails mapToTradeDetails(TradeDetailsDto tradeDetailsDto){
        return new TradeDetails(
                tradeDetailsDto.getId(),
                tradeDetailsDto.getTradeDataTime(),
                tradeDetailsDto.getStockName(),
                tradeDetailsDto.getListingPrice(),
                tradeDetailsDto.getQuantity(),
                tradeDetailsDto.getType(),
                tradeDetailsDto.getPricePerUnit()
        );
    }

}
