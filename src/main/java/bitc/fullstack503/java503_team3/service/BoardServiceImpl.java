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
public class BoardServiceImpl implements BoardService
{
  @Autowired
  private BoardMapper boardMapper;
  @Autowired
  private UlCommentMapper ulCommentMapper;
  @Autowired
  private UlFileUtils fileUtils;
  @Autowired
  private UlCommentService ulCommentService;
  
  //    목록
  @Override
  public List<UserlifeDTO> selectBoardList ()
  {
    List<UserlifeDTO> ulBoardList = boardMapper.selectBoardList ();
    for (UserlifeDTO ulBoard : ulBoardList)
    {
      int com = ulCommentMapper.getUlCommentCount (ulBoard.getUlIdx ());
      ulBoard.setUlCommentCount (com);
    }
    return ulBoardList;
  }
  
  // 게시글목록 -로그인한 사람의 지역구 기준
  @Override
  public List<UserlifeDTO> selectBoardListByLocation (String memberGu)
  {
    // 1. 지역구에 맞는 게시물 조회
    List<UserlifeDTO> ulBoardList = boardMapper.selectBoardListByLocation (memberGu);
    // 2. 각 게시물의 댓글 수 조회하여 설정
    for (UserlifeDTO ulBoard : ulBoardList)
    {
      int com = ulCommentMapper.getUlCommentCount (ulBoard.getUlIdx ());
      ulBoard.setUlCommentCount (com);  // 댓글 수 설정
    }
    // 3. 댓글 수가 포함된 게시물 목록 반환
    return ulBoardList;
  }
  
  //    작성
  @Override
  public void insertBoard (UserlifeDTO ul)
  {
    boardMapper.insertBoard (ul);
  }
  
  @Override
  public void insertBoard (UserlifeDTO ul, MultipartHttpServletRequest multipart) throws Exception
  {
    //    게시물 등록
    boardMapper.insertBoard (ul);
    //    사용자가 생성한 자바 빈즈인 FileUtils 에서 제공하는 parseFileInfo() 메소드를 사용하여 업로드된 파일 목록을 가져옴
    List<UserlifeFileDTO> fileList = UlFileUtils.parseFileInfo (ul.getUlIdx (), multipart);
    //    CollectionUtils : 스프링 프레임워크에서 제공하는 컬렉션 타입의 객체를 활용할 수 있는 유틸 클래스
    if (CollectionUtils.isEmpty (fileList) == false)
    {
      //      생성된 파일 정보 목록을 데이터베이스에 추가
      boardMapper.insertFileList (fileList, ul.getUlIdx ());
    }
  }
  
  //   상세
  @Override
  public UserlifeDTO selectBoardDetail (int ulIdx)
  {
    boardMapper.updateHitCnt (ulIdx);
    UserlifeDTO ul = boardMapper.selectBoardDetail (ulIdx);
    List<UserlifeFileDTO> files = boardMapper.selectFilesByUlIdx (ulIdx); // 이미지 파일 가져오기
    for (UserlifeFileDTO file : files)
    {
      String path = file.getUlStoredFileName ();
      int index = path.lastIndexOf ("/");
      path = path.substring (index);
      path = "/upload" + path;
      file.setUlStoredFileName (path);
    }
    ul.setFileList (files); // ✅ 게시글 DTO에 파일 리스트 추가
    // 🔍 디버깅 로그 추가
    System.out.println ("게시글 내용: " + ul.getUlContents ());
    System.out.println ("첨부 파일 개수: " + (files != null ? files.size () : 0));
    return ul;
  }
  // 추천수 중가
  
  @Override
  public Object plusLike (int ulIdx)
  {
    boardMapper.plusLike (ulIdx);
    return boardMapper.selectLikeCount (ulIdx);
  }
  
  //    게시물 수정
  @Override
  public void updateBoard (UserlifeDTO ul)
  {
    boardMapper.updateBoard (ul);
  }
  
  //    게시물 삭제
  @Override
  public void deleteBoard (int ulIdx)
  {
    //        댓글 수 확인
    //        int count = ulCommentMapper.countComment(ulIdx);
    //        int filecount= fileUtils.countFile(ulIdx);
    //        if (count > 0 && filecount > 0) {
    // 댓글삭제
    ulCommentMapper.deleteComment (ulIdx);
    boardMapper.deleteUlFile (ulIdx);
    //        }
    boardMapper.deleteBoard (ulIdx);
  }
  
  //    인기글 정렬
  @Override
  public List<UserlifeDTO> getPopularPosts (int limit)
  {
    return boardMapper.getPopularPosts (limit);
  }
  
  // 해당게시물의 댓글 갯수 조회
  @Override
  public int getUlCommentCount (int ulIdx)
  {
    return ulCommentService.getUlCommentCount (ulIdx);
  }
  
  // 카테고리별 게시물 목록 페이지로 이동
  @Override
  public List<UserlifeDTO> getBoardByCategory (String ulCate)
  {
    List<UserlifeDTO> ulBoardList = boardMapper.getBoardByCategory (ulCate);
    for (UserlifeDTO ulBoard : ulBoardList)
    {
      int com = ulCommentMapper.getUlCommentCount (ulBoard.getUlIdx ());
      ulBoard.setUlCommentCount (com);
    }
    return ulBoardList;
  }
  
  // 카테고리별 게시물 목록 페이지로 이동-로그인한사람의 지역구 기준
  @Override
  public List<UserlifeDTO> getBoardByCategoryAndLocation (String ulCate, String memberGu)
  {
    List<UserlifeDTO> ulBoardList = boardMapper.getBoardByCategoryAndLocation (ulCate, memberGu);
    for (UserlifeDTO ulBoard : ulBoardList)
    {
      int com = ulCommentMapper.getUlCommentCount (ulBoard.getUlIdx ());
      ulBoard.setUlCommentCount (com);
    }
    return ulBoardList;
  }
  
  // 카테고리별- 인기글 목록 페이지로 이동
  @Override
  public List<UserlifeDTO> getBoardByCategoryPopular ()
  {
    List<UserlifeDTO> ulBoardList = boardMapper.getBoardByCategoryPopular ();
    for (UserlifeDTO ulBoard : ulBoardList)
    {
      int com = ulCommentMapper.getUlCommentCount (ulBoard.getUlIdx ());
      ulBoard.setUlCommentCount (com);
    }
    return ulBoardList;
  }
  
  @Override
  public List<UserlifeDTO> getBoardByCategoryPopularAndLocation (String memberGu)
  {
    List<UserlifeDTO> ulBoardList = boardMapper.getBoardByCategoryPopularAndLocation (memberGu);
    for (UserlifeDTO ulBoard : ulBoardList)
    {
      int com = ulCommentMapper.getUlCommentCount (ulBoard.getUlIdx ());
      ulBoard.setUlCommentCount (com);
    }
    return ulBoardList;
  }
  
  @Override
  public String getBoardAuthorId (int ulIdx)
  {
    return boardMapper.getBoardAuthorId (ulIdx);
  }
  //
}
