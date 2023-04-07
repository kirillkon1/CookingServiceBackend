import {httpClientApi} from "@/api/index";
import {AxiosResponse} from "axios";
import {Ingredient} from "@/types/receipt/Receipt";

export async function getIngredients() {
    const res = await httpClientApi.get<Ingredient[], AxiosResponse<Ingredient[]>>(
        "/api/ingredients"
    )

    return res.data
}

export async function getIngredientById(id: number) {
    const res = await httpClientApi.get<Ingredient, AxiosResponse<Ingredient>>(
        "api/ingredients/" + id
    )

    return res.data
}


export interface SearchIngredientInterface {
    name: string,
    total: number,
    contains: boolean
}

export async function searchIngredients(req: SearchIngredientInterface) {
    const res = await httpClientApi.get<Ingredient[], AxiosResponse<Ingredient[]>>(
        "api/ingredients/search",
    {params:{
            name: req.name,
            max: req.total,
            contains: req.contains
        }
    }
    )

    return res.data

}

interface IngredientRequest {
    name: string
}

export async function saveIngredient(req: IngredientRequest) {
    //Need a jwt
    const res = await httpClientApi.post<Ingredient>(
        "api/ingredients",
        req
    )

    return res.data
}