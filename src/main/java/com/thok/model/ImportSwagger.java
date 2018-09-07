package com.thok.model;
/**
 * @author Rahul Tripathi
 *
 * thok(Two Hundred OK)
 */
public class ImportSwagger {

	private String swagger;

	private String swaggername;

	private String id;

	public String getSwaggerName() {
		return swaggername;
	}

	public void setSwaggerName(String swaggername) {
		this.swaggername = swaggername;
	}

	public String getSwagger(){
		return swagger;
	}

	public void setSwagger(String swagger){
		this.swagger=swagger;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

