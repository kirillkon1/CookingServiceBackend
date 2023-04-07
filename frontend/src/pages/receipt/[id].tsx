import {useRouter} from 'next/router'
import {GetServerSideProps} from 'next'
import {Receipt} from "@/types/receipt/Receipt";
import {ReceiptPage} from "@/components/receipt-page/page";

interface ReceiptProps {
    receipt: Receipt
}

function Receipt({receipt}: ReceiptProps) {
    const router = useRouter()

    if (router.isFallback) {
        return <div>Loading...</div>
    }

    console.log(receipt)

    return (
        <>
            <ReceiptPage receipt={receipt}/>
        </>
    )
}

export default Receipt

export const getServerSideProps: GetServerSideProps = async (context) => {
    const {id} = context.query

    const res = await fetch(`http://localhost:8080/api/receipts/${id}`)
    const receipt = await res.json()

    return {
        props: {
            receipt,
        },
    }
}