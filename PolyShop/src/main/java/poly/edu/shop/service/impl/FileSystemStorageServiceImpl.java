package poly.edu.shop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.shop.config.StorageProperties;
import poly.edu.shop.exception.StorageException;
import poly.edu.shop.service.StorageService;

@Service
public class FileSystemStorageServiceImpl implements StorageService{
	
//	//duong dan goc de luu file hinh
//	private final Path roootLocation;
//	
//	//tao ra file luu tru dua vao id duoc truyen vao
//	@Override
//	public String getStoredFileName(MultipartFile file, String id) {
//		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
//		return "p" + id + "." + ext;
//	}
//	
//	//constructor truyen cac thong tin cho cau hinh luu tru
//	public FileSystemStorageServiceImpl(StorageProperties properties) {
//		this.roootLocation = Paths.get(properties.getLocation());
//		 
//	}
//	
//	
//	//Luu file tu multipartFile
//	@Override
//	public void store(MultipartFile file, String storedFileName) {
//		try {
//			if (file.isEmpty()) {
//				throw new StorageException("Failed to store empty file!");
//			}
//			
//			Path destinationFile = this.roootLocation.resolve(Paths.get(storedFileName))
//					.normalize().toAbsolutePath();
//			
//			if (!destinationFile.getParent().equals(this.roootLocation.toAbsolutePath())) {
//				throw new StorageException("Cannot store file outside current directory");
//			}
//			try(InputStream inputStream = file.getInputStream()){
//				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
//			}
//		} catch (Exception e) {
//			throw new StorageException("Failed to store file", e);
//		}
//	}
//	
//	//nap noi dung cua file duoi dang Resource
//	@Override
//	public Resource loadAsResource(String filename) {
//		try {
//			Path file = load(filename);
//			Resource resource = new UrlResource(file.toUri());
//			
//			if (resource.exists() || resource.isReadable()) {
//				return resource;
//			}
//			
//			throw new StorageException("Could not read file: "+ filename);
//		} catch (Exception e) {
//			throw new StorageException("Could not read file: "+ filename);
//		}
//	}
//	
//	@Override
//	public Path load(String filename) {
//		return roootLocation.resolve(filename);
//	}
//	
//	//xoa cac file khong can thiet
//	@Override
//	public void delete (String storedFilename) throws IOException {
//		Path destinationFile = roootLocation.resolve(Paths.get(storedFilename))
//					.normalize().toAbsolutePath();
//		
//		Files.delete(destinationFile);
//	}
//	
//	//khoi tao cac thu muc
//	@Override
//	public void init() {
//		try {
//			Files.createDirectories(roootLocation);
////			System.out.println(roootLocation.toString());
//		} catch (Exception e) {
//			throw new StorageException("Could not initialize storage", e);
//		}
//	}
}
