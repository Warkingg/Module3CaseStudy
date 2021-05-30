package DAO;

import Model.Music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDAO implements IMusicDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/music?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Fl0@t1ng";

    private static final String INSERT_MUSIC_SQL = "INSERT INTO MUSIC(song_title,song_genre,artist_name,song_source) VALUES (?,?,?,?)";
    private static final String SELECT_ALL_MUSIC = "SELECT * FROM MUSIC";
    private static final String SELECT_MUSIC_BY_SONGTITLE = "SELECT idMusic,song_genre,artist_name FROM MUSIC where song_name=?;";
    private static final String SELECT_MUSIC_BY_SONGGENRE = "SELECT idMusic,song_name,artist_name,song_source FROM MUSIC where song_genre LIKE '?';";
    private static final String SELECT_MUSIC_BY_ARTISTNAME = "SELECT idMusic,song_name,song_genre,song_source FROM MUSIC where artist_name=?;";
    private static final String DELETE_MUSIC_SQL_BY_SONGTITLE = "DELETE from user where song_title=?";
    private static final String DELETE_MUSIC_SQL_BY_ID = "DELETE from user where idMusic=?";
    private static final String UPDATE_MUSIC_SQL = "update MUSIC set song_title=?, song_genre=?,artist_name=?,song_source=?;";

    public MusicDAO() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


    @Override
    public void insertMusic(Music music){
        System.out.println(INSERT_MUSIC_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MUSIC_SQL)) {
            preparedStatement.setString(1, music.getSongTitle());
            preparedStatement.setString(2, music.getArtistName());
            preparedStatement.setString(3, music.getSongGenre());
            preparedStatement.setString(4, music.getSongSource());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public Music selectMusicByTitle(String songTitle){
        Music music = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MUSIC_BY_SONGTITLE)) {
            preparedStatement.setString(1, songTitle);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String SongTitle = rs.getString("Title");
                String SongGenre = rs.getString("Genre");
                String ArtistName = rs.getString("Artist");
                String SongSource = rs.getString("Source");

                music = new Music(SongTitle, SongGenre, ArtistName, SongSource);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return music;
    }


    @Override
    public Music selectMusicByArtistName(String artistName) {
        return null;
    }

    @Override
    public Music selectMusicBySongGenre(String songGenre) {
        return null;
    }

    @Override
    public List<Music> selectAllMusic(){
        List<Music> musics = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MUSIC);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int IdMusic = rs.getInt("ID");
                String SongTitle = rs.getString("Song");
                String ArtistName = rs.getString("Artist");
                String SongGenre = rs.getString("Genre");
                String SongSource = rs.getString("Source");
                musics.add(new Music(IdMusic,SongTitle, ArtistName, SongGenre, SongSource));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musics;
    }

    @Override
    public boolean deleteMusic(int idMusic) {
        boolean rowDeleted = false;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MUSIC_SQL_BY_ID);){
            preparedStatement.setInt(1,idMusic);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return rowDeleted;
    }

    @Override
    public boolean updateMusic(Music music) {
        boolean rowUpdated = false;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement =connection.prepareStatement(UPDATE_MUSIC_SQL);){
            preparedStatement.setString(1,music.getSongTitle());
            preparedStatement.setString(2,music.getSongGenre());
            preparedStatement.setString(3,music.getArtistName());
            preparedStatement.setString(4,music.getSongSource());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message" + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
