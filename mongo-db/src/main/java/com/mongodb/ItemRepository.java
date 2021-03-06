package com.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

    //쿼리 필터링 필드
    @Query(value = "{name:'?0'}")
    Item findItemByName(String name);
    // findGroceryItemByName >> findItemByName 해도 되는건가?

    // 카테고리 필드를 이용해서 모든값을 가져옴,
    @Query(value = "{category:'?0'}", fields = "{'name':1, 'quantity':1}")
    List<Item> findAllByCategory(String category);

    @Override
    long count();

}
