package com.example.my_oyo.Controlller;

import com.example.my_oyo.OyoData;
import com.example.my_oyo.Repositry.OyoRepositry;
import com.example.my_oyo.Service.OyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import  java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class OyoController {
    @Autowired
    private OyoService oyoService;
    @CrossOrigin(origins = "http://localhost:3001")
    @Transactional
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadFile(@RequestParam(name="file") MultipartFile file, @RequestParam(name="gender") String gender, @RequestParam(name="cityName")String cityName){
        oyoService.storeFile(file,gender,cityName);

    }
    @CrossOrigin(origins = "http://localhost:3001")
    @Transactional
    @RequestMapping(value="/",method = RequestMethod.GET)
    public List<OyoData> getFile(HttpServletResponse response) throws IOException{
        List<OyoData> oyoData=oyoService.getAllFile();
        return  oyoData;


    }
    @CrossOrigin(origins ="http://localhost:3001")
    @Transactional
    @RequestMapping(value="/filename",method=RequestMethod.GET)
    public  ResponseEntity<ByteArrayResource>getFileByFileName(@RequestParam(name="File") String name) throws  IOException{
        OyoData oyoData=oyoService.getFileByName(name);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(oyoData.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + oyoData.getFileName() + "\"")
                .body(new ByteArrayResource(oyoData.getData(),oyoData.getGender()));

    }

  @CrossOrigin(origins = "http://localhost:3001")
  @Transactional
  @RequestMapping(value = "/city",method = RequestMethod.GET)
  public List<OyoData>getFileByCityName(@RequestParam(name="cityName") String cityName){
        List<OyoData>oyoData=oyoService.getFileByCityName(cityName);
        return  oyoData;
  }


    @CrossOrigin(origins = "http://localhost:3001")
    @Transactional
    @RequestMapping(value="/gend",method = RequestMethod.GET)
    public List<OyoData> getFile(@RequestParam(name="gender1") String gender1, HttpServletResponse response) throws IOException {
        List<OyoData> oyoData=oyoService.getFile(gender1);
        return oyoData;}



        /*for(OyoData name:oyoData){
            System.out.println(name.getFileName());
        }
        String fileBasePath="/Users/oyo/Desktop/";
        String filename1 = "/Users/oyo/Desktop/out.zip";
        System.out.println(response.getOutputStream());
        ServletOutputStream out = response.getOutputStream();



        ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(out));
        for (OyoData fileName1 : oyoData) {
            String fileName=fileName1.getFileName();

            FileSystemResource resource = new FileSystemResource(fileBasePath + fileName);
            System.out.println(resource.getFilename());
            System.out.println(resource.contentLength());
            ZipEntry zipEntry = new ZipEntry(resource.getFilename());
            zipEntry.setSize(resource.contentLength());
            zipOut.putNextEntry(zipEntry);
            System.out.println(zipOut.toString());
            StreamUtils.copy(resource.getInputStream(), zipOut);
            zipOut.closeEntry();
        }

        zipOut.finish();
        zipOut.close();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/zip");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename1 + "\"");

        response.flushBuffer();

    }*/


}
