package com.bzn.apps.tweakcook;

import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.AfterClass;
import org.junit.Test;

public class RecipeServiceTest {

	private static final HttpClient httpclient = new DefaultHttpClient();
	
	@Test
	public void test() throws Exception {
		String domain = RecipeService.getRecipes();
		HttpGet httpget = new HttpGet("http://localhost:4567/"+domain);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		String res = getResponseAsString(httpget, entity);
		
		assertThat(res).isEqualToIgnoringCase("[{\"text\":null}]");
	}

	private String getResponseAsString(HttpGet httpget, HttpEntity entity) throws IOException {
		InputStream instream = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
		String result = reader.readLine();
		instream.close();
		return result;
	}
	
	@AfterClass
	public static void tearDown(){
		httpclient.getConnectionManager().shutdown();
	}
}
