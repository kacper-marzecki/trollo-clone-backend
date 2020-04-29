package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.board.LaneResponse
import com.kmarzecki.trollo.api.lane.LaneCreateRequest
import com.kmarzecki.trollo.api.lane.LaneUpdateRequest
import java.security.Principal
import java.util.*

interface LaneService {
    fun update(request: LaneUpdateRequest, laneId: UUID, principal: Principal): LaneResponse
    fun create(request: LaneCreateRequest, principal: Principal): LaneResponse
}
