import {httpClientApi} from "@/api/index";
import {Receipt, SimpleReceipt} from "@/types/receipt/Receipt";
import {AxiosResponse} from "axios";

export async function getReceipts(name: string, total: number) {
    const res = await httpClientApi.get<Receipt, AxiosResponse<Receipt>>(
        "api/receipts/search",
        {
            params: {
                name: name,
                total: total
            }
        }
    )

    return res.data
}

export async function getReceiptById(id: number) {
    const res = await httpClientApi.get<Receipt, AxiosResponse<Receipt>>(
        `api/receipts/${id}`
    )

    return res.data
}

export async function searchSimpleReceipt(name: string, total: number) {
    const res = await httpClientApi.get<SimpleReceipt[], AxiosResponse<SimpleReceipt[]>>(
        "api/receipts/simple/search",
        {
            params: {
                name: name,
                total: total
            }
        }
    )

    return res.data
}