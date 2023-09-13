package project.service;

import project.dao.BoardDao;
import project.vo.AttachedFile;
import project.vo.Board;
import util.Transactional;

import java.util.List;

// @Service
public class DefaultBoardService implements BoardService {
    BoardDao boardDao;

    public DefaultBoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Transactional // 이 메서드는 트랜잭션 상태에서 실행하라고 지정
    @Override
    public int add(Board board) throws Exception {
        int count = boardDao.insert(board);
        if (board.getAttachedFiles().size() > 0) {
            boardDao.insertFiles(board);
        }
        return count;
    }

    @Override
    public List<Board> list() throws Exception {
        return boardDao.findAll();
    }

    @Override
    public Board get(int boardNo) throws Exception {
        return boardDao.findBy(boardNo);
    }

    @Transactional
    @Override
    public int update(Board board) throws Exception {
        int count = boardDao.update(board);
        if (count > 0 && board.getAttachedFiles().size() > 0) {
            boardDao.insertFiles(board);
        }
        return count;
    }

    @Transactional
    @Override
    public int delete(int boardNo) throws Exception {
        boardDao.deleteFiles(boardNo);
        return boardDao.delete(boardNo);
    }

    @Transactional
    @Override
    public int increaseViewCount(int boardNo) throws Exception {
        return boardDao.updateCount(boardNo);
    }

    @Override
    public AttachedFile getAttachedFile(int fileNo) throws Exception {
        return boardDao.findFileBy(fileNo);
    }

    @Override
    public int deleteAttachedFile(int fileNo) throws Exception {
        return boardDao.deleteFile(fileNo);
    }
}