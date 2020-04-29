package com.kmarzecki.trollo.api.card

import com.kmarzecki.trollo.service.CardService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/card")
@CrossOrigin(origins = ["*"], allowCredentials = "true", allowedHeaders = ["*"])
@Validated
class CardController(
        val cardService: CardService
) {

    @GetMapping
    fun getCardsForLanes(
            @RequestParam("boardId") boardId: UUID,
            principal:Principal
    ) = cardService.getCardsForLanes(boardId, principal)

    @PostMapping
    fun create(
            @Valid
            @RequestBody request: CardCreateRequest,
            principal:Principal
    ) = cardService.create(request, principal)
}