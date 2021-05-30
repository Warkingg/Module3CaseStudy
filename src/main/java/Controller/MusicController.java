package Controller;

import DAO.MusicDAO;
import Model.Music;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MusicController", urlPatterns ="/music")
public class MusicController extends HttpServlet {
    private MusicDAO musicDAO;
    public void init() {
        musicDAO = new MusicDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action) {
            case "/new":
                showNewForm(request,response);
                break;
            case "/insert":
                insertMusic(request,response);
                break;
            case "/update":
                updateMusic(request,response);
                break;
            case "/delete":
                deleteMusic(request,response);
                break;
            case "/edit":
                showEditForm(request,response);
                break;
            default:
                MusicList(request, response);
                break;
        }
    }

    private void MusicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Music> musiclists = musicDAO.selectAllMusic();
        request.setAttribute("MusicList", musiclists);
        RequestDispatcher dispatcher = request.getRequestDispatcher("music/musiclist.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/music/musicform.jsp");
        dispatcher.forward(request, response);
    }
    private void insertMusic(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String SongTitle = request.getParameter("Title");
        String SongGenre = request.getParameter("Genre");
        String ArtistName = request.getParameter("Artist");
        String SongSource = request.getParameter("Source");
        musicDAO.insertMusic(new Music(SongTitle,SongGenre,ArtistName,SongSource));
        response.sendRedirect("list");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String songtitle = request.getParameter("Title");
        Music existingUser = musicDAO.selectMusicByTitle(songtitle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/music/musicform.jsp");
        dispatcher.forward(request,response);
    }

    private void updateMusic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String SongTitle = request.getParameter("Title");
        String SongGenre = request.getParameter("Genre");
        String ArtistName = request.getParameter("Artist");
        String SongSource = request.getParameter("Source");
        musicDAO.updateMusic(new Music(SongTitle,SongGenre,ArtistName,SongSource));
        response.sendRedirect("list");

    }

    private void deleteMusic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idMusic = Integer.parseInt(request.getParameter("idMusic"));
        musicDAO.deleteMusic(idMusic);
        response.sendRedirect("list");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


