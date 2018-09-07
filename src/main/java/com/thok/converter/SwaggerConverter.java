/**
 *
 */
package com.thok.converter;

import com.thok.model.ImportSwagger;

public class SwaggerConverter {

	// convert Swagger Object to MongoDB DBObject
			// take special note of converting id String to ObjectId
			public static DBObject toDBObject(ImportSwagger i) {

				BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
						.append("swaggername", i.getSwaggerName()).append("swagger", i.getSwagger());
				//if (i.getId() != null)
					//builder = builder.append("_id", new ObjectId(i.getId()));
				return builder.get();
			}

			// convert DBObject Object to Swagger
			// take special note of converting ObjectId to String
			public static ImportSwagger toImportSwagger(DBObject doc) {
				ImportSwagger i = new ImportSwagger();
				i.setSwaggerName((String) doc.get("swaggername"));
				i.setSwagger((String) doc.get("swagger"));
				ObjectId id = (ObjectId) doc.get("_id");
				i.setId(id.toString());
				return i;

			}
}
