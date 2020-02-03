package com.unbxd.hummus.controllers;


import com.unbxd.hummus.execptions.SchemaNotFoundException;
import com.unbxd.hummus.execptions.ServiceException;
import com.unbxd.hummus.execptions.ValidationException;
import com.unbxd.hummus.model.Schema;
import com.unbxd.hummus.services.SchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RequestHandler {

    @Autowired
    private SchemaService schemaService;

    @RequestMapping("/")
    String ping() {
        return "Hummus!";
    }

    @RequestMapping(value = "/addSchema",method = RequestMethod.POST)
    ResponseEntity<String> addSchema(@RequestBody Schema schema) {
        try {
            schemaService.addSchema(schema);
        }
        catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok("Hummus!\nschema got added successfully");
    }

    @RequestMapping(value = "/getSchema/{siteKey}",method = RequestMethod.GET)
    ResponseEntity getSchema(@PathVariable String siteKey) {
        try {
            Schema schema = schemaService.getSchema(siteKey);
            return ResponseEntity.ok(schema);
        }
        catch (SchemaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}
