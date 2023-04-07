import {CookingStep} from "@/types/receipt/CookingStep";
import styles from "./cooking-page.module.css"
import {CookingPageCard} from "@/components/receipt-page/cooking/cooking-card";

export interface CookingTableProps {
    steps: CookingStep[]
}

export function CookingTable(steps: CookingTableProps) {

    return (
        <div className={styles.cooking_table}>
            {steps.steps.map((st) => (
                <CookingPageCard text={st.text} num={st.id} title={st.title}/>
                )
            )}
        </div>
    )
}