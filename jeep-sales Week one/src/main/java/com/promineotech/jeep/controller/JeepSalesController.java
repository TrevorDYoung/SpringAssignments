package com.promineotech.jeep.controller;

import com.promineotech.jeep.entitiy.Jeep;
import com.promineotech.jeep.entitiy.JeepModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"), servers = {
        @Server(url = "http:localhost:8080", description = "Local server.")})
@RequestMapping("/jeeps")
public interface JeepSalesController {

    @Operation(
            summary = "Returns list of Jeeps",
            description = "Returns a list of Jeeps given an optional model and or trim",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "A list of Jeeps is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Jeep.class))),
                    @ApiResponse(responseCode = "400",
                            description = "The request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404",
                            description = "No Jeeps where found with the input criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {//no need to type out required because the default is false.
                @Parameter(name = "model", allowEmptyValue = false,
                        description = "The model name (i.e., 'WRANGLER')"),
                @Parameter(name = "trim", allowEmptyValue = false,
                    description = "The trim level (i.e., 'Sport')")})
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<Jeep> fetchJeeps(@RequestParam JeepModel model,
                          @RequestParam String trim);

}
