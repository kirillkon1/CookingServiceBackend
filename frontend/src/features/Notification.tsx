import {notifications} from "@mantine/notifications";
import {IconX} from "@tabler/icons-react";
import * as React from "react";
import {ExclamationMark} from "tabler-icons-react";

export function showNotificatorError(status: number, error: IError) {
    notifications.show({
        icon: <IconX />,
        autoClose: 2500,
        title: "Ошибка! Код " + status,
        message: error.message + " " + error.time,
    })
}

export function showNotificatorInfo(message: string) {
    notifications.show({
        icon: <ExclamationMark />,
        autoClose: 2500,
        message: message
    })
}