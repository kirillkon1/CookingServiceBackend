import {ApiStorage} from "@/domains/apiStorage";
import {Avatar, Button} from "@mantine/core";
import React, {useEffect, useState} from "react";
import {IconExternalLink} from "@tabler/icons-react";
import styles from "./header.module.css"

export function HeaderAvatar() {

    const [username, setUsername] = useState<string>()

    useEffect(() => {
        setUsername(ApiStorage.getUser().username)
    }, [])

    function avatar() {

        if (!username) {
            return (
                <Button className={styles.avatar_button} component="a" href="/auth" variant="outline"
                        leftIcon={<IconExternalLink size="0.9rem"/>}>
                    Войти
                </Button>
            )
        }

        return <Avatar size="md" radius="md" color='green'>{username.slice(0, 2)}</Avatar>

    }

    return (
        <div>
            {avatar}
        </div>
    )

}