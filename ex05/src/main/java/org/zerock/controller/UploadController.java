package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

   @GetMapping("/uploadForm")
   public void uploadForm() {
      log.info("upload from");
   }
   
   @PostMapping("/uploadFormAction")
   public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
      
      String uploadFolder = "c:\\upload";
      
      for(MultipartFile multipartFile : uploadFile) {
         log.info("----------------------------");
         log.info("Uplaod File Name: " + multipartFile.getOriginalFilename());
         log.info("Uplaod File Size: " + multipartFile.getSize());
         
         //파일 경로 객체 설정  c:\\upload\fastAPI.PNG
         File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
         
         try {
            multipartFile.transferTo(saveFile);  //파일 저장
         }catch(Exception e) {
            e.printStackTrace();
         }
      }
   }
   
   @GetMapping("/uploadAjax")
   public void uploadAjax() {
      log.info("uploadAjax");
      getFolder();
   }
   
   /*
   @PostMapping("/uploadAjaxAction")
   public void uploadAjaxPost(MultipartFile[] uploadFile, Model model) {
      
      String uploadFolder = "c:\\upload";
      
      //make folder ...........
      File uploadPath = new File(uploadFolder, getFolder());
      log.info("upload path : " +  uploadPath);  //c\\upload\2024\11\19
      
      if(uploadPath.exists() == false) {
         uploadPath.mkdirs();
      }  // make yyyy/MM/dd folder 생성..
            
      for(MultipartFile multipartFile : uploadFile) {
         log.info("-------------------------------------------------------");
         log.info("Uplaod File Name: " + multipartFile.getOriginalFilename());
         log.info("Uplaod File Size: " + multipartFile.getSize());
         
         
         
         //soju.PNG
         String uploadFileName = multipartFile.getOriginalFilename();
         
         uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
         
         log.info("only file name: " + uploadFileName);
         
         //중복 방지
         UUID uuid =  UUID.randomUUID();

         uploadFileName = uuid.toString() + "_" + uploadFileName;
         
         
         //파일 경로 객체 설정  c:\\upload\fastAPI.PNG
//         File saveFile = new File(uploadFolder, uploadFileName);

         //파일 경로 객체 설정   //c\\upload\2024\11\19
         File saveFile = new File(uploadPath, uploadFileName);
         
         try {
            multipartFile.transferTo(saveFile);  //파일 저장
            
            if(checkImageType(saveFile)) {
               
               FileOutputStream thumbnail = new FileOutputStream(new File(
                     uploadPath, "s_" + uploadFileName));
               
               Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 200);  //섬네일 이미지 생성
               thumbnail.close();
            }
            
         }catch(Exception e) {
            e.printStackTrace();
         }
      }
      
   }
   */
   
   @PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile, Model model) {
       List<AttachFileDTO> list = new ArrayList<>();
       String uploadFolder = "c:\\upload";

       // 날짜별 폴더 경로 생성
       String uploadFolderPath = getFolder();
       File uploadPath = new File(uploadFolder, uploadFolderPath);
       if (!uploadPath.exists()) {
           uploadPath.mkdirs(); // 디렉터리 생성
       }

       for (MultipartFile multipartFile : uploadFile) {
           AttachFileDTO attachDTO = new AttachFileDTO();

           log.info("Upload File Name :" + multipartFile.getOriginalFilename());
           log.info("Upload File Size :" + multipartFile.getSize());
           
           // 원본 파일 이름
           String uploadFileName = multipartFile.getOriginalFilename();
           uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
           
           
           log.info("only file name: " + uploadFileName);
           
           attachDTO.setFileName(uploadFileName);
           

           // 파일 이름 중복 방지 처리 (UUID 추가)
           UUID uuid = UUID.randomUUID();
           uploadFileName = uuid.toString() + "_" + uploadFileName;

           // 파일 저장 경로 설정
           File saveFile = new File(uploadPath, uploadFileName);

           try {
               multipartFile.transferTo(saveFile); // 파일 저장

               if (checkImageType(saveFile)) {
                   File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
                   try (FileOutputStream thumbnail = new FileOutputStream(thumbnailFile)) {
                       Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 200, 200); // 섬네일 생성
                   }
                   attachDTO.setImage(true);
               }

           } catch (Exception e) {
               e.printStackTrace();
           }

           attachDTO.setUuid(uuid.toString());
           attachDTO.setUploadPath(uploadFolderPath.replace("\\", "/")); // JSON 출력 시 경로 표시
           list.add(attachDTO);
       }

       return new ResponseEntity<>(list, HttpStatus.OK);
   }

   
   //오늘날짜로 폴더 경로 생성
   private String getFolder() {
      SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
      
      Date date = new Date();
      String str = sdf.format(date);
   
      return str.replace("-", File.separator);
   }
   
   //이미지 파일 여부 판단.
   private boolean checkImageType(File file) {
      
      try {
         
         String contentType = Files.probeContentType(file.toPath());
         
         log.info("contentType : " + contentType);
         return contentType.startsWith("image");  //이미지 여부 판단
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      return false;
   }
   
   @GetMapping("/display")
   @ResponseBody
   public ResponseEntity<byte[]> getFile(String fileName){
	   
	   log.info("fileName :"+fileName);
	   
	   File file = new File("c:\\upload\\" + fileName);
	   
	   ResponseEntity<byte[]> result = null;
	   
	   try {
		
		   HttpHeaders header = new HttpHeaders();
		   header.add("Content-Type :", Files.probeContentType(file.toPath()));
		   result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		   
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
	   return result;
	   
   }
   
   @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
   @ResponseBody
   public ResponseEntity<Resource> downloadFile(String fileName){
	   
	   log.info("download file :" + fileName);
	   
	   Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
	   
	   log.info("resource :" + resource);
	   
	   if(resource.exists() == false) {
		   return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
	   }
	   
	   
	   String resourceName = resource.getFilename();
	   
	   String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
	   
	   log.info("resourceName "+resourceName);
	   
	   log.info("resourceOriginalName :" + resourceOriginalName);
	   
	   HttpHeaders header = new HttpHeaders();
	   
	   try {
		
		  // header.add("Content-Disposition", "attachment; filename = " + new String(resourceName.getBytes("utf-8"), "ISO-8859-1"));
		   
		   
		   header.add("Content-Disposition", "attachment; filename = " + URLEncoder.encode(resourceOriginalName, "utf-8"));
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
	   return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
   }

}
