package com.vikmir.nasa.worker;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class Caching {

    @Scheduled (cron = "@daily")
    @CacheEvict(cacheNames = "largestNasaPicture")
    public void clearCache(){
        log.info("Cache 'largestNasaPicture' was cleared.");
    }

}
