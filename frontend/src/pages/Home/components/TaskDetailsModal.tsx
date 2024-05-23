import { zodResolver } from "@hookform/resolvers/zod";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { Modal, ModalProps } from "../../../components/Modal";
import { useTaskMutation } from "../../../hooks/use-task-mutation";
import { Task } from "../../../models/task";
import { cn, formatDate, translateStatus } from "../../../utils/utils";

type TaskModalProps = {
  task: Task | null;
  editable: boolean;
} & Pick<ModalProps, "visible" | "onClose">;

const taskFormSchema = z.object({
  title: z
    .string()
    .min(5, "Título deve ter pelo menos 5 caracteres")
    .max(60, "Máximo de 60 caracteres"),
  status: z.enum(["PENDING", "IN_PROGRESS", "DONE"]).default("PENDING"),
  description: z.string().transform((value) => value || undefined),
});

type TaskFormData = z.infer<typeof taskFormSchema>;

export function TaskDetailsModal({ task, editable, ...props }: TaskModalProps) {
  const [isEditable, setIsEditable] = useState(editable && !!task);
  const { isLoading, saveTask } = useTaskMutation({ onSuccess: props.onClose });
  const { register, reset, handleSubmit, formState } = useForm<TaskFormData>({
    resolver: zodResolver(taskFormSchema),
    defaultValues: { ...task },
  });

  function handleSaveTask(data: TaskFormData) {
    saveTask({ ...data, id: task?.id });
  }

  useEffect(() => {
    const newValue = task || {
      title: "",
      description: "",
    };
    reset(newValue);
  }, [isEditable, reset, task]);

  useEffect(() => {
    setIsEditable(editable);
  }, [editable]);

  function renderModalTitle() {
    return (
      <div className="grid">
        <label htmlFor="title" className="text-xs font-semibold text-zinc-500 ">
          Título:
        </label>
        <input
          id="title"
          autoFocus
          maxLength={60}
          disabled={!isEditable}
          placeholder="Título da tarefa"
          className={cn(
            "mt-1 w-[calc(100%-2rem)] rounded bg-transparent bg-zinc-100 px-2 outline-none",
            {
              "border border-zinc-400 bg-transparent focus:outline-none focus:ring-2 focus:ring-primary-100 focus:ring-offset-2":
                isEditable,
            },
          )}
          {...register("title")}
        />
        {formState.errors.title?.message && (
          <span className="mt-1 text-xs font-normal text-red-500">
            {formState.errors.title?.message}
          </span>
        )}
      </div>
    );
  }

  return (
    <Modal
      {...props}
      isLoading={isLoading}
      showConfirmButton={isEditable}
      confirmLabel="Salvar"
      bodyClassName="mt-2"
      showCancelButton={!!task}
      title={renderModalTitle()}
      cancelLabel={isEditable ? "Descartar alterações" : "Editar"}
      onCancel={() => setIsEditable((prevState) => !prevState)}
      onConfirm={handleSubmit(handleSaveTask)}
      footerElement={
        task && (
          <p className="mr-auto text-xs text-zinc-500">
            {formatDate(task.createdAt)}
          </p>
        )
      }
    >
      {task && (
        <div className="grid">
          <label
            htmlFor="status"
            className="text-xs font-semibold text-zinc-500 "
          >
            Status:
          </label>
          <select
            id="status"
            disabled={!isEditable}
            {...register("status")}
            className={cn("mt-1 rounded bg-zinc-100 px-2 py-1", {
              "border border-zinc-400 bg-transparent focus:outline-none focus:ring-2 focus:ring-primary-100 focus:ring-offset-2":
                isEditable,
            })}
          >
            <option value="PENDING">{translateStatus("PENDING")}</option>
            <option value="IN_PROGRESS">
              {translateStatus("IN_PROGRESS")}
            </option>
            <option value="DONE">{translateStatus("DONE")}</option>
          </select>
        </div>
      )}

      <div className="mt-2">
        <label
          htmlFor="description"
          className="text-xs font-semibold text-zinc-500 "
        >
          Descrição:
        </label>
        <textarea
          id="description"
          disabled={!isEditable}
          rows={3}
          placeholder="Descrição da tarefa"
          className={cn(
            "mt-1 w-full resize-none rounded border-zinc-400 bg-zinc-100 p-2",
            {
              "border bg-transparent text-zinc-800 focus:outline-none focus:ring-2 focus:ring-primary-100 focus:ring-offset-2":
                isEditable,
            },
          )}
          {...register("description")}
        />
      </div>
    </Modal>
  );
}
