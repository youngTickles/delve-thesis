package com.example.delve.movie;

import java.util.HashMap;

import org.springframework.web.client.RestTemplate;
import com.example.delve.nowplaying.NowPlayingExample;

import com.example.delve.trailer.Trailer;

public class NowPlayingDTO{
    private NowPlayingExample nowPlayingExample;
    
    private Trailer trailerExample;
    private HashMap<String, Object> movies;
    private String title;
    private String overview;
    private String backdropPath;
    private int movieId;
    private String youtubeKey;


    public NowPlayingDTO(int i){
        RestTemplate restTemplate = new RestTemplate();
        this.nowPlayingExample = restTemplate.getForObject("https://api.themoviedb.org/3/movie/now_playing?api_key=623eeab48528051330ddc3ca73959483&language=en-US&page=1&region=HU", NowPlayingExample.class);

        this.movies = new HashMap<>();

        this.setTitle(nowPlayingExample.getResults().get(i).getTitle());
        this.setOverview(nowPlayingExample.getResults().get(i).getOverview());
        this.setBackdropPath(nowPlayingExample.getResults().get(i).getBackdropPath());
        this.setMovieId(nowPlayingExample.getResults().get(i).getId());


        RestTemplate restTemplateYtKey = new RestTemplate();
        this.trailerExample = restTemplateYtKey.getForObject("https://api.themoviedb.org/3/movie/"+nowPlayingExample.getResults().get(i).getId()+"/videos?api_key=623eeab48528051330ddc3ca73959483&language=en-US", Trailer.class);
        for(int j=0;j<trailerExample.getResults().size();j++){
            if(trailerExample.getResults().get(j).getType().contentEquals("Trailer")){
                this.setYoutubeKey(trailerExample.getResults().get(j).getKey());
            }
        }
    


        
    }


    public String getYoutubeKey(){
        return this.youtubeKey;
    }

    public void setYoutubeKey(String youtubeKey){
        this.youtubeKey = youtubeKey;

        movies.put("youtubeKey", this.getYoutubeKey());
    }

   

    public int getMovieId(){
        return this.movieId;
    }

    public void setMovieId(int movieId){
        this.movieId = movieId;
        movies.put("movieId", this.getMovieId());
    }

 



    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
        movies.put("title", this.getTitle());
    }

    public String getOverview(){
        return this.overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
        movies.put("overview", this.getOverview());
    }



    public void setBackdropPath(String backdropPath){
        this.backdropPath = backdropPath;
        movies.put("backdropPath", this.getBackdropPath());
    }

    public String getBackdropPath(){
        return this.backdropPath;
    }





}
