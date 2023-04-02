import { Html, Head, Main, NextScript } from 'next/document'
import {Notifications} from "@mantine/notifications";

export default function Document() {
  return (
    <Html lang="en">
      <Head />
      <body>
        <Main />
        <NextScript />
        <Notifications/>
      </body>
    </Html>
  )
}
