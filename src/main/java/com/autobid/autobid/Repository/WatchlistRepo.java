package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchlistRepo extends JpaRepository<watchlist, Integer> {
    List<watchlist> findByUserId(int userId);

    Optional<watchlist> findByUserIdAndListingId(int userId, int listingId);
}
