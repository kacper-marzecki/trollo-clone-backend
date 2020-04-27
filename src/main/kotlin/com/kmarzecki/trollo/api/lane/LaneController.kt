package com.kmarzecki.trollo.api.lane

import com.kmarzecki.trollo.api.board.LaneResponse
import com.kmarzecki.trollo.repository.LaneRepository
import com.kmarzecki.trollo.service.LaneService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/lane")
@CrossOrigin(origins = ["*"], allowCredentials = "true", allowedHeaders = ["*"])
class LaneController(
        val laneService: LaneService
) {
    @PutMapping("/{id}")
    fun update(
            @RequestBody request: LaneUpdateRequest,
            @PathVariable id: UUID
    ) = laneService.update(request, id)
}