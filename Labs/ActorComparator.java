package cmsc256;

import bridges.data_src_dependent.ActorMovieIMDB;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Comparator;

public class ActorComparator implements Comparator<ActorMovieIMDB> {

    public int compare(ActorMovieIMDB o1, ActorMovieIMDB o2) {
        return o1.getActor().compareTo(o2.getActor());
    }

}
