package DAO;

import Model.Music;

import java.sql.SQLException;
import java.util.List;

public interface IMusicDAO {

    public void insertMusic(Music music) throws SQLException;
    public Music selectMusicByTitle(String songName) throws SQLException;
    public Music selectMusicByArtistName(String artistName);
    public Music selectMusicBySongGenre(String songGenre);
    public List<Music> selectAllMusic() throws SQLException;
    public boolean deleteMusic(int idMusic) throws SQLException;
    public boolean updateMusic(Music music) throws SQLException;
}
