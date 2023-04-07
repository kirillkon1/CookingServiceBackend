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
import {showNotificatorError, showNotificatorInfo} from "@/features/Notification";
import {register} from "@/api";
import {ApiStorage} from "@/domains/apiStorage";
import {AxiosError} from "axios";


interface UserForm {
    username: string,
    password: string,
    email: string
}

const RegistrationForm = () => {
    const [userForm, setUserForm] = useState<UserForm>({
        username: '',
        password: '',
        email: '',
    });

    const [buttonStatus, setButtonStatus] = useState(false)

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUserForm({
            ...userForm,
            [event.target.name]: event.target.value,
        });
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        setButtonStatus(true)

        try{
            const data = await register(userForm)

            if (data.token) {
                showNotificatorInfo(data.username + " успешно был создан!")
                ApiStorage.setUser(data)

                // await router.push("/")
            }

        }catch (e: AxiosError<IError>){
            showNotificatorError(e.response?.data)
        }finally {
            setButtonStatus(false)
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
                    <TextInput value={userForm.username} name="username" label="Имя пользователя" lh={2} mt='10px'
                               placeholder="Введите имя пользователя"
                               onChange={handleChange}/>
                    <PasswordInput value={userForm.password} name="password" label="Пароль" lh={2} mt='10px'
                                   placeholder="Введите пароль" error=""
                                   onChange={handleChange}/>


                    <TextInput value={userForm.email} name="email" label="Электронная почта" lh={2} mt='10px' type="email"
                               placeholder="Введите электронную почту"
                               onChange={handleChange}

                    />

                    <Checkbox
                        mt="25px"
                        name="form_checkbox"
                        label="Я согласен предоставить своё имя и пароль для великой цели!"
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