import {Input, Select, TextInput} from "@mantine/core";
import React, {useEffect, useState} from "react";
import styles from "../header.module.css"
import AsyncSelect from "react-select/async";
import {SimpleReceipt} from "@/types/receipt/Receipt";
import {searchSimpleReceipt} from "@/api/receipt.api";

export function HeaderSearch() {

    const [data, setData] = useState<SimpleReceipt>()

    const [search, setSearch] = useState<string>()


    useEffect(() => {
        console.log("search " + search)

        // const res =  searchSimpleReceipt(search, 10)

    }, [search])

    return (
        <div>
            {/*<Input*/}
            {/*    d*/}

            {/*    placeholder="Поиск блюд"*/}
            {/*    className={styles.header_search}*/}
            {/*/>*/}
            <TextInput/>
            {/*<Select*/}
            {/*    label="Choose employee of the month"*/}
            {/*    placeholder="Pick one"*/}
            {/*    itemComponent={SelectItem}*/}
            {/*    data={data}*/}
            {/*    searchable*/}
            {/*    maxDropdownHeight={400}*/}
            {/*    nothingFound="Nobody here"*/}
            {/*    filter={(value, item) =>*/}
            {/*        item.label.toLowerCase().includes(value.toLowerCase().trim()) ||*/}
            {/*        item.description.toLowerCase().includes(value.toLowerCase().trim())*/}
            {/*    }*/}
            {/*/>*/}


        </div>

    )
}