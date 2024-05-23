import { TaskBoard } from "./components/TaskBoard";

export function Home() {
  return (
    <main className="flex h-screen w-screen flex-col items-center bg-zinc-200 p-4 pt-24">
      <h1 className="mb-4 text-2xl font-semibold text-zinc-800">
        Quadro de tarefas
      </h1>

      <TaskBoard />
    </main>
  );
}
