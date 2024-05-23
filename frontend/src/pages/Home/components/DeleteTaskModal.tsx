import { Modal, ModalProps } from "../../../components/Modal";
import { useTaskMutation } from "../../../hooks/use-task-mutation";
import { Task } from "../../../models/task";

type DeleteTaskModalProps = {
  task: Task | null;
} & Pick<ModalProps, "onClose" | "visible">;

export function DeleteTaskModal({ task, ...props }: DeleteTaskModalProps) {
  const { isLoading, deleteTask } = useTaskMutation({
    onSuccess: props.onClose,
  });

  return (
    <Modal
      title={`Excluir tarefa: #${task?.id}`}
      danger
      onConfirm={() => deleteTask(task!.id)}
      isLoading={isLoading}
      showCancelButton={false}
      confirmLabel="Excluir"
      {...props}
    >
      <p className="mb-2 text-zinc-700">
        Tem certeza que deseja excluir a tarefa: '{task?.title}'?
      </p>
    </Modal>
  );
}
