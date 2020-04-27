package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.board.LaneResponse
import com.kmarzecki.trollo.api.lane.LaneUpdateRequest
import com.kmarzecki.trollo.exception.ElementNotFountException
import com.kmarzecki.trollo.repository.LaneRepository
import com.kmarzecki.trollo.util.str
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class LaneServiceImpl(
        val laneRepository: LaneRepository
) : LaneService {
    override fun update(request: LaneUpdateRequest, laneId: UUID): LaneResponse {
        val lane = laneRepository.findByIdOrNull(laneId)
                ?: throw ElementNotFountException("Lane", laneId.str)
        lane.name = request.name
        lane.positionInBoard = request.positionInBoard
        return laneRepository.save(lane)
                .let {
                    LaneResponse(
                            id = it.id!!,
                            name = it.name,
                            positionInBoard = it.positionInBoard
                    )
                }
    }
}