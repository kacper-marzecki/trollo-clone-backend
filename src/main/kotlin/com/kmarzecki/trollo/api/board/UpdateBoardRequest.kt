package com.kmarzecki.trollo.api.board

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UpdateBoardRequest {
    var name: String? = null
}