import dayjs from "dayjs";
import isToday from "dayjs/plugin/isToday";
import isYesterday from "dayjs/plugin/isYesterday";
import { Providers } from "./components/Providers";
import { Home } from "./pages/Home";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

dayjs.extend(isToday);
dayjs.extend(isYesterday);
dayjs.locale("pt-br");

export function App() {
  return (
    <Providers>
      <Home />

      <ToastContainer theme="dark" position="bottom-right" />
    </Providers>
  );
}
