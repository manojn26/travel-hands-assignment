package com.travelhandsbackend.controller;

import com.travelhandsbackend.dto.TradeDetailsDto;
import com.travelhandsbackend.service.TradeDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TradeDetailsController {

    @Autowired
    private TradeDetailsService tradeDetailsService;


    @PostMapping("/create-trade")
    public ResponseEntity<TradeDetailsDto> createTradeDetails(@RequestBody TradeDetailsDto tradeDetailsDto){

        TradeDetailsDto savedTradeDetails = tradeDetailsService.createTradeDetails(tradeDetailsDto);

        return new ResponseEntity<>(savedTradeDetails, HttpStatus.CREATED);

    }


//    Get Trade Details By Id
    @GetMapping("get-single-trade/{id}")
    public ResponseEntity<TradeDetailsDto> getTradeDetailsById(@PathVariable("id") Long id){
        TradeDetailsDto tradeDetailsDto = tradeDetailsService.getTradeDetailsById(id);
        return ResponseEntity.ok(tradeDetailsDto);
    }


//    Get All Trade Details
    @GetMapping("/all-trades")
    public ResponseEntity<List<TradeDetailsDto>> getAllTradeDetails(){
        List<TradeDetailsDto> allTradeDetails = tradeDetailsService.getAllTradeDetails();

        return ResponseEntity.ok(allTradeDetails);
    }

//    Update TradeDetails By Id
    @PutMapping("/update-trade/{id}")
    public ResponseEntity<TradeDetailsDto> updateTradeDetails(@PathVariable("id") Long id, @RequestBody TradeDetailsDto updatedTradeDetails){
        TradeDetailsDto tradeDetailsDto = tradeDetailsService.updateTradeDetails(id,updatedTradeDetails);
        return ResponseEntity.ok(tradeDetailsDto);
    }

    @DeleteMapping("/delete-trade/{id}")
    public ResponseEntity<String> deleteTradeDetails(@PathVariable("id") Long id){
    tradeDetailsService.deleteTradeDetails(id);
    return ResponseEntity.ok("Trade Details Deleted Successfully");

    }
}
