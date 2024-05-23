import { Pencil, Trash } from "lucide-react";
import { MouseEventHandler } from "react";
import { Task } from "../../../models/task";
import { formatDate } from "../../../utils/utils";

interface TaskCardProps {
  task: Task;
  onClick: () => void;
  onEdit: () => void;
  onDelete: () => void;
}

export function TaskCard({ task, onClick, onEdit, onDelete }: TaskCardProps) {
  function handleAndStopPropagation(fn: () => void) {
    return ((event) => {
      event.stopPropagation();
      fn();
    }) as MouseEventHandler<HTMLButtonElement>;
  }

  return (
    <>
      <div
        onClick={onClick}
        className="group relative flex h-fit cursor-pointer flex-col gap-2 rounded-md border border-zinc-300 p-2 transition-all hover:bg-secondary-200/10 hover:ring-1 hover:ring-secondary-200 hover:ring-offset-2"
      >
        <header className="flex items-center justify-between gap-2">
          <h3
            title={task.title}
            className="line-clamp-1 w-full break-all font-semibold text-secondary-600"
          >
            {`#${task.id} - ${task.title}`}
          </h3>

          <div className="absolute right-1 top-1 hidden gap-1 rounded group-hover:flex">
            <button
              title="Editar"
              onClick={handleAndStopPropagation(onEdit)}
              className="group/button group rounded border border-zinc-300 bg-white p-2 shadow-md transition-all"
            >
              <Pencil
                size={16}
                className="cursor-pointer text-zinc-600 transition-all group-hover/button:scale-125 group-hover/button:stroke-[2]"
              />
            </button>

            <button
              title="Excluir"
              onClick={handleAndStopPropagation(onDelete)}
              className="group/button group rounded border border-zinc-300 bg-white p-2 shadow-md transition-all"
            >
              <Trash
                size={16}
                className="cursor-pointer text-red-600 transition-all group-hover/button:scale-125 group-hover/button:stroke-[2]"
              />
            </button>
          </div>
        </header>

        <p className="line-clamp-3 overflow-hidden text-sm text-zinc-500">
          {task.description}
        </p>

        <footer className="flex justify-end">
          <span className="text-xs text-zinc-500">
            {formatDate(task.createdAt)}
          </span>
        </footer>
      </div>
    </>
  );
}
