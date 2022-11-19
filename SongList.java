/******************************************************************************************************
 * SongList.java
 * Shashank
 * Project 3
 * CMSC256 Section 901
 * This class allows you to see information about artist songs
 ******************************************************************************************************/

package cmsc256;

import bridges.data_src_dependent.Song;
import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.connect.DataSource;

import java.util.ArrayList;
import java.util.Iterator;


public class SongList implements cmsc256.List<MySong>, Iterable<MySong> {

    private SLelement<Song> head;
    private SLelement<Song> tail;
    private SLelement<Song> temp;
    private int sizeList;
    String playlistName;

    Bridges bridges = new Bridges(3, "shashanksinha", "920515443262");


    public SongList() {
        DataSource ds = bridges.getDataSource();
        sizeList = 0;
        clear();

        ArrayList<Song> arraySong = new ArrayList<Song>();

        try {
            arraySong = ds.getSongData();
        } catch (Exception e) {
            System.out.println("Unable to connect to bridges");
        }

    }


    public void clear() {
        temp = tail = new SLelement<Song>();
        head = new SLelement<Song>(tail);
        sizeList = 0;

    }


    public boolean insert(MySong it, int pos) {
        SLelement<Song> value = new SLelement<>(it);
        if (temp == head) {
            value.setNext(head);
            sizeList++;
            return true;
        }
        temp.setNext(new SLelement<Song>(temp.getValue(), temp.getNext()));
        temp.setValue(it);
        if (temp == tail) {
            tail = temp.getNext();
        }
        sizeList++;
        return true;
    }

    public boolean add(MySong it) {
        SLelement<Song> value = new SLelement<>(null);
        tail.setNext(value);
        tail.setValue(it);
        tail = tail.getNext();
        sizeList++;
        return true;
    }

    public MySong remove(int pos) {

        if (temp == tail) {
            return null;
        }

        Song song = temp.getValue();
        temp.setValue(temp.getNext().getValue());

        if (temp.getNext() == tail) {
            tail = temp;
        }
        temp.setNext(temp.getNext().getNext());
        sizeList--;
        return (MySong) song;
    }

    public int size() {

        return sizeList;
    }

    public boolean isEmpty() {
        if (sizeList == 0 && temp.getValue() == null) {
            return true;
        }
        return false;
    }


    public boolean contains(MySong target) {
        while (temp != tail) {
            if (temp.getValue() == target) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public MySong getValue(int pos) throws IllegalArgumentException {
        try{


        } catch (Exception e){
            System.out.println("position < 0 or > number of elements in list");
        }

        return (MySong) temp.getValue();
    }

    public String getSongsByArtist(String artist) {
        DataSource ds = bridges.getDataSource();
        StringBuilder stringBuilder = new StringBuilder();

        while (temp != tail) {
            if (temp.getValue().getArtist().equals(artist)) {
                String output = "\nTitle: " + temp.getValue().getSongTitle() + "\nArtist: " + temp.getValue().getArtist() + "\nAlbum: " + temp.getValue().getAlbumTitle();
                stringBuilder.append(output);
                temp = temp.getNext();
            } else {
                temp = temp.getNext();
            }

            if (stringBuilder.toString().isEmpty()){
                return "There are no songs by " + artist + " in this playlist.";
            }

            return stringBuilder.toString();

            }

        return stringBuilder.toString();
    }


    public String getPlaylistName() {
        return playlistName;
    }


    public void setPlaylistName(String name) {
        this.playlistName = name;
    }



    public Iterator<MySong> iterator() {
        Iterable<? extends Song> arraySong = null;
        for (Song songs : arraySong) {
            add((MySong) songs);
            return null;
        }
        return null;
    }
}

