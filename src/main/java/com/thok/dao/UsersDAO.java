package com.thok.dao;
/**
 * @author Rahul Tripathi
 *
 * thok(Two Hundred OK)
 */
import java.util.ArrayList;
import java.util.List;

import com.thok.converter.UserConverter;
import com.thok.model.Users;


//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key
public class UsersDAO {

	private DBCollection col;

	public UsersDAO(MongoClient mongo) {
		this.col = mongo.getDB("thok").getCollection("Users");
	}

	public Users createUser(Users u) {
		DBObject doc = UserConverter.toDBObject(u);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		u.setId(id.toString());
		return u;
	}

	public void updateUser(Users u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		this.col.update(query, UserConverter.toDBObject(u));
	}

	public List<Users> readAllUsers() {
		List<Users> data = new ArrayList<Users>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Users u = UserConverter.toUsers(doc);
			data.add(u);
		}
		return data;
	}

	public void deleteUser(Users u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		this.col.remove(query);
	}

	public Users readUser(Users u) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(u.getId())).get();
		DBObject data = this.col.findOne(query);
		return UserConverter.toUsers(query);
	}


}
