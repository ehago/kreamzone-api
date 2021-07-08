package com.ehago.kreamzone.repository;

import com.ehago.kreamzone.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    @Query(nativeQuery = true, value =
            "SELECT i.*, br.*, b.immediately_purchase_price " +
                    "FROM kream.item i " +
                    "INNER JOIN " +
                    "kream.brand br " +
                    "on br.brand_id = i.brand_id " +
                    "LEFT JOIN " +
                    "( " +
                    "SELECT item_id, MIN(price) AS immediately_purchase_price " +
                    "FROM kream.bid " +
                    "GROUP BY item_id " +
                    ") b " +
                    "ON i.item_id = b.item_id " +
                    "ORDER BY release_date DESC " +
                    "LIMIT 16"
    )
    List<Item> getDroppedItems();

}
