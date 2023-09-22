package com.green.Shop.util;

import com.green.Shop.item.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtil {

    //파일 첨부 기능(단일 파일 업로드)
    public static ImgVO uploadFile(MultipartFile img){
        ImgVO imgVO = null;

        //첨부파일이 있다면
        if(!img.isEmpty()){
            imgVO = new ImgVO();

            //첨부파일
            String originFileName = img.getOriginalFilename(); //파일명.jpg

            //첨부 될 파일명
            String uuid = UUID.randomUUID().toString(); //랜덤 값
            int dotIndex = originFileName.lastIndexOf("."); //파일명.jpg 에서 가장 마지막에 있는 "." 을 찾아서 index로 반환.
            String extension = originFileName.substring(dotIndex); // 파일명.jpg 에서 dotIndex 부터 글자를 날라내서 반환. .jpg
            String attachedFileName = uuid + extension; //랜덤 값.jpg uuid(랜덤값) + extension(.jpg)

            //파일 첨부
            try { //transferTo() 메서드는 예외처리를 반드시 해야하는 메서드임으로 try catch가 강제
                File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName); //파일 객체에 파일경로+파일명을 넣음. ConstantVariable 클래스에 상수 UPLOAD_PATH를 불러옴.
                img.transferTo(file); //매개변수로 받은 MultipartFile 클래스의 transferTo() 메서드 사용. 이때, 매개변수로 file(지정한 파일) 을 넣어서 경로에 이미지 파일을 저장.

                imgVO.setOriginFileName(originFileName);
                imgVO.setAttachedFileName(attachedFileName);
                imgVO.setIsMain("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imgVO;
    }

    //다중 파일 업로드
    public static List<ImgVO> multiFileUpload(MultipartFile[] imgs){

        List<ImgVO> imgList = new ArrayList<>();

        for(MultipartFile img : imgs){
            ImgVO vo = uploadFile(img);

            if(vo != null){
                vo.setIsMain("N");
                imgList.add(vo);
            }
        }
        return imgList;
    }

}
