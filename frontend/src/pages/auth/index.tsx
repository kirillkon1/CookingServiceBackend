import * as React from 'react'
import {useState} from "react";
import {
    Button,
    Container,
    Text,
    TextInput,
    Divider,
    Flex,
    PasswordInput,
} from "@mantine/core";
import {Home} from "tabler-icons-react";
import Link from "next/link";
import styles from "./authForm.module.css"
import {showNotificatorError, showNotificatorInfo} from "@/features/Notification";
import {AxiosError} from "axios";
import {httpClientApi} from "@/api";


interface UserForm {
    username: string;
    password: string;
}

const LoginForm = () => {


    const [user, setUser] = useState<UserForm>({
        username: '',
        password: '123'
    });

    const [buttonStatus, setButtonStatus] = useState(false)

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUser({
            ...user,
            [event.target.name]: event.target.value,
        });
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        let response
        try {
            response = await httpClientApi.post(
                "/auth/login",
                user
            )


            const data = response.data

            if (data.token) {
                // setAnswer(data);



                showNotificatorInfo(data.username + " успешно вошел!")

                // await router.push("/home")
            }

        } catch (e: AxiosError<IError>) {
            console.log(e)
            showNotificatorError(e.response.status, e.response?.data)
        } finally {
            setButtonStatus(false)
        }
    };

    return (
        <div>


            <Container maw={400} h={"auto"} mx="auto" px="xs" className={styles.box}>

                <div className={styles.navigate_row}>
                    <Link href="#">
                        <Home
                            className={styles.form_animation}
                            size={18}
                            strokeWidth={2.5}
                            color={'#818181'}
                        />
                    </Link>

                    <Link href="/auth/registration">
                        <Text className={`${styles.form_text} ${styles.form_animation}`}> Создать новый профиль</Text>
                    </Link>
                </div>

                <Text className={styles.header_text}>Войти в профиль</Text>

                <Divider my="xs" label="Форма авторизации" labelPosition="center" color="#000000"/>

                <form onSubmit={handleSubmit}>
                    <TextInput value={user.username} name="username" label="Имя пользователя" lh={2} mt='10px'
                               placeholder=" Имя пользователя или email"
                               onChange={handleChange}/>
                    <PasswordInput value={user.password} name="password" label="Пароль" lh={2} mt='10px'
                                   placeholder="Введите пароль" error=""
                                   onChange={handleChange}/>
                    <Link href="/#">
                        <Text color="blue" size="12px" mt="10px">Забыли пароль?</Text>
                    </Link>

                    <Flex align="flex-end" justify="center" mt="10em" gap='xs'>
                        <Button
                            type="submit"
                            w="200px"
                            pos='absolute'
                            loading={buttonStatus}
                            loaderPosition="center"


                            variant="gradient"
                            gradient={{from: "teal", to: "blue", deg: 110}}
                        >Войти</Button>
                    </Flex>

                </form>
            </Container>
        </div>

    );

};
export default LoginForm