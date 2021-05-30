package Model;

public class Music {
    public int idMusic;
    protected String songTitle;
    protected String songGenre;
    protected String artistName;
    protected String songSource;

    public Music() {
    }

    public Music(String songTitle, String songGenre, String artistName, String songSource) {
        this.songTitle = songTitle;
        this.songGenre = songGenre;
        this.artistName = artistName;
        this.songSource = songSource;
    }

    public Music(int idMusic, String songTitle, String songGenre, String artistName, String songSource) {
        this.idMusic = idMusic;
        this.songTitle = songTitle;
        this.songGenre = songGenre;
        this.artistName = artistName;
        this.songSource = songSource;
    }


    public int getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(int idMusic) {
        this.idMusic = idMusic;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongSource() {
        return songSource;
    }

    public void setSongSource(String songSource) {
        this.songSource = songSource;
    }
}
