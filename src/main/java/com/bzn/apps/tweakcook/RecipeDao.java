package com.bzn.apps.tweakcook;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class RecipeDao {

	private MongoCollection recipes;
	
	public RecipeDao() {
		DB db=null;
		try {
			MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
			MongoClient client = new MongoClient(uri);
			db = client.getDB(uri.getDatabase());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		recipes = new Jongo(db).getCollection("recipes");
	}
	
	public Iterable<Recipe> retrieveAll(){
		return recipes.find().as(Recipe.class);
	}
	
}
