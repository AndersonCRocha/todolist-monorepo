import clsx, { ClassValue } from "clsx";
import dayjs from "dayjs";
import { twMerge } from "tailwind-merge";
import { TaskStatus } from "../models/task";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export function formatDate(date: Date) {
  const dayjsDate = dayjs(date);

  if (dayjsDate.isToday()) {
    return `Hoje, ${dayjsDate.format("HH:mm")}`;
  }

  if (dayjsDate.isYesterday()) {
    return `Ontem, ${dayjsDate.format("HH:mm")}`;
  }

  return dayjsDate.format("DD/MM/YYYY HH:mm");
}

export function translateStatus(status: TaskStatus) {
  const statusMapping = {
    PENDING: "Pendente",
    IN_PROGRESS: "Em andamento",
    DONE: "Concluído",
  } as const;

  return statusMapping[status];
}
