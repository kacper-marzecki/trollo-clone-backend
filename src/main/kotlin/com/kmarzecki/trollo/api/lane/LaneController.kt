package com.kmarzecki.trollo.api.lane

import com.kmarzecki.trollo.service.LaneService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/lane")
@CrossOrigin(origins = ["*"], allowCredentials = "true", allowedHeaders = ["*"])
@Validated
class LaneController(
        val laneService: LaneService
) {
    @PutMapping("/{id}")
    fun update(
            @RequestBody request: LaneUpdateRequest,
            @PathVariable id: UUID,
            principal: Principal
    ) = laneService.update(request, id, principal)

    @PostMapping
    fun create(
            @Valid
            @RequestBody request: LaneCreateRequest,
            principal:Principal
    ) = laneService.create(request, principal)
}