// import {Text} from "@mantine/core";
//
// interface ReceiptProps {
//     name: string
//     rating: number
//
//     image_url: string
// }
//
// async function getData() {
//     const ingredients_url = "http://localhost:8080/api/ingredients/1"
//
//     const res = await fetch(ingredients_url)
//
//     if (!res.ok) {
//         throw new Error('Failed to fetch data');
//     }
//     console.log("123")
//     console.log(res.json()<Ingredient>)
//
//     const ingredient = await res.json()
//
//     return ingredient
// }
//
//
// export default async function ReceiptCard({data}) {
//
//     return (
//         <>
//             <Text>{data}</Text>
//         </>
//     )
// }

import {useEffect, useState} from "react";
import {Text, TextInput} from "@mantine/core";
import * as React from "react";

export default function MyComponent() {
    const [ingredients, setIngredients] = useState<Ingredient[]>([]);
    const [name, setName] = useState<string>()

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setName(event.target.value)
    };



    useEffect(() => {
        async function fetchData() {
            const response = await fetch("http://localhost:8080/api/ingredients/search?max=100&contains=true&name="+name);
            const data = await response.json();
            const mappedData = data.map(({ id, name }) => ({ id, name }));
            setIngredients(mappedData);
        }

        fetchData();
    }, [name]);

    return (
        <div>

            <TextInput label='Название' value={name} onChange={handleChange}></TextInput>
            <Text>Название {name ?? <div>{name}</div>}</Text>
            {ingredients.map((ingredient) => (
                <div key={ingredient.id}>{ingredient.id} {ingredient.name}</div>
            ))}
        </div>
    );
}
