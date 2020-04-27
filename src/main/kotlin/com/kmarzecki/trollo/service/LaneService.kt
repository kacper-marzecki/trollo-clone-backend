package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.board.LaneResponse
import com.kmarzecki.trollo.api.lane.LaneUpdateRequest
import java.util.*

interface LaneService {
    fun update(request: LaneUpdateRequest, laneId: UUID): LaneResponse
}
