import {AuthResponse} from "@/api";

type User = AuthResponse

export class ApiStorage {

    static jwt_key = "jwt_key"
    static USER_KEY = "user_key"

    static setUser(user: User) {
        localStorage.setItem(this.USER_KEY, JSON.stringify(user))
    }

    static getUser(): User {

        if (typeof window !== 'undefined') {
            // Perform localStorage action

            if (localStorage.getItem(this.USER_KEY)) {
                return JSON.parse(localStorage.getItem(this.USER_KEY))
            }
        }

        return null


    }

}