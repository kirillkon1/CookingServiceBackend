import styles from "./cooking-page.module.css"

export interface StepProps {
    num: number;
    title: number
    text: string
}

export function CookingPageCard(props: StepProps) {


    return (

        <div className={styles.card}>

            <div className={styles.flex}>
                <div className={styles.step_number_icon}>
                    <div className={styles.step_number}>{props.num}</div>
                </div>

                <div className={styles.title_text}>{props.title}</div>

            </div>

            <div className={styles.card_text}>{props.text}</div>
        </div>
    )

}