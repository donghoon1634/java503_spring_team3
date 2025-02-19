package bitc.fullstack503.java503_team3.util;
import bitc.fullstack503.java503_team3.dto.MemberProfileDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class MemberProfileUtils
{
  public List<MemberProfileDTO> memberProfileHref (String memberId, MultipartHttpServletRequest multipart) throws Exception
  {
    if (ObjectUtils.isEmpty (multipart))
    {
      return null;
    }
    List<MemberProfileDTO> fileList = new ArrayList<> ();
    String fileHref = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
    ZonedDateTime current = ZonedDateTime.now ();
    String datePath = current.format (formatter);
    // 저장할 폴더 설정 (resources/static/images/member/profile)
    String basePath = "C:\\fullstack503\\spring\\upload\\";
    String dataPath = "\\member\\profile\\";
    String uploadPath = basePath + dataPath;
    File file = new File (uploadPath);
    if (!file.exists ())
    {
      file.mkdirs ();
    }
    Iterator<String> iterator = multipart.getFileNames ();
    String newFileName;
    String originalFileExt;
    String contentType;
    while (iterator.hasNext ())
    {
      String name = iterator.next ();
      List<MultipartFile> multipartFileList = multipart.getFiles (name);
      for (MultipartFile uploadFile : multipartFileList)
      {
        contentType = uploadFile.getContentType ();
        if (ObjectUtils.isEmpty (contentType))
        {
          break;
        }
        originalFileExt = getFileExtension (contentType);
        if (originalFileExt == null)
        {
          break;
        }
        newFileName = memberId + originalFileExt;
        MemberProfileDTO fileDTO = new MemberProfileDTO ();
        fileDTO.setMemberProfileMemberId (memberId);
        fileDTO.setMemberProfileHref (dataPath + newFileName);
        fileList.add (fileDTO);
        file = new File (uploadPath + newFileName);
        uploadFile.transferTo (file);  // 파일 저장
      }
    }
    return fileList;
  }
  
  // 파일 확장자 반환 메소드 (지원 확장자 추가)
  private String getFileExtension (String contentType)
  {
    switch (contentType)
    {
      case "image/jpeg":
        return ".jpg";
      case "image/png":
        return ".png";
      case "image/gif":
        return ".gif";
      case "image/bmp":
        return ".bmp";
      case "image/webp":
        return ".webp";
      case "image/svg+xml":
        return ".svg";
      case "image/tiff":
        return ".tiff";
      default:
        return null; // 지원되지 않는 파일 형식은 저장하지 않음
    }
  }
  
  // resources/static 경로 가져오기
  private String getResourceBasePath () throws IOException
  {
    return new ClassPathResource ("static/").getFile ().getAbsolutePath () + "/";
  }
}
