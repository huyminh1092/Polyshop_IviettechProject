package poly.edu.shop.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	@Autowired
	HttpServletRequest request;
	
	
	public void saveFile(MultipartFile file, String path, String fileName) throws IOException {
		Path pathh = Paths.get(path);
		if (!Files.exists(pathh)) {
			Files.createDirectories(pathh);
		}
		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = pathh.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new IOException("Can not save file "+fileName);
		}
	}
}
