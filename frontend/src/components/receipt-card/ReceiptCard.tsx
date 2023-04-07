import {SimpleReceipt} from "@/types/receipt/Receipt";
import styles from "./receiptCard.module.css"
import {Badge, Card, CardSection, Image, Text} from '@mantine/core';


interface ReceiptCardProps {
    data: SimpleReceipt
}

export function ReceiptCard(prop: ReceiptCardProps) {

    return (


        // <div className={styles.container}>
        <Card w="250px" h="350px" shadow="lg" padding="lg" radius="md" withBorder>
            <CardSection component='a' href={"/receipt/" + prop.data.id
            }>
                <div>
                    <Image
                        src="https://cover.imglib.info/uploads/cover/gaoneng-lai-xi/cover/nEGmR0gmKE1y_250x350.jpg"
                        // src="https://images.squarespace-cdn.com/content/v1/5b4bd921b98a7828aa146701/1535586596824-7IXIN4O03YNHLJMKKOXI/Healthy-Food-Balance.jpg?format=2500w"
                        height="150px"
                        withPlaceholder
                        maw={250}
                        mx="auto"
                    />
                </div>
            </CardSection>

            <div className={styles.justify}>
                <Text color = "white" className={styles.truncate}>blank</Text>
                <Badge variant="gradient" className={styles.rating_icon}>
                    {prop.data.rating} ☆
                </Badge>
            </div>

            <div className={styles.categories_list}>
                {prop.data.categories.map((cat) => (

                    <div key={cat.id}>
                        <Badge color="green" className={styles.category_item} size="12px">
                            {cat.name}
                        </Badge>
                    </div>
                ))}
            </div>

            <div className={styles.text_name}>
                <Text lineClamp={3} size="18px" ff="sans-serif">{prop.data.name}</Text>
            </div>
        </Card>
        // </div>
    )
}