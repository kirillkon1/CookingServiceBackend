export function dateFormat(date: Date){
    const formattedDate = date.toLocaleDateString('en-GB', { day: '2-digit', month: '2-digit', year: 'numeric' })
    const formattedTime = date.toLocaleTimeString('en-US', { hour12: false, hour: '2-digit', minute: '2-digit', second: '2-digit' })
    return `${formattedDate} ${formattedTime}`
}

export function refactorSentence(text: string) {

    const tmp = text.charAt(0).toUpperCase() + text.slice(1)

    return tmp.split('. ').map((part, index) => {
        if (index === 0) {
            return part
        } else {
            return part.charAt(0).toUpperCase() + part.slice(1)
        }
    }).join('. ')
}

