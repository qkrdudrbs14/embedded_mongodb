package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
// import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

@DataMongoTest
// @SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@DisplayName("given object to save"
		+ " when save object using MongoDB template"
		+ " then object is saved"
	)
	@Test
	public void test(@Autowired MongoTemplate mongoTemplate) {

		// given 
		DBObject objectToSave = BasicDBObjectBuilder.start()
			.add("son", "mori")
			.get();

		// when 
		mongoTemplate.save(objectToSave, "family");

		// then
		List<DBObject> resultList = mongoTemplate.findAll(DBObject.class, "family");

		resultList.forEach(t -> {
			System.out.println(t.toString());
		});
	}

}
