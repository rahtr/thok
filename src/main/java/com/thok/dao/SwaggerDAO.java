/**
 *
 */
package com.thok.dao;

/**
 * @author Rahul Tripathi
 *
 * thok(Two Hundred OK)
 */
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.thok.converter.SwaggerConverter;
import com.thok.model.ImportSwagger;


public class SwaggerDAO {

	private DBCollection col;

	@SuppressWarnings("deprecation")
	public SwaggerDAO(MongoClient mongo) {

		//this.col = (DBCollection) mongo.getDatabase("thok").getCollection("swagger");
		this.col = mongo.getDB("thok").getCollection("swagger");
	}

	public ImportSwagger createSwagger(ImportSwagger i) {
		DBObject doc = SwaggerConverter.toDBObject(i);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		i.setId(id.toString());
		return i;
	}

	public void updateSwagger(ImportSwagger i) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(i.getId())).get();
		this.col.update(query, SwaggerConverter.toDBObject(i));
	}

	public List<ImportSwagger> readAllSwaggers() {
		List<ImportSwagger> data = new ArrayList<ImportSwagger>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			ImportSwagger i = SwaggerConverter.toImportSwagger(doc);
			data.add(i);
		}
		return data;
	}

	public void deleteSwagger(ImportSwagger i) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(i.getId())).get();
		this.col.remove(query);
	}

	public ImportSwagger readSwagger(ImportSwagger i) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(i.getId())).get();
		DBObject data = this.col.findOne(query);
		return SwaggerConverter.toImportSwagger(query);
	}


}
