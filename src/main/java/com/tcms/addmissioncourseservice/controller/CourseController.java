package com.tcms.addmissioncourseservice.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcms.addmissioncourseservice.payload.CourseDto;
import com.tcms.addmissioncourseservice.payload.ModuleDto;
import com.tcms.addmissioncourseservice.services.CourseService;
import com.tcms.addmissioncourseservice.services.FileService;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        CourseDto updatedCourse = courseService.updateCourse(courseDto, courseId);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long courseId) {
        CourseDto courseDto = courseService.getCourse(courseId);
        return ResponseEntity.ok(courseDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDto>> getCourses() {
        List<CourseDto> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/{courseId}/modules")
    public ResponseEntity<List<ModuleDto>> getModulesByCourseId(@PathVariable Long courseId) {
        List<ModuleDto> modules = courseService.getModulesByCourseId(courseId);
        return ResponseEntity.ok(modules);
    }
    //post image upload
    @PostMapping("/image/upload/{courseId}")
    public ResponseEntity<CourseDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Long courseId) throws IOException{
    	CourseDto courseDto = courseService.getCourse(courseId);
    	String fileName=this.fileService.uploadImage(path, image);
    	
    	courseDto.setImageName(fileName);
    	CourseDto updateCourse=this.courseService.updateCourse(courseDto, courseId);
    	return new ResponseEntity<CourseDto>(updateCourse, HttpStatus.OK);
    }
    @GetMapping(value="/img/{imageName}",produces=MediaType.IMAGE_PNG_VALUE)
    public void downloadImage(@PathVariable("imageName")String imageName, HttpServletResponse response) throws IOException {
    	InputStream resourse=this.fileService.getResource(path, imageName);
    	response.setContentType(MediaType.IMAGE_PNG_VALUE);
    	StreamUtils.copy(resourse,response.getOutputStream());
    }
    
}