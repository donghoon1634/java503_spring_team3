package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.CommentDTO;
import bitc.fullstack503.java503_team3.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSercviceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    // 게시물의 댓글 목록을 가져오는 API
    @Override
    public List<CommentDTO> getCommentsByBoardIdx(int boardIdx) {
        List<CommentDTO> comments = commentMapper.getCommentsByBoardIdx(boardIdx);
        return comments;
    }
    // 댓글을 추가하는 API
    @Override
    public void addComment(CommentDTO commentDTO) {
        commentMapper.addComment(commentDTO);

    }
    // 댓글 추천수를 증가시키는 API
    @Override
    public void incrementRecommend(int commentIdx) {
        commentMapper.incrementRecommend(commentIdx);
    }
}
