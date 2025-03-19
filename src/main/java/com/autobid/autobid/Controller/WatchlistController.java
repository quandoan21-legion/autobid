package com.autobid.autobid.Controller;

import com.autobid.autobid.DTO.WatchlistListingDTO;
import com.autobid.autobid.DTO.WatchlistRequestDTO;
import com.autobid.autobid.Entity.car_images;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Entity.watchlist;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.WatchlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistRepo watchlistRepo;

    @Autowired
    private com.autobid.autobid.Repository.CarInformationRepo carInformationRepo;

    @PostMapping("/add")
    public MessageFactory addToWatchlist(@RequestBody WatchlistRequestDTO request) {
        // Check if the listing exists
        Optional<car_information> listingOpt = carInformationRepo.findById(request.getListing_id());
        if (listingOpt.isEmpty()) {
            return new MessageFactory().MessageResponse("Listing not found", false, List.of());
        }

        // Check if the listing is already in the user's watchlist
        Optional<watchlist> existingWatchlistItem = watchlistRepo.findByUserIdAndListingId(request.getUser_id(), request.getListing_id());
        if (existingWatchlistItem.isPresent()) {
            return new MessageFactory().MessageResponse("Listing is already in the watchlist", false, List.of());
        }

        // Add the listing to the watchlist
        watchlist newWatchlistItem = new watchlist();
        newWatchlistItem.setUserId(request.getUser_id());
        newWatchlistItem.setListingId(request.getListing_id());
        watchlistRepo.save(newWatchlistItem);

        // Fetch the updated watchlist for the user
        List<watchlist> userWatchlist = watchlistRepo.findByUserId(request.getUser_id());

        // Convert each watchlist item to WatchlistListingDTO
        List<WatchlistListingDTO> watchlistListingDTOs = userWatchlist.stream()
                .map(item -> {
                    car_information carInfo = carInformationRepo.findById(item.getListingId()).orElse(null);
                    return (carInfo != null) ? new WatchlistListingDTO(carInfo) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new MessageFactory().MessageResponse("Listing added to the watchlist", true, watchlistListingDTOs);
    }

    @DeleteMapping("/remove")
    public MessageFactory removeFromWatchlist(@RequestBody WatchlistRequestDTO request) {
        // Check if the listing exists in the user's watchlist
        Optional<watchlist> watchlistItem = watchlistRepo.findByUserIdAndListingId(request.getUser_id(), request.getListing_id());
        if (watchlistItem.isEmpty()) {
            return new MessageFactory().MessageResponse("Listing not found in watchlist", false, List.of());
        }

        // Remove the listing from the watchlist
        watchlistRepo.delete(watchlistItem.get());

        // Fetch the updated watchlist for the user
        List<watchlist> userWatchlist = watchlistRepo.findByUserId(request.getUser_id());

        // Convert each watchlist item to WatchlistListingDTO
        List<WatchlistListingDTO> watchlistListingDTOs = userWatchlist.stream()
                .map(item -> {
                    car_information carInfo = carInformationRepo.findById(item.getListingId()).orElse(null);
                    return (carInfo != null) ? new WatchlistListingDTO(carInfo) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new MessageFactory().MessageResponse("Listing removed from watchlist", true, watchlistListingDTOs);
    }

    @GetMapping("/user")
    public MessageFactory getWatchlistByUser(@RequestParam("user_id") int userId) {
        // Fetch the user's watchlist
        List<watchlist> watchlistItems = watchlistRepo.findByUserId(userId);

        // Convert each watchlist item to WatchlistListingDTO
        List<WatchlistListingDTO> watchlistListingDTOs = watchlistItems.stream()
                .map(item -> {
                    car_information carInfo = carInformationRepo.findById(item.getListingId()).orElse(null);
                    return (carInfo != null) ? new WatchlistListingDTO(carInfo) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new MessageFactory().MessageResponse("Watchlist retrieved successfully", true, watchlistListingDTOs);
    }

}
