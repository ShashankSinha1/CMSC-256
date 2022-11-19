package cmsc256;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.ArrayList;
import java.util.List;


public class SortingLab {
    public static void main(String[] args) throws Exception {
        // In the main method, create the Bridges object replacing

        //  the 2nd & 3rd parameters with

        //  your individual Bridges user id and API key

        Bridges bridges = new Bridges(3, "shashanksinha", "920515443262");
        DataSource ds = bridges.getDataSource();
        List<ActorMovieIMDB> movieData = null;

        try {

            movieData = ds.getActorMovieIMDBData(Integer.MAX_VALUE);

        } catch (Exception e) {

            System.out.println("Unable to connect to Bridges.");

        }

        for (int i = 0; i < 5; i++) {
            ActorMovieIMDB entry = movieData.get(i);
            System.out.println("" + i + ".  " + entry.getActor() + " was in " + entry.getMovie());
        }

        ArrayList<ActorMovieIMDB> filteredMovieList = new ArrayList<>();

        System.out.println("\nHere are the actors in the Movie: Being John Malkovich\n");
        for(ActorMovieIMDB movie : movieData){
            if(movie.getMovie().equals("Being_John_Malkovich_(1999)")){
                System.out.println(movie.getActor());
                filteredMovieList.add(movie);
            }
        }

        System.out.println("\nHere are the names of the same actors sorted by First Name\n");
        ArrayList<String> sortedActors = new ArrayList<>();
        ActorComparator sorted = new ActorComparator();

        filteredMovieList.sort(new ActorComparator());
        for(ActorMovieIMDB movie: filteredMovieList){
            System.out.println(movie.getActor());

        }

    }
}


