package com.siny.data.model

data class DetailData(
    var cd1: Int = 0,       //신구약 구분 1:구약, 2:신약
    var cd2: Int = 0,       //명칭에 대한 코드 : 1:창세기, 2:출애굽기
    var cd3: Int = 0,       //장
    var cd4: Int = 0,       //절
    var txt: String = "",   //내용
)