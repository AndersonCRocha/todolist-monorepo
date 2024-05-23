import { CreateTaskData, Task, UpdateTaskData } from "../models/task";
import { httpClient } from "./http-client";

class TaskService {
  async getAll() {
    const { data } = await httpClient.get<Task[]>("tasks");
    return data;
  }

  async create(task: CreateTaskData) {
    const { data } = await httpClient.post<Task>("tasks", task);
    return data;
  }

  async update({ id, ...task }: UpdateTaskData) {
    const { data } = await httpClient.put<Task>(`tasks/${id}`, task);
    return data;
  }

  async delete(id: number) {
    await httpClient.delete(`tasks/${id}`);
  }
}

export const taskService = new TaskService();
