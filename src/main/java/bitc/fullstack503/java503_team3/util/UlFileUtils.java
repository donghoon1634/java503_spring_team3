package bitc.fullstack503.java503_team3.util;

import bitc.fullstack503.java503_team3.dto.UserlifeFileDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UlFileUtils {


    //    ObjectUtils : 스프링 프레임워크에서 제공하는 유틸 클래스
//    업로드된 정보가 존재하는지 여부를 확인
    public static List<UserlifeFileDTO> parseFileInfo(int ulIdx, MultipartHttpServletRequest multipart) throws Exception {

        if (ObjectUtils.isEmpty(multipart)) {
            return null;
        }

        List<UserlifeFileDTO> fileList = new ArrayList<>();
        String path = "C:/fullstack503/spring/upload/board/";
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        Iterator<String> iterator = multipart.getFileNames();
        String newFileName;
        String originalFileExt;
        String contentType;

        while (iterator.hasNext()) {
            String name = iterator.next();
            List<MultipartFile> multipartFileList = multipart.getFiles(name);

            for (MultipartFile uploadFile : multipartFileList) {
                contentType = uploadFile.getContentType();

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                }

                if (contentType.contains("image/jpeg")) {
                    originalFileExt = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalFileExt = ".png";
                } else if (contentType.contains("image/gif")) {
                    originalFileExt = ".gif";
                } else {
                    break;
                }

                // 파일명 생성
                newFileName = System.nanoTime() + originalFileExt;
                String storedFilePath = path + newFileName;

                // ✅ **파일 정보 설정**
                UserlifeFileDTO fileDTO = new UserlifeFileDTO();
                fileDTO.setUlIdx(ulIdx); // 게시글 ID 저장
                fileDTO.setUlOriginalFileName(uploadFile.getOriginalFilename()); // 원본 파일명
                fileDTO.setUlStoredFileName(storedFilePath); // 저장된 파일 경로
                fileDTO.setUlFileSize((int) uploadFile.getSize()); // 파일 크기

                fileList.add(fileDTO);

                // ✅ **파일 저장**
                file = new File(storedFilePath);
                uploadFile.transferTo(file);

                // 🔍 **디버깅 로그 추가**
                System.out.println("파일 저장 완료: " + storedFilePath);
                System.out.println("파일 정보 DTO: " + fileDTO.toString());
            }
        }

        return fileList;
    }

    public void deleteUlFile(int ulIdx) {
    }

//    public int countFile(int ulIdx) {
//        return board
//    }
}

