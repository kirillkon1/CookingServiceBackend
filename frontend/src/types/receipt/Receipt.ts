import {IUser} from "@/types/user/IUser";

export interface SimpleReceipt {
    id: number,
    name: string,
    rating: number,
    image_url: string

    categories: Category[]
}


export interface Receipt {
    id: number,
    name: string,
    description: string,
    rating: number,
    calories: number,
    amount_of_portions: number,
    image_url: string

    create_date: any

    last_modify_date: any

    categories: Category[]

    user: IUser

    compositions: Composition[]
}


export interface Category {
    id: number,
    name: string
    type: string
}


export interface Composition {
    id: number,
    amount: number,
    description: string,
    ingredient: Ingredient
    metric: Metric
}

export interface Ingredient {
    id: number,
    name: string
}


export interface Metric {
    id: number,
    name: string
}