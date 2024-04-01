package com.travelhandsbackend.service.impl;

import com.travelhandsbackend.dto.TradeDetailsDto;
import com.travelhandsbackend.dto.mapper.TradeDetailsMapper;
import com.travelhandsbackend.entity.TradeDetails;
import com.travelhandsbackend.repository.ResourceNotFoundException;
import com.travelhandsbackend.repository.TradeDetailsRepository;
import com.travelhandsbackend.service.TradeDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TradeDetailsServiceImpl implements TradeDetailsService {

    private TradeDetailsRepository tradeDetailsRepository;


    @Override
    public TradeDetailsDto createTradeDetails(TradeDetailsDto tradeDetailsDto) {
        TradeDetails tradeDetails = TradeDetailsMapper.mapToTradeDetails(tradeDetailsDto);
        TradeDetails saveTradeDetails =tradeDetailsRepository.save(tradeDetails);

        return TradeDetailsMapper.mapTradeDetailsDto(saveTradeDetails);
    }

    @Override
    public TradeDetailsDto getTradeDetailsById(Long id) {
        TradeDetails tradeDetails = tradeDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no data match with this Id"));

        return TradeDetailsMapper.mapTradeDetailsDto(tradeDetails);
    }

    @Override
    public List<TradeDetailsDto> getAllTradeDetails() {
        List<TradeDetails> allTradeDetails = tradeDetailsRepository.findAll();

        return  allTradeDetails.stream().map((trade) -> TradeDetailsMapper.mapTradeDetailsDto(trade)).collect(Collectors.toList());

    }

    @Override
    public TradeDetailsDto updateTradeDetails(Long id, TradeDetailsDto tradeDetailsDto) {

        TradeDetails tradeDetails = tradeDetailsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("There is no data match with this Id")
        );

        tradeDetails.setTradeDataTime(tradeDetails.getTradeDataTime());
        tradeDetails.setStockName(tradeDetails.getStockName());
        tradeDetails.setListingPrice(tradeDetails.getListingPrice());
        tradeDetails.setQuantity(tradeDetails.getQuantity());
        tradeDetails.setType(tradeDetailsDto.getType());
        tradeDetails.setPricePerUnit(tradeDetails.getPricePerUnit());

        TradeDetails updatedTradeDetails = tradeDetailsRepository.save(tradeDetails);


        return TradeDetailsMapper.mapTradeDetailsDto(updatedTradeDetails);
    }

    @Override
    public void deleteTradeDetails(Long id) {
        TradeDetails tradeDetails = tradeDetailsRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException("There is no data match with this Id")
        );

        tradeDetailsRepository.deleteById(id);

    }
}
