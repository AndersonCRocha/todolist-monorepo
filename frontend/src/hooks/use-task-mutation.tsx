import { useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "react-toastify";
import { CreateTaskData, UpdateTaskData } from "../models/task";
import { ApiError } from "../services/http-client";
import { taskService } from "../services/task-service";

interface TaskMutationProps {
  onSuccess: () => void;
}

export function useTaskMutation({ onSuccess }: TaskMutationProps) {
  const queryClient = useQueryClient();

  const { mutate: createTask, isPending: creationIsLoading } = useMutation({
    mutationFn: taskService.create,
    onSuccess: () => {
      onSuccess();
      toast.success("Tarefa criada com sucesso");
      queryClient.invalidateQueries({ queryKey: ["tasks"] });
    },
    onError: (error) => {
      if (error instanceof ApiError) {
        toast.error(error.message);
      }
    },
  });

  const { mutate: updateTask, isPending: updateIsLoading } = useMutation({
    mutationFn: taskService.update,
    onSuccess: () => {
      onSuccess();
      toast.success("Tarefa atualizada com sucesso");
      queryClient.invalidateQueries({ queryKey: ["tasks"] });
    },
    onError: (error) => {
      if (error instanceof ApiError) {
        toast.error(error.message);
      }
    },
  });

  const { mutate: deleteTask, isPending: deleteIsLoading } = useMutation({
    mutationFn: taskService.delete,
    onSuccess: () => {
      onSuccess();
      toast.success("Tarefa excluída com sucesso");
      queryClient.invalidateQueries({ queryKey: ["tasks"] });
    },
    onError: (error) => {
      if (error instanceof ApiError) {
        toast.error(error.message);
      }
    },
  });

  type SaveTask = CreateTaskData | UpdateTaskData;

  function isUpdateTaskData(data: SaveTask): data is UpdateTaskData {
    return (data as UpdateTaskData).id !== undefined;
  }

  function saveTask(data: SaveTask) {
    if (isUpdateTaskData(data)) {
      updateTask(data);
    } else {
      createTask(data);
    }
  }

  const isLoading = creationIsLoading || updateIsLoading || deleteIsLoading;

  return {
    saveTask,
    deleteTask,
    isLoading,
  };
}
