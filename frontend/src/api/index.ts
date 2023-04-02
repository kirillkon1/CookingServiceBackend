import axios from "axios";
import {config} from "@/config";

export const httpClientApi = axios.create(
    {
        baseURL: config.backend_url
        // baseURL: "http://localhost:8080"
    }
)


console.log("123 " + config.backend_url)
// export const httpClientImageApi = axios.create