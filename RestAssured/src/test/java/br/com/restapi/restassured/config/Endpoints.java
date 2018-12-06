package br.com.restapi.restassured.config;

public interface Endpoints {

	String GET_VIDEOGAMES = "/videogames";
	
	String GET_VIDEOGAMES_BY_ID = "/videogames/{videoGameId}";
	
	String POST_VIDEOGAMES = "/videogames";
	
	String PUT_VIDEOGAMES = "/videogames/{videoGameId}";
	
	String DELETE_VIDEOGAMES = "/videogames/{videoGameId}";
	
}
