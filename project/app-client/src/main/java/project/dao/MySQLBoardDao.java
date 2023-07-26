package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.vo.Board;

public class MySQLBoardDao implements BoardDao {
  Connection con;

  public MySQLBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into project_board(title,content,writer,password)"
            + " values(?,?,?,sha1(?))")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setString(3, board.getWriter());
      stmt.setString(4, board.getPassword());

      stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Board> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select board_no, title, writer, view_count, created_date"
            + " from project_board"
            + " order by view_count desc")) {

      try(ResultSet rs = stmt.executeQuery()){
        List<Board> list = new ArrayList<>();
        while (rs.next()) {
          Board b = new Board();
          b.setNo(rs.getInt("board_no"));
          b.setTitle(rs.getString("title"));
          b.setWriter(rs.getString("writer"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));

          list.add(b);
        }
        return list;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Board findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select board_no, title, content, writer, view_count, created_date"
            + " from project_board"
            + " where board_no=?"
            + " order by board_no desc")) {

      stmt.setInt(1, no);

      try(ResultSet rs = stmt.executeQuery()){
        if (rs.next()) {
          Board b = new Board();
          b.setNo(rs.getInt("board_no"));
          b.setTitle(rs.getString("title"));
          b.setContent(rs.getString("content"));
          b.setWriter(rs.getString("writer"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));

          stmt.executeUpdate("update project_board set"
              + " view_count=view_count + 1"
              + " where board_no=" + no);

          return b;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update project_board set"
            + " title=?,"
            + " content=?"
            + " where board_no=? and password=sha1(?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getNo());
      stmt.setString(4, board.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Board board) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from project_board where board_no=? and password=sha1(?)")) {

      stmt.setInt(1, board.getNo());
      stmt.setString(2, board.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}