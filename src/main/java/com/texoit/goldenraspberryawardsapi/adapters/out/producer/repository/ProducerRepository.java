package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.response.ProducerIntervalsProjection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProducerRepository extends JpaRepository<ProducerEntity, UUID> {

    @Cacheable(value="producers", unless = "#result == null")
    Optional<ProducerEntity> findByName(String name);

    @Query(
            value = """
                    WITH ProducerIntervals AS (
                    	SELECT
                    	    P.NAME AS PRODUCER,
                    	    (OM.PRODUCTIONYEAR - M.PRODUCTIONYEAR) AS YEARINTERVAL,
                    	    M.PRODUCTIONYEAR AS PREVIOUSWIN,
                    	    OM.PRODUCTIONYEAR AS FOLLOWINGWIN
                    	FROM
                    	    PRODUCER P
                    	JOIN
                    	    MOVIE_PRODUCER MP1 ON P.ID = MP1.PRODUCER_ID
                    	JOIN
                    	    MOVIE M ON MP1.MOVIE_ID = M.ID
                    	JOIN
                    	    MOVIE_PRODUCER MP2 ON P.ID = MP2.PRODUCER_ID
                    	JOIN
                    	    MOVIE OM ON MP2.MOVIE_ID = OM.ID
                    	WHERE
                    	    M.WINNER = TRUE
                    	    AND OM.WINNER = TRUE
                    	    AND OM.PRODUCTIONYEAR > M.PRODUCTIONYEAR
                    	ORDER BY
                    	    YEARINTERVAL ASC
                    )
                    SELECT
                        PRODUCER,
                        YEARINTERVAL,
                        PREVIOUSWIN,
                        FOLLOWINGWIN
                    FROM
                    	ProducerIntervals
                    WHERE
                    	YEARINTERVAL = (
                    		SELECT
                    			MIN(YEARINTERVAL)
                    		FROM
                    			ProducerIntervals
                    	)
                    """,
            nativeQuery = true)
    List<ProducerIntervalsProjection> findMinIntervals();

    @Query(
            value = """
                    WITH ProducerIntervals AS (
                    	SELECT
                            P.NAME AS PRODUCER,
                            (M.PRODUCTIONYEAR - LAG(M.PRODUCTIONYEAR) OVER (PARTITION BY P.NAME ORDER BY M.PRODUCTIONYEAR)) AS YEARINTERVAL,
                            LAG(M.PRODUCTIONYEAR) OVER (PARTITION BY P.NAME ORDER BY M.PRODUCTIONYEAR) AS PREVIOUSWIN,
                            M.PRODUCTIONYEAR AS FOLLOWINGWIN
                    	FROM
                    	    PRODUCER P
                    	JOIN
                    	    MOVIE_PRODUCER MP1 ON P.ID = MP1.PRODUCER_ID
                    	JOIN
                    	    MOVIE M ON MP1.MOVIE_ID = M.ID
                    	WHERE
                    	    M.WINNER = TRUE
                    	ORDER BY
                    	    PRODUCER, M.PRODUCTIONYEAR
                    )
                    SELECT
                        PRODUCER,
                        YEARINTERVAL,
                        PREVIOUSWIN,
                        FOLLOWINGWIN
                    FROM
                    	ProducerIntervals
                    WHERE
                    	YEARINTERVAL = (
                    		SELECT
                    			MAX(YEARINTERVAL)
                    		FROM
                    			ProducerIntervals
                    	)
                    """,
            nativeQuery = true)
    List<ProducerIntervalsProjection> findMaxIntervals();

}
