import { useQuery } from "@tanstack/react-query";
import { useMemo } from "react";
import { Task, TaskStatus } from "../models/task";
import { taskService } from "../services/task-service";

export function useTaskBoard() {
  const {
    data: tasks,
    isLoading,
    isError,
  } = useQuery({
    queryKey: ["tasks"],
    queryFn: taskService.getAll,
    initialData: [],
  });

  const tasksByStatus = useMemo(
    () =>
      tasks?.reduce<Record<TaskStatus, Task[]>>(
        (acc, task) => {
          acc[task.status].push(task);
          return acc;
        },
        {
          PENDING: [],
          IN_PROGRESS: [],
          DONE: [],
        },
      ),
    [tasks],
  );

  return {
    isLoading,
    isError,
    tasksByStatus,
  };
}
