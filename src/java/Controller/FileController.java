/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import Dao.FileDao;
import Entity.EnFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author ms
 */
@Named
@SessionScoped
public class FileController implements Serializable {

    private EnFile enFile;
    private Part part;
    private FileDao fileDao;
    private List<EnFile> fileList;

    private CountryController CountryController;

    private final String uploadPath = "C:\\Internet\\";

    public String upload() {
        try {
            InputStream input = part.getInputStream();
            File f = new File(this.uploadPath + part.getSubmittedFileName());
            Files.copy(input, f.toPath(), REPLACE_EXISTING);

            String format = "dd-MM-yyyy-hhmmss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String extension = f.getName().substring(f.getName().lastIndexOf("."));
            String fileName = sdf.format(new Date()) + extension;
            File newFile = new File(this.uploadPath + fileName);
            f.renameTo(newFile);

            this.enFile.setName(newFile.getName());
            this.enFile.setPath(newFile.getParent());
            this.enFile.setType(part.getContentType());
            this.getFileDao().create(this.enFile);

        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return "/admin/file/file?faces-redirect=true";
    }

    public void clearForm() {
        this.enFile = new EnFile();
    }

    public String close() {
        return "/admin/index?faces-redirect=true";
    }

    public void deleteConfirm(EnFile enFile) {
        this.enFile = enFile;

    }

    public String delete() {
        this.getFileDao().delete(this.enFile);
        return "/admin/file/file?faces-redirect=true";

    }

    public void updateForm(EnFile enFile) {
        this.enFile = enFile;
    }

    /*public void update() {
        this.getFileDao().update(this.enFile);
        this.enFile = new EnFile();
        this.clearForm();
    }*/
    public EnFile getEnFile() {
        if (this.enFile == null) {
            this.enFile = new EnFile();
        }
        return enFile;
    }

    public void setEnFile(EnFile enFile) {
        this.enFile = enFile;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public FileDao getFileDao() {
        if (this.fileDao == null) {
            this.fileDao = new FileDao();
        }
        return fileDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public List<EnFile> getFileList() {
        this.fileList = this.getFileDao().findAll();
        return fileList;
    }

    public void setFileList(List<EnFile> fileList) {
        this.fileList = fileList;
    }

    public CountryController getCountryController() {
        if (this.CountryController == null) {
            this.CountryController = new CountryController();
        }
        return CountryController;
    }

    public void setCountryController(CountryController CountryController) {
        this.CountryController = CountryController;
    }

}
