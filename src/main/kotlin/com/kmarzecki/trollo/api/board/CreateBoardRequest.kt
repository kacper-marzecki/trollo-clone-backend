package com.kmarzecki.trollo.api.board

import com.sun.istack.NotNull

data class CreateBoardRequest (
    @NotNull
    var name: String
)