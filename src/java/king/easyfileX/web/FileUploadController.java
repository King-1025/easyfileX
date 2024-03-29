package king.easyfileX.web;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import king.easyfileX.exception.StorageException;
import king.easyfileX.exception.StorageFileNotFoundException;
import king.easyfileX.model.Result;
import king.easyfileX.service.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ResponseBody 
    @GetMapping("/fileList")
    public Result listUploadedFiles() throws IOException {
       Result result=new Result();

       try {
            result.setContent(
            storageService.
            loadAll().
            map(path -> MvcUriComponentsBuilder.fromMethodName(
                FileUploadController.class,
                "serveFile",
                path.getFileName().toString()).build().toString()
              ).collect(Collectors.toList()));
	} catch (Exception e){
              e.printStackTrace();	      
              result.setStatus(Result.FAILED);
	      result.setContent("list file failed!");
	}
    
       return result;
    }

    @ResponseBody
    @GetMapping("/files/{filename:.+}")
    public Object serveFile(@PathVariable String filename) {

	try {
              Resource file = storageService.loadAsResource(filename);
              return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	} catch(Exception e) {
              e.printStackTrace();	      
              return new Result(Result.FAILED,"Not Found!");
	}
    }

    @ResponseBody 
    @PostMapping("/uploadFile")
    public Result handleFileUpload(
		    @RequestParam("file") MultipartFile file) {
        Result result=new Result();
        
	try {
              storageService.store(file);
              result.setContent("You successfully uploaded " + file.getOriginalFilename() + "!");
	} catch (Exception e){
              e.printStackTrace();	      
              result.setStatus(Result.FAILED);
	      result.setContent("upload file failed!");
	}

        return result;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
