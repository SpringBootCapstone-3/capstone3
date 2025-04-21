package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Guarantee;
import com.example.capstone3.Service.GuaranteeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/guarantee")
@RequiredArgsConstructor
public class GuaranteeController {
    private final GuaranteeService guaranteeService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllGuarantees(){
        return ResponseEntity.status(200).body(guaranteeService.getAllGuarantees());
    }
    //add
    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Guarantee guarantee){
        guaranteeService.addGuarantee(guarantee);
        return ResponseEntity.status(200).body(new ApiResponse("Added"));
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity updateGuarantee(@PathVariable Integer id,@RequestBody @Valid Guarantee guarantee){
        guaranteeService.updateGuarantee(id, guarantee);
        return ResponseEntity.status(200).body(new ApiResponse("Updated"));
    }
    //delete
    @DeleteMapping("/del/{id}")
    public ResponseEntity delGuarantee(@PathVariable Integer id){
        guaranteeService.deleteGuarantee(id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }

}
