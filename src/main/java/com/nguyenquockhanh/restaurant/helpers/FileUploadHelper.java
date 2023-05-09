package com.nguyenquockhanh.restaurant.helpers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadHelper {

	public static String uploadImage(MultipartFile multipartFile, ServletContext servletContext, String pathType) {
		try {
			String fileName = generateFileName(multipartFile.getOriginalFilename());
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(servletContext.getRealPath("/uploads/images/" + pathType + "/" + fileName));
			Files.write(path, bytes);
			return fileName;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static boolean deleteImage(String fileName, ServletContext servletContext, String pathType) {
		try {
			Path path = Paths.get(servletContext.getRealPath("/uploads/images/" + pathType +"/" + fileName));
			Files.delete(path);
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	public static String uploadFile(MultipartFile multipartFile, String direction, ServletContext servletContext) {
		try {
			String fileName = generateFileName(multipartFile.getOriginalFilename());
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(servletContext.getRealPath("/uploads/" + direction + fileName));
			Files.write(path, bytes);
			return fileName;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static boolean deleteFile(String fileName, String direction, ServletContext servletContext) {
		try {
			Path path = Paths.get(servletContext.getRealPath("/uploads/" + direction + fileName));
			Files.deleteIfExists(path);
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	private static String generateFileName(String fileName) {
		String name = UUID.randomUUID().toString().replace("-", "");
		int index = fileName.lastIndexOf(".");
		String ext = fileName.substring(index);
		return name + ext;
	}
	
}