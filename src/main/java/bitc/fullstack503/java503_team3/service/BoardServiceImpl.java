package bitc.fullstack503.java503_team3.service;

import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeFileDTO;
import bitc.fullstack503.java503_team3.mapper.BoardMapper;
import bitc.fullstack503.java503_team3.mapper.UlCommentMapper;
import bitc.fullstack503.java503_team3.util.UlFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
@Service
public class BoardServiceImpl implements BoardService {

@Autowired
private BoardMapper boardMapper;
@Autowired
private UlCommentMapper ulCommentMapper;
    //    목록
    @Override
    public List<UserlifeDTO> selectBoardList() {
        return boardMapper.selectBoardList();
    }






    //    작성
    @Override
    public void insertBoard(UserlifeDTO ul) {
        boardMapper.insertBoard(ul);
    }
    @Override
    public void insertBoard(UserlifeDTO ul, MultipartHttpServletRequest multipart) throws Exception {

//    게시물 등록
        boardMapper.insertBoard(ul);

//    사용자가 생성한 자바 빈즈인 FileUtils 에서 제공하는 parseFileInfo() 메소드를 사용하여 업로드된 파일 목록을 가져옴
        List<UserlifeFileDTO> fileList = UlFileUtils.parseFileInfo(ul.getUlIdx(), multipart);

//    CollectionUtils : 스프링 프레임워크에서 제공하는 컬렉션 타입의 객체를 활용할 수 있는 유틸 클래스
        if (CollectionUtils.isEmpty(fileList) == false) {
//      생성된 파일 정보 목록을 데이터베이스에 추가
            boardMapper.insertFileList(fileList,ul.getUlIdx());
        }


    }



    //   상세
    @Override
    public UserlifeDTO selectBoardDetail(int ulIdx) {
        boardMapper.updateHitCnt(ulIdx);
        UserlifeDTO ul= boardMapper.selectBoardDetail(ulIdx);
        return ul;
    }
    //추천수 중가
    @Override
    public Object plusLike(int ulIdx) {
        boardMapper.plusLike(ulIdx);

        return boardMapper.selectLikeCount(ulIdx);
    }

    //    게시물 수정
    @Override
    public void updateBoard(UserlifeDTO ul) {
        boardMapper.updateBoard(ul);
    }

    //    게시물 삭제
    @Override
    public void deleteBoard(int ulIdx) {

//        댓글 수 확인
        int count = ulCommentMapper.countComment(ulIdx);
        if (count > 0) {
            //댓글삭제
            ulCommentMapper.deleteComment(ulIdx);
        }
        boardMapper.deleteBoard(ulIdx);

    }

    //    인기글 정렬
    @Override
    public List<UserlifeDTO> getPopularPosts(int limit) {
        return boardMapper.getPopularPosts(limit);

    }


}
