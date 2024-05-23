import axios from "axios";

export const httpClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080",
});

export class ApiError extends Error {
  private _status: number;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  private _body: unknown;

  constructor(status: number, message: string, body: unknown) {
    super(message);
    this._status = status;
    this._body = body;
  }

  get body() {
    return this._body;
  }

  get status() {
    return this._status;
  }
}

httpClient.interceptors.response.use(
  (config) => config,
  (error) => {
    const apiError = new ApiError(
      error.response?.status || 500,
      error.response?.data?.message ||
        "Ocorreu um erro desconhecido, tente novamente mais tarde",
      error.response?.data,
    );
    return Promise.reject(apiError);
  },
);
