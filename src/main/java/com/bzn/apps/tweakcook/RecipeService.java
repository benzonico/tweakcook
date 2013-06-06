package com.bzn.apps.tweakcook;
import static spark.Spark.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import spark.*;

public class RecipeService {

	private static final ObjectMapper om = new ObjectMapper();
	
	public static void main(String[] args) {
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
					return  om.writeValueAsString(Lists.newArrayList(new Recipe("Cookies"),new Recipe("Balthazar")));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return "";
			}
		});
		return domain;
	}

}
