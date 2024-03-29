import '@/styles/globals.css'
import type {AppProps} from 'next/app'
import {Notifications} from "@mantine/notifications";
import * as React from "react";


export default function App({Component, pageProps}: AppProps) {
    return (
        <>
            <Component {...pageProps} />
            <Notifications/>
        </>
    )


}
