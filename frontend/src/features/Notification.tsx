import {notifications} from "@mantine/notifications";
import {IconX} from "@tabler/icons-react";
import * as React from "react";
import {ExclamationMark} from "tabler-icons-react";

export function showNotificatorError(error: IError) {
    try {
        notifications.show({
            icon: <IconX/>,
            autoClose: 2500,
            title: "Ошибка!",
            message: error.message + " " + error.time,
        })
    } catch (e) {
        console.log(e.message)
        notifications.show({
            icon: <IconX/>,
            autoClose: 2500,
            title: "Ошибка!",
            message: "Ой что-то пошло не так!",
        })
    }

}

export function showNotificatorInfo(message: string) {
    notifications.show({
        icon: <ExclamationMark/>,
        autoClose: 2500,
        message: message
    })
}