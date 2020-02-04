package com.example.my_oyo;

import javax.persistence.*;

@Entity
@Table(name="OYO_LIFE")
public class OyoData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    private String fileName;

    private String FileType;

    private  String gender;
    private  String cityName;

    @Lob
    private byte[] data;

    public OyoData(String fileName, String fileType, String gender, byte[] data,String cityName) {
        this.fileName = fileName;
        FileType = fileType;
        this.gender = gender;
        this.data = data;
        this.cityName=cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public OyoData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
