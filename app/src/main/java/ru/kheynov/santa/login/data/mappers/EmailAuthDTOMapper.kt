package ru.kheynov.santa.login.data.mappers

import remote.dto.LoginViaEmailDTO
import remote.dto.RegisterViaEmailDTO
import ru.kheynov.santa.login.domain.model.AuthDTO

internal fun AuthDTO.SignInViaEmail.toRegisterDTO(): RegisterViaEmailDTO {
    return RegisterViaEmailDTO(
        username = userInfo.name,
        email = email,
        password = password,
    )
}

internal fun AuthDTO.LoginViaEmail.toLoginDTO(): LoginViaEmailDTO {
    return LoginViaEmailDTO(
        email = email,
        password = password
    )
}