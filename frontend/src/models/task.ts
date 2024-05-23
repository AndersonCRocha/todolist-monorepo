export type TaskStatus = "PENDING" | "IN_PROGRESS" | "DONE";

export interface Task {
  id: number;
  title: string;
  description: string;
  status: TaskStatus;
  createdAt: Date;
}

export interface CreateTaskData {
  title: string;
  description?: string;
}

export interface UpdateTaskData {
  id: number;
  title: string;
  description?: string;
  status: TaskStatus;
}
