package com.example.my_oyo.Service;

import com.example.my_oyo.OyoData;
import com.example.my_oyo.Repositry.OyoRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.my_oyo.exception.FileStorageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class OyoService {
    @Autowired
    private OyoRepositry oyoRepositry;


    public OyoData storeFile(MultipartFile file, String gender,String cityName) {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("hello kunal");
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("invalid file"+fileName);
            }

            OyoData dbFile = new OyoData(fileName, file.getContentType(),gender,file.getBytes(),cityName);

            return oyoRepositry.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public List<OyoData> getFile(String gender){
        return oyoRepositry.findOyoDataByGender(gender);


    }
    public  List<OyoData>getAllFile(){
        List<OyoData>oyoData;

        oyoData=(List<OyoData>)oyoRepositry.findAll();
        return oyoData;

    }
    public  List<OyoData>getFileByCityName(String cityName){
        List<OyoData>oyoData;

        oyoData=(List<OyoData>)oyoRepositry.findOyoDataByCityName(cityName);
        return oyoData;

    }
    public  OyoData getFileByName(String fileName){
        OyoData oyoData=oyoRepositry.findOyoDataByFileName(fileName);
        return  oyoData;
    }


}
