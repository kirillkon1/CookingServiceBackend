import {Badge, HoverCard, Image, Modal, Rating} from "@mantine/core";
import {HeaderMenu} from "@/components/header/Header";
import styles from "./receipt-page.module.css"
import {Receipt} from "@/types/receipt/Receipt";
import {dateFormat, refactorSentence} from "@/features/utils";


interface ReceiptPageProps {
    receipt: Receipt
}


export function ReceiptPage({receipt}: ReceiptPageProps) {

    return (
        <>
            <HeaderMenu/>
            <div className={styles.page}>
                <div className={styles.container}>
                    <div className={styles.receipt_header}>
                        <Image
                            className={styles.image}
                            src={receipt.image_url}
                            withPlaceholder

                            height={350}
                            width={250}
                        />

                        <div className={styles.receipt_header_info}>

                            <div style={{float: "right", marginRight: "10px", opacity: "0.8"}}>
                                <div style={{display: "flex", gap: "10px", position: "relative", width: "auto"}}>
                                    {receipt.rating}
                                    <Rating defaultValue={receipt.rating}/></div>
                            </div>

                            <div style={{fontSize: "24px", fontWeight: "bold", color: "#000000"}}>
                                {refactorSentence(receipt.name)}
                            </div>

                            <div style={{marginTop: "10px", display: "flex", gap: "10px", flexWrap: "wrap"}}>
                                {receipt.categories.map((cat) => (
                                    <Badge radius="sm" color="gray" style={{fontSize: "8px", height: "12px"}}>
                                        {cat.name}
                                    </Badge>
                                ))}

                            </div>


                            <div>
                                Username: {receipt.user.username}
                            </div>

                            <div>
                                <HoverCard shadow="md" openDelay={500} closeDelay={400} style={{marginLeft: "16px"}}>
                                    <HoverCard.Target>
                                        <div>
                                            Дата добавления: {dateFormat(new Date(receipt.create_date * 1000))}
                                        </div>
                                    </HoverCard.Target>
                                    <HoverCard.Dropdown>
                                        <div style={{fontSize: "12px", width: "fit-content", padding: "-1px"}}>
                                            Последнее
                                            обновление: {dateFormat(new Date(receipt.last_modify_date * 1000))}
                                        </div>
                                    </HoverCard.Dropdown>
                                </HoverCard>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </>
    )
}