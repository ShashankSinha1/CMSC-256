package cmsc256;

import bridges.data_src_dependent.Song;

public class MySong extends bridges.data_src_dependent.Song implements Comparable<MySong>{



  public MySong(){
    super();

  }

  public MySong(String artist,
                String song, String album, String lyrics, String release_date){

  super (artist, song, album, lyrics, release_date);

  }

  public MySong(Song s){
    super(s.getArtist(), s.getSongTitle(), s.getAlbumTitle(), s.getLyrics(), s.getReleaseDate());

  }

    public int compareTo(MySong o1) {
      return this.getSongTitle().compareTo(o1.getSongTitle());
    }
}


