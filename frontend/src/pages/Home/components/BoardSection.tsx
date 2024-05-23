import { PlusIcon } from "lucide-react";
import { useState } from "react";
import { Button } from "../../../components/Button";
import { Task, TaskStatus } from "../../../models/task";
import { translateStatus } from "../../../utils/utils";
import { DeleteTaskModal } from "./DeleteTaskModal";
import { TaskCard } from "./TaskCard";
import { TaskDetailsModal } from "./TaskDetailsModal";

interface BoardSectionProps {
  status: TaskStatus;
  tasks: Task[];
  showNewTaskButton?: boolean;
}

export function BoardSection({
  status,
  tasks,
  showNewTaskButton,
}: BoardSectionProps) {
  const [isDetailsModalOpen, setIsDetailsModalOpen] = useState(false);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
  const [isContentEditable, setIsContentEditable] = useState(false);
  const [selectedTask, setSelectedTask] = useState<Task | null>(null);

  function handleEditOrCreateTask(task: Task | null) {
    setSelectedTask(task);
    setIsDetailsModalOpen(true);
    setIsContentEditable(true);
  }

  function handleDeleteTask(task: Task) {
    setSelectedTask(task);
    setIsDeleteModalOpen(true);
  }

  function handleClickCard(task: Task) {
    setSelectedTask(task);
    setIsDetailsModalOpen(true);
    setIsContentEditable(false);
  }

  return (
    <>
      <section className="relative flex h-full max-h-full min-h-80 w-80 min-w-80 flex-col overflow-hidden rounded-md bg-white">
        <h2 className="p-2 text-xl font-semibold text-zinc-600">
          {translateStatus(status)}
        </h2>

        <div className="grid gap-2 overflow-hidden overflow-y-auto p-2">
          {tasks.length === 0 ? (
            <div className="my-4 flex h-full items-center justify-center">
              <p className="text-zinc-500">Nenhuma tarefa</p>
            </div>
          ) : (
            tasks.map((task) => (
              <TaskCard
                key={task.id}
                task={task}
                onClick={() => handleClickCard(task)}
                onEdit={() => handleEditOrCreateTask(task)}
                onDelete={() => handleDeleteTask(task)}
              />
            ))
          )}
        </div>

        {showNewTaskButton && (
          <footer className="sticky bottom-0 left-0 right-0 mt-auto border-t border-zinc-200 bg-white p-2">
            <Button
              onClick={() => handleEditOrCreateTask(null)}
              leftIcon={<PlusIcon size={20} />}
              className="w-full gap-2"
            >
              Nova tarefa
            </Button>
          </footer>
        )}
      </section>

      <TaskDetailsModal
        task={selectedTask}
        visible={isDetailsModalOpen}
        editable={isContentEditable}
        onClose={() => {
          setIsDetailsModalOpen(false);
          setSelectedTask(null);
        }}
      />

      <DeleteTaskModal
        task={selectedTask}
        visible={isDeleteModalOpen && !!selectedTask}
        onClose={() => {
          setIsDeleteModalOpen(false);
          setSelectedTask(null);
        }}
      />
    </>
  );
}
