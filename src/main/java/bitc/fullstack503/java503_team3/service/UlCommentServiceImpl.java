package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import bitc.fullstack503.java503_team3.mapper.UlCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UlCommentServiceImpl implements UlCommentService {
    @Autowired
    private UlCommentMapper ulCommentMapper;

    // 게시물의 댓글 목록을 가져오는 API
    @Override
    public List<UserlifeCommentDTO> getUlCommentByUlIdx(int ulIdx) {
        List<UserlifeCommentDTO> comments = ulCommentMapper.getUlCommentByUlIdx(ulIdx);
        return comments;
    }
    // 추천수 증가
    @Override
    public Object ulCommentLikeUpDate(int ulCommentIdx) {
        ulCommentMapper.ulCommentLikeUpDate(ulCommentIdx);  // 추천수 증가
        return ulCommentMapper.ulCommentLikeSelect(ulCommentIdx);  // 업데이트된 추천수 반환
    }
    // 댓글 등록순
    @Override
    public List<UserlifeCommentDTO> ulCommentAsc(int ulIdx, int offset, int limit) {
        return ulCommentMapper.ulCommentAsc(ulIdx, offset, limit);
    }
    // 댓글 최신순
    @Override
    public List<UserlifeCommentDTO> ulCommentDesc(int ulIdx, int offset, int limit) {
        return ulCommentMapper.ulCommentDesc(ulIdx, offset, limit);
    }
    // 댓글을 추가하는 API
    @Override
    public void ulCommentInsert(UserlifeCommentDTO ulcDTO) {
        ulCommentMapper.ulCommentInsert(ulcDTO);
    }
    // 댓글 삭제
    @Override
    public void ulCommentdelet(int ulIdx) {
        ulCommentMapper.ulCommentdelet(ulIdx);
    }
    // 기본댓글 5개씩
    @Override
    public List<UserlifeCommentDTO> getCommentsByPage(int ulIdx, int offset, int limit) {
        return ulCommentMapper.selectCommentsByPage(ulIdx, offset, limit);
    }

    @Override
    public int getTotalCommentCount(Long ulIdx) {
        return ulCommentMapper.getTotalCommentCount(ulIdx);
    }

    // 더보기
    @Override
    public List<UserlifeCommentDTO> getCommentsByPage(int ulIdx, int offset, int limit, String descOrAsc) {
        if ("desc".equalsIgnoreCase(descOrAsc)) {
            // 내림차순인 경우
            return ulCommentMapper.selectCommentsByPage(ulIdx, offset, limit);
        } else if ("asc".equalsIgnoreCase(descOrAsc)) {
            // 오름차순인 경우
            return ulCommentMapper.selectCommentsAscByPage(ulIdx, offset, limit);
        } else {
            // desc 또는 asc 외의 값이 들어왔을 경우에 대한 처리 (옵션)
            throw new IllegalArgumentException("Invalid value for descOrAsc: " + descOrAsc);
        }

    }


}
