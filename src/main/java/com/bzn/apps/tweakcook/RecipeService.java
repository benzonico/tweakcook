package com.bzn.apps.tweakcook;
import static spark.Spark.get;
import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;
import spark.Request;
import spark.Response;
import spark.Route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public class RecipeService {

	private static final ObjectMapper om = new ObjectMapper();
	private static RecipeDao recipeDao;
	
	
	public static void main(String[] args) {
		recipeDao = new RecipeDao();
		staticFileLocation("/public");
		setPort(Integer.parseInt(System.getenv("PORT")));
		getRecipes();
	}
	
	public static String getRecipes(){
		String domain = "/recipe"; 
		get(new Route(domain){
			@Override
			public Object handle(Request request, Response response) {
				response.type("application/json");
				try {
					return  om.writeValueAsString(Lists.newArrayList(recipeDao.retrieveAll()));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return "";
			}
		});
		return domain;
	}
	
	
	

}
