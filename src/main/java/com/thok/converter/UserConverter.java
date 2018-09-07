package com.thok.converter;
import com.thok.model.Users;


public class UserConverter {

	// convert Person Object to MongoDB DBObject
		// take special note of converting id String to ObjectId
		public static DBObject toDBObject(Users u) {

			BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
					.append("userID", u.getLDAP()).append("password", u.getPassword());
			if (u.getId() != null)
				builder = builder.append("_id", new ObjectId(u.getId()));
			return builder.get();
		}

		// convert DBObject Object to Person
		// take special note of converting ObjectId to String
		public static Users toUsers(DBObject doc) {
			Users u = new Users();
			u.setLDAP((String) doc.get("ldap"));
			u.setPassword((String) doc.get("password"));
			ObjectId id = (ObjectId) doc.get("_id");
			u.setId(id.toString());
			return u;

		}
}
