package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String,Movie>movieMap=new HashMap<>();
    Map<String,Director>directorMap=new HashMap<>();
    Map<Movie,Director> map=new HashMap<>();

    public String addMovie(Movie movie){
        String name=movie.getName();
        movieMap.put(name,movie);
        return"movie added";
    }
    public String addDirector(Director director){
        String name=director.getName();
        directorMap.put(name,director);
        return "director added";
    }
    public String addMovieDirectorPair(String MovieName,String DirectorName){
        Movie movie;
        Director director;
        if(movieMap.containsKey(MovieName) && directorMap.containsKey(DirectorName)){
            movie=movieMap.get(MovieName);
            director=directorMap.get(DirectorName);
        }
        else{
            return "Invalid Request";
        }
        map.put(movie,director);
        return"Pair added successfully";
    }
    public Movie getMovieByName(String name){
        if(!movieMap.containsKey(name)){
            return null;
        }
        return movieMap.get(name);
    }

    public Director getDirectorByName(String name){
        if(!directorMap.containsKey(name)){
            return null;
        }
        return directorMap.get(name);
    }

    public List<Movie> getMoviesByDirectorName(String name){
        List<Movie> movieList=new ArrayList<>();
        for(Movie m: map.keySet()){
            if(map.get(m).getName().equals(name)){
                movieList.add(m);
            }
        }
        return movieList;
    }

    public List<Movie> findAllMovies(){
        List<Movie> movieList=new ArrayList<>();
        for(Movie m: map.keySet()){
            movieList.add(m);
        }
        return movieList;
    }

    public String deleteDirectorByName(String name){
        for(Movie m: map.keySet()){
            if(map.get(m).getName().equals(name)){
                map.remove(m);
            }
        }
        return "Movies and director deleted successfully";
    }

    public String deleteAllDirectors(){
        map.clear();
        return"all the paired movies deleted";
    }
}
