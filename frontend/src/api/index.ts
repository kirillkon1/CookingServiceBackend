import axios, {AxiosResponse} from "axios";
import {config} from "@/config";
import {ApiStorage} from "@/domains/apiStorage";


export const httpClientApi = axios.create(
    {
        baseURL: config.backend_url

    }
)
httpClientApi.interceptors.request.use(
    function (config) {

        const token = ApiStorage.getUser()?.token

        if (!token) {
            return config
        }

        config.headers.set("Authorization", "Bearer " + token)
        return config
    }
)


export interface LoginRequest {
    username: string;
    password: string;
}

export interface AuthResponse {
    token: string
    username: string
    user_id: number
}

export async function login(form: LoginRequest) {
    const res = await httpClientApi.post<AuthResponse, AxiosResponse<AuthResponse>>(
        "/auth/login",
        form,
        {timeout: 5000}
    )
    return res.data
}

export interface RegisterRequest {
    username: string,
    password: string,
    email: string
}

export async function register(form: RegisterRequest) {
    const res = await httpClientApi.post<AuthResponse, AxiosResponse<AuthResponse>>(
        "/auth/register",
        form,
        {timeout: 7000}
    )

    return res.data

}