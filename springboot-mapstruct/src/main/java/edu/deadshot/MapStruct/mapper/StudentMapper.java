package edu.deadshot.MapStruct.mapper;

import edu.deadshot.MapStruct.model.Student;
import edu.deadshot.MapStruct.model.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "studentContactDTO.email", source = "email")
    @Mapping(target = "studentContactDTO.mobile", source = "mobile")
    StudentDTO studentToStudentDTO(Student student);

}
