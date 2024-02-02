package uz.zako.zakoBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.ModulesPayload;
import uz.zako.zakoBackend.service.ModulesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ModulesController {
    private final ModulesService modulesService;
    @GetMapping("/auth/modules/find/{id}")
    public ResponseEntity<Result> findById(@PathVariable Long id){
        Result result = modulesService.findById(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }

    @PostMapping("/admin/modules/save")
    public ResponseEntity<Result> addModule(@RequestBody ModulesPayload modulesPayload){
        Result result = modulesService.saveModule(modulesPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }

    @PutMapping("/admin/modules/edit/{id}")
    public ResponseEntity<Result> editModule(@PathVariable Long id, @RequestBody ModulesPayload modulesPayload){
        Result result = modulesService.editModule(id, modulesPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }

    @DeleteMapping("/admin/modules/delete/{id}")
    public ResponseEntity<Result> deleteModule(@PathVariable Long id){
        Result result = modulesService.delete(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }

    @GetMapping("/auth/modules/{id}")
    public ResponseEntity<Result> findByCourseID(@PathVariable Long id){
        Result result = modulesService.findAllByCourseId(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }
}
