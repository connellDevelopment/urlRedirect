package com.url.shorten.repository;

import com.url.shorten.model.UrlModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * URL persistence operations
 */
@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Long> {

    /**
     *
     * @param longUrl
     * @param shortUrl
     * @return
     */
    List<UrlModel> findByShortUrlAndLongUrl(String longUrl, String shortUrl);

    /**
     *
     * @param shortUrl
     * @return
     */
    Optional<UrlModel> findByShortUrl(String shortUrl);

    /**
     *
     * @param longUrl
     * @return
     */
    Optional<UrlModel> findByLongUrl(String longUrl);

}
