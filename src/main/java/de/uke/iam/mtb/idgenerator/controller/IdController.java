package de.uke.iam.mtb.idgenerator.controller;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.uke.iam.mtb.idgenerator.util.IdCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
public class IdController {

    @Operation(summary = "Get id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "<id>"),
            @ApiResponse(responseCode = "400", description = "Either first name, last name or birthday are null or empty") })
    @GetMapping("/id_generator/{first_name}/{last_name}/{birthday}")
    public ResponseEntity<String> generateId(
            @Parameter(description = "Patient's first name. Cannot be null or empty", required = true) @PathVariable("first_name") String firstName,
            @Parameter(description = "Patient's last name. Cannot be null or empty", required = true) @PathVariable("last_name") String lastName,
            @Parameter(description = "Patient's birthday. Cannot be null. Expects birthday in format yyyy-mm-dd", required = true) @PathVariable Date birthday) {
        HttpStatus status;
        if (firstName != null && lastName != null && birthday != null && !firstName.isEmpty() && !lastName.isEmpty()) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<String>(IdCreator.createId(firstName, lastName, birthday), status);
    }
}
