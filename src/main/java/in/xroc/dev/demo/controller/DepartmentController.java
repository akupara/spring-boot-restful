package in.xroc.dev.demo.controller;

import com.github.shihyuho.jackson.databind.SerializeAllExcept;
import in.xroc.dev.demo.domain.Department;
import in.xroc.dev.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Demonstrate how to implement basic restful apis
 * <p>
 * TODO:
 * 1. TypedJsonSerializeFilter to inlcude/excludes properties dynamically
 * 2. Error Handling
 * 3. Pagination
 * 4. Filtering, Sorting
 * 5. API version
 * 6. Auth and audit using spring security mechanism
 * 7. Rate limiting and other spring actuator features
 * 8. Unit Test & Integration Test (Focus on Integration Test with a in-memory DB using spring boot test.)
 * 9. HTTP method override
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping()
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @SerializeAllExcept({"createdBy", "updatedBy"})
    @PostMapping()
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        // TODO: remove it after implementing auth feature
        department.setCreatedBy("sys");
        department.setUpdatedBy("sys");

        Department created = departmentRepository.save(department);

        return ResponseEntity.created(linkTo(methodOn(this.getClass()).getDepartmentByUid(created.getCode())).toUri())
                .body(created);
    }

    @SerializeAllExcept({"createdBy", "updatedBy"})
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentByUid(@PathVariable(value = "id") String id) {
        Optional<Department> optional = departmentRepository.findById(id);
        return optional.map(department -> ResponseEntity.ok().body(department))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SerializeAllExcept({"createdBy", "updatedBy"})
    @RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") String id,
                                                       @Valid @RequestBody Department newDepartment) {
        Optional<Department> optional = departmentRepository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Department existingDepartment = optional.get();
        existingDepartment.setCode(newDepartment.getCode());
        existingDepartment.setName(newDepartment.getName());
        existingDepartment.setLocation(newDepartment.getLocation());

        Department updatedDepartment = departmentRepository.save(existingDepartment);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable(value = "id") String id) {
        Optional<Department> optional = departmentRepository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        departmentRepository.delete(optional.get());
        return ResponseEntity.ok().build();

    }
}
