package uz.zako.zakoBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.ConnectionPayload;
import uz.zako.zakoBackend.service.ConnectionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ConnectionController {

    private final ConnectionService connectionService;

    //save connection
    @PostMapping("/admin/connection/save")
    public ResponseEntity<?> addConnection(@RequestBody ConnectionPayload connectionPayload){
        Result result= connectionService.saveConnection(connectionPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }


    @PutMapping("/admin/connection/edit/{id}")
    public ResponseEntity<?> editConnection(@RequestBody ConnectionPayload connectionPayload, @PathVariable Long id){
        Result result=connectionService.editConnection(id,connectionPayload);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }


    @GetMapping("/auth")
    public ResponseEntity<?> getConnection(){
        Result result=connectionService.getConnection();
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }

    @GetMapping("/admin/connection/find/{id}")
    public ResponseEntity<?> findConnection(@PathVariable Long id){
        Result result=connectionService.findConnection(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
    }

    @DeleteMapping("/admin/connection/delete/{id}")
        public ResponseEntity<?> deleteConnection(@PathVariable Long id){
        Result result=connectionService.deleteConnection(id);
        return ResponseEntity.status(result.isStatus()?200:400).body(result);
        }

}
