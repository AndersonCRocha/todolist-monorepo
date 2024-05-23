import { HTMLAttributes } from "react";

import { cn } from "../../utils/utils";
import styles from "./styles.module.css";

export function Spinner({ className }: HTMLAttributes<HTMLElement>) {
  return <div className={cn(styles.spinner, "text-primary-900", className)} />;
}
