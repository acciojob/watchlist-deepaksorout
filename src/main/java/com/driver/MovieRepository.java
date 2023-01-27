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
    Map<String,List<String>> map=new HashMap<>();

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
        if(!movieMap.containsKey(MovieName) || !directorMap.containsKey(DirectorName)){
            return"Movie or Director not found";
        }
        if(!map.containsKey(DirectorName)){
            List<String> list=new ArrayList<>();
            list.add(MovieName);
            map.put(DirectorName,list);
        }
        else{
            List<String> list=map.get(DirectorName);
            list.add(MovieName);
            map.put(DirectorName,list);
        }
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

    public List<String> getMoviesByDirectorName(String name){
        if(!map.containsKey(name)){
            return null;
        }
        return map.get(name);
    }

    public List<String> findAllMovies(){
        List<String> list=new ArrayList<>();
        for(String s:movieMap.keySet()){
            list.add(s);
        }
        return list;
    }

    public String deleteDirectorByName(String name){
        if(map.containsKey(name)){
            for(String s:map.get(name)){
                if(movieMap.containsKey(s)){
                    movieMap.remove(s);
                }
            }
        }
        map.remove(name);
        if(directorMap.containsKey(name)){
            directorMap.remove(name);
        }
        return " director and its movies deleted successfully";
    }

    public String deleteAllDirectors(){
        for(String s: map.keySet()) {
            for (String movie : map.get(s)) {
                if (movieMap.containsKey(movie)) {
                    movieMap.remove(movie);
                }
            }
            map.remove(s);
        }
        directorMap.clear();
        return"all director and their movies deleted";
    }
}
