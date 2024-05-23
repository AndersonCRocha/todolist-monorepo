import { useTaskBoard } from "../../../hooks/use-task-board";
import { TaskStatus } from "../../../models/task";
import { BoardSection } from "./BoardSection";

export function TaskBoard() {
  const { tasksByStatus } = useTaskBoard();

  return (
    <div className="flex max-w-full items-center gap-4 overflow-x-auto">
      {Object.entries(tasksByStatus).map(([status, tasks]) => (
        <BoardSection
          key={status}
          status={status as TaskStatus}
          tasks={tasks}
          showNewTaskButton={status === "PENDING"}
        />
      ))}
    </div>
  );
}
