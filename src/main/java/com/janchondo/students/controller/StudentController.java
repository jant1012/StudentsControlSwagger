package com.janchondo.students.controller;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.janchondo.students.entity.Student;
import com.janchondo.students.service.StudentService;
@Api(value = "SwaggerRestController", description = "REST Apis related to Student Controller")
@RestController
public class StudentController {
	private StudentService studentService;
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	@ApiOperation(value = "Get all students registered ", response = Student.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/students")
	public ResponseEntity<List<Student>> searchAll() {
		List<Student> studentsList = studentService.searchAllStudents();
		return new ResponseEntity<List<Student>>(studentsList,HttpStatus.OK);
	}
	@ApiOperation(value = "Save one student in the system ", response = Student.class)
	@PostMapping("/students/save")
	public void saveStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
	}
	@ApiOperation(value = "Delete one student to the system ", response = Student.class)
	@GetMapping("/students/delete/{studentID}")
	public void deleteStudent(@PathVariable("studentID") String studentID) {
		studentService.deleteStudent(studentID);
	}
	@ApiOperation(value = "Update one student to the system", response = Student.class)
	@PatchMapping("/students/update/{studentID}")
	public void updateStudent(@PathVariable("studentID") String studentID,@RequestBody Student student){
		studentService.updateStudent(studentID, student);
	}
	@ApiOperation(value = "Search student by Id in the system", response = Student.class)
	@GetMapping("/students/{studentID}")
	public ResponseEntity<Student> searchStudentById(@PathVariable("studentID") String studentID) {
		 Student student= studentService.findStudentByID(studentID);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
}