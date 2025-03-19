package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.bids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BidsRepo extends JpaRepository<bids, Integer> {
    @Query("SELECT b FROM bids b WHERE b.auction_id = :auctionId ORDER BY b.bid_amount DESC LIMIT 1")
    Optional<bids> findHighestBidByAuctionId(@Param("auctionId") Integer auctionId);
}
