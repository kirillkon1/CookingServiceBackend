import * as React from 'react'
import {useState} from "react";
import styles from "../authForm.module.css";
import {
    Button,
    Container,
    Text,
    TextInput,
    Divider,
    Flex,
    PasswordInput,
    Checkbox
} from "@mantine/core";
import {ArrowNarrowLeft} from "tabler-icons-react";
import Link from "next/link";
import {showNotificatorError} from "@/features/Notification";


interface UserForm {
    username: string;
    password: string;
    email: string;

    form_checkbox: boolean
}

const RegistrationForm = () => {
    const [user, setUser] = useState<UserForm>({
        username: '123',
        password: '123',
        email: '123@mail.ru',
        form_checkbox: false
    });
    const [answer, setAnswer] = useState<IAuth>(null);
    const [buttonStatus, setButtonStatus] = useState(false)

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUser({
            ...user,
            [event.target.name]: event.target.value,
        });
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        setButtonStatus(true)

        const response = await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        });

        setButtonStatus(false)

        if (!response.ok) {
            showNotificatorError(response.status, await response.json()<IError>);
            setAnswer(null)
            return;
        }

        const data = await response.json();
        console.log(data)

        if (data.token) {
            setAnswer(data);
        } else {
            showNotificatorError(response.status, await response.json()<IError>);
        }
    };

    return (
        <div>
            <Container maw={400} h={"auto"} mx="auto" px="xs" className={styles.box}>


                <Link href="/auth">
                    <ArrowNarrowLeft
                        size={16}
                        strokeWidth={3}
                        color={'grey'}
                    />
                </Link>

                <Text className={styles.header_text}>Регистрация</Text>

                <Divider my="xs" label="Форма регистрации" labelPosition="center" color="#000000"/>

                <form onSubmit={handleSubmit}>
                    <TextInput value={user.username} name="username" label="Имя пользователя" lh={2} mt='10px'
                               placeholder="Введите имя пользователя"
                               onChange={handleChange}/>
                    <PasswordInput value={user.password} name="password" label="Пароль" lh={2} mt='10px'
                                   placeholder="Введите пароль" error=""
                                   onChange={handleChange}/>


                    <TextInput value={user.email} name="email" label="Электронная почта" lh={2} mt='10px' type="email"
                               placeholder="Введите электронную почту"
                               onChange={handleChange}

                    />

                    <Checkbox
                        mt="25px"
                        name="form_checkbox"
                        label="Я согласен отдать все свои деньги и дом"
                        onChange={handleChange}
                        withAsw
                    />

                    <Flex align="flex-end" justify="center" mt="5em" gap='xs'>
                        <Button className={styles.button_form}
                                type="submit"


                                loading={buttonStatus}
                                loaderPosition="center"


                                variant="gradient"
                                gradient={{from: "teal", to: "blue", deg: 110}}
                        >Зарегистрироваться</Button>
                    </Flex>

                </form>
            </Container>
        </div>

    );

};
export default RegistrationForm