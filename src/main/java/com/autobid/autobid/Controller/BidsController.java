package com.autobid.autobid.Controller;

import com.autobid.autobid.DTO.BidRequestDTO;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BidsController {
    @Autowired
    private BidService bidsService;

    @PostMapping("/listings/add-bid/{car_id}")
    public MessageFactory placeBid(@PathVariable("car_id") Integer carId, @RequestBody BidRequestDTO bidRequest) {
        return bidsService.placeBid(carId, bidRequest.getUser_id(), bidRequest.getBid_amount());
    }
}
